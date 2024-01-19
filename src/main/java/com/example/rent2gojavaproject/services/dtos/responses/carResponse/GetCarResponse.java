package com.example.rent2gojavaproject.services.dtos.responses.carResponse;

import com.example.rent2gojavaproject.models.*;
import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandResponse;
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarResponse {
    private int id;

    private int kilometer;

    private int year;

    private double dailyPrice;

    private String plate;

    private String imageUrl;

    private GetModelResponse model;

    private String colorName;

    private BodyType bodyType;

    private FuelType fuelType;

    private GearType gearType;

    private String cylinderCount;

    private String enginePower;
}
