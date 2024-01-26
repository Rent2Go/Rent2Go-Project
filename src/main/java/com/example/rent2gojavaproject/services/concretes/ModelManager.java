package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.constants.HibernateConstants;
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
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapperService mapperService;
    private final ModelBusinessRules modelBusinessRules;
    private final EntityManager entityManager;

    @Override
    public DataResult<List<GetModelListResponse>> getAllModels() {

        List<Model> models = modelRepository.findAll();
        List<GetModelListResponse> responses = models.stream()
                .map(model -> this.mapperService.forResponse()
                        .map(model, GetModelListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());

    }

    @Override
    public DataResult<Iterable<GetModelListResponse>> findAll(boolean isActive) {

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(HibernateConstants.IS_ACTIVE_FILTER_MODEL.getValue());

        filter.setParameter(HibernateConstants.IS_ACTIVE.getValue(), isActive);

        Iterable<GetModelListResponse> models = this.modelRepository.findAll().stream()
                .map(model -> this.mapperService.forResponse().map(model, GetModelListResponse.class))
                .collect(Collectors.toList());
        session.disableFilter(HibernateConstants.IS_ACTIVE_FILTER_MODEL.getValue());

        return new SuccessDataResult<>(models, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetModelResponse> getById(int id) {

        Model model = this.modelRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.MODEL.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        GetModelResponse response = this.mapperService.forResponse().map(model, GetModelResponse.class);

        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());
    }

    @Override
    public Result addModel(AddModelRequest addModelRequest) {

        String editValue = modelBusinessRules.checkIfExistsByIdAndName(addModelRequest.getBrandId(), addModelRequest.getName());

        Model model = this.mapperService.forRequest().map(addModelRequest, Model.class);
        model.setName(editValue);
        model.setId(0);
        this.modelRepository.save(model);

        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateModel(UpdateModelRequest updateModelRequest) {

        String editValue = modelBusinessRules.checkIfExistsByIdAndName(updateModelRequest
                .getBrandId(), updateModelRequest.getName());
        Model existingModel = this.modelRepository.findById(updateModelRequest.getId())
                .orElseThrow(() -> new NotFoundException(MessageConstants.MODEL.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        Model model = this.mapperService.forRequest().map(updateModelRequest, Model.class);
        updateModelRequest.setName(editValue);
        modelBusinessRules.changeIsActive(existingModel, updateModelRequest.isActive());

        this.modelRepository.save(model);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result deleteModel(int id) {

        Model model = this.modelRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.ID_NOT_FOUND.getMessage() + id));
        modelBusinessRules.changeDeleteDate(model);

        this.modelRepository.save(model);
        this.modelRepository.delete(model);

        return new SuccessResult(MessageConstants.DELETE.getMessage());
    }

    @Override
    public boolean existsById(int id) {
        return this.modelRepository.existsById(id);
    }
}
