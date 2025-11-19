package com.example.CricketManagement.exception;


// 400 Bad Request
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}