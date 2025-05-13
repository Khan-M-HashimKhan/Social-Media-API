package com.example.exception;

public class InvalidAccountException extends RuntimeException {
    public InvalidAccountException() {
        super("Invalid account data.");
    }
}
