package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.services.abstracts.ColorService;
import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.AddColorRequest;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/colors")
@AllArgsConstructor
public class ColorsController {

    private final ColorService colorService;

    @GetMapping("/getAll")
    List<GetColorListResponse> getAllColor(){

        return this.colorService.getAllColors();
    }

    @GetMapping("/{id}")
    GetColorResponse getById(@PathVariable int id){

        return this.colorService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    String createColor(@RequestBody @Valid AddColorRequest addColorRequest){

        return this.colorService.addColor(addColorRequest);
    }


}
