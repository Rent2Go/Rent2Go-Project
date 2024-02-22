package com.example.rent2gojavaproject.services.dtos.responses.modelResponse;

import com.example.rent2gojavaproject.services.dtos.responses.brandResponse.GetBrandResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelListResponse {

    private int id;

    private String name;

    private GetBrandResponse brand;
}
