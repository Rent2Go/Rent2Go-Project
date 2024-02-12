package com.example.rent2gojavaproject.core.utilities.constants;

public enum CloudinaryConstants {
    USE_FILENAME("use_filename"),
    UNIQUE_FILENAME("unique_filename"),
    OVERWRITE("overwrite"),
    PUBLIC_ID("public_id"),
    URL("url"),
    BASE_PUBLIC_ID_CAR("rent2go/carImages/"),
    BASE_PUBLIC_ID_USER("rent2go/userImages/"),
    BASE_PUBLIC_ID_LOGO("rent2go/");

    private final String value;

    CloudinaryConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
