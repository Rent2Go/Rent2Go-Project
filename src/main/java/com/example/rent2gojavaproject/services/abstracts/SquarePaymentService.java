package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.services.dtos.requests.payments.PaymentRequest;
import com.example.rent2gojavaproject.services.dtos.responses.payments.SquarePaymentResponse;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.CreatePaymentResponse;

public interface SquarePaymentService {

    DataResult<CreatePaymentResponse> processPayment(PaymentRequest paymentRequest) throws Exception;

}
