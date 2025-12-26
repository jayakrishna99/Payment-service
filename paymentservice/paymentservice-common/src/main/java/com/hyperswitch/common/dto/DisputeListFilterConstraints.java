package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.DisputeStage;
import com.hyperswitch.common.types.DisputeStatus;

import java.time.Instant;

/**
 * Filter constraints for dispute listing
 */
public class DisputeListFilterConstraints {
    private String disputeId;
    private String paymentId;
    private String merchantId;
    private String connector;
    private DisputeStatus disputeStatus;
    private DisputeStage disputeStage;
    private String reason;
    private String currency;
    private Instant receivedTime;
    private Instant receivedTimeLt;
    private Instant receivedTimeGt;
    private Instant receivedTimeLte;
    private Instant receivedTimeGte;
    private Integer limit;
    private Integer offset;
    
    public DisputeListFilterConstraints() {
    }
    
    public String getDisputeId() {
        return disputeId;
    }
    
    public void setDisputeId(String disputeId) {
        this.disputeId = disputeId;
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
    
    public DisputeStatus getDisputeStatus() {
        return disputeStatus;
    }
    
    public void setDisputeStatus(DisputeStatus disputeStatus) {
        this.disputeStatus = disputeStatus;
    }
    
    public DisputeStage getDisputeStage() {
        return disputeStage;
    }
    
    public void setDisputeStage(DisputeStage disputeStage) {
        this.disputeStage = disputeStage;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    public Instant getReceivedTime() {
        return receivedTime;
    }
    
    public void setReceivedTime(Instant receivedTime) {
        this.receivedTime = receivedTime;
    }
    
    public Instant getReceivedTimeLt() {
        return receivedTimeLt;
    }
    
    public void setReceivedTimeLt(Instant receivedTimeLt) {
        this.receivedTimeLt = receivedTimeLt;
    }
    
    public Instant getReceivedTimeGt() {
        return receivedTimeGt;
    }
    
    public void setReceivedTimeGt(Instant receivedTimeGt) {
        this.receivedTimeGt = receivedTimeGt;
    }
    
    public Instant getReceivedTimeLte() {
        return receivedTimeLte;
    }
    
    public void setReceivedTimeLte(Instant receivedTimeLte) {
        this.receivedTimeLte = receivedTimeLte;
    }
    
    public Instant getReceivedTimeGte() {
        return receivedTimeGte;
    }
    
    public void setReceivedTimeGte(Instant receivedTimeGte) {
        this.receivedTimeGte = receivedTimeGte;
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

