package com.example.rent2gojavaproject.core.utilities.constants;

public enum UrlPathConstants {
    BACKEND_URL("https://api.rentogo.com.tr/"),
    CLIENT_URL("https://rentogo.com.tr/"),
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
