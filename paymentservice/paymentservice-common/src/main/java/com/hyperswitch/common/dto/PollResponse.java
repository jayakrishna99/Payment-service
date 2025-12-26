package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Response DTO for poll status
 */
@Schema(description = "Response for poll status")
public class PollResponse {
    
    @Schema(description = "Poll ID", example = "poll_123456")
    @JsonProperty("poll_id")
    private String pollId;
    
    @Schema(description = "Poll status", example = "PENDING")
    @JsonProperty("status")
    private String status;

    // Getters and Setters
    public String getPollId() {
        return pollId;
    }

    public void setPollId(String pollId) {
        this.pollId = pollId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

