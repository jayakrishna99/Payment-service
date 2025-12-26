package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

/**
 * Response DTO for tokenization operations
 */
@Schema(description = "Response for tokenization operations")
public class TokenizationResponse {
    
    @Schema(description = "Unique identifier returned by the tokenization service", 
            example = "12345_tok_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("id")
    private String id;
    
    @Schema(description = "Created time of the tokenization id", example = "2024-02-24T11:04:09.922Z")
    @JsonProperty("created_at")
    private Instant createdAt;
    
    @Schema(description = "Status of the tokenization id created", example = "enabled")
    @JsonProperty("flag")
    private String flag;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}

