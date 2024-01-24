package com.example.rent2gojavaproject.core.exceptions;

public class UserNotEnabledException extends RuntimeException   {
    public UserNotEnabledException(String message) {
        super(message);
    }
}
