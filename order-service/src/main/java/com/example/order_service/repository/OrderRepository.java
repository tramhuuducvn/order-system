package com.example.order_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.order_service.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}