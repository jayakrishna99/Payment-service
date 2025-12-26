package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;

/**
 * Amount details for payment intent
 */
public class AmountDetails {
    private Amount amount;
    private String currency;
    
    public AmountDetails() {
    }
    
    public AmountDetails(Amount amount, String currency) {
        this.amount = amount;
        this.currency = currency;
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
}

