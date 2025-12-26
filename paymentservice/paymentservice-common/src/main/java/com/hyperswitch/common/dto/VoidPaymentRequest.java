package com.hyperswitch.common.dto;

/**
 * Request DTO for voiding a payment
 */
public class VoidPaymentRequest {
    private String cancellationReason; // Optional reason for voiding

    // Getters and Setters
    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}

