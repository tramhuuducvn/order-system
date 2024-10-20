package com.example.delivery_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = { "com.example.order_sys_repository.repository" })
@EntityScan(basePackages = { "com.example.order_sys_repository.repository.entity" })
@ComponentScan(basePackages = { "com.example.delivery_service", "com.example.order_sys_repository.provider" })
public class DeliveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryServiceApplication.class, args);
	}

}
