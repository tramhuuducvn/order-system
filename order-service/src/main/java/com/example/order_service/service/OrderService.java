package com.example.order_service.service;

import static com.example.order_sys_repository.utils.MessageJsonUtil.json;

import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.example.order_sys_repository.constant.MessageAction;
import com.example.order_sys_repository.model.Order;
import com.example.order_sys_repository.provider.ServiceBusClientProvider;
import com.example.order_sys_repository.repository.OrderRepository;

@Service
public class OrderService {
    private static final String inventoryQueue = "inventory_queue";
    private static final String sbNamespace = "ordersyssvcbusnamespace";
    private OrderRepository repository;
    private ServiceBusClientProvider sbClientProvider;

    public OrderService(OrderRepository repository, ServiceBusClientProvider provider) {
        this.repository = repository;
        this.sbClientProvider = provider;
    }

    public void createAnOrder(Order order) {
        System.out.println("DUKE = " + order.toString());
        Order result = repository.insert(order);
        ServiceBusSenderClient senderClient = sbClientProvider.getSender(sbNamespace, inventoryQueue);
        senderClient.sendMessage(new ServiceBusMessage(json(result, MessageAction.CHECK_INVENTORY)));
    }

    public Order getOderById(String id) {
        return repository.findById(id).get();
    }
}
