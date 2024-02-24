package com.example.rent2gojavaproject;

import com.example.rent2gojavaproject.core.exceptions.BusinessRuleException;
import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.models.Discount;
import com.example.rent2gojavaproject.services.abstracts.CarService;
import com.example.rent2gojavaproject.services.abstracts.CustomerService;
import com.example.rent2gojavaproject.services.abstracts.DiscountService;
import com.example.rent2gojavaproject.services.abstracts.EmployeeService;
import com.example.rent2gojavaproject.services.rules.RentalBusinessRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RentalTest {
    @Mock
    private CarService carService;

    @Mock
    private CustomerService customerService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private RentalBusinessRules rentalBusinessRules;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


        @Test
        void shouldNotThrowExceptionWhenCarCustomerAndEmployeeExistById() {
            int carId = 1;
            int customerId = 1;
            int employeeId = 1;

            when(carService.existsById(carId)).thenReturn(true);
            when(customerService.existsById(customerId)).thenReturn(true);
            when(employeeService.existsById(employeeId)).thenReturn(true);

            assertDoesNotThrow(() -> rentalBusinessRules.checkIfExistsById(carId, customerId, employeeId));
        }

        @Test
        void shouldThrowNotFoundExceptionWhenCarDoesNotExistById() {
            int carId = 1;
            int customerId = 1;
            int employeeId = 1;

            when(carService.existsById(carId)).thenReturn(false);
            when(customerService.existsById(customerId)).thenReturn(true);
            when(employeeService.existsById(employeeId)).thenReturn(true);

            assertThrows(NotFoundException.class, () -> rentalBusinessRules.checkIfExistsById(carId, customerId, employeeId));
        }

        @Test
        void shouldThrowNotFoundExceptionWhenCustomerDoesNotExistById() {
            int carId = 1;
            int customerId = 1;
            int employeeId = 1;

            when(carService.existsById(carId)).thenReturn(true);
            when(customerService.existsById(customerId)).thenReturn(false);
            when(employeeService.existsById(employeeId)).thenReturn(true);

            assertThrows(NotFoundException.class, () -> rentalBusinessRules.checkIfExistsById(carId, customerId, employeeId));
        }

        @Test
        void shouldThrowNotFoundExceptionWhenEmployeeDoesNotExistById() {
            int carId = 1;
            int customerId = 1;
            int employeeId = 1;

            when(carService.existsById(carId)).thenReturn(true);
            when(customerService.existsById(customerId)).thenReturn(true);
            when(employeeService.existsById(employeeId)).thenReturn(false);

            assertThrows(NotFoundException.class, () -> rentalBusinessRules.checkIfExistsById(carId, customerId, employeeId));
        }

        @Test
        void shouldNotThrowExceptionWhenEndKilometerIsGreaterThanStartKilometer() {
            int kilometer = 100;
            Integer endKilometer = 200;

            assertDoesNotThrow(() -> rentalBusinessRules.checkIfKilometer(kilometer, endKilometer));
        }

        @Test
        void shouldThrowBusinessRuleExceptionWhenEndKilometerIsLessThanStartKilometer() {
            int kilometer = 100;
            Integer endKilometer = 50;

            assertThrows(BusinessRuleException.class, () -> rentalBusinessRules.checkIfKilometer(kilometer, endKilometer));
        }

        @Test
        void shouldNotThrowExceptionWhenEndDateIsAfterStartDateAndRentalDaysAreLessThan25() {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusDays(10);

            assertDoesNotThrow(() -> rentalBusinessRules.checkRentalPeriod(startDate, endDate));
        }

        @Test
        void shouldThrowBusinessRuleExceptionWhenEndDateIsBeforeStartDate() {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.minusDays(1);

            assertThrows(BusinessRuleException.class, () -> rentalBusinessRules.checkRentalPeriod(startDate, endDate));
        }

        @Test
        void shouldThrowBusinessRuleExceptionWhenRentalDaysAreMoreThan25() {
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.plusDays(26);

            assertThrows(BusinessRuleException.class, () -> rentalBusinessRules.checkRentalPeriod(startDate, endDate));
        }

        @Test
        void shouldReturnDiscountWhenDiscountCodeExists() {
            String discountCode = "DEFAULT";
            Discount discount = new Discount();

            when(discountService.findByDiscountCode(discountCode)).thenReturn(discount);

            assertEquals(discount, rentalBusinessRules.getDiscountByCodeOrDefault(discountCode));
        }

        @Test
        void shouldReturnDefaultDiscountWhenDiscountCodeDoesNotExist() {
            String discountCode = "DEFAULT";
            Discount discount = new Discount();

            when(discountService.findByDiscountCode(discountCode)).thenReturn(null);
            when(discountService.findByDiscountCode("DEFAULT")).thenReturn(discount);

            assertEquals(discount, rentalBusinessRules.getDiscountByCodeOrDefault(discountCode));
        }


    }