package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for creating a payment method session (v2 API)
 */
@Schema(description = "Request to create a payment method session")
public class PaymentMethodSessionRequest {
    
    @Schema(description = "The customer ID for which the payment methods session is to be created", example = "cus_y3oqhf46pyzuxjbcn2giaqnb44")
    @JsonProperty("customer_id")
    private String customerId;
    
    @Schema(description = "The billing address details of the customer")
    @JsonProperty("billing")
    private Address billing;
    
    @Schema(description = "The return URL to which the customer should be redirected to after adding the payment method")
    @JsonProperty("return_url")
    private String returnUrl;
    
    @Schema(description = "The tokenization type to be applied")
    @JsonProperty("psp_tokenization")
    private String pspTokenization;
    
    @Schema(description = "The network tokenization configuration if applicable")
    @JsonProperty("network_tokenization")
    private Map<String, Object> networkTokenization;
    
    @Schema(description = "The time (seconds) when the session will expire. If not provided, the session will expire in 15 minutes", example = "900", defaultValue = "900")
    @JsonProperty("expires_in")
    private Integer expiresIn;
    
    @Schema(description = "Contains data to be passed on to tokenization service (if present) to create token_id for given JSON data")
    @JsonProperty("tokenization_data")
    private Map<String, Object> tokenizationData;

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

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getPspTokenization() {
        return pspTokenization;
    }

    public void setPspTokenization(String pspTokenization) {
        this.pspTokenization = pspTokenization;
    }

    public Map<String, Object> getNetworkTokenization() {
        return networkTokenization;
    }

    public void setNetworkTokenization(Map<String, Object> networkTokenization) {
        this.networkTokenization = networkTokenization;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Map<String, Object> getTokenizationData() {
        return tokenizationData;
    }

    public void setTokenizationData(Map<String, Object> tokenizationData) {
        this.tokenizationData = tokenizationData;
    }
}

