package com.example.rent2gojavaproject.core.utilities.alerts;


public enum Message {

    ADD("The addition operation was successful!"),
    UPDATE("The update operation was successful!"),
    DELETE("The deletion operation was successful!"),
    GET_ALL("All records were listed!"),
    GET("The record was found!");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
