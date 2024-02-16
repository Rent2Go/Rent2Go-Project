package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.abstracts.JobTitleService;
import com.example.rent2gojavaproject.services.dtos.responses.departmentResponse.GetDepartmentResponse;
import com.example.rent2gojavaproject.services.dtos.responses.jobTitleResponse.GetJobTitleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobtitles")
@AllArgsConstructor
public class JobTitleController {

        private final JobTitleService jobTitleService;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public DataResult<List<GetJobTitleResponse>> getAll() {

        return this.jobTitleService.getAll();


    }
    @GetMapping("/{id} ")
    public DataResult<GetJobTitleResponse> getById(@PathVariable int id) {

        return this.jobTitleService.getById(id);
    }
}
