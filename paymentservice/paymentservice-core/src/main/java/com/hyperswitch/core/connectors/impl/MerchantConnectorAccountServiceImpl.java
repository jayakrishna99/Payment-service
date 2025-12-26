package com.hyperswitch.core.connectors.impl;

import com.hyperswitch.common.dto.ConnectorVerifyRequest;
import com.hyperswitch.common.dto.ConnectorVerifyResponse;
import com.hyperswitch.common.dto.MerchantConnectorAccountRequest;
import com.hyperswitch.common.dto.MerchantConnectorAccountResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.connectors.MerchantConnectorAccountService;
import com.hyperswitch.connectors.ConnectorService;
import com.hyperswitch.storage.entity.MerchantConnectorAccountEntity;
import com.hyperswitch.storage.repository.MerchantConnectorAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

/**
 * Implementation of MerchantConnectorAccountService
 */
@Service
public class MerchantConnectorAccountServiceImpl implements MerchantConnectorAccountService {

    private static final Logger log = LoggerFactory.getLogger(MerchantConnectorAccountServiceImpl.class);
    private static final String CONNECTOR_ACCOUNT_NOT_FOUND_MSG = "Merchant connector account not found";
    private static final String DEFAULT_STATUS = "active";
    private static final String DEFAULT_VERSION = "v1";

    private final MerchantConnectorAccountRepository repository;
    private final ConnectorService connectorService;

    @Autowired
    public MerchantConnectorAccountServiceImpl(
            MerchantConnectorAccountRepository repository,
            ConnectorService connectorService) {
        this.repository = repository;
        this.connectorService = connectorService;
    }

