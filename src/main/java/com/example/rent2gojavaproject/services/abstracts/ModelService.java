package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.services.dtos.requests.modelRequest.AddModelRequest;
import com.example.rent2gojavaproject.services.dtos.requests.modelRequest.UpdateModelRequest;
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelResponse;

import java.util.List;

public interface ModelService {
    List<GetModelListResponse> getAllModels();

    GetModelResponse getById(int id);

    String addModel(AddModelRequest addModelRequest);

    String updateModel(UpdateModelRequest updateModelRequest);

    String deleteModel(int id);
}
