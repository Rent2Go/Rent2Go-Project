package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.abstracts.EmployeeService;
import com.example.rent2gojavaproject.services.dtos.requests.employeeRequest.AddEmployeeRequest;
import com.example.rent2gojavaproject.services.dtos.requests.employeeRequest.UpdateEmployeeRequest;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.employeeResponse.GetEmployeeResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
@CrossOrigin
public class EmployeesController {
    private final EmployeeService employeeService;

    @GetMapping("/getall")
    public DataResult<List<GetEmployeeListResponse>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getAllActiveOrNot")
    public DataResult<Iterable<GetEmployeeListResponse>> findAll(@RequestParam boolean isActive) {
        return this.employeeService.findAll(isActive);
    }

    @GetMapping("/{id}")
    public DataResult<GetEmployeeResponse> getEmployeeById(@PathVariable int id) {
        return employeeService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result createEmployee(@RequestBody @Valid AddEmployeeRequest addEmployeeRequest) {
        return employeeService.addEmployee(addEmployeeRequest);
    }

    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateEmployee(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest) {
        return employeeService.updateEmployee(updateEmployeeRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }
}
