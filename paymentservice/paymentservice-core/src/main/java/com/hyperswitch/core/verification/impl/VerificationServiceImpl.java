package com.hyperswitch.core.verification.impl;

import com.hyperswitch.common.dto.ApplePayMerchantResponse;
import com.hyperswitch.common.dto.ApplePayMerchantVerificationRequest;
import com.hyperswitch.common.dto.ApplePayVerifiedDomainsResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.verification.VerificationService;
import com.hyperswitch.storage.entity.ApplePayVerifiedDomainEntity;
import com.hyperswitch.storage.repository.ApplePayVerifiedDomainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of VerificationService
 */
@Service
public class VerificationServiceImpl implements VerificationService {
    
    private static final Logger log = LoggerFactory.getLogger(VerificationServiceImpl.class);
    
    private static final String APPLE_PAY_VERIFICATION_COMPLETED_MESSAGE = "Applepay verification Completed";
    
    private final ApplePayVerifiedDomainRepository applePayVerifiedDomainRepository;
    
    @Autowired
    public VerificationServiceImpl(ApplePayVerifiedDomainRepository applePayVerifiedDomainRepository) {
        this.applePayVerifiedDomainRepository = applePayVerifiedDomainRepository;
    }
    
    @Override
    public Mono<Result<ApplePayMerchantResponse, PaymentError>> registerApplePayMerchant(
            String merchantId,
            String profileId,
            ApplePayMerchantVerificationRequest request) {
        
        if (request == null) {
            log.warn("Request is null for merchant: {}", merchantId);
            return Mono.just(Result.err(PaymentError.of("INVALID_REQUEST", "Request cannot be null")));
        }
        
        if (request.getDomainNames() == null || request.getDomainNames().isEmpty()) {
            log.warn("Domain names are null or empty for merchant: {}", merchantId);
            return Mono.just(Result.err(PaymentError.of("INVALID_REQUEST", "Domain names cannot be null or empty")));
        }
        
        log.info("Registering Apple Pay merchant for merchant: {}, profile: {}, domains: {}", 
                merchantId, profileId, request.getDomainNames());
        
        // TODO: In production, integrate with Apple Pay verification API
        // For now, we simulate the verification and store the domains
        
        List<Mono<ApplePayVerifiedDomainEntity>> saveOperations = new ArrayList<>();
        
        for (String domainName : request.getDomainNames()) {
            // Check if domain already exists
            Mono<ApplePayVerifiedDomainEntity> saveOp = applePayVerifiedDomainRepository
                .findByMerchantIdAndMerchantConnectorAccountIdAndDomainName(
                    merchantId, request.getMerchantConnectorAccountId(), domainName)
                .switchIfEmpty(Mono.defer(() -> {
                    // Create new entity
                    ApplePayVerifiedDomainEntity entity = new ApplePayVerifiedDomainEntity();
                    entity.setMerchantId(merchantId);
                    entity.setProfileId(profileId);
                    entity.setMerchantConnectorAccountId(request.getMerchantConnectorAccountId());
                    entity.setDomainName(domainName);
                    entity.setCreatedAt(Instant.now());
                    entity.setUpdatedAt(Instant.now());
                    return applePayVerifiedDomainRepository.save(entity);
                }));
            
            saveOperations.add(saveOp);
        }
        
        return Flux.concat(saveOperations)
            .collectList()
            .then(Mono.defer(() -> {
                ApplePayMerchantResponse response = new ApplePayMerchantResponse();
                response.setStatusMessage(APPLE_PAY_VERIFICATION_COMPLETED_MESSAGE);
                return Mono.just(Result.<ApplePayMerchantResponse, PaymentError>ok(response));
            }))
            .onErrorResume(Throwable.class, error -> {
                log.error("Error registering Apple Pay merchant: {}", error.getMessage(), error);
                return Mono.<Result<ApplePayMerchantResponse, PaymentError>>just(
                    Result.err(PaymentError.of("APPLE_PAY_REGISTRATION_FAILED", 
                        "Failed to register Apple Pay merchant")));
            });
    }
    
    @Override
    public Mono<Result<ApplePayMerchantResponse, PaymentError>> getApplePayMerchantRegistration(
            String merchantId) {
        
        log.info("Getting Apple Pay merchant registration for merchant: {}", merchantId);
        
        // TODO: In production, retrieve actual registration status from Apple Pay
        // For now, return a default response
        
        ApplePayMerchantResponse response = new ApplePayMerchantResponse();
        response.setStatusMessage(APPLE_PAY_VERIFICATION_COMPLETED_MESSAGE);
        return Mono.just(Result.<ApplePayMerchantResponse, PaymentError>ok(response));
    }
    
    @Override
    public Mono<Result<ApplePayVerifiedDomainsResponse, PaymentError>> getApplePayVerifiedDomains(
            String merchantId,
            String merchantConnectorAccountId) {
        
        if (merchantConnectorAccountId == null || merchantConnectorAccountId.isEmpty()) {
            log.warn("Merchant connector account ID is null or empty for merchant: {}", merchantId);
            return Mono.just(Result.err(PaymentError.of("INVALID_REQUEST", 
                "Merchant connector account ID cannot be null or empty")));
        }
        
        log.info("Getting Apple Pay verified domains for merchant: {}, mca: {}", 
                merchantId, merchantConnectorAccountId);
        
        return applePayVerifiedDomainRepository
            .findDistinctDomainNamesByMerchantIdAndMerchantConnectorAccountId(
                merchantId, merchantConnectorAccountId)
            .collectList()
            .defaultIfEmpty(new ArrayList<>())
            .map(domains -> {
                ApplePayVerifiedDomainsResponse response = new ApplePayVerifiedDomainsResponse();
                response.setVerifiedDomains(domains);
                return Result.<ApplePayVerifiedDomainsResponse, PaymentError>ok(response);
            })
            .onErrorResume(Throwable.class, error -> {
                log.error("Error getting Apple Pay verified domains: {}", error.getMessage(), error);
                return Mono.just(Result.<ApplePayVerifiedDomainsResponse, PaymentError>err(
                    PaymentError.of("APPLE_PAY_DOMAINS_RETRIEVAL_FAILED", 
                        "Failed to get Apple Pay verified domains")));
            });
    }
}

