package com.example.delivery_service.prossesor;

import org.springframework.stereotype.Component;

import com.example.order_sys_repository.constant.DeliveryStatus;
import com.example.order_sys_repository.model.MessageContent;
import com.example.order_sys_repository.model.Order;
import com.example.order_sys_repository.repository.OrderRepository;
import com.example.order_sys_repository.utils.MessageJsonUtil;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DeliveryMessageProcessor {

    private OrderRepository repository;

    public void process(String message) {
        MessageContent content = MessageJsonUtil.object(message);
        switch (content.getAction()) {
            case LETS_DELIVEY:
                try {
                    Thread.sleep(5000);
                    Order newOrder = content.getOrder();
                    newOrder.setStatus(DeliveryStatus.DELIVERING);
                    repository.save(newOrder);
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
