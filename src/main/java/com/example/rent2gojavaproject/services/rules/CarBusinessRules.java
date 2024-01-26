package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.core.exceptions.AlreadyExistsException;
import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.services.abstracts.ColorService;
import com.example.rent2gojavaproject.services.abstracts.ModelService;
import jakarta.mail.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final ModelService modelService;
    private final ColorService colorService;
    private final CarRepository carRepository;


    public String plateUniqueness(String plate) {

        String licensePlate = plate.replace(" ", "").toUpperCase();
        boolean result = this.carRepository.existsByPlate(licensePlate);
        if (result) {
            throw new AlreadyExistsException(plate + MessageConstants.ALREADY_EXISTS.getMessage());
        }

        return licensePlate;
    }

    public void updateCarMethod(int modelId, int colorId) {

        if (!modelService.existsById(modelId)) {
            throw new NotFoundException(MessageConstants.MODEL.getMessage() + MessageConstants.NOT_FOUND.getMessage());
        }
        if (!colorService.existsById(colorId)) {
            throw new NotFoundException(MessageConstants.COLOR.getMessage() + MessageConstants.NOT_FOUND.getMessage());
        }

    }
}
