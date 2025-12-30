package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * Response DTO for dashboard metadata
 */
public class DashboardMetadataResponse {
    
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

