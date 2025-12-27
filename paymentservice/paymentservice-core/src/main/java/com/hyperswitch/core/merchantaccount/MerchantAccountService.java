package com.hyperswitch.core.merchantaccount;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for merchant account operations
 */
public interface MerchantAccountService {
    
    /**
     * Create merchant account (v2)
     */
    Mono<Result<MerchantAccountResponse, PaymentError>> createMerchantAccount(
            String organizationId,
            MerchantAccountCreateRequest request);
    
    /**
     * Create merchant account (v1)
     */
    Mono<Result<MerchantAccountResponse, PaymentError>> createMerchantAccountV1(
            MerchantAccountCreateV1Request request);
    
    /**
     * Get merchant account
     */
    Mono<Result<MerchantAccountResponse, PaymentError>> getMerchantAccount(String merchantId);
    
    /**
     * Update merchant account
     */
    Mono<Result<MerchantAccountResponse, PaymentError>> updateMerchantAccount(
            String merchantId,
            MerchantAccountUpdateRequest request);
    
    /**
     * Delete merchant account (v1 only)
     */
    Mono<Result<Void, PaymentError>> deleteMerchantAccount(String merchantId);
    
    /**
     * List merchant accounts (v1 only)
     */
    Mono<Result<Flux<MerchantAccountResponse>, PaymentError>> listMerchantAccounts();
    
    /**
     * List profiles for merchant account
     */
    Mono<Result<Flux<com.hyperswitch.common.dto.ProfileResponse>, PaymentError>> listProfiles(
            String merchantId);
    
    /**
     * Toggle KV mode for merchant account
     */
    Mono<Result<ToggleKVResponse, PaymentError>> toggleKV(
            String merchantId,
            ToggleKVRequest request);
    
    /**
     * Get KV status for merchant account
     */
    Mono<Result<ToggleKVResponse, PaymentError>> getKVStatus(String merchantId);
    
    /**
     * Transfer keys between merchant accounts
     */
    Mono<Result<TransferKeyResponse, PaymentError>> transferKeys(
            MerchantKeyTransferRequest request);
    
    /**
     * Toggle KV for all merchant accounts
     */
    Mono<Result<ToggleKVResponse, PaymentError>> toggleAllKV(ToggleKVRequest request);
    
    /**
     * Enable platform account
     */
    Mono<Result<MerchantAccountResponse, PaymentError>> enablePlatformAccount(String merchantId);
}

