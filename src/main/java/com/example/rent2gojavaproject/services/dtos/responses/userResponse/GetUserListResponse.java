package com.example.rent2gojavaproject.services.dtos.responses.userResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserListResponse {
    private int id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private String imageUrl;
}
