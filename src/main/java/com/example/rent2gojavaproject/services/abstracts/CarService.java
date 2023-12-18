package com.example.rent2gojavaproject.services.abstracts;


import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.AddCarRequest;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.UpdateCarRequest;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;

import java.util.List;

public interface CarService {

    DataResult<List<GetCarListResponse>> getAllCars();

    DataResult<GetCarResponse> getById(int id);

    Result addCar(AddCarRequest addCarRequest);

    Result updateCar(UpdateCarRequest updateCarRequest);

    Result deleteCar(int id);
    boolean existsByPlate(String plate);


}
