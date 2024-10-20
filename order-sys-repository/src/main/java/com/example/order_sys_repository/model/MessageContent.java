package com.example.order_sys_repository.model;

import com.example.order_sys_repository.constant.MessageAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageContent {

    private Order order;
    private MessageAction action;
}
