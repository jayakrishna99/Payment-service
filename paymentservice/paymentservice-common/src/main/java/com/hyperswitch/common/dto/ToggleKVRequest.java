package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for toggling KV (Key-Value) mode
 */
public class ToggleKVRequest {
    
    @JsonProperty("kv_enabled")
    private Boolean kvEnabled;
    
    // Getters and Setters
    public Boolean getKvEnabled() {
        return kvEnabled;
    }
    
    public void setKvEnabled(Boolean kvEnabled) {
        this.kvEnabled = kvEnabled;
    }
}

