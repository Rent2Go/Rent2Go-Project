package com.example.rent2gojavaproject.services.dtos.responses.customerResponse;

import com.example.rent2gojavaproject.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomer {
    private int id;
    private int userId;
    private String userImageUrl;
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPhoneNumber;
    private String userIdCardNumber;
    private String userCityName;
    private String userDistrictName;
    private String userAddress;
}
