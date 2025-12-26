package com.hyperswitch.common.dto;

/**
 * Request DTO for syncing payment status with connector
 */
public class SyncPaymentRequest {
    private String paymentId; // Payment ID for v1 sync endpoint
    private Boolean forceSync; // Force sync even if recently synced
    private Boolean expandAttempts; // Include payment attempts in response

    // Getters and Setters
    public Boolean getForceSync() {
        return forceSync;
    }

    public void setForceSync(Boolean forceSync) {
        this.forceSync = forceSync;
    }

    public Boolean getExpandAttempts() {
        return expandAttempts;
    }

    public void setExpandAttempts(Boolean expandAttempts) {
        this.expandAttempts = expandAttempts;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}

