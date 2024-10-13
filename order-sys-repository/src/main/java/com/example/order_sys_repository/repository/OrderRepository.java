package com.example.order_sys_repository.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.order_sys_repository.model.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}