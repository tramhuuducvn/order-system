package com.example.delivery_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.delivery_service.service.DeliveryService;
import com.example.order_sys_repository.constant.DeliveryStatus;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/delivery")
@AllArgsConstructor
public class DeliveryController {

    private DeliveryService service;

    @PostMapping
    public void create(@RequestParam String orderId, @RequestParam DeliveryStatus status) {
        System.out.println("DUKE = " + orderId + " - " + status);
        service.addDeliveryStatus(orderId, status);
    }
}
