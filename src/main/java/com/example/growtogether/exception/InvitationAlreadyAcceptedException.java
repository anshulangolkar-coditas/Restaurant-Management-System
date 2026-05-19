package com.example.growtogether.exception;

public class InvitationAlreadyAcceptedException extends RuntimeException {
    public InvitationAlreadyAcceptedException(String message) {
        super(message);
    }
}
