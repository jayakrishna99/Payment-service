package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for tax calculation
 */
@Schema(description = "Request for tax calculation")
public class TaxCalculationRequest {
    
    @Schema(description = "Billing address for tax calculation")
    @JsonProperty("billing_address")
    private Address billingAddress;
    
    @Schema(description = "Shipping address for tax calculation")
    @JsonProperty("shipping_address")
    private Address shippingAddress;
    
    @Schema(description = "Additional tax calculation parameters")
    @JsonProperty("parameters")
    private Map<String, Object> parameters;

    // Getters and Setters
    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}

