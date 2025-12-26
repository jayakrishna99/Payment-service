package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Request DTO for batch card info operations
 */
@Schema(description = "Request for batch card info operations")
public class BatchCardInfoRequest {
    
    @Schema(description = "List of card info requests")
    @JsonProperty("cards")
    private List<CardInfoRequest> cards;

    // Getters and Setters
    public List<CardInfoRequest> getCards() {
        return cards;
    }

    public void setCards(List<CardInfoRequest> cards) {
        this.cards = cards;
    }
}

