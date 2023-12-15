package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.models.Color;
import com.example.rent2gojavaproject.repositories.ColorRepository;
import com.example.rent2gojavaproject.services.abstracts.ColorService;
import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.AddColorRequest;
import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.UpdateColorRequest;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorManager implements ColorService {

    private final ColorRepository colorRepository;
    private ModelMapperService mapperService;

    @Override
    public List<GetColorListResponse> getAllColors() {
        List<Color> colors = this.colorRepository.findAll();
        List<GetColorListResponse> responses = colors.stream().map(color -> this.mapperService
                    .forResponse()
                    .map(color, GetColorListResponse.class))
                .collect(Collectors.toList());

        return responses;
    }

    @Override
    public GetColorResponse getById(int id) {
        return null;
    }

    @Override
    public String addColor(AddColorRequest addColorRequest) {
        return null;
    }

    @Override
    public String updateColor(UpdateColorRequest updateColorRequest) {
        return null;
    }

    @Override
    public String deleteColor(int id) {
        return null;
    }


}
