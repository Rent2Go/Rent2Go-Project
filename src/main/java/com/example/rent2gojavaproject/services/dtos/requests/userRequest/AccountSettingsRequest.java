package com.example.rent2gojavaproject.services.dtos.requests.userRequest;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountSettingsRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;
    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 2, max = 20, message = "Surname must be between 2 and 20 characters")
    private String surname;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    @Past(message = "Birth date must be in the past")
    public LocalDate birthDate;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "05[0-9]{9}", message = "Invalid phone number format. It must be in the format 05xxxxxxxxx.")
    private String phoneNumber;

}
