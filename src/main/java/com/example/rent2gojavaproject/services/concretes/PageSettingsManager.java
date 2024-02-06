package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.PageSettings;
import com.example.rent2gojavaproject.repositories.PageSettingsRepository;
import com.example.rent2gojavaproject.services.abstracts.PageSettingsService;
import com.example.rent2gojavaproject.services.dtos.requests.pageSettingsRequest.AddSettingRequest;
import com.example.rent2gojavaproject.services.dtos.requests.pageSettingsRequest.UpdateSettingRequest;
import com.example.rent2gojavaproject.services.dtos.responses.pageSettingsResponse.GetPageSettingListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.pageSettingsResponse.GetPageSettingResponse;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PageSettingsManager implements PageSettingsService {

    private PageSettingsRepository pageSettingsRepository;
    private ModelMapperService mapperService;
    private EntityManager entityManager;

    @Override
    public DataResult<List<GetPageSettingListResponse>> getAllSettings(){
        List<PageSettings> pageSettings = this.pageSettingsRepository.findAll();
        List<GetPageSettingListResponse> responses = pageSettings.stream()
                .map(pageSettings1 -> this.mapperService.forResponse()
                        .map(pageSettings, GetPageSettingListResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public DataResult<GetPageSettingResponse> getById(int id){
        PageSettings pageSettings = this.pageSettingsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.ID_NOT_FOUND.getMessage()));


        GetPageSettingResponse response = this.mapperService.forResponse().map(pageSettings,GetPageSettingResponse.class);

        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());

    }

    @Override
    public Result addSetting(AddSettingRequest addSettingRequest){
        PageSettings pageSettings = this.mapperService.forRequest().map(addSettingRequest, PageSettings.class);
        this.pageSettingsRepository.save(pageSettings);

        return new SuccessResult(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result updateSetting(UpdateSettingRequest updateSettingRequest){
        this.pageSettingsRepository.findById(updateSettingRequest.getId()).orElseThrow(()->new NotFoundException(MessageConstants.NOT_FOUND.getMessage()));
        PageSettings pageSettings = this.mapperService.forRequest().map(updateSettingRequest, PageSettings.class);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }
}
