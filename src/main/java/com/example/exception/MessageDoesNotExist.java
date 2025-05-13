package com.example.exception;

public class MessageDoesNotExist extends RuntimeException {
    public MessageDoesNotExist(){
        super ("Message Does Not Exist");
    }
    
}
