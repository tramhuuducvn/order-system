package com.example.inventory_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.order_sys_repository.model.Product;
import com.example.order_sys_repository.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private ProductRepository repository;

    public List<Product> getProduct() {
        return repository.findAll();
    }

    public Product getProduct(String id) {
        return repository.findById(id).orElse(null);
    }

    public void createProduct(Product product) {
        repository.insert(product);
    }

    public void updateProduct(Product product) {
        repository.save(product);
    }
}
