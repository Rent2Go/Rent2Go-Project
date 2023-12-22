package com.example.rent2gojavaproject.models;


public enum BodyType {
    SEDAN("Sedan"),
    HATCHBACK("Hatchback"),
    SUV("SUV"),
    COUPE("Coupe");

    private final String typeName;

    BodyType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
