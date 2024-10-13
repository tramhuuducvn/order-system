package com.example.order_sys_repository.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.order_sys_repository.constant.DeliveryStatus;
import com.example.order_sys_repository.constant.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("order")
public class Order {

    @Id
    private String id;
    private String userId;
    private List<Item> items;
    private PaymentMethod paymentMethod;
    private DeliveryStatus status;

    @Override
    public String toString() {
        return "Order [id=" + id + ", userId=" + userId + ", items=" + items + ", paymentMethod=" + paymentMethod + "]";
    }
}
