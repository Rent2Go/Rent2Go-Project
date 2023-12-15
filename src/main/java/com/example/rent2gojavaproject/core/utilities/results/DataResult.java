package com.example.rent2gojavaproject.core.utilities.results;

public class DataResult<T> extends Result {


    private T data;

    public DataResult() {
    }

    public DataResult(T data, String message, boolean result) {
        super(message, result);
        this.data = data;
    }

    public DataResult(T data, boolean result) {
        super(result);
        this.data = data;
    }

    public DataResult(String message, boolean result) {
        super(message, result);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
