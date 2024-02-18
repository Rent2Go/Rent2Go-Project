package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.models.MailConfiguration;
import com.example.rent2gojavaproject.services.dtos.requests.mailConfigurationRequest.AddMailConfigurationRequest;
import com.example.rent2gojavaproject.services.dtos.requests.mailConfigurationRequest.UpdateMailConfigurationRequest;
import com.example.rent2gojavaproject.services.dtos.responses.mailConfigurationResponse.GetMailConfigurationResponse;

public interface MailConfigurationService {

    DataResult<GetMailConfigurationResponse> getMailConfiguration();

    Result updateMailConfiguration(UpdateMailConfigurationRequest request);

    void addMailConfiguration(MailConfiguration mailConfiguration);
}
