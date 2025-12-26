package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;

/**
 * Amount details response for payment intent
 */
public class AmountDetailsResponse {
    private Amount amount;
    private String currency;
    private Long amountCaptured;
    private Long amountAuthorized;
    
    public AmountDetailsResponse() {
    }
    
    public Amount getAmount() {
        return amount;
    }
    
    public void setAmount(Amount amount) {
        this.amount = amount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Long getAmountCaptured() {
        return amountCaptured;
    }
    
    public void setAmountCaptured(Long amountCaptured) {
        this.amountCaptured = amountCaptured;
    }
    
    public Long getAmountAuthorized() {
        return amountAuthorized;
    }
    
    public void setAmountAuthorized(Long amountAuthorized) {
        this.amountAuthorized = amountAuthorized;
    }
}

