package com.hyperswitch.core.ephemeralkeys.impl;

import com.hyperswitch.common.dto.EphemeralKeyRequest;
import com.hyperswitch.common.dto.EphemeralKeyResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.ephemeralkeys.EphemeralKeyService;
import com.hyperswitch.storage.entity.EphemeralKeyEntity;
import com.hyperswitch.storage.repository.EphemeralKeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

/**
 * Implementation of EphemeralKeyService
 */
@Service
public class EphemeralKeyServiceImpl implements EphemeralKeyService {

    private static final Logger log = LoggerFactory.getLogger(EphemeralKeyServiceImpl.class);
    private static final String EPHEMERAL_KEY_NOT_FOUND_MSG = "Ephemeral key not found";
    
    @Value("${ephemeral.key.validity.hours:24}")
    private int validityHours;
    
    private final EphemeralKeyRepository repository;

    @Autowired
    public EphemeralKeyServiceImpl(EphemeralKeyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Result<EphemeralKeyResponse, PaymentError>> createEphemeralKey(
            String merchantId,
            EphemeralKeyRequest request) {
        log.info("Creating ephemeral key for merchant: {}, customer: {}", merchantId, request.getCustomerId());
        
        try {
            String id = generateEphemeralKeyId();
            String secret = generateEphemeralKeySecret();
            Instant now = Instant.now();
            Instant expiresAt = now.plusSeconds(validityHours * 3600L);
            
            EphemeralKeyEntity entity = new EphemeralKeyEntity();
            entity.setId(id);
            entity.setCustomerId(request.getCustomerId());
            entity.setMerchantId(merchantId);
            entity.setSecret(secret);
            entity.setCreatedAt(now);
            entity.setExpiresAt(expiresAt);
            
            return repository.save(entity)
                .map(this::toResponse)
                .map(Result::<EphemeralKeyResponse, PaymentError>ok)
                .onErrorResume(error -> {
                    log.error("Error creating ephemeral key", error);
                    return Mono.just(Result.<EphemeralKeyResponse, PaymentError>err(
                        PaymentError.of("EPHEMERAL_KEY_CREATE_FAILED",
                            "Failed to create ephemeral key: " + error.getMessage())
                    ));
                });
        } catch (Exception e) {
            log.error("Error creating ephemeral key", e);
            return Mono.just(Result.<EphemeralKeyResponse, PaymentError>err(
                PaymentError.of("EPHEMERAL_KEY_CREATE_FAILED",
                    "Failed to create ephemeral key: " + e.getMessage())
            ));
        }
    }

    @Override
    public Mono<Result<EphemeralKeyResponse, PaymentError>> deleteEphemeralKey(
            String merchantId,
            String ephemeralKeyId) {
        log.info("Deleting ephemeral key: {} for merchant: {}", ephemeralKeyId, merchantId);
        
        return repository.findByIdAndMerchantId(ephemeralKeyId, merchantId)
            .switchIfEmpty(Mono.error(new RuntimeException(EPHEMERAL_KEY_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                EphemeralKeyResponse response = toResponse(entity);
                return repository.delete(entity)
                    .thenReturn(Result.<EphemeralKeyResponse, PaymentError>ok(response));
            })
            .onErrorResume(error -> {
                log.error("Error deleting ephemeral key", error);
                String errorCode = EPHEMERAL_KEY_NOT_FOUND_MSG.equals(error.getMessage())
                    ? "EPHEMERAL_KEY_NOT_FOUND"
                    : "EPHEMERAL_KEY_DELETE_FAILED";
                return Mono.just(Result.<EphemeralKeyResponse, PaymentError>err(
                    PaymentError.of(errorCode,
                        "Failed to delete ephemeral key: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<EphemeralKeyResponse, PaymentError>> validateEphemeralKey(
            String merchantId,
            String secret) {
        log.debug("Validating ephemeral key for merchant: {}", merchantId);
        
        return repository.findBySecretAndMerchantId(secret, merchantId)
            .filter(entity -> entity.getExpiresAt().isAfter(Instant.now()))
            .map(this::toResponse)
            .map(Result::<EphemeralKeyResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<EphemeralKeyResponse, PaymentError>err(
                PaymentError.of("EPHEMERAL_KEY_INVALID",
                    "Ephemeral key is invalid or expired")
            )))
            .onErrorResume(error -> {
                log.error("Error validating ephemeral key", error);
                return Mono.just(Result.<EphemeralKeyResponse, PaymentError>err(
                    PaymentError.of("EPHEMERAL_KEY_VALIDATION_FAILED",
                        "Failed to validate ephemeral key: " + error.getMessage())
                ));
            });
    }

    private EphemeralKeyResponse toResponse(EphemeralKeyEntity entity) {
        EphemeralKeyResponse response = new EphemeralKeyResponse();
        response.setId(entity.getId());
        response.setCustomerId(entity.getCustomerId());
        response.setMerchantId(entity.getMerchantId());
        response.setSecret(entity.getSecret());
        response.setCreatedAt(entity.getCreatedAt());
        response.setExpiresAt(entity.getExpiresAt());
        return response;
    }

    private String generateEphemeralKeyId() {
        return "eki_" + UUID.randomUUID().toString().replace("-", "").substring(0, 24);
    }

    private String generateEphemeralKeySecret() {
        return "epk_" + UUID.randomUUID().toString().replace("-", "");
    }
}

