package com.hyperswitch.core.connectors;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service for real connector API integrations
 * Handles actual API calls to payment connectors (Stripe, PayPal, etc.)
 */
public interface ConnectorApiService {
    
    /**
     * Create connector session for payment
     * POST /api/payments/{payment_id}/connector_session
     */
    Mono<Result<ConnectorSessionResponse, PaymentError>> createConnectorSession(
            String paymentId,
            ConnectorSessionRequest request);
    
    /**
     * Create connector session for payment (without payment ID in path)
     * POST /api/payments/connector_session
     */
    Mono<Result<ConnectorSessionResponse, PaymentError>> createConnectorSession(
            ConnectorSessionRequest request);
    
    /**
     * Execute payment through connector
     */
    Mono<Result<ConnectorPaymentResponse, PaymentError>> executePayment(
            String paymentId,
            ConnectorPaymentRequest request);
    
    /**
     * Authorize payment through connector
     */
    Mono<Result<ConnectorAuthorizationResponse, PaymentError>> authorizePayment(
            String paymentId,
            ConnectorAuthorizationRequest request);
    
    /**
     * Capture payment through connector
     */
    Mono<Result<ConnectorCaptureResponse, PaymentError>> capturePayment(
            String paymentId,
            ConnectorCaptureRequest request);
    
    /**
     * Process refund through connector
     */
    Mono<Result<ConnectorRefundResponse, PaymentError>> processRefund(
            String refundId,
            ConnectorRefundRequest request);
    
    /**
     * Get connector payment status
     */
    Mono<Result<ConnectorPaymentStatusResponse, PaymentError>> getPaymentStatus(
            String paymentId,
            String connectorName);
    
    /**
     * Sync payment status from connector
     */
    Mono<Result<ConnectorPaymentStatusResponse, PaymentError>> syncPaymentStatus(
            String paymentId,
            String connectorName);
}

