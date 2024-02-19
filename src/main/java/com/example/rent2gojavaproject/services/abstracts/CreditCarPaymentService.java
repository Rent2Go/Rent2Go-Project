package com.example.rent2gojavaproject.services.abstracts;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.dtos.requests.CreditCardPaymentRequest.AddCreditCardPaymentRequest;
import com.example.rent2gojavaproject.services.dtos.requests.CreditCardPaymentRequest.UpdateCreditCardPaymentRequest;
import com.example.rent2gojavaproject.services.dtos.responses.CreditCardPaymentResponse.GetAllCreditCardResponse;

import java.util.List;

public interface CreditCarPaymentService {

    DataResult<List<GetAllCreditCardResponse>> getAll();

    Result AddCreditCard(AddCreditCardPaymentRequest paymentRequest);

    Result updateCreditCard(UpdateCreditCardPaymentRequest paymentRequest);

    Result delete(int id);

    //DataResult<CreditCarPaymentService> findByCardNumberAndCardHolderNameAndExpirationDateAndCvv(AddCreditCardPaymentRequest paymentRequest );
    boolean existsByCardNumberAndCardHolderNameAndExpirationDateAndCvv(AddCreditCardPaymentRequest paymentRequest );
}