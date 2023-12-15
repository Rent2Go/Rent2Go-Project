package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {

    private final CarRepository carRepository;


    public String plateUniqueness (String plate){

        String licensePlate = plate.replace(" ", "").toUpperCase();
        boolean result = this.carRepository.existsByPlate(licensePlate);
        if (result) {
            throw new IllegalArgumentException("Car Plate already exists! : " + plate);
        }

        return licensePlate;
    }
}
