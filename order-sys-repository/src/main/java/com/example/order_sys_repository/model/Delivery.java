package com.example.order_sys_repository.model;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.order_sys_repository.constant.DeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("delivery")
public class Delivery {

    @Id
    private String id;
    private String orderId;
    private DeliveryStatus status;

    @CreatedDate
    private LocalDate createdDate;
}
