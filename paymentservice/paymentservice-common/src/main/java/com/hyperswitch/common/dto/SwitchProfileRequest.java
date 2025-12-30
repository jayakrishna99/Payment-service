package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request DTO for switching profile
 */
public class SwitchProfileRequest {
    
    @JsonProperty("profile_id")
    private String profileId;
    
    // Getters and Setters
    public String getProfileId() {
        return profileId;
    }
    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }
}

