package com.example.growtogether.exception;

public class BranchTableNotFoundException extends RuntimeException {
    public BranchTableNotFoundException(String message) {
        super(message);
    }
}
