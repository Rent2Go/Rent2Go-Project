package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
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
    private final ModelMapperService mapperService;
    private final ModelBusinessRules modelBusinessRules;

    @Override
    public DataResult<List<GetModelListResponse>> getAllModels() {
        List<Model> models = modelRepository.findAll();
        List<GetModelListResponse> responses = models.stream()
                .map(model -> this.mapperService.forResponse()
                        .map(model, GetModelListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<GetModelListResponse>>(responses, Message.GET_ALL.getMessage());

    }

    @Override
    public DataResult<GetModelResponse> getById(int id) {
        Model model = this.modelRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find model id!"));

        GetModelResponse response = this.mapperService.forResponse().map(model, GetModelResponse.class);
        return new SuccessDataResult<GetModelResponse>(response, Message.GET.getMessage());
    }

    @Override
    public Result addModel(AddModelRequest addModelRequest) {

        String editValue = modelBusinessRules.checkIfExistsByIdAndName(addModelRequest.getBrandId(), addModelRequest.getName());

        Model model = this.mapperService.forRequest().map(addModelRequest, Model.class);
        model.setName(editValue);

        this.modelRepository.save(model);

        return new SuccessResult(Message.ADD.getMessage());
    }

    @Override
    public Result updateModel(UpdateModelRequest updateModelRequest) {

        String editValue = modelBusinessRules.checkIfExistsByIdAndName(updateModelRequest.getBrandId(), updateModelRequest.getName());

        this.modelRepository.findById(updateModelRequest.getId()).orElseThrow(() -> new RuntimeException("Couldn't find model id!"));

        Model model = this.mapperService.forRequest().map(updateModelRequest, Model.class);
        updateModelRequest.setName(editValue);

        this.modelRepository.save(model);

        return new SuccessResult(Message.UPDATE.getMessage());
    }

    @Override
    public Result deleteModel(int id) {
        this.modelRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find model id"));
        this.modelRepository.deleteById(id);
        return new SuccessResult(Message.DELETE.getMessage());
    }
}