    @Override
    public Mono<Result<MerchantConnectorAccountResponse, PaymentError>> createConnectorAccount(
            String merchantId,
            MerchantConnectorAccountRequest request) {
        log.info("Creating connector account for merchant: {}, connector: {}", merchantId, request.getConnectorName());

        String merchantConnectorId = generateMerchantConnectorId(merchantId, request.getConnectorName(), request.getProfileId());
        
        // Check if connector account already exists
        return repository.existsByMerchantConnectorId(merchantConnectorId)
            .flatMap(exists -> {
                if (Boolean.TRUE.equals(exists)) {
                    return Mono.just(Result.<MerchantConnectorAccountResponse, PaymentError>err(
                        PaymentError.of("CONNECTOR_ACCOUNT_EXISTS", 
                            "Connector account already exists for this merchant and connector")
                    ));
                }
                
                MerchantConnectorAccountEntity entity = new MerchantConnectorAccountEntity();
                entity.setId(UUID.randomUUID().toString());
                entity.setMerchantId(merchantId);
                entity.setMerchantConnectorId(merchantConnectorId);
                entity.setConnectorName(request.getConnectorName());
                entity.setTestMode(request.getTestMode() != null ? request.getTestMode() : false);
                entity.setDisabled(request.getDisabled() != null ? request.getDisabled() : false);
                entity.setPaymentMethodsEnabled(request.getPaymentMethodsEnabled());
                entity.setConnectorType(request.getConnectorType());
                entity.setMetadata(request.getMetadata());
                entity.setConnectorLabel(request.getConnectorLabel());
                entity.setBusinessCountry(request.getBusinessCountry());
                entity.setBusinessLabel(request.getBusinessLabel());
                entity.setBusinessSubLabel(request.getBusinessSubLabel());
                entity.setFrmConfigs(request.getFrmConfigs());
                entity.setConnectorWebhookDetails(request.getConnectorWebhookDetails());
                entity.setProfileId(request.getProfileId());
                entity.setApplepayVerifiedDomains(request.getApplepayVerifiedDomains());
                entity.setPmAuthConfig(request.getPmAuthConfig());
                entity.setStatus(request.getStatus() != null ? request.getStatus() : DEFAULT_STATUS);
                entity.setVersion(DEFAULT_VERSION);
                entity.setCreatedAt(Instant.now());
                entity.setModifiedAt(Instant.now());
                
                // Serialize connector account details to bytes (simplified - in production use proper serialization)
                if (request.getConnectorAccountDetails() != null) {
                    entity.setConnectorAccountDetails(
                        request.getConnectorAccountDetails().toString().getBytes()
                    );
                }
                
                return repository.save(entity)
                    .map(this::toResponse)
                    .map(Result::<MerchantConnectorAccountResponse, PaymentError>ok);
            })
            .onErrorResume(error -> {
                log.error("Error creating connector account", error);
                return Mono.just(Result.<MerchantConnectorAccountResponse, PaymentError>err(
                    PaymentError.of("CONNECTOR_ACCOUNT_CREATE_FAILED",
                        "Failed to create connector account: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<MerchantConnectorAccountResponse, PaymentError>> getConnectorAccount(
            String merchantId,
            String merchantConnectorId) {
        log.info("Getting connector account: {} for merchant: {}", merchantConnectorId, merchantId);
        
        return repository.findByMerchantConnectorId(merchantConnectorId)
            .filter(entity -> merchantId.equals(entity.getMerchantId()))
            .map(this::toResponse)
            .map(Result::<MerchantConnectorAccountResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<MerchantConnectorAccountResponse, PaymentError>err(
                PaymentError.of("CONNECTOR_ACCOUNT_NOT_FOUND", CONNECTOR_ACCOUNT_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error getting connector account", error);
                return Mono.just(Result.<MerchantConnectorAccountResponse, PaymentError>err(
                    PaymentError.of("CONNECTOR_ACCOUNT_GET_FAILED",
                        "Failed to get connector account: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<Flux<MerchantConnectorAccountResponse>, PaymentError>> listConnectorAccounts(String merchantId) {
        log.info("Listing connector accounts for merchant: {}", merchantId);
        
        Flux<MerchantConnectorAccountResponse> accounts = repository.findByMerchantId(merchantId)
            .map(this::toResponse);
        
        return Mono.just(Result.<Flux<MerchantConnectorAccountResponse>, PaymentError>ok(accounts))
            .onErrorResume(error -> {
                log.error("Error listing connector accounts", error);
                return Mono.just(Result.<Flux<MerchantConnectorAccountResponse>, PaymentError>err(
                    PaymentError.of("CONNECTOR_ACCOUNT_LIST_FAILED",
                        "Failed to list connector accounts: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<MerchantConnectorAccountResponse, PaymentError>> updateConnectorAccount(
            String merchantId,
            String merchantConnectorId,
            MerchantConnectorAccountRequest request) {
        log.info("Updating connector account: {} for merchant: {}", merchantConnectorId, merchantId);
        
        return repository.findByMerchantConnectorId(merchantConnectorId)
            .filter(entity -> merchantId.equals(entity.getMerchantId()))
            .switchIfEmpty(Mono.error(new RuntimeException(CONNECTOR_ACCOUNT_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Update fields
                if (request.getTestMode() != null) {
                    entity.setTestMode(request.getTestMode());
                }
                if (request.getDisabled() != null) {
                    entity.setDisabled(request.getDisabled());
                }
                if (request.getPaymentMethodsEnabled() != null) {
                    entity.setPaymentMethodsEnabled(request.getPaymentMethodsEnabled());
                }
                if (request.getConnectorType() != null) {
                    entity.setConnectorType(request.getConnectorType());
                }
                if (request.getMetadata() != null) {
                    entity.setMetadata(request.getMetadata());
                }
                if (request.getConnectorLabel() != null) {
                    entity.setConnectorLabel(request.getConnectorLabel());
                }
                if (request.getBusinessCountry() != null) {
                    entity.setBusinessCountry(request.getBusinessCountry());
                }
                if (request.getBusinessLabel() != null) {
                    entity.setBusinessLabel(request.getBusinessLabel());
                }
                if (request.getBusinessSubLabel() != null) {
                    entity.setBusinessSubLabel(request.getBusinessSubLabel());
                }
                if (request.getFrmConfigs() != null) {
                    entity.setFrmConfigs(request.getFrmConfigs());
                }
                if (request.getConnectorWebhookDetails() != null) {
                    entity.setConnectorWebhookDetails(request.getConnectorWebhookDetails());
                }
                if (request.getProfileId() != null) {
                    entity.setProfileId(request.getProfileId());
                }
                if (request.getApplepayVerifiedDomains() != null) {
                    entity.setApplepayVerifiedDomains(request.getApplepayVerifiedDomains());
                }
                if (request.getPmAuthConfig() != null) {
                    entity.setPmAuthConfig(request.getPmAuthConfig());
                }
                if (request.getStatus() != null) {
                    entity.setStatus(request.getStatus());
                }
                if (request.getConnectorAccountDetails() != null) {
                    entity.setConnectorAccountDetails(
                        request.getConnectorAccountDetails().toString().getBytes()
                    );
                }
                
                entity.setModifiedAt(Instant.now());
                
                return repository.save(entity);
            })
            .map(this::toResponse)
            .map(Result::<MerchantConnectorAccountResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error updating connector account", error);
                String errorCode = CONNECTOR_ACCOUNT_NOT_FOUND_MSG.equals(error.getMessage())
                    ? "CONNECTOR_ACCOUNT_NOT_FOUND"
                    : "CONNECTOR_ACCOUNT_UPDATE_FAILED";
                return Mono.just(Result.<MerchantConnectorAccountResponse, PaymentError>err(
                    PaymentError.of(errorCode,
                        "Failed to update connector account: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<Void, PaymentError>> deleteConnectorAccount(
            String merchantId,
            String merchantConnectorId) {
        log.info("Deleting connector account: {} for merchant: {}", merchantConnectorId, merchantId);
        
        return repository.findByMerchantConnectorId(merchantConnectorId)
            .filter(entity -> merchantId.equals(entity.getMerchantId()))
            .switchIfEmpty(Mono.error(new RuntimeException(CONNECTOR_ACCOUNT_NOT_FOUND_MSG)))
            .flatMap(repository::delete)
            .thenReturn(Result.<Void, PaymentError>ok(null))
            .onErrorResume(error -> {
                log.error("Error deleting connector account", error);
                String errorCode = CONNECTOR_ACCOUNT_NOT_FOUND_MSG.equals(error.getMessage())
                    ? "CONNECTOR_ACCOUNT_NOT_FOUND"
                    : "CONNECTOR_ACCOUNT_DELETE_FAILED";
                return Mono.just(Result.<Void, PaymentError>err(
                    PaymentError.of(errorCode,
                        "Failed to delete connector account: " + error.getMessage())
                ));
            });
    }

    @Override
    public Mono<Result<ConnectorVerifyResponse, PaymentError>> verifyConnector(ConnectorVerifyRequest request) {
        log.info("Verifying connector: {} for merchant: {}", request.getConnectorName(), request.getMerchantId());
        
        // Verify connector by attempting to get connector implementation
        try {
            ConnectorService connectorService = this.connectorService;
            String connectorName = request.getConnectorName();
            
            // Check if connector is available
            if (connectorService.getAvailableConnectors().contains(connectorName.toLowerCase())) {
                // In production, this would make an actual API call to verify credentials
                // For now, we'll just check if the connector exists
                return Mono.just(Result.<ConnectorVerifyResponse, PaymentError>ok(
                    new ConnectorVerifyResponse(true, "Connector verified successfully", connectorName)
                ));
            } else {
                return Mono.just(Result.<ConnectorVerifyResponse, PaymentError>err(
                    PaymentError.of("CONNECTOR_NOT_AVAILABLE",
                        "Connector " + connectorName + " is not available")
                ));
            }
        } catch (Exception e) {
            log.error("Error verifying connector", e);
            return Mono.just(Result.<ConnectorVerifyResponse, PaymentError>err(
                PaymentError.of("CONNECTOR_VERIFY_FAILED",
                    "Failed to verify connector: " + e.getMessage())
            ));
        }
    }

    private MerchantConnectorAccountResponse toResponse(MerchantConnectorAccountEntity entity) {
        MerchantConnectorAccountResponse response = new MerchantConnectorAccountResponse();
        response.setId(entity.getId());
        response.setMerchantId(entity.getMerchantId());
        response.setMerchantConnectorId(entity.getMerchantConnectorId());
        response.setConnectorName(entity.getConnectorName());
        response.setTestMode(entity.getTestMode());
        response.setDisabled(entity.getDisabled());
        response.setPaymentMethodsEnabled(entity.getPaymentMethodsEnabled());
        response.setConnectorType(entity.getConnectorType());
        response.setMetadata(entity.getMetadata());
        response.setConnectorLabel(entity.getConnectorLabel());
        response.setBusinessCountry(entity.getBusinessCountry());
        response.setBusinessLabel(entity.getBusinessLabel());
        response.setBusinessSubLabel(entity.getBusinessSubLabel());
        response.setFrmConfigs(entity.getFrmConfigs());
        response.setConnectorWebhookDetails(entity.getConnectorWebhookDetails());
        response.setProfileId(entity.getProfileId());
        response.setApplepayVerifiedDomains(entity.getApplepayVerifiedDomains());
        response.setPmAuthConfig(entity.getPmAuthConfig());
        response.setStatus(entity.getStatus());
        response.setVersion(entity.getVersion());
        response.setCreatedAt(entity.getCreatedAt());
        response.setModifiedAt(entity.getModifiedAt());
        return response;
    }

    private String generateMerchantConnectorId(String merchantId, String connectorName, String profileId) {
        String base = merchantId + "_" + connectorName.toLowerCase();
        if (profileId != null && !profileId.isEmpty()) {
            base += "_" + profileId;
        }
        return base + "_" + UUID.randomUUID().toString().substring(0, 8);
    }
}

