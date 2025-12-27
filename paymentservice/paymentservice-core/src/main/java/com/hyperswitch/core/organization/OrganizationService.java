package com.hyperswitch.core.organization;

import com.hyperswitch.common.dto.OrganizationRequest;
import com.hyperswitch.common.dto.OrganizationResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for organization operations
 */
public interface OrganizationService {
    
    /**
     * Create organization
     */
    Mono<Result<OrganizationResponse, PaymentError>> createOrganization(OrganizationRequest request);
    
    /**
     * Get organization
     */
    Mono<Result<OrganizationResponse, PaymentError>> getOrganization(String organizationId);
    
    /**
     * Update organization
     */
    Mono<Result<OrganizationResponse, PaymentError>> updateOrganization(
            String organizationId,
            OrganizationRequest request);
    
    /**
     * List merchant accounts for organization
     */
    Mono<Result<Flux<com.hyperswitch.common.dto.MerchantAccountResponse>, PaymentError>> listMerchantAccounts(
            String organizationId);
}

