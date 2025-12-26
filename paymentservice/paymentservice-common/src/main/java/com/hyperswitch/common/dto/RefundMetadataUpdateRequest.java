package com.hyperswitch.common.dto;

import java.util.Map;

/**
 * Request DTO for updating refund metadata (v2 API)
 */
public class RefundMetadataUpdateRequest {
    private String reason;
    private Map<String, Object> metadata;
    
    public RefundMetadataUpdateRequest() {
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public Map<String, Object> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}

