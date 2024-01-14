package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.models.City;
import com.example.rent2gojavaproject.repositories.CityRepository;
import com.example.rent2gojavaproject.services.abstracts.CityService;
import com.example.rent2gojavaproject.services.dtos.responses.cityResponse.GetCityListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityManager implements CityService {
    private final CityRepository cityRepository;
    private ModelMapperService mapperService;


    @Override
    public DataResult<List<GetCityListResponse>> getAllCities() {

        List<City> cities = this.cityRepository.findAll();
        List<GetCityListResponse> responses = cities.stream()
                .map(city -> this.mapperService.forResponse()
                        .map(city, GetCityListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<>(responses, Message.GET_ALL.getMessage());
    }
}
