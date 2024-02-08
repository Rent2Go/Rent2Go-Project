package com.example.rent2gojavaproject.services.abstracts;


import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.AddCarRequest;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.UpdateCarRequest;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {

    DataResult<List<GetCarListResponse>> getAllCars();

    DataResult<GetCarResponse> getById(int id);

    Result addCar(AddCarRequest addCarRequest, MultipartFile file) throws IOException;

    Result updateCarImage(String carPlate , MultipartFile file) throws IOException ;

    Result updateCarIsActive(int id, boolean isActive) ;

    Result updateCar(UpdateCarRequest updateCarRequest);

    Result deleteCar(int id);

    boolean existsByPlate(String plate);

    boolean existsById(int id);

    DataResult<Iterable<GetCarListResponse>> findAll(boolean isActive);


}
