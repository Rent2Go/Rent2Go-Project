package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.MailConfiguration;
import com.example.rent2gojavaproject.repositories.MailConfigurationRepository;
import com.example.rent2gojavaproject.services.abstracts.MailConfigurationService;
import com.example.rent2gojavaproject.services.dtos.requests.mailConfigurationRequest.AddMailConfigurationRequest;
import com.example.rent2gojavaproject.services.dtos.requests.mailConfigurationRequest.UpdateMailConfigurationRequest;
import com.example.rent2gojavaproject.services.dtos.responses.mailConfigurationResponse.GetMailConfigurationResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailConfigurationManager implements MailConfigurationService {
    private final MailConfigurationRepository repository;

    private final ModelMapperService mapperService;



    @Override
    public DataResult<GetMailConfigurationResponse> getMailConfiguration() {
        MailConfiguration mailConfiguration = repository.findById(1)
                .orElseThrow(() -> new NotFoundException(MessageConstants.NOT_FOUND.getMessage()));

        GetMailConfigurationResponse response = mapperService.forResponse().map(mailConfiguration, GetMailConfigurationResponse.class);

        return new SuccessDataResult<>(response, MessageConstants.GET.getMessage());
    }

    @Override
    public Result updateMailConfiguration(UpdateMailConfigurationRequest request) {
        this.repository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Mail configuration not found"));

        MailConfiguration mailConfiguration = mapperService.forRequest().map(request, MailConfiguration.class);

        repository.save(mailConfiguration);

        return new SuccessResult(MessageConstants.UPDATE.getMessage());
    }

    @Override
    public void addMailConfiguration(MailConfiguration mailConfiguration) {

        repository.save(mailConfiguration);
    }

}
