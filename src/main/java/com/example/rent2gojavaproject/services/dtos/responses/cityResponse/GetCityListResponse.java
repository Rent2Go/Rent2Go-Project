package com.example.rent2gojavaproject.services.dtos.responses.cityResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCityListResponse {

    private Integer id;
    private String cityName;
}
