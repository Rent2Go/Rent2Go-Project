package com.example.rent2gojavaproject.controllers;

import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.Result;
import com.example.rent2gojavaproject.services.abstracts.CreditCarPaymentService;
import com.example.rent2gojavaproject.services.dtos.requests.CreditCardPaymentRequest.AddCreditCardPaymentRequest;
import com.example.rent2gojavaproject.services.dtos.requests.CreditCardPaymentRequest.UpdateCreditCardPaymentRequest;
import com.example.rent2gojavaproject.services.dtos.responses.CreditCardPaymentResponse.GetAllCreditCardResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/creditcard")
@AllArgsConstructor
@CrossOrigin
public class CreditCardPaymentController {
    private  final CreditCarPaymentService paymentService;

    @GetMapping()
    public DataResult<List<GetAllCreditCardResponse>> getAll() {

      return  this.paymentService.getAll();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Result AddCreditCard(@RequestBody @Valid AddCreditCardPaymentRequest paymentRequest) {

        return this.paymentService.AddCreditCard (paymentRequest);
    }

    @PutMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Result updateCreditCard(@RequestBody  @Valid  UpdateCreditCardPaymentRequest paymentRequest) {

        return this.paymentService.updateCreditCard(paymentRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Result delete(@PathVariable int id) {
        return this.paymentService.delete(id);
    }

    @PostMapping("/checkpayment")
    public boolean existsByCardNumberAndCardHolderNameAndExpirationDateAndCvv(@RequestBody @Valid AddCreditCardPaymentRequest paymentRequest) {
        return this.paymentService.existsByCardNumberAndCardHolderNameAndExpirationDateAndCvv(paymentRequest);
    }


}
