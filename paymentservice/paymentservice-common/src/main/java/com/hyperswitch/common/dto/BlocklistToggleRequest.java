package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for toggling blocklist guard
 */
@Schema(description = "Request for toggling blocklist guard")
public class BlocklistToggleRequest {
    
    @Schema(description = "Enable blocklist guard", example = "true", required = true)
    @JsonProperty("enabled")
    private Boolean enabled;

    // Getters and Setters
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}

