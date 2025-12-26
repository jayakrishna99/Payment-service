package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Request DTO for config operations
 */
@Schema(description = "Request for config operations")
public class ConfigRequest {
    
    @Schema(description = "Config key", example = "payment_timeout", required = true)
    @JsonProperty("key")
    private String key;
    
    @Schema(description = "Config value", example = "300", required = true)
    @JsonProperty("value")
    private String value;

    // Getters and Setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

