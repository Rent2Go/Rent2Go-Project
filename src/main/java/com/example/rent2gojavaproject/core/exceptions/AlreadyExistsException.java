package com.example.rent2gojavaproject.core.exceptions;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }
}
