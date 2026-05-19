package com.example.growtogether.exception;

public class EmailSendingFailureException extends RuntimeException {
    public EmailSendingFailureException(String message) {
        super(message);
    }
}
