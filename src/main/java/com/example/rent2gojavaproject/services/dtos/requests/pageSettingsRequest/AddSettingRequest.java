package com.example.rent2gojavaproject.services.dtos.requests.pageSettingsRequest;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSettingRequest {





        @NotNull(message = "The settings id cannot be null.")
        @Positive(message = "Id must be a positive number.")
        private int id;

        @NotBlank(message = "Title cannot be blank.")
        private String title;

        @NotBlank(message = "URL cannot be blank.")
        private String url;
        @NotBlank(message = "logo cannot be blank.")
        private String logo;
        @NotBlank(message = "logo cannot be blank.")
        private String tabLogo;

        @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number.")
        private String phoneNumber;

        @Email(message = "Invalid email format.")
        private String email;

        @NotBlank(message = "Address cannot be blank.")
        private String address;
        @NotBlank(message = "Linkedin cannot be blank.")
        private String linkedin;
        @NotBlank(message = "instagram cannot be blank.")
        private String instagram;

        @NotBlank(message = "Github cannot be blank.")
        private String github;
    }


