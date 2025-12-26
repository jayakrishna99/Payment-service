package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response DTO for deleting tokenized data
 */
@Schema(description = "Response for deleting tokenized data")
public class DeleteTokenDataResponse {
    
    @Schema(description = "Unique identifier returned by the tokenization service", 
            example = "12345_tok_01926c58bc6e77c09e809964e72af8c8")
    @JsonProperty("id")
    private String id;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

