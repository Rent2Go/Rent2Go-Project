package com.example.rent2gojavaproject.services.rules;

import com.example.rent2gojavaproject.core.exceptions.BusinessRuleException;
import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.models.Discount;
import com.example.rent2gojavaproject.services.abstracts.CarService;
import com.example.rent2gojavaproject.services.abstracts.CustomerService;
import com.example.rent2gojavaproject.services.abstracts.DiscountService;
import com.example.rent2gojavaproject.services.abstracts.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@Service
public class RentalBusinessRules {

    private CarService carService;
    private CustomerService customerService;
    private EmployeeService employeeService;
    private DiscountService discountService;

    public void checkIfExistsById(int carId, int customerId, int employeeId) {

        if (!(carService.existsById(carId))) {
            throw new NotFoundException(MessageConstants.CAR.getMessage() + MessageConstants.NOT_FOUND.getMessage());
        } else if (!(customerService.existsById(customerId))) {
            throw new NotFoundException(MessageConstants.CUSTOMER.getMessage() + MessageConstants.NOT_FOUND.getMessage());
        } else if (!(employeeService.existsById(employeeId))) {
            throw new NotFoundException(MessageConstants.EMPLOYEE.getMessage() + MessageConstants.NOT_FOUND.getMessage());
        }
    }


    public void checkIfExistsById(int carId, int customerId) {

        if (!(carService.existsById(carId))) {
            throw new NotFoundException(MessageConstants.CAR.getMessage() + MessageConstants.NOT_FOUND.getMessage());
        } else if (!(customerService.existsById(customerId))) {
            throw new NotFoundException(MessageConstants.CUSTOMER.getMessage() + MessageConstants.NOT_FOUND.getMessage());
        }
    }

    public void checkIfKilometer(int kilometer, Integer endKilometer) {

        Integer newKilometer = Integer.valueOf(kilometer);
        if (newKilometer > endKilometer) {
            throw new BusinessRuleException(MessageConstants.KILOMETER_ERROR.getMessage());
        }
    }

    public void checkRentalPeriod(LocalDate startDate, LocalDate endDate) {

        Period period = Period.between(startDate, endDate);
        int rentalDays = period.getDays();
        if (startDate.isAfter(endDate)) {
            throw new BusinessRuleException(MessageConstants.START_DATE_ERROR.getMessage());
        } else if (rentalDays > 25) {
            throw new BusinessRuleException(MessageConstants.MAX_RENTAL_DAYS_ERROR.getMessage());
        }
    }

    public Discount getDiscountByCodeOrDefault(String discountCode) {

        if (discountCode == null || discountCode.isEmpty()) {
            return discountService.findByDiscountCode("DEFAULT");
        }

        Discount selectedDiscount = discountService.findByDiscountCode(discountCode);

        return (selectedDiscount != null) ? selectedDiscount : discountService.findByDiscountCode("DEFAULT");
    }

    public double calculateTotalPrice(LocalDate startDate, LocalDate endDate, double dailyPrice, String discountCode) {
        Double totalPrice = null;
        double tax = 0.20 ;
        double totalTaxPrice = (tax * dailyPrice);
        long dateResult =(endDate.toEpochDay() - startDate.toEpochDay());
        dateResult = dateResult <= 0 ? 1 : dateResult;

        Discount discount = this.discountService.findByDiscountCode(discountCode);

        double totalDiscount = (discount.getPercentage()) * dailyPrice *
                ( dateResult >= 0 ? 1 : (dateResult));


       if(totalDiscount > 0 ){
            totalPrice = ((dailyPrice  * dateResult ) + (totalTaxPrice) )- totalDiscount;
       }
            totalPrice = (dailyPrice  * dateResult ) + (totalTaxPrice);

        return (totalPrice < 0) ? 0 : totalPrice;
    }

}
