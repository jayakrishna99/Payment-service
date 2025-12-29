package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for user key transfer
 */
public class UserKeyTransferResponse {
    
    @JsonProperty("total_transferred")
    private Integer totalTransferred;
    
    // Getters and Setters
    public Integer getTotalTransferred() {
        return totalTransferred;
    }
    
    public void setTotalTransferred(Integer totalTransferred) {
        this.totalTransferred = totalTransferred;
    }
}

