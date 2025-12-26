package com.hyperswitch.core.paymentmethods.impl;

import com.hyperswitch.common.dto.NetworkTokenStatusResponse;
import com.hyperswitch.common.dto.PaymentMethodRequest;
import com.hyperswitch.common.dto.PaymentMethodResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.CustomerId;
import com.hyperswitch.common.types.PaymentMethodId;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.paymentmethods.PaymentMethodService;
import com.hyperswitch.storage.entity.PaymentMethodEntity;
import com.hyperswitch.storage.repository.CustomerRepository;
import com.hyperswitch.storage.repository.PaymentMethodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Implementation of PaymentMethodService
 */
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private static final Logger log = LoggerFactory.getLogger(PaymentMethodServiceImpl.class);
    private static final String PAYMENT_METHOD_NOT_FOUND_MSG = "Payment method not found";
    private static final String CUSTOMER_NOT_FOUND_MSG = "Customer not found";
    private static final String DEFAULT_STATUS = "active";

    private final PaymentMethodRepository paymentMethodRepository;
    private final CustomerRepository customerRepository;
    private final PaymentMethodMapper paymentMethodMapper;
    private final com.hyperswitch.storage.repository.PaymentIntentRepository paymentIntentRepository;

    @Autowired
    public PaymentMethodServiceImpl(
            PaymentMethodRepository paymentMethodRepository,
            CustomerRepository customerRepository,
            PaymentMethodMapper paymentMethodMapper,
            com.hyperswitch.storage.repository.PaymentIntentRepository paymentIntentRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.customerRepository = customerRepository;
        this.paymentMethodMapper = paymentMethodMapper;
        this.paymentIntentRepository = paymentIntentRepository;
    }

    @SuppressWarnings("null")
    @Override
    public Mono<Result<PaymentMethodResponse, PaymentError>> createPaymentMethod(PaymentMethodRequest request) {
        log.info("Creating payment method for customer: {}", request.getCustomerId());
        
        // Verify customer exists
        return customerRepository.findByCustomerId(request.getCustomerId().getValue())
            .switchIfEmpty(Mono.error(new RuntimeException(CUSTOMER_NOT_FOUND_MSG)))
            .flatMap(customer -> {
                PaymentMethodId paymentMethodId = PaymentMethodId.generate();
                
                PaymentMethodEntity entity = PaymentMethodEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .paymentMethodId(paymentMethodId.getValue())
                    .customerId(request.getCustomerId().getValue())
                    .merchantId(request.getMerchantId().getValue())
                    .paymentMethodType(request.getPaymentMethodType())
                    .paymentMethodSubtype(request.getPaymentMethodSubtype())
                    .paymentMethodData(request.getPaymentMethodData())
                    .lockerId(request.getLockerId())
                    .status(DEFAULT_STATUS)
                    .connectorMandateDetails(request.getConnectorMandateDetails())
                    .networkTransactionId(request.getNetworkTransactionId())
                    .clientSecret(request.getClientSecret())
                    .createdAt(Instant.now())
                    .modifiedAt(Instant.now())
                    .build();
                
                return paymentMethodRepository.save(entity);
            })
            .map(paymentMethodMapper::toPaymentMethodResponse)
            .map(Result::<PaymentMethodResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error creating payment method", error);
                String errorMessage = error.getMessage();
                String errorCode = CUSTOMER_NOT_FOUND_MSG.equals(errorMessage) 
                    ? "CUSTOMER_NOT_FOUND" 
                    : "PAYMENT_METHOD_CREATE_FAILED";
                return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(PaymentError.of(
                    errorCode,
                    "Failed to create payment method: " + errorMessage
                )));
            });
    }

    @Override
    public Mono<Result<PaymentMethodResponse, PaymentError>> getPaymentMethod(PaymentMethodId paymentMethodId) {
        log.info("Getting payment method: {}", paymentMethodId);
        
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId.getValue())
            .map(paymentMethodMapper::toPaymentMethodResponse)
            .map(Result::<PaymentMethodResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<PaymentMethodResponse, PaymentError>err(
                PaymentError.of("PAYMENT_METHOD_NOT_FOUND", PAYMENT_METHOD_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error getting payment method", error);
                return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(PaymentError.of(
                    "PAYMENT_METHOD_GET_FAILED",
                    "Failed to get payment method: " + error.getMessage()
                )));
            });
    }

    @Override
    public Mono<Result<Flux<PaymentMethodResponse>, PaymentError>> listCustomerPaymentMethods(CustomerId customerId) {
        log.info("Listing payment methods for customer: {}", customerId);
        
        Flux<PaymentMethodResponse> paymentMethods = paymentMethodRepository
            .findByCustomerIdOrderByCreatedAtDesc(customerId.getValue())
            .map(paymentMethodMapper::toPaymentMethodResponse);
        
        return Mono.just(Result.<Flux<PaymentMethodResponse>, PaymentError>ok(paymentMethods))
            .onErrorResume(error -> {
                log.error("Error listing payment methods", error);
                return Mono.just(Result.<Flux<PaymentMethodResponse>, PaymentError>err(PaymentError.of(
                    "PAYMENT_METHOD_LIST_FAILED",
                    "Failed to list payment methods: " + error.getMessage()
                )));
            });
    }

    @Override
    public Mono<Result<Void, PaymentError>> setDefaultPaymentMethod(CustomerId customerId, PaymentMethodId paymentMethodId) {
        log.info("Setting default payment method {} for customer {}", paymentMethodId, customerId);
        
        // Verify payment method exists and belongs to customer
        String customerIdValue = customerId.getValue();
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId.getValue())
            .filter(pm -> customerIdValue.equals(pm.getCustomerId()))
            .switchIfEmpty(Mono.error(new RuntimeException(PAYMENT_METHOD_NOT_FOUND_MSG)))
            .flatMap(paymentMethod -> 
                // Update customer's default_payment_method_id
                customerRepository.findByCustomerId(customerId.getValue())
                    .flatMap(customer -> {
                        customer.setDefaultPaymentMethodId(paymentMethodId.getValue());
                        customer.setModifiedAt(Instant.now());
                        return customerRepository.save(customer);
                    })
            )
            .thenReturn(Result.<Void, PaymentError>ok(null))
            .onErrorResume(error -> {
                log.error("Error setting default payment method", error);
                return Mono.just(Result.<Void, PaymentError>err(PaymentError.of(
                    "PAYMENT_METHOD_SET_DEFAULT_FAILED",
                    "Failed to set default payment method: " + error.getMessage()
                )));
            });
    }

    @Override
    public Mono<Result<Void, PaymentError>> deletePaymentMethod(PaymentMethodId paymentMethodId) {
        log.info("Deleting payment method: {}", paymentMethodId);
        
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId.getValue())
            .flatMap(paymentMethod -> {
                @SuppressWarnings("null")
                PaymentMethodEntity entityToDelete = paymentMethod;
                return paymentMethodRepository.delete(entityToDelete)
                    .then(Mono.just(Result.<Void, PaymentError>ok(null)));
            })
            .switchIfEmpty(Mono.just(Result.<Void, PaymentError>err(
                PaymentError.of("PAYMENT_METHOD_NOT_FOUND", PAYMENT_METHOD_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error deleting payment method", error);
                return Mono.just(Result.<Void, PaymentError>err(PaymentError.of(
                    "PAYMENT_METHOD_DELETE_FAILED",
                    "Failed to delete payment method: " + error.getMessage()
                )));
            });
    }
    
    @Override
    public Mono<Result<PaymentMethodResponse, PaymentError>> getPaymentMethodByClientSecret(String clientSecret) {
        log.info("Getting payment method by client secret");
        
        return paymentMethodRepository.findByClientSecret(clientSecret)
            .map(paymentMethodMapper::toPaymentMethodResponse)
            .map(Result::<PaymentMethodResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<PaymentMethodResponse, PaymentError>err(
                PaymentError.of("PAYMENT_METHOD_NOT_FOUND", "Payment method not found for client secret")
            )))
            .onErrorResume(error -> {
                log.error("Error getting payment method by client secret", error);
                return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(PaymentError.of(
                    "PAYMENT_METHOD_GET_FAILED",
                    "Failed to get payment method: " + error.getMessage()
                )));
            });
    }
    
    @Override
    public Mono<Result<PaymentMethodResponse, PaymentError>> updatePaymentMethod(
            PaymentMethodId paymentMethodId,
            PaymentMethodRequest request) {
        log.info("Updating payment method: {}", paymentMethodId);
        
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId.getValue())
            .flatMap(existing -> {
                // Update fields from request
                if (request.getPaymentMethodData() != null) {
                    existing.setPaymentMethodData(request.getPaymentMethodData());
                }
                if (request.getNetworkTransactionId() != null) {
                    existing.setNetworkTransactionId(request.getNetworkTransactionId());
                }
                if (request.getConnectorMandateDetails() != null) {
                    existing.setConnectorMandateDetails(request.getConnectorMandateDetails());
                }
                if (request.getLockerId() != null) {
                    existing.setLockerId(request.getLockerId());
                }
                existing.setModifiedAt(Instant.now());
                
                return paymentMethodRepository.save(existing);
            })
            .map(paymentMethodMapper::toPaymentMethodResponse)
            .map(Result::<PaymentMethodResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<PaymentMethodResponse, PaymentError>err(
                PaymentError.of("PAYMENT_METHOD_NOT_FOUND", PAYMENT_METHOD_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error updating payment method", error);
                return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(PaymentError.of(
                    "PAYMENT_METHOD_UPDATE_FAILED",
                    "Failed to update payment method: " + error.getMessage()
                )));
            });
    }
    
    @Override
    public Mono<Result<NetworkTokenStatusResponse, PaymentError>> checkNetworkTokenStatus(PaymentMethodId paymentMethodId) {
        log.info("Checking network token status for payment method: {}", paymentMethodId);
        
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId.getValue())
            .map(pm -> {
                String networkTransactionId = pm.getNetworkTransactionId();
                boolean hasNetworkToken = networkTransactionId != null && !networkTransactionId.isEmpty();
                
                // Determine status based on network transaction ID and payment method status
                String status;
                if (pm.getStatus() != null && ("inactive".equalsIgnoreCase(pm.getStatus()) || "deleted".equalsIgnoreCase(pm.getStatus()))) {
                    status = "INACTIVE";
                } else {
                    status = hasNetworkToken ? "ACTIVE" : "INACTIVE";
                }
                
                return new NetworkTokenStatusResponse(
                    pm.getPaymentMethodId(),
                    networkTransactionId,
                    status,
                    pm.getModifiedAt() != null ? pm.getModifiedAt() : pm.getCreatedAt(),
                    hasNetworkToken
                );
            })
            .map(Result::<NetworkTokenStatusResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<NetworkTokenStatusResponse, PaymentError>err(
                PaymentError.of("PAYMENT_METHOD_NOT_FOUND", PAYMENT_METHOD_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error checking network token status", error);
                return Mono.just(Result.<NetworkTokenStatusResponse, PaymentError>err(PaymentError.of(
                    "NETWORK_TOKEN_STATUS_CHECK_FAILED",
                    "Failed to check network token status: " + error.getMessage()
                )));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.TokenizeCardResponse, PaymentError>> tokenizeCard(
            com.hyperswitch.common.dto.TokenizeCardRequest request) {
        log.info("Tokenizing card for customer: {}, merchant: {}", request.getCustomerId(), request.getMerchantId());
        
        // Validate customer exists
        return customerRepository.findByCustomerId(request.getCustomerId())
            .switchIfEmpty(Mono.error(new RuntimeException(CUSTOMER_NOT_FOUND_MSG)))
            .flatMap(customer -> {
                // Convert card data to payment method data
                java.util.Map<String, Object> pmData = new java.util.HashMap<>();
                if (request.getCard() != null) {
                    pmData.put("cardNumber", request.getCard().getCardNumber());
                    pmData.put("expiryMonth", request.getCard().getExpiryMonth());
                    pmData.put("expiryYear", request.getCard().getExpiryYear());
                    pmData.put("cardHolderName", request.getCard().getCardHolderName());
                    pmData.put("cardNetwork", request.getCard().getCardNetwork());
                }
                
                // Create payment method from card data using builder
                PaymentMethodRequest pmRequest = PaymentMethodRequest.builder()
                    .customerId(com.hyperswitch.common.types.CustomerId.of(request.getCustomerId()))
                    .merchantId(com.hyperswitch.common.types.MerchantId.of(request.getMerchantId()))
                    .paymentMethodType("CARD")
                    .paymentMethodData(pmData)
                    .build();
                
                // Create payment method
                return createPaymentMethod(pmRequest)
                    .flatMap(result -> {
                        if (result.isOk()) {
                            PaymentMethodResponse pmResponse = result.unwrap();
                            
                            // Generate token
                            String token = "tok_" + UUID.randomUUID().toString().replace("-", "");
                            String networkToken = null;
                            
                            // If network tokenization is enabled, generate network token
                            if (Boolean.TRUE.equals(request.getEnableNetworkTokenization())) {
                                networkToken = "nt_" + UUID.randomUUID().toString().replace("-", "");
                            }
                            
                            com.hyperswitch.common.dto.TokenizeCardResponse response = new com.hyperswitch.common.dto.TokenizeCardResponse();
                            response.setPaymentMethodId(pmResponse.getPaymentMethodId().getValue());
                            response.setCustomerId(request.getCustomerId());
                            response.setMerchantId(request.getMerchantId());
                            response.setToken(token);
                            response.setNetworkToken(networkToken);
                            response.setNetworkTokenizationEnabled(request.getEnableNetworkTokenization());
                            response.setCreatedAt(Instant.now());
                            
                            return Mono.just(Result.<com.hyperswitch.common.dto.TokenizeCardResponse, PaymentError>ok(response));
                        } else {
                            return Mono.just(Result.<com.hyperswitch.common.dto.TokenizeCardResponse, PaymentError>err(result.unwrapErr()));
                        }
                    });
            })
            .onErrorResume(error -> {
                log.error("Error tokenizing card", error);
                String errorCode = CUSTOMER_NOT_FOUND_MSG.equals(error.getMessage())
                    ? "CUSTOMER_NOT_FOUND"
                    : "CARD_TOKENIZATION_FAILED";
                return Mono.just(Result.<com.hyperswitch.common.dto.TokenizeCardResponse, PaymentError>err(
                    PaymentError.of(errorCode,
                        "Failed to tokenize card: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<Flux<PaymentMethodResponse>, PaymentError>> listPaymentMethods(
            String merchantId,
            String customerId,
            String paymentMethodType) {
        log.info("Listing payment methods for merchant: {}, customer: {}, type: {}", merchantId, customerId, paymentMethodType);
        
        Flux<PaymentMethodEntity> query;
        
        if (customerId != null && !customerId.isEmpty()) {
            query = paymentMethodRepository.findByCustomerIdAndStatusOrderByCreatedAtDesc(customerId, DEFAULT_STATUS);
        } else if (merchantId != null && !merchantId.isEmpty()) {
            query = paymentMethodRepository.findByMerchantIdOrderByCreatedAtDesc(merchantId);
        } else {
            query = paymentMethodRepository.findAll();
        }
        
        // Filter by payment method type if provided
        if (paymentMethodType != null && !paymentMethodType.isEmpty()) {
            query = query.filter(pm -> paymentMethodType.equalsIgnoreCase(pm.getPaymentMethodType()));
        }
        
        Flux<PaymentMethodResponse> response = query
            .map(paymentMethodMapper::toPaymentMethodResponse);
        
        return Mono.just(Result.<Flux<PaymentMethodResponse>, PaymentError>ok(response))
            .onErrorResume(error -> {
                log.error("Error listing payment methods", error);
                return Mono.just(Result.<Flux<PaymentMethodResponse>, PaymentError>err(
                    PaymentError.of("PAYMENT_METHOD_LIST_FAILED",
                        "Failed to list payment methods: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<Flux<PaymentMethodResponse>, PaymentError>> getPaymentMethodsForPayment(
            com.hyperswitch.common.types.PaymentId paymentId,
            String merchantId) {
        log.info("Getting payment methods for payment: {}, merchant: {}", paymentId, merchantId);
        
        // Get payment intent to find customer
        return paymentIntentRepository.findByPaymentId(paymentId.getValue())
            .flatMap(intent -> {
                String customerId = intent.getCustomerId();
                if (customerId != null && !customerId.isEmpty()) {
                    return listCustomerPaymentMethods(CustomerId.of(customerId));
                } else {
                    // Return empty flux if no customer
                    return Mono.just(Result.<Flux<PaymentMethodResponse>, PaymentError>ok(Flux.empty()));
                }
            })
            .switchIfEmpty(Mono.just(Result.<Flux<PaymentMethodResponse>, PaymentError>err(
                PaymentError.of("PAYMENT_NOT_FOUND",
                    "Payment not found: " + paymentId.getValue())
            )))
            .onErrorResume(error -> {
                log.error("Error getting payment methods for payment", error);
                return Mono.just(Result.<Flux<PaymentMethodResponse>, PaymentError>err(
                    PaymentError.of("PAYMENT_METHODS_FOR_PAYMENT_FAILED",
                        "Failed to get payment methods for payment: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.PaymentMethodTokenResponse, PaymentError>> getPaymentMethodToken(
            PaymentMethodId paymentMethodId,
            String merchantId) {
        log.info("Getting payment method token for: {}, merchant: {}", paymentMethodId, merchantId);
        
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId.getValue())
            .filter(pm -> merchantId == null || merchantId.equals(pm.getMerchantId()))
            .map(pm -> {
                com.hyperswitch.common.dto.PaymentMethodTokenResponse response = new com.hyperswitch.common.dto.PaymentMethodTokenResponse();
                response.setPaymentMethodId(pm.getPaymentMethodId());
                
                // Extract token from payment method data
                String token = null;
                String networkToken = null;
                if (pm.getPaymentMethodData() != null) {
                    token = (String) pm.getPaymentMethodData().get("token");
                    networkToken = (String) pm.getPaymentMethodData().get("networkToken");
                }
                
                response.setToken(token);
                response.setNetworkToken(networkToken);
                response.setTokenData(pm.getPaymentMethodData());
                response.setIsActive(DEFAULT_STATUS.equals(pm.getStatus()));
                
                return Result.<com.hyperswitch.common.dto.PaymentMethodTokenResponse, PaymentError>ok(response);
            })
            .switchIfEmpty(Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodTokenResponse, PaymentError>err(
                PaymentError.of("PAYMENT_METHOD_NOT_FOUND", PAYMENT_METHOD_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error getting payment method token", error);
                return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodTokenResponse, PaymentError>err(
                    PaymentError.of("PAYMENT_METHOD_TOKEN_GET_FAILED",
                        "Failed to get payment method token: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.PaymentMethodFilterResponse, PaymentError>> getPaymentMethodFilters(
            String merchantId,
            String connector) {
        log.info("Getting payment method filters for merchant: {}, connector: {}", merchantId, connector);
        
        try {
            com.hyperswitch.common.dto.PaymentMethodFilterResponse response = new com.hyperswitch.common.dto.PaymentMethodFilterResponse();
            java.util.Map<String, java.util.List<com.hyperswitch.common.dto.PaymentMethodFilterResponse.ConnectorPaymentMethodInfo>> connectorMap = new java.util.HashMap<>();
            
            // Get available connectors
            java.util.List<String> connectors = java.util.Arrays.asList("stripe", "adyen", "checkout");
            if (connector != null && !connector.isEmpty()) {
                connectors = java.util.Arrays.asList(connector);
            }
            
            // Build connector payment method info
            for (String conn : connectors) {
                java.util.List<com.hyperswitch.common.dto.PaymentMethodFilterResponse.ConnectorPaymentMethodInfo> pmList = new java.util.ArrayList<>();
                
                // Add card payment method
                com.hyperswitch.common.dto.PaymentMethodFilterResponse.ConnectorPaymentMethodInfo cardInfo = 
                    new com.hyperswitch.common.dto.PaymentMethodFilterResponse.ConnectorPaymentMethodInfo();
                cardInfo.setPaymentMethod("card");
                cardInfo.setPaymentMethodType("credit");
                cardInfo.setCurrencies(java.util.Arrays.asList("USD", "EUR", "GBP", "INR"));
                cardInfo.setCountries(java.util.Arrays.asList("US", "GB", "IN", "DE", "FR"));
                pmList.add(cardInfo);
                
                connectorMap.put(conn, pmList);
            }
            
            response.setConnector(connectorMap);
            
            return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodFilterResponse, PaymentError>ok(response))
                .onErrorResume(error -> {
                    log.error("Error getting payment method filters", error);
                    return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodFilterResponse, PaymentError>err(
                        PaymentError.of("PAYMENT_METHOD_FILTERS_FAILED",
                            "Failed to get payment method filters: " + error.getMessage())
                    ));
                });
        } catch (Exception e) {
            log.error("Error getting payment method filters", e);
            return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodFilterResponse, PaymentError>err(
                PaymentError.of("PAYMENT_METHOD_FILTERS_FAILED",
                    "Failed to get payment method filters: " + e.getMessage())
            ));
        }
    }
    
    @Override
    public Mono<Result<PaymentMethodResponse, PaymentError>> migratePaymentMethod(
            String merchantId,
            com.hyperswitch.common.dto.PaymentMethodMigrateRequest request) {
        log.info("Migrating payment method: {} from {} to {}", 
            request.getPaymentMethodId(), request.getFromConnector(), request.getToConnector());
        
        return paymentMethodRepository.findByPaymentMethodId(request.getPaymentMethodId())
            .switchIfEmpty(Mono.error(new RuntimeException(PAYMENT_METHOD_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Update connector information
                if (entity.getPaymentMethodData() == null) {
                    entity.setPaymentMethodData(new java.util.HashMap<>());
                }
                entity.getPaymentMethodData().put("from_connector", request.getFromConnector());
                entity.getPaymentMethodData().put("to_connector", request.getToConnector());
                entity.getPaymentMethodData().put("migrated_at", Instant.now().toString());
                
                if (request.getMetadata() != null) {
                    entity.getPaymentMethodData().put("metadata", request.getMetadata());
                }
                
                entity.setModifiedAt(Instant.now());
                
                return paymentMethodRepository.save(entity)
                    .map(paymentMethodMapper::toPaymentMethodResponse)
                    .map(Result::<PaymentMethodResponse, PaymentError>ok);
            })
            .onErrorResume(error -> {
                log.error("Error migrating payment method", error);
                String errorCode = PAYMENT_METHOD_NOT_FOUND_MSG.equals(error.getMessage())
                    ? "PAYMENT_METHOD_NOT_FOUND"
                    : "PAYMENT_METHOD_MIGRATE_FAILED";
                return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(PaymentError.of(
                    errorCode,
                    "Failed to migrate payment method: " + error.getMessage()
                )));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse, PaymentError>> batchMigratePaymentMethods(
            String merchantId,
            com.hyperswitch.common.dto.PaymentMethodBatchMigrateRequest request) {
        log.info("Batch migrating {} payment methods for merchant: {}", 
            request.getPaymentMethods() != null ? request.getPaymentMethods().size() : 0, merchantId);
        
        if (request.getPaymentMethods() == null || request.getPaymentMethods().isEmpty()) {
            return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse, PaymentError>err(
                PaymentError.of("INVALID_REQUEST", "Payment methods list cannot be empty")));
        }
        
        return Flux.fromIterable(request.getPaymentMethods())
            .flatMap(migrateRequest -> migratePaymentMethod(merchantId, migrateRequest)
                .map(result -> {
                    com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse.PaymentMethodMigrateResult migrateResult =
                        new com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse.PaymentMethodMigrateResult();
                    migrateResult.setPaymentMethodId(migrateRequest.getPaymentMethodId());
                    
                    if (result.isOk()) {
                        migrateResult.setSuccess(true);
                        migrateResult.setPaymentMethod(result.unwrap());
                    } else {
                        migrateResult.setSuccess(false);
                        migrateResult.setErrorMessage(result.unwrapErr().getMessage());
                    }
                    
                    return migrateResult;
                })
                .onErrorResume(error -> {
                    com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse.PaymentMethodMigrateResult migrateResult =
                        new com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse.PaymentMethodMigrateResult();
                    migrateResult.setPaymentMethodId(migrateRequest.getPaymentMethodId());
                    migrateResult.setSuccess(false);
                    migrateResult.setErrorMessage(error.getMessage());
                    return Mono.just(migrateResult);
                })
            )
            .collectList()
            .map(results -> {
                com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse response =
                    new com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse();
                response.setResults(results);
                response.setTotalCount(results.size());
                response.setSuccessCount((int) results.stream().filter(r -> Boolean.TRUE.equals(r.getSuccess())).count());
                response.setFailureCount((int) results.stream().filter(r -> Boolean.FALSE.equals(r.getSuccess())).count());
                
                return Result.<com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse, PaymentError>ok(response);
            })
            .onErrorResume(error -> {
                log.error("Error in batch migrate payment methods", error);
                return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodBatchMigrateResponse, PaymentError>err(
                    PaymentError.of("BATCH_MIGRATE_FAILED", "Failed to batch migrate payment methods: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse, PaymentError>> batchUpdatePaymentMethods(
            String merchantId,
            com.hyperswitch.common.dto.PaymentMethodBatchUpdateRequest request) {
        log.info("Batch updating {} payment methods for merchant: {}", 
            request.getPaymentMethods() != null ? request.getPaymentMethods().size() : 0, merchantId);
        
        if (request.getPaymentMethods() == null || request.getPaymentMethods().isEmpty()) {
            return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse, PaymentError>err(
                PaymentError.of("INVALID_REQUEST", "Payment methods list cannot be empty")));
        }
        
        return Flux.fromIterable(request.getPaymentMethods())
            .flatMap(updateItem -> paymentMethodRepository.findByPaymentMethodId(updateItem.getPaymentMethodId())
                .switchIfEmpty(Mono.error(new RuntimeException(PAYMENT_METHOD_NOT_FOUND_MSG)))
                .flatMap(entity -> {
                    updatePaymentMethodEntity(entity, updateItem);
                    entity.setModifiedAt(Instant.now());
                    
                    return paymentMethodRepository.save(entity)
                        .map(paymentMethodMapper::toPaymentMethodResponse)
                        .map(pm -> {
                            com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse.PaymentMethodUpdateResult updateResult =
                                new com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse.PaymentMethodUpdateResult();
                            updateResult.setPaymentMethodId(updateItem.getPaymentMethodId());
                            updateResult.setSuccess(true);
                            updateResult.setPaymentMethod(pm);
                            return updateResult;
                        });
                })
                .onErrorResume(error -> {
                    com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse.PaymentMethodUpdateResult updateResult =
                        new com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse.PaymentMethodUpdateResult();
                    updateResult.setPaymentMethodId(updateItem.getPaymentMethodId());
                    updateResult.setSuccess(false);
                    updateResult.setErrorMessage(error.getMessage());
                    return Mono.just(updateResult);
                }))
            .collectList()
            .map(results -> {
                com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse response =
                    new com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse();
                response.setResults(results);
                response.setTotalCount(results.size());
                response.setSuccessCount((int) results.stream().filter(r -> Boolean.TRUE.equals(r.getSuccess())).count());
                response.setFailureCount((int) results.stream().filter(r -> Boolean.FALSE.equals(r.getSuccess())).count());
                
                return Result.<com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse, PaymentError>ok(response);
            })
            .onErrorResume(error -> {
                log.error("Error in batch update payment methods", error);
                return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodBatchUpdateResponse, PaymentError>err(
                    PaymentError.of("BATCH_UPDATE_FAILED", "Failed to batch update payment methods: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.BatchTokenizeCardResponse, PaymentError>> batchTokenizeCards(
            String merchantId,
            com.hyperswitch.common.dto.BatchTokenizeCardRequest request) {
        log.info("Batch tokenizing {} cards for merchant: {}", 
            request.getCards() != null ? request.getCards().size() : 0, merchantId);
        
        if (request.getCards() == null || request.getCards().isEmpty()) {
            return Mono.just(Result.<com.hyperswitch.common.dto.BatchTokenizeCardResponse, PaymentError>err(
                PaymentError.of("INVALID_REQUEST", "Cards list cannot be empty")));
        }
        
        return Flux.fromIterable(request.getCards())
            .index()
            .flatMap(tuple -> {
                long index = tuple.getT1();
                com.hyperswitch.common.dto.TokenizeCardRequest cardRequest = tuple.getT2();
                
                // Set merchant and customer ID if not set
                if (cardRequest.getMerchantId() == null) {
                    cardRequest.setMerchantId(merchantId);
                }
                if (cardRequest.getCustomerId() == null && request.getCustomerId() != null) {
                    cardRequest.setCustomerId(request.getCustomerId());
                }
                
                return tokenizeCard(cardRequest)
                    .map(result -> {
                        com.hyperswitch.common.dto.BatchTokenizeCardResponse.TokenizeCardResult tokenizeResult =
                            new com.hyperswitch.common.dto.BatchTokenizeCardResponse.TokenizeCardResult();
                        tokenizeResult.setIndex((int) index);
                        
                        if (result.isOk()) {
                            tokenizeResult.setSuccess(true);
                            tokenizeResult.setTokenizeCardResponse(result.unwrap());
                        } else {
                            tokenizeResult.setSuccess(false);
                            tokenizeResult.setErrorMessage(result.unwrapErr().getMessage());
                        }
                        
                        return tokenizeResult;
                    })
                    .onErrorResume(error -> {
                        com.hyperswitch.common.dto.BatchTokenizeCardResponse.TokenizeCardResult tokenizeResult =
                            new com.hyperswitch.common.dto.BatchTokenizeCardResponse.TokenizeCardResult();
                        tokenizeResult.setIndex((int) index);
                        tokenizeResult.setSuccess(false);
                        tokenizeResult.setErrorMessage(error.getMessage());
                        return Mono.just(tokenizeResult);
                    });
            })
            .collectList()
            .map(results -> {
                com.hyperswitch.common.dto.BatchTokenizeCardResponse response =
                    new com.hyperswitch.common.dto.BatchTokenizeCardResponse();
                response.setResults(results);
                response.setTotalCount(results.size());
                response.setSuccessCount((int) results.stream().filter(r -> Boolean.TRUE.equals(r.getSuccess())).count());
                response.setFailureCount((int) results.stream().filter(r -> Boolean.FALSE.equals(r.getSuccess())).count());
                
                return Result.<com.hyperswitch.common.dto.BatchTokenizeCardResponse, PaymentError>ok(response);
            })
            .onErrorResume(error -> {
                log.error("Error in batch tokenize cards", error);
                return Mono.just(Result.<com.hyperswitch.common.dto.BatchTokenizeCardResponse, PaymentError>err(
                    PaymentError.of("BATCH_TOKENIZE_FAILED", "Failed to batch tokenize cards: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.PaymentMethodCollectLinkResponse, PaymentError>> initiatePaymentMethodCollectLink(
            String merchantId,
            com.hyperswitch.common.dto.PaymentMethodCollectLinkRequest request) {
        log.info("Initiating payment method collect link for merchant: {}, customer: {}", 
            merchantId, request.getCustomerId());
        
        // Generate collect link ID
        String collectLinkId = "pm_collect_" + UUID.randomUUID().toString().replace("-", "");
        
        // Generate collect link URL
        String baseUrl = System.getenv().getOrDefault("PAYMENT_METHOD_COLLECT_BASE_URL", "https://api.hyperswitch.io");
        String collectLinkUrl = baseUrl + "/api/payment_methods/collect/" + merchantId + "/" + collectLinkId;
        
        // Calculate expiry time
        Instant expiresAt = Instant.now().plusSeconds(
            request.getExpiryTime() != null ? request.getExpiryTime() : 3600); // Default 1 hour
        
        com.hyperswitch.common.dto.PaymentMethodCollectLinkResponse response =
            new com.hyperswitch.common.dto.PaymentMethodCollectLinkResponse();
        response.setCollectLinkId(collectLinkId);
        response.setCollectLinkUrl(collectLinkUrl);
        response.setCustomerId(request.getCustomerId());
        response.setMerchantId(merchantId);
        response.setExpiresAt(expiresAt);
        response.setStatus("active");
        
        // In a real implementation, store the collect link in database
        // For now, return the response
        
        return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodCollectLinkResponse, PaymentError>ok(response));
    }
    
    @Override
    public Mono<Result<String, PaymentError>> renderPaymentMethodCollectLink(
            String merchantId,
            String collectLinkId) {
        log.info("Rendering payment method collect link: {} for merchant: {}", collectLinkId, merchantId);
        
        // In a real implementation, retrieve the collect link from database
        // and render the payment method collection form
        // For now, return a placeholder HTML form
        
        String htmlForm = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Payment Method Collection</title>
            </head>
            <body>
                <h1>Payment Method Collection Form</h1>
                <p>Collect Link ID: %s</p>
                <p>Merchant ID: %s</p>
                <form id="paymentMethodForm">
                    <!-- Payment method collection form will be rendered here -->
                </form>
            </body>
            </html>
            """.formatted(collectLinkId, merchantId);
        
        return Mono.just(Result.<String, PaymentError>ok(htmlForm));
    }
    
    @Override
    public Mono<Result<PaymentMethodResponse, PaymentError>> createPaymentMethodIntent(
            String merchantId,
            com.hyperswitch.common.dto.PaymentMethodIntentCreateRequest request) {
        log.info("Creating payment method intent for customer: {} and merchant: {}", 
            request.getCustomerId(), merchantId);
        
        // Verify customer exists
        return customerRepository.findByCustomerId(request.getCustomerId())
            .switchIfEmpty(Mono.error(new RuntimeException(CUSTOMER_NOT_FOUND_MSG)))
            .flatMap(customer -> {
                PaymentMethodId paymentMethodId = PaymentMethodId.generate();
                
                // Create payment method entity with intent status (no payment method data yet)
                // Store billing and metadata in paymentMethodData temporarily
                Map<String, Object> intentData = buildIntentData(request);
                
                PaymentMethodEntity entity = PaymentMethodEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .paymentMethodId(paymentMethodId.getValue())
                    .customerId(request.getCustomerId())
                    .merchantId(merchantId)
                    .status("intent_created") // Special status for intent
                    .paymentMethodData(intentData) // Store billing and metadata temporarily
                    .createdAt(Instant.now())
                    .modifiedAt(Instant.now())
                    .build();
                
                return paymentMethodRepository.save(entity);
            })
            .map(paymentMethodMapper::toPaymentMethodResponse)
            .map(Result::<PaymentMethodResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error creating payment method intent", error);
                String errorMessage = error.getMessage();
                String errorCode = CUSTOMER_NOT_FOUND_MSG.equals(errorMessage) 
                    ? "CUSTOMER_NOT_FOUND" 
                    : "PAYMENT_METHOD_INTENT_CREATE_FAILED";
                return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(PaymentError.of(
                    errorCode,
                    "Failed to create payment method intent: " + errorMessage
                )));
            });
    }
    
    @Override
    public Mono<Result<PaymentMethodResponse, PaymentError>> confirmPaymentMethodIntent(
            String merchantId,
            String paymentMethodId,
            com.hyperswitch.common.dto.PaymentMethodIntentConfirmRequest request) {
        log.info("Confirming payment method intent: {} for merchant: {}", paymentMethodId, merchantId);
        
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId)
            .switchIfEmpty(Mono.error(new RuntimeException(PAYMENT_METHOD_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Verify it's an intent (status should be "intent_created")
                if (!"intent_created".equals(entity.getStatus())) {
                    return Mono.error(new RuntimeException("Payment method is not in intent state"));
                }
                
                // Verify merchant matches
                if (!merchantId.equals(entity.getMerchantId())) {
                    return Mono.error(new RuntimeException("Payment method does not belong to merchant"));
                }
                
                // Update with payment method data
                entity.setPaymentMethodData(request.getPaymentMethodData());
                entity.setPaymentMethodType(request.getPaymentMethodType());
                entity.setPaymentMethodSubtype(request.getPaymentMethodSubtype());
                entity.setStatus(DEFAULT_STATUS); // Change to active status
                entity.setModifiedAt(Instant.now());
                
                // Update customer ID if provided in confirm request
                if (request.getCustomerId() != null && !request.getCustomerId().equals(entity.getCustomerId())) {
                    // Verify new customer exists
                    return customerRepository.findByCustomerId(request.getCustomerId())
                        .switchIfEmpty(Mono.error(new RuntimeException(CUSTOMER_NOT_FOUND_MSG)))
                        .flatMap(customer -> {
                            entity.setCustomerId(request.getCustomerId());
                            return paymentMethodRepository.save(entity);
                        });
                }
                
                return paymentMethodRepository.save(entity);
            })
            .map(paymentMethodMapper::toPaymentMethodResponse)
            .map(Result::<PaymentMethodResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error confirming payment method intent", error);
                String errorMessage = error.getMessage();
                String errorCode = determineErrorCodeForIntentConfirm(errorMessage);
                return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(PaymentError.of(
                    errorCode,
                    "Failed to confirm payment method intent: " + errorMessage
                )));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.TokenizeCardResponse, PaymentError>> tokenizeCardUsingPaymentMethod(
            String merchantId,
            PaymentMethodId paymentMethodId,
            com.hyperswitch.common.dto.TokenizeCardRequest request) {
        log.info("Tokenizing card using existing payment method: {} for merchant: {}", paymentMethodId, merchantId);
        
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId.getValue())
            .switchIfEmpty(Mono.error(new RuntimeException(PAYMENT_METHOD_NOT_FOUND_MSG)))
            .flatMap(existingPm -> {
                // Verify merchant matches
                if (!merchantId.equals(existingPm.getMerchantId())) {
                    return Mono.error(new RuntimeException("Payment method does not belong to merchant"));
                }
                
                // Use existing payment method data and create a new tokenized version
                // This is similar to tokenizeCard but uses existing PM as base
                return tokenizeCard(request);
            })
            .onErrorResume(error -> {
                log.error("Error tokenizing card using payment method", error);
                String errorCode = PAYMENT_METHOD_NOT_FOUND_MSG.equals(error.getMessage())
                    ? "PAYMENT_METHOD_NOT_FOUND"
                    : "TOKENIZE_CARD_USING_PM_FAILED";
                return Mono.just(Result.<com.hyperswitch.common.dto.TokenizeCardResponse, PaymentError>err(
                    PaymentError.of(errorCode, "Failed to tokenize card using payment method: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<PaymentMethodResponse, PaymentError>> updatePaymentMethodV1(
            String merchantId,
            PaymentMethodId paymentMethodId,
            PaymentMethodRequest request) {
        log.info("Updating payment method (v1): {} for merchant: {}", paymentMethodId, merchantId);
        
        // This is essentially the same as updatePaymentMethod but with merchant validation
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId.getValue())
            .switchIfEmpty(Mono.error(new RuntimeException(PAYMENT_METHOD_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Verify merchant matches
                if (!merchantId.equals(entity.getMerchantId())) {
                    return Mono.error(new RuntimeException("Payment method does not belong to merchant"));
                }
                
                // Update fields from request
                if (request.getPaymentMethodData() != null) {
                    entity.setPaymentMethodData(request.getPaymentMethodData());
                }
                if (request.getNetworkTransactionId() != null) {
                    entity.setNetworkTransactionId(request.getNetworkTransactionId());
                }
                if (request.getConnectorMandateDetails() != null) {
                    entity.setConnectorMandateDetails(request.getConnectorMandateDetails());
                }
                if (request.getLockerId() != null) {
                    entity.setLockerId(request.getLockerId());
                }
                
                entity.setModifiedAt(Instant.now());
                
                return paymentMethodRepository.save(entity);
            })
            .map(paymentMethodMapper::toPaymentMethodResponse)
            .map(Result::<PaymentMethodResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error updating payment method (v1)", error);
                String errorCode = PAYMENT_METHOD_NOT_FOUND_MSG.equals(error.getMessage())
                    ? "PAYMENT_METHOD_NOT_FOUND"
                    : "PAYMENT_METHOD_UPDATE_V1_FAILED";
                return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(
                    PaymentError.of(errorCode, "Failed to update payment method: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<PaymentMethodResponse, PaymentError>> savePaymentMethod(
            String merchantId,
            PaymentMethodId paymentMethodId) {
        log.info("Saving payment method: {} for merchant: {}", paymentMethodId, merchantId);
        
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId.getValue())
            .switchIfEmpty(Mono.error(new RuntimeException(PAYMENT_METHOD_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Verify merchant matches
                if (!merchantId.equals(entity.getMerchantId())) {
                    return Mono.error(new RuntimeException("Payment method does not belong to merchant"));
                }
                
                // Mark as saved/stored by updating status or adding flag
                // In Hyperswitch, this typically sets is_stored flag or similar
                // For now, we'll update the status to indicate it's saved
                if (!"saved".equals(entity.getStatus()) && !"active".equals(entity.getStatus())) {
                    entity.setStatus("saved");
                }
                entity.setModifiedAt(Instant.now());
                
                return paymentMethodRepository.save(entity);
            })
            .map(paymentMethodMapper::toPaymentMethodResponse)
            .map(Result::<PaymentMethodResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error saving payment method", error);
                String errorCode = PAYMENT_METHOD_NOT_FOUND_MSG.equals(error.getMessage())
                    ? "PAYMENT_METHOD_NOT_FOUND"
                    : "SAVE_PAYMENT_METHOD_FAILED";
                return Mono.just(Result.<PaymentMethodResponse, PaymentError>err(
                    PaymentError.of(errorCode, "Failed to save payment method: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.PaymentMethodAuthLinkResponse, PaymentError>> createPaymentMethodAuthLink(
            String merchantId,
            com.hyperswitch.common.dto.PaymentMethodAuthLinkRequest request) {
        log.info("Creating payment method auth link for merchant: {}, payment: {}", merchantId, request.getPaymentId());
        
        // Generate link token
        String linkToken = "link_" + UUID.randomUUID().toString().replace("-", "");
        
        // Determine connector based on payment method
        String connector = determineConnectorForPaymentMethod(request.getPaymentMethod(), request.getPaymentMethodType());
        
        com.hyperswitch.common.dto.PaymentMethodAuthLinkResponse response = 
            new com.hyperswitch.common.dto.PaymentMethodAuthLinkResponse();
        response.setLinkToken(linkToken);
        response.setConnector(connector);
        
        return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodAuthLinkResponse, PaymentError>ok(response))
            .onErrorResume(error -> {
                log.error("Error creating payment method auth link", error);
                return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodAuthLinkResponse, PaymentError>err(
                    PaymentError.of("PAYMENT_METHOD_AUTH_LINK_CREATE_FAILED",
                        "Failed to create payment method auth link: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.PaymentMethodAuthExchangeResponse, PaymentError>> exchangePaymentMethodAuthToken(
            String merchantId,
            com.hyperswitch.common.dto.PaymentMethodAuthExchangeRequest request) {
        log.info("Exchanging payment method auth token for merchant: {}, payment: {}", merchantId, request.getPaymentId());
        
        // Exchange public token for access token
        // In production, this would call the connector's exchange endpoint
        String accessToken = "access_" + UUID.randomUUID().toString().replace("-", "");
        
        com.hyperswitch.common.dto.PaymentMethodAuthExchangeResponse response = 
            new com.hyperswitch.common.dto.PaymentMethodAuthExchangeResponse();
        response.setAccessToken(accessToken);
        
        return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodAuthExchangeResponse, PaymentError>ok(response))
            .onErrorResume(error -> {
                log.error("Error exchanging payment method auth token", error);
                return Mono.just(Result.<com.hyperswitch.common.dto.PaymentMethodAuthExchangeResponse, PaymentError>err(
                    PaymentError.of("PAYMENT_METHOD_AUTH_EXCHANGE_FAILED",
                        "Failed to exchange payment method auth token: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Determine connector based on payment method type
     */
    @SuppressWarnings("unused")
    private String determineConnectorForPaymentMethod(String paymentMethod, String paymentMethodType) {
        // In production, this would query the merchant connector configuration
        // For now, return a default connector based on payment method
        if ("card".equalsIgnoreCase(paymentMethod)) {
            return "stripe"; // Default card connector
        }
        return "stripe"; // Default connector
    }
    
    /**
     * Determine error code for payment method intent confirm
     */
    private String determineErrorCodeForIntentConfirm(String errorMessage) {
        if (PAYMENT_METHOD_NOT_FOUND_MSG.equals(errorMessage)) {
            return "PAYMENT_METHOD_NOT_FOUND";
        }
        if (CUSTOMER_NOT_FOUND_MSG.equals(errorMessage)) {
            return "CUSTOMER_NOT_FOUND";
        }
        return "PAYMENT_METHOD_INTENT_CONFIRM_FAILED";
    }
    
    @Override
    public Mono<Result<com.hyperswitch.common.dto.TokenDataResponse, PaymentError>> getPaymentMethodTokenData(
            String merchantId,
            PaymentMethodId paymentMethodId,
            com.hyperswitch.common.dto.GetTokenDataRequest request) {
        log.info("Getting token data for payment method: {} for merchant: {}, token type: {}", 
            paymentMethodId, merchantId, request.getTokenType());
        
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId.getValue())
            .switchIfEmpty(Mono.error(new RuntimeException(PAYMENT_METHOD_NOT_FOUND_MSG)))
            .flatMap(entity -> {
                // Verify merchant matches
                if (!merchantId.equals(entity.getMerchantId())) {
                    return Mono.error(new RuntimeException("Payment method does not belong to merchant"));
                }
                
                // Build token data response based on token type
                com.hyperswitch.common.dto.TokenDataResponse response = 
                    new com.hyperswitch.common.dto.TokenDataResponse();
                response.setPaymentMethodId(paymentMethodId.getValue());
                response.setTokenType(request.getTokenType());
                
                // Build token details based on token type
                Map<String, Object> tokenDetails = buildTokenDetails(entity, request.getTokenType());
                response.setTokenDetails(tokenDetails);
                
                return Mono.just(response);
            })
            .map(Result::<com.hyperswitch.common.dto.TokenDataResponse, PaymentError>ok)
            .onErrorResume(error -> {
                log.error("Error getting payment method token data", error);
                String errorCode = PAYMENT_METHOD_NOT_FOUND_MSG.equals(error.getMessage())
                    ? "PAYMENT_METHOD_NOT_FOUND"
                    : "GET_TOKEN_DATA_FAILED";
                return Mono.just(Result.<com.hyperswitch.common.dto.TokenDataResponse, PaymentError>err(
                    PaymentError.of(errorCode, "Failed to get payment method token data: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Build intent data from request
     */
    private Map<String, Object> buildIntentData(com.hyperswitch.common.dto.PaymentMethodIntentCreateRequest request) {
        Map<String, Object> intentData = new HashMap<>();
        if (request.getBilling() != null) {
            intentData.put("billing", convertBillingAddressToMap(request.getBilling()));
        }
        if (request.getMetadata() != null) {
            intentData.put("metadata", request.getMetadata());
        }
        intentData.put("intent", true); // Mark as intent
        return intentData;
    }
    
    /**
     * Convert billing address to map
     */
    private Map<String, Object> convertBillingAddressToMap(com.hyperswitch.common.dto.Address billing) {
        Map<String, Object> billingMap = new HashMap<>();
        if (billing.getLine1() != null) {
            billingMap.put("line1", billing.getLine1());
        }
        if (billing.getLine2() != null) {
            billingMap.put("line2", billing.getLine2());
        }
        if (billing.getCity() != null) {
            billingMap.put("city", billing.getCity());
        }
        if (billing.getState() != null) {
            billingMap.put("state", billing.getState());
        }
        if (billing.getZip() != null) {
            billingMap.put("zip", billing.getZip());
        }
        if (billing.getCountry() != null) {
            billingMap.put("country", billing.getCountry());
        }
        return billingMap;
    }
    
    /**
     * Update payment method entity with update item data
     */
    private void updatePaymentMethodEntity(
            PaymentMethodEntity entity,
            com.hyperswitch.common.dto.PaymentMethodBatchUpdateRequest.PaymentMethodUpdateItem updateItem) {
        if (updateItem.getPaymentMethodData() != null) {
            entity.setPaymentMethodData(updateItem.getPaymentMethodData());
        }
        if (updateItem.getNetworkTransactionId() != null) {
            entity.setNetworkTransactionId(updateItem.getNetworkTransactionId());
        }
        if (updateItem.getConnectorMandateDetails() != null) {
            entity.setConnectorMandateDetails(updateItem.getConnectorMandateDetails());
        }
        if (updateItem.getMetadata() != null) {
            if (entity.getPaymentMethodData() == null) {
                entity.setPaymentMethodData(new HashMap<>());
            }
            entity.getPaymentMethodData().put("metadata", updateItem.getMetadata());
        }
    }
    
    /**
     * Build token details based on token type
     */
    private Map<String, Object> buildTokenDetails(PaymentMethodEntity entity, String tokenType) {
        Map<String, Object> tokenDetails = new HashMap<>();
        
        if ("network_token".equals(tokenType)) {
            buildNetworkTokenDetails(entity, tokenDetails);
        } else {
            buildStandardTokenDetails(entity, tokenDetails);
        }
        
        return tokenDetails;
    }
    
    /**
     * Build network token details
     */
    private void buildNetworkTokenDetails(PaymentMethodEntity entity, Map<String, Object> tokenDetails) {
        if (entity.getNetworkTransactionId() != null) {
            tokenDetails.put("network_token", entity.getNetworkTransactionId());
        }
        if (entity.getPaymentMethodData() != null) {
            tokenDetails.putAll(entity.getPaymentMethodData());
        }
    }
    
    /**
     * Build standard token details
     */
    private void buildStandardTokenDetails(PaymentMethodEntity entity, Map<String, Object> tokenDetails) {
        if (entity.getPaymentMethodData() != null) {
            tokenDetails.putAll(entity.getPaymentMethodData());
        }
    }
}

