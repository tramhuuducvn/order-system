package com.example.order_service.service;

import org.springframework.stereotype.Service;

import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository repository;

    public void createAnOrder(Order order) {
        System.out.println("DUKE = " + order.toString());
        repository.insert(order);
    }
}
