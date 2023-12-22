package com.example.rent2gojavaproject.models;


public enum FuelType {
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid");

    private final String typeName;

    FuelType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
