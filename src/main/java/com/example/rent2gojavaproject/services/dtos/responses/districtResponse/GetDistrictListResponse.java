package com.example.rent2gojavaproject.services.dtos.responses.districtResponse;

import com.example.rent2gojavaproject.services.dtos.responses.cityResponse.GetCityListResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDistrictListResponse {

    private Integer id;

    private String districtName;

    private GetCityListResponse city;
}
