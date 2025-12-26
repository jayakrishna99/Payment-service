package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

/**
 * Request DTO for listing webhook events
 */
public class WebhookEventListRequest {
    
    @JsonProperty("event_type")
    private String eventType;
    
    @JsonProperty("connector")
    private String connector;
    
    @JsonProperty("delivery_status")
    private String deliveryStatus;
    
    @JsonProperty("start_date")
    private Instant startDate;
    
    @JsonProperty("end_date")
    private Instant endDate;
    
    @JsonProperty("limit")
    private Integer limit;
    
    @JsonProperty("offset")
    private Integer offset;
    
    // Getters and Setters
    public String getEventType() {
        return eventType;
    }
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    public String getConnector() {
        return connector;
    }
    
    public void setConnector(String connector) {
        this.connector = connector;
    }
    
    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    public Instant getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }
    
    public Instant getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }
    
    public Integer getLimit() {
        return limit;
    }
    
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    
    public Integer getOffset() {
        return offset;
    }
    
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}

