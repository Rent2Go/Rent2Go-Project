package com.example.rent2gojavaproject.services.dtos.responses.carResponse;

import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorResponse;
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

    private GetModelResponse modelResponse;

    private GetColorResponse colorResponse ;
}
