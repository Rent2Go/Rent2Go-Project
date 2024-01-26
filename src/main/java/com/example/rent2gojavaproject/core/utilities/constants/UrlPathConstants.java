package com.example.rent2gojavaproject.core.utilities.constants;

public enum UrlPathConstants {
    BACKEND_URL("https://api.rentogo.com.tr"),
    CONFIRMATION_URL("/api/confirm?token="),
    PASSWORD_CHANGE_PATH("passwordchange?token=");

    private final String path;

    UrlPathConstants(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
