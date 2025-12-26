package com.hyperswitch.common.dto;

/**
 * Request for manual refund update
 */
public class UpdateRefundRequest {
    private String status;
    private String reason;
    
    /**
     * Default constructor for JSON deserialization
     */
    public UpdateRefundRequest() {
        // Empty constructor for Jackson deserialization
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
}

