package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.repositories.CustomerRepository;
import com.example.rent2gojavaproject.repositories.EmployeeRepository;
import com.example.rent2gojavaproject.repositories.RentalRepository;

public class RentalBusinessRules {

    private CarRepository carRepository;
    private RentalRepository rentalRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    public void checkIfExistsById(int carId, int customerId, int employeeId) {
        if (!(carRepository.existsById(carId))) {
            throw new IllegalStateException("Car ID doesn't exist !");
        }
        else if (!(customerRepository.existsById(customerId))) {
            throw new IllegalStateException("Customer ID doesn't exist !");
        }
        else if (!(employeeRepository.existsById(employeeId))) {
            throw new IllegalStateException("Employee ID doesn't exist !");
        }

    }



}
