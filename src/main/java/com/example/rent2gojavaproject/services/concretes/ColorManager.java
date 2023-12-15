package com.example.rent2gojavaproject.services.concretes;

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
    public DataResult<List<GetColorListResponse>> getAllColors() {
        List<Color> colors = this.colorRepository.findAll();
        List<GetColorListResponse> responses = colors.stream().map(color -> this.mapperService
                    .forResponse()
                    .map(color, GetColorListResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<GetColorListResponse>>(responses, "Transaction Successfully");
    }

    @Override
    public DataResult<GetColorResponse> getById(int id) {
        Color color = this.colorRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find color id"));
        GetColorResponse responses = this.mapperService.forResponse().map(color, GetColorResponse.class);
        return new SuccessDataResult<>(responses, "Transaction Successfully");
    }

    @Override
    public Result addColor(AddColorRequest addColorRequest) {

        Color color = this.mapperService.forRequest().map(addColorRequest, Color.class);
        this.colorRepository.save(color);
        return new SuccessResult("Added Color Successfully");
    }

    @Override
    public String updateColor(UpdateColorRequest updateColorRequest) {

        Color color = this.colorRepository.findById(updateColorRequest.getId()).orElseThrow(()-> new RuntimeException("Color not found"));
        color = this.mapperService.forRequest().map(updateColorRequest, Color.class);
        this.colorRepository.save(color);

        return "Transaction Successfully";
    }

    @Override
    public String deleteColor(int id) {
        this.colorRepository.findById(id).orElseThrow(()-> new RuntimeException("id not found"));
        this.colorRepository.deleteById(id);

        return "Transaction Successfully";
    }


}
