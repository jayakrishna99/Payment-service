package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Request DTO for setting dashboard metadata
 */
public class DashboardMetadataRequest {
    
    @JsonProperty("metadata")
    private Map<String, Object> metadata;
    
    // Getters and Setters
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

