package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Employee;
import com.example.rent2gojavaproject.repositories.EmployeeRepository;
import com.example.rent2gojavaproject.services.abstracts.EmployeeService;
import com.example.rent2gojavaproject.services.dtos.requests.employeeRequest.AddEmployeeRequest;
import com.example.rent2gojavaproject.services.dtos.requests.employeeRequest.UpdateEmployeeRequest;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapperService mapperService;

    @Override
    public DataResult<List<GetEmployeeListResponse>> getAllEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();
        List<GetEmployeeListResponse> responses = employees.stream().map(employee -> this.mapperService.forResponse().map(employee, GetEmployeeListResponse.class)).collect(Collectors.toList());


        return new SuccessDataResult<List<GetEmployeeListResponse>>(responses, Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetEmployeeResponse> getById(int id) {
        Employee employee = this.employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find employee id"));

        GetEmployeeResponse response = this.mapperService.forResponse().map(employee, GetEmployeeResponse.class);


        return new SuccessDataResult<GetEmployeeResponse>(response, Message.GET.getMessage());
    }

    @Override
    public Result addEmployee(AddEmployeeRequest addEmployeeRequest) {
        Employee employee = this.mapperService.forRequest().map(addEmployeeRequest, Employee.class);

        this.employeeRepository.save(employee);
        return new SuccessResult(Message.ADD.getMessage());
    }

    @Override
    public Result updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) {
        this.employeeRepository.findById(updateEmployeeRequest.getId()).orElseThrow(() -> new RuntimeException("Couldn't find employee id"));

        Employee employee = this.mapperService.forRequest().map(updateEmployeeRequest, Employee.class);
        this.employeeRepository.save(employee);
        return new SuccessResult(Message.UPDATE.getMessage());
    }

    @Override
    public Result deleteEmployee(int id) {
        this.employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find employee id"));
        this.employeeRepository.deleteById(id);
        return new SuccessResult(Message.DELETE.getMessage());
    }
}
