package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.services.abstracts.ColorService;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
