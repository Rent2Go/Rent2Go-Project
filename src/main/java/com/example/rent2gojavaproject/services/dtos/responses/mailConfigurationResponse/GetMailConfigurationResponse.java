package com.example.rent2gojavaproject.services.dtos.responses.mailConfigurationResponse;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMailConfigurationResponse {

    private int id;
    private String host;
    private int port;
    private String username;
    private String password;
}
