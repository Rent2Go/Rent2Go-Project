package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.abstracts.OurTeamService;
import com.example.rent2gojavaproject.services.dtos.requests.ourTeamRequest.AddOurTeamRequest;
import com.example.rent2gojavaproject.services.dtos.responses.ourTeamResponse.GetOurTeamResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/ourteams")
@AllArgsConstructor
@CrossOrigin
public class OurTeamsController {

    private final OurTeamService ourTeamService;

    @GetMapping()
    public DataResult<List<GetOurTeamResponse>> getAllOurTeam() {

        return this.ourTeamService.getAllOurTeam();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result createOurTeam(@RequestPart("addOurTeamRequest") AddOurTeamRequest addOurTeamRequest, @RequestPart("file") MultipartFile file) throws IOException {

        return this.ourTeamService.addOurTeam(addOurTeamRequest, file);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteOurTeam(@PathVariable int id) {

        return this.ourTeamService.deleteOurTeam(id);
    }
}
