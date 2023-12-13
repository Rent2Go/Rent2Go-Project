package com.example.rent2gojavaproject.services.dtos.responses.carResponse;

import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarResponse {

    private String name;
    private GetModelResponse model;
    private GetCarResponse color;
}
