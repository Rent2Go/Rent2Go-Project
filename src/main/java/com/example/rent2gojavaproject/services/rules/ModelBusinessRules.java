package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.core.exceptions.AlreadyExistsException;
import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.models.Car;
import com.example.rent2gojavaproject.models.Model;
import com.example.rent2gojavaproject.repositories.ModelRepository;
import com.example.rent2gojavaproject.services.abstracts.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class ModelBusinessRules {

    private ModelRepository modelRepository;
    private BrandService brandService;

    public String checkIfExistsByIdAndName(int brandId, String name) {

        String value = name.toLowerCase().trim();
        if (!(brandService.existsById(brandId))) {

            throw new NotFoundException("Brand ID doesn't exist !");

        } else if (modelRepository.existsByName(value)) {

            throw new AlreadyExistsException("Model already exists");
        }

        return value;
    }

    public void changeIsActive(Model model, boolean isActive) {
        model.setActive(isActive);

        List<Car> existingModels = model.getCars();

        if (existingModels != null) {
            existingModels.forEach(car -> {
                car.setActive(model.isActive());
                car.setDeletedAt(model.getUpdatedAt());
            });
        }

    }

    public void changeDeleteDate(Model model) {

        model.setDeletedAt(LocalDate.now());
        List<Car> existingModels = model.getCars();
        model.getCars().forEach(car -> {
            car.setDeletedAt(model.getDeletedAt());
        });
    }


}
