package com.example.rent2gojavaproject.services.dtos.requests.CreditCardPaymentRequest;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreditCardPaymentRequest {

    private int id;
    @NotBlank(message = "Card number is required")
    @Size(min = 16, max = 16, message = "Card number must be 16 characters long")
    private String cardNumber;

    @NotBlank(message = "Card holder name is required")
    private String cardHolderName;

    @NotBlank(message = "Expiration month is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])$", message = "Expiration month must be between 01 and 12")
    private String expirationMonth;

    @NotBlank(message = "Expiration year is required")
    @Pattern(regexp = "^(20)\\d{2}$", message = "Expiration year must be in format YYYY")
    private String expirationYear;

    @NotBlank(message = "CVV is required")
    @Size(min = 3, max = 3, message = "CVV must be 3 characters long")
    private String cvv;



}
