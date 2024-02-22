package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessResult;
import com.example.rent2gojavaproject.models.CreditCardPayment;
import com.example.rent2gojavaproject.repositories.CreditCardPaymentRepository;
import com.example.rent2gojavaproject.services.abstracts.CreditCarPaymentService;
import com.example.rent2gojavaproject.services.dtos.requests.CreditCardPaymentRequest.AddCreditCardPaymentRequest;
import com.example.rent2gojavaproject.services.dtos.requests.CreditCardPaymentRequest.UpdateCreditCardPaymentRequest;
import com.example.rent2gojavaproject.services.dtos.responses.CreditCardPaymentResponse.GetAllCreditCardResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreditCardPaymentManager implements CreditCarPaymentService {
    private final CreditCardPaymentRepository paymentRepository;
    private final ModelMapperService mapperService;

    @Override
    public DataResult<List<GetAllCreditCardResponse>> getAll() {
        List<CreditCardPayment> creditCardPayment = this.paymentRepository.findAll();
        List<GetAllCreditCardResponse> cardResponses = new ArrayList<>();

        cardResponses = creditCardPayment.stream()
                .map(payment -> this.mapperService.forResponse()
                        .map(payment, GetAllCreditCardResponse.class))
                .collect(Collectors.toList());


        return new SuccessDataResult<>(cardResponses, MessageConstants.PAYMENT.GET_ALL.getMessage());
    }

    @Override
    public Result AddCreditCard(AddCreditCardPaymentRequest paymentRequest) {
        CreditCardPayment creditCardPayment = this.mapperService.forRequest().map(paymentRequest, CreditCardPayment.class);
        this.paymentRepository.save(creditCardPayment);

        return new SuccessResult(MessageConstants.PAYMENT.ADD.getMessage());
    }

    @Override
    public Result updateCreditCard(UpdateCreditCardPaymentRequest paymentRequest) {
        this.paymentRepository.findById(paymentRequest.getId())
                .orElseThrow(() -> new NotFoundException(MessageConstants.PAYMENT.getMessage()));

        CreditCardPayment creditCardPayment = this.mapperService.forRequest().map(paymentRequest, CreditCardPayment.class);
            this.paymentRepository.save(creditCardPayment);

        return new SuccessResult(MessageConstants.PAYMENT.UPDATE.getMessage());
    }

    @Override
    public Result delete(int id) {
        this.paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageConstants.PAYMENT.getMessage()));
        this.paymentRepository.deleteById(id);
        return new SuccessResult(MessageConstants.PAYMENT.DELETE.getMessage());
    }

    @Override
    public boolean existsByCardNumberAndCardHolderNameAndExpirationDateAndCvv(AddCreditCardPaymentRequest paymentRequest) {
        return this.paymentRepository
                .existsByCardNumberAndCardHolderNameAndExpirationMonthAndExpirationYearAndCvv(paymentRequest.getCardNumber(),
                        paymentRequest.getCardHolderName(),
                        paymentRequest.getExpirationMonth(),
                        paymentRequest.getExpirationYear(),
                        paymentRequest.getCvv());
    }
}
