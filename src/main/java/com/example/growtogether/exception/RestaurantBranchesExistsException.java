package com.example.growtogether.exception;

public class RestaurantBranchesExistsException extends RuntimeException {
    public RestaurantBranchesExistsException(String message) {
        super(message);
    }
}
