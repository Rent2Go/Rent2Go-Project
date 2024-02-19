package com.example.rent2gojavaproject.repositories;

import com.example.rent2gojavaproject.models.CreditCardPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CreditCardPaymentRepository extends JpaRepository<CreditCardPayment,Integer> {
/*
    @Query("SELECT c FROM CreditCardPayment c" +
            " WHERE c.cardNumber = :cardNumber " +
            "AND c.cardHolderName = :cardHolderName " +
            "AND c.expirationMonth = :expirationMonth " +
            "AND c.expirationYear = :expirationYear " +
            "AND c.cvv = :cvv")
    Optional<CreditCardPayment> findByCardNumberAndCardHolderNameAndExpirationDateAndCvv
            (@Param("cardNumber") String cardNumber,
             @Param("cardHolderName") String cardHolderName,
             @Param("expirationDate") String expirationMonth,
             @Param("expirationDate") String expirationYear,
             @Param("cvv") String cvv);
*/


    boolean existsByCardNumberAndCardHolderNameAndExpirationMonthAndExpirationYearAndCvv(String cardNumber, String cardHolderName, String expirationMonth, String expirationYear, String cvv);
}
