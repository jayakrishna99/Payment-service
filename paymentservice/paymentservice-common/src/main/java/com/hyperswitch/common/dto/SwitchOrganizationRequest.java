package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for switching organization
 */
public class SwitchOrganizationRequest {
    
    @JsonProperty("org_id")
    private String orgId;
    
    // Getters and Setters
    public String getOrgId() {
        return orgId;
    }
    
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}

