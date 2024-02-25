package com.example.rent2gojavaproject.services.dtos.responses.customerResponse;

import com.example.rent2gojavaproject.models.City;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerListResponse {

    private int id;
    private int userId;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private int driverLicenceAge;
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
