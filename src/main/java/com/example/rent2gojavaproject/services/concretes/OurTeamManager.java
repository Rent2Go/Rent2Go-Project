package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.models.OurTeam;
import com.example.rent2gojavaproject.repositories.OurTeamRepository;
import com.example.rent2gojavaproject.services.abstracts.FileUpload;
import com.example.rent2gojavaproject.services.abstracts.OurTeamService;
import com.example.rent2gojavaproject.services.dtos.requests.ourTeamRequest.AddOurTeamRequest;
import com.example.rent2gojavaproject.services.dtos.responses.ourTeamResponse.GetOurTeamResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OurTeamManager implements OurTeamService {

    private final OurTeamRepository ourTeamRepository;
    private ModelMapperService mapperService;
    private FileUpload fileUpload;


    @Override
    public DataResult<List<GetOurTeamResponse>> getAllOurTeam() {

        List<OurTeam> ourTeams = this.ourTeamRepository.findAll();
        List<GetOurTeamResponse> responses = ourTeams.stream().map(ourTeam -> this.mapperService
                        .forResponse()
                        .map(ourTeam, GetOurTeamResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<>(responses, MessageConstants.GET_ALL.getMessage());
    }

    @Override
    public Result addOurTeam(AddOurTeamRequest addOurTeamRequest, MultipartFile file) throws IOException {

        String imageUrl = fileUpload.uploadFileUser(file, addOurTeamRequest.getLinkedin());

        OurTeam ourTeam = this.mapperService.forRequest().map(addOurTeamRequest, OurTeam.class);
        ourTeam.setImageUrl(imageUrl);

        this.ourTeamRepository.save(ourTeam);

        return new SuccessDataResult<>(MessageConstants.ADD.getMessage());
    }

    @Override
    public Result deleteOurTeam(int id) {

        OurTeam ourTeam = this.ourTeamRepository.findById(id).orElseThrow(()
                -> new NotFoundException(MessageConstants.NOT_FOUND.getMessage()+ id));

        this.ourTeamRepository.deleteById(id);

        return new SuccessDataResult<>(MessageConstants.DELETE.getMessage());
    }
}
