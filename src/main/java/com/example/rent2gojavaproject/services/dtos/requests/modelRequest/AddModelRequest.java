package com.example.rent2gojavaproject.services.dtos.requests.modelRequest;

import com.example.rent2gojavaproject.services.dtos.requests.brandRequest.AddBrandRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddModelRequest {

    private String name;
    private int brandId;
}
