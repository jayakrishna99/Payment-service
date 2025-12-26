package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

/**
 * Request DTO for updating a payment method session (v2 API)
 */
@Schema(description = "Request to update a payment method session")
public class PaymentMethodSessionUpdateRequest {
    
    @Schema(description = "The billing address details of the customer")
    @JsonProperty("billing")
    private Address billing;
    
    @Schema(description = "The tokenization type to be applied")
    @JsonProperty("psp_tokenization")
    private String pspTokenization;
    
    @Schema(description = "The network tokenization configuration if applicable")
    @JsonProperty("network_tokenization")
    private Map<String, Object> networkTokenization;
    
    @Schema(description = "Contains data to be passed on to tokenization service (if present) to create token_id for given JSON data")
    @JsonProperty("tokenization_data")
    private Map<String, Object> tokenizationData;

    // Getters and Setters
    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
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

    public Map<String, Object> getTokenizationData() {
        return tokenizationData;
    }

    public void setTokenizationData(Map<String, Object> tokenizationData) {
        this.tokenizationData = tokenizationData;
    }
}

