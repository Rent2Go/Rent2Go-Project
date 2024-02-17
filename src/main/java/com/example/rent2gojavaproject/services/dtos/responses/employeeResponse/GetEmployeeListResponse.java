package com.example.rent2gojavaproject.services.dtos.responses.employeeResponse;

import com.example.rent2gojavaproject.models.JobTitle;
import com.example.rent2gojavaproject.models.User;
import com.example.rent2gojavaproject.services.dtos.responses.jobTitleResponse.GetJobTitleResponse;
import com.example.rent2gojavaproject.services.dtos.responses.rentalResponse.GetRentalListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.userResponse.GetUserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeListResponse {
    private int id;

    private double salary;

    private GetUserResponse user;

    private GetJobTitleResponse jobTitle;

}
