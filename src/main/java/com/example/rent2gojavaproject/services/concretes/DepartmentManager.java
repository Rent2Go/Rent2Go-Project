package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.models.Car;
import com.example.rent2gojavaproject.models.Department;
import com.example.rent2gojavaproject.models.JobTitle;
import com.example.rent2gojavaproject.repositories.DepartmentRepository;
import com.example.rent2gojavaproject.services.abstracts.DepartmentService;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;
import com.example.rent2gojavaproject.services.dtos.responses.departmentResponse.GetDepartmentResponse;
import com.example.rent2gojavaproject.services.dtos.responses.jobTitleResponse.GetJobTitleResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentManager implements DepartmentService {

    private ModelMapperService mapperService;
    private DepartmentRepository departmentRepository;

    @Override
    public DataResult<List<GetDepartmentResponse>> getAll() {
        List<Department> departments  = this.departmentRepository.findAll();
        List<GetDepartmentResponse> responses = departments.stream().map(department -> this.mapperService
                        .forResponse()
                        .map(department, GetDepartmentResponse.class))
                .collect(Collectors.toList());


        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetDepartmentResponse> getById(int id) {
        Department department = this.departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.CAR.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
       GetDepartmentResponse response = this.mapperService.forResponse().map(department, GetDepartmentResponse.class);

        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());
    }
}
