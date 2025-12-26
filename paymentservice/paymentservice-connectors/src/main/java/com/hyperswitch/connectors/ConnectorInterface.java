package com.hyperswitch.connectors;

import com.hyperswitch.common.enums.Connector;
import reactor.core.publisher.Mono;

/**
 * Interface for payment processor connectors
 * Each payment processor (Stripe, Adyen, etc.) implements this interface
 */
public interface ConnectorInterface {
    
    /**
     * Get the connector name
     */
    Connector getConnector();
    
    /**
     * Authorize a payment
     */
    Mono<ConnectorResponse> authorize(ConnectorRequest request);
    
    /**
     * Capture a payment
     */
    Mono<ConnectorResponse> capture(ConnectorRequest request);
    
    /**
     * Process a refund
     */
    Mono<ConnectorResponse> refund(ConnectorRequest request);
    
    /**
     * Verify webhook signature
     */
    Mono<Boolean> verifyWebhook(String payload, String signature, String secret);
    
    /**
     * Parse webhook payload
     */
    Mono<WebhookPayload> parseWebhook(String payload);
    
    /**
     * Sync payment status with connector (psync)
     */
    Mono<ConnectorResponse> syncPayment(String connectorTransactionId);
    
    /**
     * Create a customer on the connector
     * @param customerData Customer data to create
     * @return Connector customer ID
     */
    Mono<String> createCustomer(ConnectorCustomerData customerData);
    
    /**
     * Sync dispute status with connector
     * @param connectorDisputeId The dispute ID from the connector
     * @return Latest dispute status and details from connector
     */
    Mono<ConnectorResponse> syncDispute(String connectorDisputeId);
}

