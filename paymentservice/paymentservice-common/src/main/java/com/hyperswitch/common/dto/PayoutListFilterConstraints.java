package com.hyperswitch.common.dto;

import java.time.Instant;

/**
 * Filter constraints for payout listing
 */
public class PayoutListFilterConstraints {
    private String payoutId;
    private String paymentId;
    private String merchantId;
    private String connector;
    private String status;
    private String currency;
    private Instant startTime;
    private Instant endTime;
    private Long minAmount;
    private Long maxAmount;
    private Integer limit;
    private Integer offset;
    
    public PayoutListFilterConstraints() {
    }
    
    public String getPayoutId() {
        return payoutId;
    }
    
    public void setPayoutId(String payoutId) {
        this.payoutId = payoutId;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getMerchantId() {
        return merchantId;
    }
    
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
    
    public String getConnector() {
        return connector;
    }
    
    public void setConnector(String connector) {
        this.connector = connector;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Instant getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }
    
    public Instant getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
    
    public Long getMinAmount() {
        return minAmount;
    }
    
    public void setMinAmount(Long minAmount) {
        this.minAmount = minAmount;
    }
    
    public Long getMaxAmount() {
        return maxAmount;
    }
    
    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }
    
    public Integer getLimit() {
        return limit;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    public Integer getOffset() {
        return offset;
    }
    
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}

