package com.example.rent2gojavaproject.services.dtos.responses.ourTeamResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOurTeamResponse {

        private int id;

        private String name;

        private String surname;

        private String position;

        private String linkedin;

        private String github;

        private String imageUrl;

        private String description;
}
