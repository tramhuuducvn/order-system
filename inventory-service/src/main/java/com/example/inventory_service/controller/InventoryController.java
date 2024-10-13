package com.example.inventory_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory_service.service.InventoryService;
import com.example.order_sys_repository.model.Product;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private InventoryService service;

    @GetMapping
    public List<Product> getProduct() {
        return service.getProduct();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return service.getProduct(id);
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        service.createProduct(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        service.updateProduct(product);
    }

}
