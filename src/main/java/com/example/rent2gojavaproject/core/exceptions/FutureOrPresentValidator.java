package com.example.rent2gojavaproject.core.exceptions;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.util.Date;

public class FutureOrPresentValidator implements ConstraintValidator<FutureOrPresent, LocalDate> {
    public void initialize(FutureOrPresent constraint) {
    }

    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return date == null || date.isAfter(LocalDate.now().minusDays(1)); // Bugünkü veya gelecekteki tarihleri kabul eder
    }
}
