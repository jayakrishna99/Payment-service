package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

/**
 * Response DTO for conversation listing
 */
public class ConversationResponse {
    
    @JsonProperty("conversation_id")
    private String conversationId;
    
    @JsonProperty("title")
    private String title;
    
    @JsonProperty("last_message_at")
    private Instant lastMessageAt;
    
    @JsonProperty("message_count")
    private Integer messageCount;
    
    // Getters and Setters
    public String getConversationId() {
        return conversationId;
    }
    
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Instant getLastMessageAt() {
        return lastMessageAt;
    }
    
    public void setLastMessageAt(Instant lastMessageAt) {
        this.lastMessageAt = lastMessageAt;
    }
    
    public Integer getMessageCount() {
        return messageCount;
    }
    
    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }
}

