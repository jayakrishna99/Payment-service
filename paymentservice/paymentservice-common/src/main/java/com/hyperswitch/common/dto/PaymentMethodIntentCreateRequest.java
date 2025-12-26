package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for creating a payment method intent (v2 API)
 */
@Schema(description = "Request to create a payment method intent")
public class PaymentMethodIntentCreateRequest {
    
    @Schema(description = "The unique identifier of the customer", example = "12345_cus_01926c58bc6e77c09e809964e72af8c8", minLength = 32, maxLength = 64)
    @JsonProperty("customer_id")
    private String customerId;
    
    @Schema(description = "The billing details of the payment method")
    @JsonProperty("billing")
    private Address billing;
    
    @Schema(description = "Metadata is useful for storing additional, structured information on an object", example = "{\"city\": \"NY\", \"unit\": \"245\"}")
    @JsonProperty("metadata")
    private Map<String, Object> metadata;

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

