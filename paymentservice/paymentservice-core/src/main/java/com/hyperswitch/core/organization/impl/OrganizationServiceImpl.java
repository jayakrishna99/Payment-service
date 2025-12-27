package com.hyperswitch.core.organization.impl;

import com.hyperswitch.common.dto.OrganizationRequest;
import com.hyperswitch.common.dto.OrganizationResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.organization.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

/**
 * Implementation of OrganizationService
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    
    private static final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);
    
    @Override
    public Mono<Result<OrganizationResponse, PaymentError>> createOrganization(OrganizationRequest request) {
        log.info("Creating organization: {}", request.getName());
        
        return Mono.fromCallable(() -> {
            String organizationId = "org_" + UUID.randomUUID().toString().replace("-", "");
            
            OrganizationResponse response = new OrganizationResponse();
            response.setOrganizationId(organizationId);
            response.setName(request.getName());
            response.setDescription(request.getDescription());
            response.setMetadata(request.getMetadata());
            response.setCreatedAt(Instant.now());
            response.setUpdatedAt(Instant.now());
            
            // In production, this would:
            // 1. Validate organization data
            // 2. Store organization in database
            // 3. Create default settings and configurations
            // 4. Return created organization
            
            return Result.<OrganizationResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error creating organization: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("ORGANIZATION_CREATE_FAILED",
                "Failed to create organization: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<OrganizationResponse, PaymentError>> getOrganization(String organizationId) {
        log.info("Getting organization: {}", organizationId);
        
        return Mono.fromCallable(() -> {
            // In production, this would query organization from database
            OrganizationResponse response = new OrganizationResponse();
            response.setOrganizationId(organizationId);
            response.setName("Sample Organization");
            response.setDescription("Sample organization description");
            response.setCreatedAt(Instant.now());
            response.setUpdatedAt(Instant.now());
            
            return Result.<OrganizationResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting organization: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("ORGANIZATION_RETRIEVAL_FAILED",
                "Failed to get organization: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<OrganizationResponse, PaymentError>> updateOrganization(
            String organizationId,
            OrganizationRequest request) {
        log.info("Updating organization: {}", organizationId);
        
        return Mono.fromCallable(() -> {
            OrganizationResponse response = new OrganizationResponse();
            response.setOrganizationId(organizationId);
            response.setName(request.getName());
            response.setDescription(request.getDescription());
            response.setMetadata(request.getMetadata());
            response.setUpdatedAt(Instant.now());
            
            // In production, this would:
            // 1. Validate organization exists
            // 2. Update organization in database
            // 3. Return updated organization
            
            return Result.<OrganizationResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error updating organization: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("ORGANIZATION_UPDATE_FAILED",
                "Failed to update organization: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<Flux<com.hyperswitch.common.dto.MerchantAccountResponse>, PaymentError>> listMerchantAccounts(
            String organizationId) {
        log.info("Listing merchant accounts for organization: {}", organizationId);
        
        return Mono.fromCallable(() -> {
            // In production, this would query merchant accounts from database
            com.hyperswitch.common.dto.MerchantAccountResponse response = 
                new com.hyperswitch.common.dto.MerchantAccountResponse();
            response.setMerchantId("merchant_" + UUID.randomUUID().toString().substring(0, 8));
            response.setOrganizationId(organizationId);
            response.setCreatedAt(Instant.now());
            
            return Result.<Flux<com.hyperswitch.common.dto.MerchantAccountResponse>, PaymentError>ok(Flux.just(response));
        })
        .onErrorResume(error -> {
            log.error("Error listing merchant accounts: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("MERCHANT_ACCOUNT_LIST_FAILED",
                "Failed to list merchant accounts: " + error.getMessage())));
        });
    }
}

