package com.example.rent2gojavaproject.core.utilities.constants;

public enum UrlPathConstants {
    BACKEND_URL("http://localhost:8080/"),
    CLIENT_URL("http://localhost:3000/"),
    CONFIRMATION_URL("api/confirm?token="),
    PASSWORD_CHANGE_PATH("sign-in/change-password?token=");

    private final String path;

    UrlPathConstants(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
