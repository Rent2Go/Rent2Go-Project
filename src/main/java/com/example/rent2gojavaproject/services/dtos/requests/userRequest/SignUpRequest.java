package com.example.rent2gojavaproject.services.dtos.requests.userRequest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    String firstName;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 2, max = 20, message = "Surname must be between 2 and 20 characters")
    String lastName;

    @Pattern(regexp = "05[0-9]{9}", message = "Invalid phone number format. It must be in the format 05xxxxxxxxx.")
    String phoneNumber;

    @Email(message = "Invalid email address format")
    String email;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull
    @Past(message = "Birth date must be in the past")
    public LocalDate birthDate;

    @NotBlank(message = "ID Number cannot be blank")
    @Size(min = 11, max = 11, message = "ID Number must be exactly 11 characters")
    @Pattern(regexp = "\\d{11}", message = "ID Number must consist of 11 digits")
    private String idCardNumber;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=.*!()-_])(?=\\S+$).{8,}",
            message = "At least 8 characters\n" +
                    "\n" +
                    "Contains at least one digit\n" +
                    "\n" +
                    "Contains at least one lowercase and one uppercase letter\n" +
                    "\n" +
                    "Contains at least one special character from the set (@#%$^.*etc.)\n" +
                    "\n" +
                    "Does not contain spaces, tabs, etc.")

    String password;
}