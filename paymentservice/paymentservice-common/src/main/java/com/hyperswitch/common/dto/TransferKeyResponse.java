package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for key transfer operations
 */
public class TransferKeyResponse {
    
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

