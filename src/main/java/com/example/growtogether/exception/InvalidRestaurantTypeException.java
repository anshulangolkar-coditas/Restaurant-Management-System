package com.example.growtogether.exception;

public class InvalidRestaurantTypeException extends RuntimeException {
    public InvalidRestaurantTypeException(String message) {
        super(message);
    }
}
