package com.hyperswitch.core.tokenization;

import com.hyperswitch.common.dto.DeleteTokenDataRequest;
import com.hyperswitch.common.dto.DeleteTokenDataResponse;
import com.hyperswitch.common.dto.TokenizationRequest;
import com.hyperswitch.common.dto.TokenizationResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for tokenization operations
 */
public interface TokenizationService {
    
    /**
     * Create tokenized data in vault
     */
    Mono<Result<TokenizationResponse, PaymentError>> createTokenVault(
            String merchantId, 
            TokenizationRequest request);
    
    /**
     * Delete tokenized data from vault
     */
    Mono<Result<DeleteTokenDataResponse, PaymentError>> deleteTokenizedData(
            String merchantId, 
            String tokenId,
            DeleteTokenDataRequest request);
}

