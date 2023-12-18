package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.repositories.ColorRepository;
import com.example.rent2gojavaproject.repositories.ModelRepository;
import com.example.rent2gojavaproject.services.abstracts.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final ModelRepository modelRepository;
    private final ColorRepository colorRepository;
    private final CarService carService;


    public String plateUniqueness (String plate){

        String licensePlate = plate.replace(" ", "").toUpperCase();
        boolean result = this.carService.existsByPlate(licensePlate);
        if (result) {
            throw new IllegalArgumentException("Car Plate already exists! : " + plate);
        }

        return licensePlate;
    }

    public void updateCarMethod (int modelId, int colorId){
        if (!(modelRepository.existsById(modelId) && colorRepository.existsById(colorId))){
            throw  new IllegalStateException("Model or color does not exist");
        }
    }
}
