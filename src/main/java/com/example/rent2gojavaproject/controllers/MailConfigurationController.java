package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.models.MailConfiguration;
import com.example.rent2gojavaproject.services.abstracts.MailConfigurationService;
import com.example.rent2gojavaproject.services.dtos.requests.mailConfigurationRequest.UpdateMailConfigurationRequest;
import com.example.rent2gojavaproject.services.dtos.responses.mailConfigurationResponse.GetMailConfigurationResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail-configuration")
@AllArgsConstructor
@CrossOrigin
public class MailConfigurationController {
    private final MailConfigurationService service;
    @GetMapping()
    public DataResult<GetMailConfigurationResponse> getMailConfiguration() {
        return service.getMailConfiguration();
    }

    @PostMapping()
    public void addMailConfiguration(@RequestBody MailConfiguration mailConfiguration) {
        service.addMailConfiguration(mailConfiguration);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Result updateMailConfiguration(@RequestBody UpdateMailConfigurationRequest request) {
       return service.updateMailConfiguration(request);
    }
}
