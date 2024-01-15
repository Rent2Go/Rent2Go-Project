package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.abstracts.ColorService;
import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.AddColorRequest;
import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.UpdateColorRequest;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
@AllArgsConstructor
@CrossOrigin
public class ColorsController {

    private final ColorService colorService;

    @GetMapping("/getall")
    public DataResult<List<GetColorListResponse>> getAllColor() {

        return this.colorService.getAllColors();
    }

    @GetMapping("/{id}")
    public DataResult<GetColorResponse> getById(@PathVariable int id) {

        return this.colorService.getById(id);
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result createColor(@RequestBody @Valid AddColorRequest addColorRequest) {

        return this.colorService.addColor(addColorRequest);
    }


    @PutMapping("/update")
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateColor(@RequestBody @Valid UpdateColorRequest updateColorRequest) {

        return this.colorService.updateColor(updateColorRequest);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteColor(@PathVariable int id) {

        return this.colorService.deleteColor(id);
    }

    @GetMapping("/filteredgetall")
    public DataResult<Iterable<GetColorListResponse>> findAll(@RequestParam boolean isActive) {

        return this.colorService.findAll(isActive);
    }


}
