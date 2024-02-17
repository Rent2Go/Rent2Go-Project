/*package com.example.rent2gojavaproject.controllers;


import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.abstracts.SquarePaymentService;
import com.example.rent2gojavaproject.services.dtos.requests.payments.PaymentRequest;
import com.squareup.square.models.CreatePaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class SquarePaymentController {

    public final SquarePaymentService squarePaymentService;




    @PostMapping()
    public DataResult<CreatePaymentResponse>processPayment(@RequestBody @Valid PaymentRequest paymentRequest) throws Exception {

        return this.squarePaymentService.processPayment(paymentRequest);

    }



}*/
