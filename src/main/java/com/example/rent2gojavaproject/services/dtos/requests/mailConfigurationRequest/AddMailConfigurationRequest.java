package com.example.rent2gojavaproject.services.dtos.requests.mailConfigurationRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddMailConfigurationRequest {
    private String host;
    private int port;
    private String username;
    private String password;
}
