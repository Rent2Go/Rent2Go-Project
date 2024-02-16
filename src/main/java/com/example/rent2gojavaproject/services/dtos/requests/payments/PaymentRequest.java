package com.example.rent2gojavaproject.services.dtos.requests.payments;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @Pattern(regexp="[0-9]{16}", message="Card number must be 16 digits")
    private String cardNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])$", message="Expiration month must be between 01 and 12")
    private String expirationMonth;

    @Pattern(regexp="[0-9]{4}", message="Expiration year must be 4 digits")
    @Max(value = 2050)
    private String expirationYear;

    @Pattern(regexp="[0-9]{3}", message="CVV must be 3 digits")
    private String cvv;

    @Min(value = 1, message = "Amount must be greater than 0")
    private long amount;

}