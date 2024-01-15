package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.alerts.Message;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.models.District;
import com.example.rent2gojavaproject.repositories.DistrictRepository;
import com.example.rent2gojavaproject.services.abstracts.DistrictService;
import com.example.rent2gojavaproject.services.dtos.responses.districtResponse.GetDistrictListResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DistrictManager implements DistrictService {

    private final DistrictRepository districtRepository;
    private ModelMapperService mapperService;

    @Override
    public DataResult<List<GetDistrictListResponse>> getAllDistricts() {

        List<District> districts = this.districtRepository.findAll();
        List<GetDistrictListResponse> responses = districts.stream()
                .map(district -> this.mapperService.forResponse()
                        .map(district, GetDistrictListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses, Message.GET_ALL.getMessage());
    }
}
