package com.example.rent2gojavaproject.services.dtos.responses.customerResponse;

import com.example.rent2gojavaproject.models.City;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerListResponse {

    private int id;
    private GetUserResponse user;

}
