package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.Amount;
import java.time.Instant;
import java.util.Map;

/**
 * Request DTO for creating a payment session
 */
public class CreatePaymentSessionRequest {
    private Amount amount;
    private String merchantId;
    private String customerId;
    private String paymentMethodId;
    private Map<String, Object> metadata;
    private Instant expiresAt; // Optional: defaults to 24 hours from now

    // Getters and Setters
    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}

