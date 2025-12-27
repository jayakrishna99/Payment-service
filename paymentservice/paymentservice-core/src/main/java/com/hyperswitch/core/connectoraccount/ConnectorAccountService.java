package com.hyperswitch.core.connectoraccount;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for connector account operations
 */
public interface ConnectorAccountService {
    
    /**
     * Create connector account
     */
    Mono<Result<ConnectorAccountResponse, PaymentError>> createConnectorAccount(
            ConnectorAccountCreateRequest request);
    
    /**
     * Get connector account
     */
    Mono<Result<ConnectorAccountResponse, PaymentError>> getConnectorAccount(String id);
    
    /**
     * Update connector account
     */
    Mono<Result<ConnectorAccountResponse, PaymentError>> updateConnectorAccount(
            String id,
            ConnectorAccountUpdateRequest request);
    
    /**
     * Delete connector account
     */
    Mono<Result<ConnectorAccountDeleteResponse, PaymentError>> deleteConnectorAccount(String id);
}

