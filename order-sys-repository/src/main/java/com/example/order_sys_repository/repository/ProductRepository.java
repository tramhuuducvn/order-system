package com.example.order_sys_repository.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.order_sys_repository.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
