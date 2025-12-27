package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for volume split routing operations
 */
public class VolumeSplitRequest {
    
    @JsonProperty("splits")
    private Map<String, Double> splits;
    
    @JsonProperty("config")
    private Map<String, Object> config;
    
    // Getters and Setters
    public Map<String, Double> getSplits() {
        return splits;
    }
    
    public void setSplits(Map<String, Double> splits) {
        this.splits = splits;
    }
    
    public Map<String, Object> getConfig() {
        return config;
    }
    
    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }
}

