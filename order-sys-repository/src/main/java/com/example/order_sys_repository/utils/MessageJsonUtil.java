package com.example.order_sys_repository.utils;

import com.example.order_sys_repository.constant.MessageAction;
import com.example.order_sys_repository.model.MessageContent;
import com.example.order_sys_repository.model.Order;
import com.google.gson.Gson;

public class MessageJsonUtil {
    public static String json(MessageContent message) {
        Gson gson = new Gson();
        return gson.toJson(message);
    }

    public static String json(Order order, MessageAction action) {
        Gson gson = new Gson();
        MessageContent content = MessageContent.builder().action(action).order(order).build();
        return gson.toJson(content);
    }

    public static MessageContent object(String message) {
        Gson gson = new Gson();
        return gson.fromJson(message, MessageContent.class);
    }
}
