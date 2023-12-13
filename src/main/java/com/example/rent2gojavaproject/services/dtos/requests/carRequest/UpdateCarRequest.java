package com.example.rent2gojavaproject.services.dtos.requests.carRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    private int id;
    private int kilometer;

    private int year;

    private double dailyPrice;

    private String plate;

    private int modelId;

    private int colorId;
}
