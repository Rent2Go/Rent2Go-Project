package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.models.Model;
import com.example.rent2gojavaproject.services.dtos.requests.modelRequest.AddModelRequest;
import com.example.rent2gojavaproject.services.dtos.requests.modelRequest.UpdateModelRequest;
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelResponse;

import java.util.List;

public interface ModelService {
    DataResult<List<GetModelListResponse>> getAllModels();

    DataResult<GetModelResponse> getById(int id);

    Result addModel(AddModelRequest addModelRequest);

    Result updateModel(UpdateModelRequest updateModelRequest);

    Result deleteModel(int id);

    boolean existsById(int id);

    DataResult<Iterable<GetModelListResponse>> findAll(boolean isDeleted);
}

