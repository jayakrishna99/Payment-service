package com.hyperswitch.connectors;

import com.hyperswitch.common.types.Result;
import com.hyperswitch.common.errors.PaymentError;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * Service for managing and calling payment connectors
 */
public interface ConnectorService {
    
    /**
     * Authorize a payment with a connector
     */
    Mono<Result<ConnectorResponse, PaymentError>> authorize(
        String paymentId,
        Long amount,
        String currency,
        String connectorName,
        Map<String, Object> paymentMethodData
    );
    
    /**
     * Capture a payment
     */
    Mono<Result<ConnectorResponse, PaymentError>> capture(
        String paymentId,
        Long amount,
        String currency,
        String connectorName,
        String connectorTransactionId
    );
    
    /**
     * Process a refund
     */
    Mono<Result<ConnectorResponse, PaymentError>> refund(
        String paymentId,
        Long amount,
        String currency,
        String connectorName,
        String connectorTransactionId
    );
    
    /**
     * Get connector implementation by name
     */
    ConnectorInterface getConnector(String connectorName);
    
    /**
     * Get all available connectors
     */
    List<String> getAvailableConnectors();
    
    /**
     * Verify 3DS authentication
     */
    Mono<Result<ConnectorResponse, PaymentError>> verify3DS(
        String paymentId,
        String authenticationId,
        String connectorName,
        String connectorTransactionId
    );
    
    /**
     * Sync payment status with connector (psync)
     */
    Mono<Result<ConnectorResponse, PaymentError>> syncPayment(
        String connectorName,
        String connectorTransactionId
    );
    
    /**
     * Sync refund status with connector
     */
    Mono<Result<ConnectorResponse, PaymentError>> syncRefund(
        String refundId,
        String paymentId,
        String merchantId
    );
}

