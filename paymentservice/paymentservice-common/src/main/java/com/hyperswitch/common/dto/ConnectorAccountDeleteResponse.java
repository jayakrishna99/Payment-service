package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response DTO for connector account deletion
 */
public class ConnectorAccountDeleteResponse {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("deleted")
    private Boolean deleted;
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Boolean getDeleted() {
        return deleted;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}

