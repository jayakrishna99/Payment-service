package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;

/**
 * Amount details update for payment intent
 */
public class AmountDetailsUpdate {
    private Amount amount;
    private String currency;
    
    public AmountDetailsUpdate() {
    }
    
    public AmountDetailsUpdate(Amount amount, String currency) {
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

