package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.core.exceptions.AlreadyExistsException;
import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.repositories.ColorRepository;
import com.example.rent2gojavaproject.repositories.ModelRepository;
import com.example.rent2gojavaproject.services.abstracts.CarService;
import com.example.rent2gojavaproject.services.abstracts.ColorService;
import com.example.rent2gojavaproject.services.abstracts.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final ModelService modelService;
    private final ColorService colorService;
    private final CarRepository carRepository;


    public String plateUniqueness (String plate){

        String licensePlate = plate.replace(" ", "").toUpperCase();
        boolean result = this.carRepository.existsByPlate(licensePlate);
        if (result) {
            throw new AlreadyExistsException("Car Plate already exists! : " + plate);
        }

        return licensePlate;
    }

    public void updateCarMethod (int modelId, int colorId){
        if (!(modelService.existsById(modelId) && colorService.existsById(colorId))){
            throw  new NotFoundException("Model or color does not exist");
        }
    }
}
