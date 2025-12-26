package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.WebhookEventType;
import java.time.Instant;
import java.util.Map;

/**
 * Webhook event DTO
 */
public class WebhookEvent {
    private String eventId;
    private WebhookEventType eventType;
    private String paymentId;
    private String merchantId;
    private String connector;
    private Map<String, Object> data;
    private Instant createdAt;
    private Instant deliveredAt;
    private String deliveryStatus; // PENDING, DELIVERED, FAILED

    // Getters and Setters
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public WebhookEventType getEventType() {
        return eventType;
    }

    public void setEventType(WebhookEventType eventType) {
        this.eventType = eventType;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Instant deliveredAt) {
        this.deliveredAt = deliveredAt;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}

