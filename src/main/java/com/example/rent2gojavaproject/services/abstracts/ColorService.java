package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.AddColorRequest;
import com.example.rent2gojavaproject.services.dtos.requests.colorRequest.UpdateColorRequest;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.colorResponse.GetColorResponse;

import java.util.List;

public interface ColorService {

    List<GetColorListResponse> getAllColors();

    GetColorResponse getById(int id);

    String addColor(AddColorRequest addColorRequest);

    String updateColor(UpdateColorRequest updateColorRequest);

    String deleteColor(int id);

}
