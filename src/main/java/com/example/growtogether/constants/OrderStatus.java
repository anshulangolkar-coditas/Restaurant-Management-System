package com.example.growtogether.constants;

import com.example.growtogether.exception.InvalidOrderStatusException;

public enum OrderStatus{

    PLACED,
    COOKING,
    READY,
    CANCELLED;

    public static OrderStatus toValue(String status){

        for(OrderStatus orderStatus : OrderStatus.values()){
            if(orderStatus.name().equalsIgnoreCase(status)){
                return orderStatus;
            }
        }
        throw new InvalidOrderStatusException(ExceptionMessages.INVALID_ORDER_STATUS);
    }

}
