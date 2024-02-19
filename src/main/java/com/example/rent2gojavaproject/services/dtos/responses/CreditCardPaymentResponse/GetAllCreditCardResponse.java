package com.example.rent2gojavaproject.services.dtos.responses.CreditCardPaymentResponse;

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
public class GetAllCreditCardResponse {

    private Long id;

    private String cardNumber;

    private String cardHolderName;

    private String expirationMonth;

    private String expirationYear;

    private String cvv;


}
