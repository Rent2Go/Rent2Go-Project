package com.example.rent2gojavaproject.core.utilities.results;

public abstract class Result {


    private String message;


    private boolean result;


    public Result() {
    }

    public Result(String message, boolean result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
