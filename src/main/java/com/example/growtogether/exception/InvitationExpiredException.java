package com.example.growtogether.exception;

public class InvitationExpiredException extends RuntimeException {
    public InvitationExpiredException(String message) {
        super(message);
    }
}
