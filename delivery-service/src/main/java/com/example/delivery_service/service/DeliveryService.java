package com.example.delivery_service.service;

import org.springframework.stereotype.Service;

import com.example.order_sys_repository.constant.DeliveryStatus;
import com.example.order_sys_repository.model.Delivery;
import com.example.order_sys_repository.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeliveryService {
    private DeliveryRepository repository;

    public void addDeliveryStatus(String orderId, DeliveryStatus status) {
        Delivery delivery = Delivery.builder()
                .orderId(orderId)
                .status(status)
                .build();
        repository.insert(delivery);
    }
}
