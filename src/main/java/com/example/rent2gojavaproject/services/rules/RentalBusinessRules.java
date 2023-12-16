package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.repositories.CustomerRepository;
import com.example.rent2gojavaproject.repositories.EmployeeRepository;
import com.example.rent2gojavaproject.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@Service
public class RentalBusinessRules {

    private CarRepository carRepository;
    private RentalRepository rentalRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

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

        if (rentalDays > 25) {
            throw new IllegalStateException("Car can be rented for a maximum of 25 days.!");
        }
    }

    public double calculateTotalPrice(LocalDate startDate, LocalDate endDate, double dailyPrice) {


        Long rentalDays = endDate.toEpochDay() - startDate.toEpochDay();
        Double totalPrice = dailyPrice * rentalDays;

        //  Double a =  Double.valueOf(rentalDays) * rentalPrice;

        return totalPrice;
    }


}
