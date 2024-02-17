package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.models.Car;
import com.example.rent2gojavaproject.models.JobTitle;
import com.example.rent2gojavaproject.repositories.JobTitleRepository;
import com.example.rent2gojavaproject.services.abstracts.JobTitleService;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;
import com.example.rent2gojavaproject.services.dtos.responses.jobTitleResponse.GetJobTitleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobTitleManager implements JobTitleService {

    private final JobTitleRepository jobTitleRepository;

    private final ModelMapperService mapperService;


    @Override
    public DataResult<List<GetJobTitleResponse>> getAll() {

        List<JobTitle> jobTitles  = this.jobTitleRepository.findAll();
        List<GetJobTitleResponse> responses = jobTitles.stream().map(car -> this.mapperService
                        .forResponse()
                        .map(car, GetJobTitleResponse.class))
                .collect(Collectors.toList());


        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());

    }

    @Override
    public DataResult<GetJobTitleResponse> getById(int id) {


        JobTitle jobTitle = this.jobTitleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.CAR.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        GetJobTitleResponse response = this.mapperService.forResponse().map(jobTitle, GetJobTitleResponse.class);

        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());
    }
}
