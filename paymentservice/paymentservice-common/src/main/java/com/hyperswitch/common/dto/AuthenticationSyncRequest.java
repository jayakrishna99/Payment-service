package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for syncing authentication
 */
@Schema(description = "Request for syncing authentication")
public class AuthenticationSyncRequest {
    
    @Schema(description = "Force sync", example = "false")
    @JsonProperty("force_sync")
    private Boolean forceSync;

    // Getters and Setters
    public Boolean getForceSync() {
        return forceSync;
    }

    public void setForceSync(Boolean forceSync) {
        this.forceSync = forceSync;
    }
}

