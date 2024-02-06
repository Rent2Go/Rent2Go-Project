package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.abstracts.ModelService;
import com.example.rent2gojavaproject.services.dtos.requests.modelRequest.AddModelRequest;
import com.example.rent2gojavaproject.services.dtos.requests.modelRequest.UpdateModelRequest;
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.modelResponse.GetModelResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@AllArgsConstructor
@CrossOrigin
public class ModelsController {
    private final ModelService modelService;

    @GetMapping()
    public DataResult<List<GetModelListResponse>> getAllModels() {
        return modelService.getAllModels();
    }

    @GetMapping("/{id}")
    public DataResult<GetModelResponse> getModelById(@PathVariable int id) {
        return modelService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result createModel(@RequestBody @Valid AddModelRequest addModelRequest) {
        return modelService.addModel(addModelRequest);
    }

    @PutMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateModel(@RequestBody @Valid UpdateModelRequest updateModelRequest) {
        return modelService.updateModel(updateModelRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result deleteModel(@PathVariable int id) {
        return modelService.deleteModel(id);
    }

    @GetMapping("/getallisactive")
    public DataResult<Iterable<GetModelListResponse>> findAll(@RequestParam boolean isActive) {

        return this.modelService.findAll(isActive);
    }

}
