package com.hyperswitch.common.dto;

/**
 * Amount filter for payment listing
 */
public class AmountFilter {
    private Long startAmount;
    private Long endAmount;
    
    public AmountFilter() {
    }
    
    public Long getStartAmount() {
        return startAmount;
    }
    
    public void setStartAmount(Long startAmount) {
        this.startAmount = startAmount;
    }
    
    public Long getEndAmount() {
        return endAmount;
    }
    
    public void setEndAmount(Long endAmount) {
        this.endAmount = endAmount;
    }
}

