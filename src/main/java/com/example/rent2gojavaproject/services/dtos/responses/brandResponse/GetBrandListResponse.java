package com.example.rent2gojavaproject.services.dtos.responses.brandResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBrandListResponse {

    private int id;

    private String name;
}
