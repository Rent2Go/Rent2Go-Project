package com.example.rent2gojavaproject.services.dtos.requests.userRequest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserLocationRequest {
    @Min(value = 10, message = "Address information must contain at least 10 characters.")
    private String address;
    @Positive(message ="City id must be positive number")
    private int cityId;
    @Positive(message ="District id must be positive number")
    private int districtId;
}
