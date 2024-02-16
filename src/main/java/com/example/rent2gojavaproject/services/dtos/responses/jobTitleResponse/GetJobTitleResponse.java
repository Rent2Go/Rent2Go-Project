package com.example.rent2gojavaproject.services.dtos.responses.jobTitleResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetJobTitleResponse {

        private int id;
        private String name;
        private String departmentName;


}
