package com.example.rent2gojavaproject.services.dtos.requests.ourTeamRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddOurTeamRequest {

    @NotBlank(message = "Name can't be empty.")
    @Size(max = 20, message = "Name must be less than 50 characters.")
    private String name;

    @NotBlank(message = "Surname can't be empty.")
    @Size(max = 15, message = "Surname must be less than 50 characters.")
    private String surname;

    @NotBlank(message = "Position can't be empty.")
    @Size(max = 30, message = "Position must be less than 50 characters.")
    private String position;

    @NotBlank(message = "LinkedIn URL can't be empty.")
    private String linkedin;

    @NotBlank(message = "GitHub URL can't be empty.")
    private String github;

    private String description;
}
