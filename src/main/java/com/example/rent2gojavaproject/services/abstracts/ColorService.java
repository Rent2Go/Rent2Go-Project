package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.AddColorRequest;
import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.UpdateColorRequest;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorResponse;

import java.util.List;

public interface ColorService {

    DataResult<List<GetColorListResponse>> getAllColors();

    DataResult<GetColorResponse> getById(int id);

    Result addColor(AddColorRequest addColorRequest);

    Result updateColor(UpdateColorRequest updateColorRequest);

    Result deleteColor(int id);

}
