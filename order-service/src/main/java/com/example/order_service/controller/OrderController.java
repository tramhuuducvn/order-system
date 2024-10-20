package com.example.order_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.service.OrderService;
import com.example.order_sys_repository.model.Order;
import com.example.order_sys_repository.model.Product;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    // private
    private OrderService orderService;

    @PostMapping
    public void createAnOrder(@RequestBody Order order) {
        orderService.createAnOrder(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOder(@PathVariable String id) {
        return ResponseEntity.ok().body(orderService.getOderById(id));
    }
}
