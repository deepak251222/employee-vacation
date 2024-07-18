package com.vacation.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException() {

    }
}
