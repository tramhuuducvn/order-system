package com.example.order_sys_repository.provider;

import static com.example.order_sys_repository.constant.ServiceBusConstant.DELIMITER;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.azure.core.amqp.AmqpRetryOptions;
import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusReceiverClient;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.azure.messaging.servicebus.administration.ServiceBusAdministrationClient;
import com.azure.messaging.servicebus.administration.ServiceBusAdministrationClientBuilder;
import com.azure.messaging.servicebus.models.SubQueue;

import lombok.Getter;

@Getter
@Component
public class ServiceBusClientProvider {

    private final int cacheTimeToLive;
    private final String connectionString;
    private final Map<String, ServiceBusSenderClient> serviceBusSenders;
    private final Map<String, ServiceBusReceiverClient> serviceBusDLQReceivers;

    public ServiceBusClientProvider(
            @Value("${spring.cloud.azure.servicebus.connection-string}") String connectionString) {
        this.connectionString = connectionString;
        this.cacheTimeToLive = 30000;
        this.serviceBusSenders = new ConcurrentHashMap<>();
        this.serviceBusDLQReceivers = new ConcurrentHashMap<>();
    }

    public ServiceBusSenderClient getSender(String sbNamespace,
            String sbQueueName) {
        String key = String.join(DELIMITER, sbNamespace, sbQueueName);
        ServiceBusSenderClient senderClient = serviceBusSenders.get(key);

        if (Objects.isNull(senderClient)) {
            senderClient = new ServiceBusClientBuilder()
                    .connectionString(connectionString)
                    .sender()
                    .queueName(sbQueueName)
                    .buildClient();

            serviceBusSenders.put(key, senderClient);
        }

        return senderClient;
    }

    public ServiceBusReceiverClient getReceiver(String sbNamespace,
            String queueName, SubQueue subQueue) {
        String key = String.join(DELIMITER, sbNamespace, queueName);
        ServiceBusReceiverClient receiverClient = serviceBusDLQReceivers.get(key);
        AmqpRetryOptions amqpRetryOptions = new AmqpRetryOptions();
        amqpRetryOptions.setMaxRetries(1);
        if (Objects.isNull(receiverClient)) {
            receiverClient = new ServiceBusClientBuilder().connectionString(connectionString)
                    .receiver()
                    .maxAutoLockRenewDuration(Duration.ofMinutes(cacheTimeToLive))
                    .queueName(queueName)
                    .subQueue(subQueue)
                    .disableAutoComplete()
                    .buildClient();

            serviceBusDLQReceivers.put(key, receiverClient);
        }
        return receiverClient;
    }

    public ServiceBusAdministrationClient getAdminClient(String sbNamespace) {
        return new ServiceBusAdministrationClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    public void closeReceiver(final String sbNamespace, final String queueName) {
        String key = String.join(DELIMITER, sbNamespace, queueName);
        Optional.ofNullable(serviceBusDLQReceivers.get(key)).ifPresent(ServiceBusReceiverClient::close);
        serviceBusDLQReceivers.remove(key);
    }

    public void closeSender(final String sbNamespace, final String queueName) {
        String key = String.join(DELIMITER, sbNamespace, queueName);
        Optional.ofNullable(serviceBusSenders.get(key)).ifPresent(ServiceBusSenderClient::close);
        serviceBusSenders.remove(key);
    }
}
