package com.example.rent2gojavaproject.services.dtos.responses.carResponse;

import com.example.rent2gojavaproject.models.BodyType;
import com.example.rent2gojavaproject.models.FuelType;
import com.example.rent2gojavaproject.models.GearType;
<<<<<<< HEAD
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelListResponse;
=======
>>>>>>> b46abc4e1cf81bc61f85f18aef9fdb4ed59a60a6
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarListResponse {
    private int id;

    private int kilometer;

    private int year;

    private double dailyPrice;

    private String plate;

    private String imageUrl;

    private GetModelListResponse model;

    private String colorName;

    private BodyType bodyType;

    private FuelType fuelType;

    private GearType gearType;

    private String cylinderCount;

    private String enginePower;
}
