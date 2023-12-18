package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.employeeRequest.AddEmployeeRequest;
import com.example.rent2gojavaproject.services.dtos.requests.employeeRequest.UpdateEmployeeRequest;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeResponse;

import java.util.List;

public interface EmployeeService {

    DataResult<List<GetEmployeeListResponse>> getAllEmployees();

    DataResult<GetEmployeeResponse> getById(int id);

    Result addEmployee(AddEmployeeRequest addEmployeeRequest);

    Result updateEmployee(UpdateEmployeeRequest updateEmployeeRequest);

    Result deleteEmployee(int id);

    boolean existsById(int id);

}
