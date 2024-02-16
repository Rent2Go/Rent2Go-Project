package com.example.rent2gojavaproject.services.dtos.responses.pageSettingsResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetPageSettingListResponse {
    private int id;
    private String title;
    private String url;
    private String logo;
    private String tabLogo;
    private String phoneNumber;
    private String email;
    private String address;
    private String linkedin;
    private String instagram;
    private String github;
}
