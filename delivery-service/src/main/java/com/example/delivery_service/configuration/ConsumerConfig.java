package com.example.delivery_service.configuration;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.azure.spring.messaging.checkpoint.Checkpointer;
import com.example.delivery_service.prossesor.DeliveryMessageProcessor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.azure.spring.messaging.AzureHeaders.CHECKPOINTER;

@Configuration
@Slf4j
@AllArgsConstructor
public class ConsumerConfig {

  private DeliveryMessageProcessor processor;

  @Bean
  public Consumer<Message<String>> consume() {
    return message -> {
      Checkpointer checkpointer = (Checkpointer) message.getHeaders().get(CHECKPOINTER);

      log.info("New message received: '{}'", message.getPayload());
      checkpointer.success()
          .doOnSuccess(s -> {
            log.info("Message '{}' successfully checkpointed", message.getPayload());
            processor.process(message.getPayload());
          })
          .doOnError(e -> log.error("Error found", e))
          .block();
    };
  }
}
