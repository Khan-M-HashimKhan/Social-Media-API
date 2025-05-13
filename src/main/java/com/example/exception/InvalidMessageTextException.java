package com.example.exception;

public class InvalidMessageTextException extends RuntimeException {
    public InvalidMessageTextException(){
        super("Invalid message text");
    }
    
}
