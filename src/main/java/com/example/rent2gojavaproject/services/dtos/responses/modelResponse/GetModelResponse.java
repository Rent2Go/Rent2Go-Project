package com.example.rent2gojavaproject.services.dtos.responses.modelResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelResponse {

    private int id;

    private String name;

    private String brandName;
}
