package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.ourTeamRequest.AddOurTeamRequest;
import com.example.rent2gojavaproject.services.dtos.responses.ourTeamResponse.GetOurTeamResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OurTeamService {
    DataResult<List<GetOurTeamResponse>> getAllOurTeam();

    Result addOurTeam(AddOurTeamRequest addOurTeamRequest, MultipartFile file) throws IOException;

    Result deleteOurTeam(int id);
}
