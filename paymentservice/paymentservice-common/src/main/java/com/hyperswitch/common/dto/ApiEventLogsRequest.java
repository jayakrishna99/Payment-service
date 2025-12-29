package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for API event logs
 */
public class ApiEventLogsRequest {
    
    @JsonProperty("type")
    private String type; // Payment, Refund, Dispute
    
    @JsonProperty("payment_id")
    private String paymentId;
    
    @JsonProperty("refund_id")
    private String refundId;
    
    @JsonProperty("dispute_id")
    private String disputeId;
    
    // Getters and Setters
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    
    public String getRefundId() {
        return refundId;
    }
    
    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }
    
    public String getDisputeId() {
        return disputeId;
    }
    
    public void setDisputeId(String disputeId) {
        this.disputeId = disputeId;
    }
}

