package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.abstracts.PageSettingsService;
import com.example.rent2gojavaproject.services.dtos.requests.pageSettingsRequest.AddSettingRequest;
import com.example.rent2gojavaproject.services.dtos.requests.pageSettingsRequest.UpdateSettingRequest;
import com.example.rent2gojavaproject.services.dtos.responses.pageSettingsResponse.GetPageSettingListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.pageSettingsResponse.GetPageSettingResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/settings")
@AllArgsConstructor
@CrossOrigin
public class PageSettingsController {

    private final PageSettingsService settingsService;



    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public DataResult<List<GetPageSettingListResponse>> getAllSettings(){

        return this.settingsService.getAllSettings();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DataResult<GetPageSettingResponse> getById(@PathVariable("id") int id){

        return this.settingsService.getById(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Result addSetting(AddSettingRequest addSettingRequest){

            return this.settingsService.addSetting(addSettingRequest);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Result updateSetting(@RequestBody UpdateSettingRequest updateSettingRequest){

        return this.settingsService.updateSetting(updateSettingRequest);
    }

    @PutMapping("/settingsimage")
    @ResponseStatus(HttpStatus.OK)
    public Result updateSetting(@RequestPart("settings") UpdateSettingRequest updateSettingRequest, @RequestPart("file") MultipartFile file) throws IOException {
        return this.settingsService.updateSetting(updateSettingRequest,file);
    }
}
