package com.example.growtogether.exception;

public class InvalidFoodTypeException extends RuntimeException {
    public InvalidFoodTypeException(String message) {
        super(message);
    }
}
