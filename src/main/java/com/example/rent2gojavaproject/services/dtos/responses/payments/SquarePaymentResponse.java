package com.example.rent2gojavaproject.services.dtos.responses.payments;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SquarePaymentResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("amount_money")
    private AmountMoney amountMoney;

    @JsonProperty("status")
    private String status;

    @JsonProperty("source_type")
    private String sourceType;

    @JsonProperty("card_details")
    private CardDetails cardDetails;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    // Getter ve Setter metotları

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AmountMoney getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(AmountMoney amountMoney) {
        this.amountMoney = amountMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public CardDetails getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(CardDetails cardDetails) {
        this.cardDetails = cardDetails;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

class AmountMoney {
    private Long amount;
    private String currency;

    // Getter ve Setter metotları
}

class CardDetails {
    private String cardBrand;
    private String last4;

    // Getter ve Setter metotları
}