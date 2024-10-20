package com.example.inventory_service.prossesor;

import static com.example.order_sys_repository.utils.MessageJsonUtil.json;

import org.springframework.stereotype.Component;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.example.order_sys_repository.constant.DeliveryStatus;
import com.example.order_sys_repository.constant.MessageAction;
import com.example.order_sys_repository.model.MessageContent;
import com.example.order_sys_repository.model.Order;
import com.example.order_sys_repository.provider.ServiceBusClientProvider;
import com.example.order_sys_repository.repository.OrderRepository;
import com.example.order_sys_repository.utils.MessageJsonUtil;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InventoryMessageProcessor {

    private static final String inventoryQueue = "delivery_queue";
    private static final String sbNamespace = "ordersyssvcbusnamespace";
    private OrderRepository repository;
    private ServiceBusClientProvider sbClientProvider;

    public void process(String message) {
        MessageContent content = MessageJsonUtil.object(message);
        switch (content.getAction()) {
            case CHECK_INVENTORY:
                try {
                    Thread.sleep(5000);
                    Order newOrder = content.getOrder();
                    newOrder.setStatus(DeliveryStatus.PREPARE_GOODS);
                    repository.save(newOrder);
                    ServiceBusSenderClient senderClient = sbClientProvider.getSender(sbNamespace, inventoryQueue);
                    senderClient.sendMessage(new ServiceBusMessage(json(newOrder, MessageAction.LETS_DELIVEY)));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }
}
