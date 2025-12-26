package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Request DTO for Apple Pay merchant verification
 */
@Schema(description = "Request for Apple Pay merchant verification")
public class ApplePayMerchantVerificationRequest {
    
    @Schema(description = "Domain names to verify", required = true)
    @JsonProperty("domain_names")
    private List<String> domainNames;
    
    @Schema(description = "Merchant connector account ID", required = true)
    @JsonProperty("merchant_connector_account_id")
    private String merchantConnectorAccountId;

    // Getters and Setters
    public List<String> getDomainNames() {
        return domainNames;
    }

    public void setDomainNames(List<String> domainNames) {
        this.domainNames = domainNames;
    }

    public String getMerchantConnectorAccountId() {
        return merchantConnectorAccountId;
    }

    public void setMerchantConnectorAccountId(String merchantConnectorAccountId) {
        this.merchantConnectorAccountId = merchantConnectorAccountId;
    }
}

