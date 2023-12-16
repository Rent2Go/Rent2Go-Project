package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.alerts.Message;
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
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorManager implements ColorService {

    private final ColorRepository colorRepository;
    private ModelMapperService mapperService;
    private final ColorBusinessRules businessRules;

    @Override
    public DataResult<List<GetColorListResponse>> getAllColors() {
        List<Color> colors = this.colorRepository.findAll();
        List<GetColorListResponse> responses = colors.stream().map(color -> this.mapperService
                        .forResponse()
                        .map(color, GetColorListResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<GetColorListResponse>>(responses, Message.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetColorResponse> getById(int id) {
        Color color = this.colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find color id"));
        GetColorResponse responses = this.mapperService.forResponse().map(color, GetColorResponse.class);
        return new SuccessDataResult<>(responses, Message.GET.getMessage());
    }

    @Override
    public Result addColor(AddColorRequest addColorRequest) {
        String editName = businessRules.checkIfExistsByName(addColorRequest.getName());
        Color color = this.mapperService.forRequest().map(addColorRequest, Color.class);
        color.setName(editName);
        this.colorRepository.save(color);
        return new SuccessResult(Message.ADD.getMessage());
    }

    @Override
    public Result updateColor(UpdateColorRequest updateColorRequest) {
        String editName = businessRules.checkIfExistsByName(updateColorRequest.getName());
        this.colorRepository.findById(updateColorRequest.getId()).orElseThrow(() -> new RuntimeException("Color not found"));
        Color color = this.mapperService.forRequest().map(updateColorRequest, Color.class);
        color.setName(editName);
        this.colorRepository.save(color);

        return new SuccessResult(Message.UPDATE.getMessage());
    }

    @Override
    public Result deleteColor(int id) {
        this.colorRepository.findById(id).orElseThrow(() -> new RuntimeException("id not found"));
        this.colorRepository.deleteById(id);

        return new SuccessResult(Message.DELETE.getMessage());
    }


}
