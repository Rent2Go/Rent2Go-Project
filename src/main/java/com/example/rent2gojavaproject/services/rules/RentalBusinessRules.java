package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.models.Discount;
import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.repositories.CustomerRepository;
import com.example.rent2gojavaproject.repositories.DiscountRepository;
import com.example.rent2gojavaproject.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@Service
public class RentalBusinessRules {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private DiscountRepository discountRepository;

    public void checkIfExistsById(int carId, int customerId, int employeeId) {
        if (!(carRepository.existsById(carId))) {
            throw new IllegalStateException("Car ID doesn't exist !");
        } else if (!(customerRepository.existsById(customerId))) {
            throw new IllegalStateException("Customer ID doesn't exist !");
        } else if (!(employeeRepository.existsById(employeeId))) {
            throw new IllegalStateException("Employee ID doesn't exist !");
        }


    }

    public void checkRentalPeriod(LocalDate startDate, LocalDate endDate) {
        Period period = Period.between(startDate, endDate);
        int rentalDays = period.getDays();
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before rental end date");
        } else if (rentalDays > 25) {
            throw new IllegalStateException("Car can be rented for a maximum of 25 days.!");
        }
    }

    public double calculateTotalPrice(LocalDate startDate, LocalDate endDate, double dailyPrice, String discountCode) {
        Discount discount = this.discountRepository.findByDiscountCode(discountCode);
        double percentAge = discount.getPercentage()/100;

        double totalDiscount = 0;
        double totalPrice = 0;
        long rentalDays = endDate.toEpochDay() - startDate.toEpochDay();
        if (percentAge > 0) {
            totalDiscount = (((dailyPrice * rentalDays) * discount.getPercentage()));
            totalPrice = (dailyPrice * rentalDays) - totalDiscount;

        } else {
            totalPrice = dailyPrice * rentalDays;
        }
        return totalPrice;
    }


}
