package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.abstracts.DepartmentService;
import com.example.rent2gojavaproject.services.dtos.responses.departmentResponse.GetDepartmentResponse;
import com.example.rent2gojavaproject.services.dtos.responses.jobTitleResponse.GetJobTitleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public DataResult<List<GetDepartmentResponse>> getAll() {

        return this.departmentService.getAll();


    }
    @GetMapping("/{id}")
    public DataResult<GetDepartmentResponse> getById(@PathVariable int id) {

        return this.departmentService.getById(id);
    }


}
