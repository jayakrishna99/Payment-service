package com.hyperswitch.core.connectors;

import com.hyperswitch.common.dto.ConnectorVerifyRequest;
import com.hyperswitch.common.dto.ConnectorVerifyResponse;
import com.hyperswitch.common.dto.MerchantConnectorAccountRequest;
import com.hyperswitch.common.dto.MerchantConnectorAccountResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service for managing merchant connector accounts
 */
public interface MerchantConnectorAccountService {
    
    /**
     * Create a new merchant connector account
     */
    Mono<Result<MerchantConnectorAccountResponse, PaymentError>> createConnectorAccount(
        String merchantId,
        MerchantConnectorAccountRequest request
    );
    
    /**
     * Get a merchant connector account by ID
     */
    Mono<Result<MerchantConnectorAccountResponse, PaymentError>> getConnectorAccount(
        String merchantId,
        String merchantConnectorId
    );
    
    /**
     * List all connector accounts for a merchant
     */
    Mono<Result<Flux<MerchantConnectorAccountResponse>, PaymentError>> listConnectorAccounts(String merchantId);
    
    /**
     * Update a merchant connector account
     */
    Mono<Result<MerchantConnectorAccountResponse, PaymentError>> updateConnectorAccount(
        String merchantId,
        String merchantConnectorId,
        MerchantConnectorAccountRequest request
    );
    
    /**
     * Delete a merchant connector account
     */
    Mono<Result<Void, PaymentError>> deleteConnectorAccount(
        String merchantId,
        String merchantConnectorId
    );
    
    /**
     * Verify connector account credentials
     */
    Mono<Result<ConnectorVerifyResponse, PaymentError>> verifyConnector(ConnectorVerifyRequest request);
}

