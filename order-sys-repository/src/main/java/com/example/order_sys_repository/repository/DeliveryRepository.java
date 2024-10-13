package com.example.order_sys_repository.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.order_sys_repository.model.Delivery;

@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {

}
