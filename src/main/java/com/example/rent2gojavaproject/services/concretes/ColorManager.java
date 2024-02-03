package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.HibernateConstants;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.Color;
import com.example.rent2gojavaproject.repositories.ColorRepository;
import com.example.rent2gojavaproject.services.abstracts.ColorService;
import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.AddColorRequest;
import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.UpdateColorRequest;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorResponse;
import com.example.rent2gojavaproject.services.rules.ColorBusinessRules;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorManager implements ColorService {

    private final ColorRepository colorRepository;
    private final ColorBusinessRules businessRules;
    private ModelMapperService mapperService;
    private EntityManager entityManager;

    @Override
    public DataResult<List<GetColorListResponse>> getAllColors() {
        List<Color> colors = this.colorRepository.findAll();
        List<GetColorListResponse> responses = colors.stream().map(color -> this.mapperService
                        .forResponse()
                        .map(color, GetColorListResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<Iterable<GetColorListResponse>> findAll(boolean isActive) {

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter(HibernateConstants.IS_ACTIVE_FILTER_COLOR.getValue());

        filter.setParameter(HibernateConstants.IS_ACTIVE.getValue(), isActive);

        Iterable<GetColorListResponse> colors = this.colorRepository.findAll()
                .stream().map(color -> this.mapperService.forResponse()
                        .map(color, GetColorListResponse.class))
                .collect(Collectors.toList());
        session.disableFilter(HibernateConstants.IS_ACTIVE_FILTER_COLOR.getValue());

        return new SuccessDataResult<>(colors, MessageConstants.GET_ALL.getMessage());
    }


    @Override
    public DataResult<GetColorResponse> getById(int id) {

        Color color = this.colorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.COLOR.getMessage() + MessageConstants.NOT_FOUND.getMessage()));
        GetColorResponse responses = this.mapperService.forResponse().map(color, GetColorResponse.class);

        return new SuccessDataResult<>(responses, MessageConstants.GET.getMessage());
    }

    @Override
    public Result addColor(AddColorRequest addColorRequest) {

        String editName = businessRules.checkIfExistsByName(addColorRequest.getName());
        Color color = this.mapperService.forRequest().map(addColorRequest, Color.class);

        color.setName(editName);
        this.colorRepository.save(color);

        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateColor(UpdateColorRequest updateColorRequest) {

        String editName = businessRules.checkIfExistsByName(updateColorRequest.getName());
        this.colorRepository.findById(updateColorRequest.getId())
                .orElseThrow(() -> new NotFoundException(MessageConstants.COLOR.getMessage() + MessageConstants.NOT_FOUND.getMessage()));

        Color color = this.mapperService.forRequest().map(updateColorRequest, Color.class);
        color.setName(editName);
        this.colorRepository.save(color);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public Result deleteColor(int id) {

        Color color = this.colorRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.ID_NOT_FOUND.getMessage() + id));
        color.setDeletedAt(LocalDate.now());

        this.colorRepository.save(color);
        this.colorRepository.delete(color);

        return new SuccessResult(MessageConstants.DELETE.getMessage());
    }

    @Override
    public boolean existsById(int id) {
        return this.colorRepository.existsById(id);
    }

}
