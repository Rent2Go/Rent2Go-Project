package com.example.rent2gojavaproject.core.utilities.constants;

public enum CloudinaryConstants {
    USE_FILENAME("use_filename"),
    UNIQUE_FILENAME("unique_filename"),
    OVERWRITE("overwrite"),
    PUBLIC_ID("public_id"),
    URL("url"),
    BASE_PUBLIC_ID("rent2go/carImages/");

    private final String value;

    CloudinaryConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
