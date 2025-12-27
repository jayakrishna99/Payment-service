package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for transferring keys between merchant accounts
 */
public class MerchantKeyTransferRequest {
    
    @JsonProperty("from")
    private Integer from;
    
    @JsonProperty("limit")
    private Integer limit;
    
    // Getters and Setters
    public Integer getFrom() {
        return from;
    }
    
    public void setFrom(Integer from) {
        this.from = from;
    }
    
    public Integer getLimit() {
        return limit;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

