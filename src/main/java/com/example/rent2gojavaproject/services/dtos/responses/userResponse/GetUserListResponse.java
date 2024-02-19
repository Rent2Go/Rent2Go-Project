package com.example.rent2gojavaproject.services.dtos.responses.userResponse;

import com.example.rent2gojavaproject.models.City;
import com.example.rent2gojavaproject.models.Customer;
import com.example.rent2gojavaproject.models.District;
import com.example.rent2gojavaproject.services.dtos.responses.cityResponse.GetCityListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.customerResponse.GetCustomerResponse;
import com.example.rent2gojavaproject.services.dtos.responses.districtResponse.GetDistrictListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserListResponse {

    private int id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private String password;

    private LocalDate birthDate;

    private String idCardNumber;

    private String address;

    private GetCityListResponse city;

    private GetDistrictListResponse district;

    private String role;

    private boolean isActive;

    private String imageUrl;

    private GetCustomerResponse customer;

    private GetEmployeeResponse employee;


}
