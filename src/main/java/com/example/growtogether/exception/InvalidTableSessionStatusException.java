package com.example.growtogether.exception;

public class InvalidTableSessionStatusException extends RuntimeException {
    public InvalidTableSessionStatusException(String message) {
        super(message);
    }
}
