package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.pageSettingsRequest.AddSettingRequest;
import com.example.rent2gojavaproject.services.dtos.requests.pageSettingsRequest.UpdateSettingRequest;
import com.example.rent2gojavaproject.services.dtos.responses.pageSettingsResponse.GetPageSettingListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.pageSettingsResponse.GetPageSettingResponse;
import io.jsonwebtoken.io.IOException;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface PageSettingsService {

    DataResult<List<GetPageSettingListResponse>>getAllSettings();

    DataResult<GetPageSettingResponse> getById(int id);

    Result addSetting(AddSettingRequest addSettingRequest) throws IOException;


    Result updateSetting(UpdateSettingRequest updateSettingRequest);

    Result updateSetting(UpdateSettingRequest updateSettingRequest, MultipartFile[] file) throws java.io.IOException;

}
