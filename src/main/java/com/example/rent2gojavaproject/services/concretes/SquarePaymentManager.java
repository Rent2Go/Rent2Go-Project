/*package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.exceptions.CardInformationNotValid;
import com.example.rent2gojavaproject.core.exceptions.NotFoundException;
import com.example.rent2gojavaproject.core.utilities.constants.MessageConstants;
import com.example.rent2gojavaproject.core.utilities.results.DataResult;
import com.example.rent2gojavaproject.core.utilities.results.SuccessDataResult;
import com.example.rent2gojavaproject.services.abstracts.SquarePaymentService;
import com.example.rent2gojavaproject.services.dtos.requests.payments.PaymentRequest;
import com.squareup.square.Environment;
import com.squareup.square.SquareClient;
import com.squareup.square.api.PaymentsApi;
import com.squareup.square.exceptions.ApiException;
import com.squareup.square.models.CreatePaymentRequest;
import com.squareup.square.models.CreatePaymentResponse;
import com.squareup.square.models.Money;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SquarePaymentManager implements SquarePaymentService {

    private final String accessToken = "EAAAl2f0lgQahVVcFYC1RFUiHNWygIcK1cL5VKzQ3Kw3_vtk0x5h0qV05pZ4Y9KY";
    private final SquareClient client = new SquareClient.Builder()
            .environment(Environment.SANDBOX)
            .accessToken(accessToken)
            .build();


    private final PaymentsApi paymentsApi = client.getPaymentsApi();
    private final String locationId = "LWHFCJXMTQZY1";

    private final  UUID uuid = UUID.randomUUID();

    @Override
    public DataResult<CreatePaymentResponse> processPayment(PaymentRequest paymentRequest) throws Exception {
        Money amountMoney = new Money.Builder()
                .amount(paymentRequest.getAmount())
                .currency("USD")
                .build();



        CreatePaymentRequest createPaymentRequest = new CreatePaymentRequest.Builder(locationId, UUID.randomUUID().toString())
                .amountMoney(new Money.Builder().amount(paymentRequest.getAmount()).currency("USD").build())
                .sourceId("cnon:card-nonce-ok")
                .build();

        try {
            CreatePaymentResponse response = paymentsApi.createPayment(createPaymentRequest);
            return new SuccessDataResult<>(response, MessageConstants.ADD.getMessage());
        } catch (ApiException | IOException e) {
            throw new CardInformationNotValid(e.getMessage());
        }
    }


}
*/