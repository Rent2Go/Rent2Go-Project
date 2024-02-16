package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.dtos.responses.jobTitleResponse.GetJobTitleResponse;

import java.util.List;

public interface JobTitleService {

    DataResult<List<GetJobTitleResponse>> getAll();

    DataResult<GetJobTitleResponse> getById(int id);

}
