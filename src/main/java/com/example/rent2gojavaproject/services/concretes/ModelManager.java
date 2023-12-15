package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.models.Model;
import com.example.rent2gojavaproject.repositories.ModelRepository;
import com.example.rent2gojavaproject.services.abstracts.ModelService;
import com.example.rent2gojavaproject.services.dtos.requests.modelRequest.AddModelRequest;
import com.example.rent2gojavaproject.services.dtos.requests.modelRequest.UpdateModelRequest;
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelResponse;
import com.example.rent2gojavaproject.services.rules.ModelBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private ModelMapperService mapperService;
    @Override
    public List<GetModelListResponse> getAllModels() {
        List<Model> models = modelRepository.findAll();
        List<GetModelListResponse> responses = models.stream()
                .map(model -> this.mapperService.forResponse()
                        .map(model,GetModelListResponse.class)).collect(Collectors.toList());

        return responses;

    }

    @Override
    public GetModelResponse getById(int id) {
        Model model = this.modelRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find model id!"));

        GetModelResponse response = this.mapperService.forResponse().map(model,GetModelResponse.class);
        return response;
    }

    @Override
    public String addModel(AddModelRequest addModelRequest) {

        Model model = this.mapperService.forRequest().map(addModelRequest,Model.class);
        this.modelRepository.save(model);

        return "Transaction Successfully.";
    }

    @Override
    public String updateModel(UpdateModelRequest updateModelRequest) {
        return null;
    }

    @Override
    public String deleteModel(int id) {
        return null;
    }
}
