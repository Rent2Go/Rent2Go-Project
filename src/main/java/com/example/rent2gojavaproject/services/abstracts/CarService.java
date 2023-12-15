package com.example.rent2gojavaproject.services.abstracts;


import com.example.rent2gojavaproject.services.dtos.requests.carRequest.AddCarRequest;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.UpdateCarRequest;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;

import java.util.List;

public interface CarService {

    List<GetCarListResponse> getAllCars();

    GetCarResponse getById(int id);

    String addCar(AddCarRequest addCarRequest);

    String updateCar(UpdateCarRequest updateCarRequest);

    String deleteCar(int id);


}
