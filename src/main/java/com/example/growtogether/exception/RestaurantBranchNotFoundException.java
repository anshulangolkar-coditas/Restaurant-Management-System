package com.example.growtogether.exception;

public class RestaurantBranchNotFoundException extends RuntimeException {
    public RestaurantBranchNotFoundException(String message) {
        super(message);
    }
}
