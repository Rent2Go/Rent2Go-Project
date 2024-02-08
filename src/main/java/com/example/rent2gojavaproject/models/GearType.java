package com.example.rent2gojavaproject.models;


public enum GearType {
        MANUAL("Manual"),
        AUTOMATIC("Automatic");

    private final String typeName;

    GearType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

}
