package com.example.rent2gojavaproject.services.dtos.responses.departmentResponse;

import com.example.rent2gojavaproject.services.dtos.responses.jobTitleResponse.GetJobTitleResponse;
import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDepartmentResponse {

    private int id;

    private String name;

    private GetJobTitleResponse jobTitle;
}
