package com.example.rent2gojavaproject.services.dtos.requests.carRequest;

import com.example.rent2gojavaproject.models.BodyType;
import com.example.rent2gojavaproject.models.FuelType;
import com.example.rent2gojavaproject.models.GearType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    @NotNull(message = "The car id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int id;
    @NotNull(message = "The kilometer field cannot be null.")
    @Min(value = 0, message = "Kilometer must be greater than or equal to 0.")
    private int kilometer;
    @NotNull(message = "The year field cannot be null.")
    @Min(value = 2005, message = "Year must be greater than or equal to 2005.")
    @Max(value = 2024, message = "Year must be less than or equal to the current year.")
    private int year;
    @NotNull(message = "The daily price field cannot be null.")
    @Positive(message = "Daily price must be greater than 0.")
    private double dailyPrice;
    @NotBlank(message = "The plate field can't be empty.")
    @Pattern(regexp = "^(?iu)\\s?(0[1-9]|[1-7][0-9]|8[01])\\s?[A-Za-zğüşıöçĞÜŞİÖÇ]{1,3}\\s?[0-9]{2,4}\\s?$", message = "Invalid licence plate")
    @Size(min = 5, max = 9, message = "Licence plate must be between 5 and 9 characters")
    private String plate;
    @NotNull(message = "The model id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int modelId;
    @NotNull(message = "The color id cannot be null.")
    @Positive(message = "Id must be a positive number.")
    private int colorId;
    @NotNull(message = "Body type cannot be null")
    private BodyType bodyType;
    @NotNull(message = "Fuel type cannot be null")
    private FuelType fuelType;
    @NotNull(message = "Gear type cannot be null")
    private GearType gearType;
    @Positive(message = "Cylinder Count value must be a positive number.")
    private double cylinderCount;
    @Positive(message = "Engine Power value must be a positive number.")
    private int enginePower;
    private boolean isActive;
}
