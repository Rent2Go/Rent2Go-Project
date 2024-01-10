package com.example.rent2gojavaproject.services.dtos.responses.districtResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDistrictListResponse {
    private Integer id;
    private String districtName;
}
