package com.hyperswitch.core.payouts.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.PayoutId;
import com.hyperswitch.common.types.PayoutStatus;
import com.hyperswitch.connectors.ConnectorService;
import com.hyperswitch.core.payouts.PayoutService;
import com.hyperswitch.storage.entity.PayoutEntity;
import com.hyperswitch.storage.repository.PayoutRepository;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of PayoutService
 */
@Service
public class PayoutServiceImpl implements PayoutService {

    private static final Logger log = LoggerFactory.getLogger(PayoutServiceImpl.class);
    private static final String PAYOUT_NOT_FOUND = "Payout not found";
    private static final int CURRENCY_MULTIPLIER = 100;

    private final PayoutRepository payoutRepository;
    private final ObjectMapper objectMapper;
    private final ConnectorService connectorService;

    @Value("${hyperswitch.payout.link.base-url:https://api.hyperswitch.io}")
    private String payoutLinkBaseUrl;

    @Autowired
    public PayoutServiceImpl(
            PayoutRepository payoutRepository, 
            ObjectMapper objectMapper,
            ConnectorService connectorService) {
        this.payoutRepository = payoutRepository;
        this.objectMapper = objectMapper;
        this.connectorService = connectorService;
    }

    @Override
    public Mono<Either<PaymentError, PayoutResponse>> createPayout(String merchantId, PayoutRequest request) {
        log.info("Creating payout for merchant: {}", merchantId);
        
        return Mono.fromCallable(() -> createPayoutEntity(merchantId, request))
            .flatMap(payoutRepository::save)
            .flatMap(entity -> handlePayoutLinkCreation(entity, request))
            .onErrorResume(error -> {
                log.error("Error creating payout", error);
                return Mono.just(Either.left(PaymentError.of("PAYOUT_CREATION_FAILED", error.getMessage())));
            });
    }
    
    /**
     * Create payout entity from request
     */
    private PayoutEntity createPayoutEntity(String merchantId, PayoutRequest request) {
        PayoutId payoutId = PayoutId.generate();
        Instant now = Instant.now();
        
        Long amountInMinorUnits = convertAmountToMinorUnits(request);
        
        PayoutEntity entity = new PayoutEntity();
        entity.setPayoutId(payoutId.getValue());
        entity.setMerchantId(merchantId);
        entity.setCustomerId(request.getCustomerId());
        entity.setAddressId(request.getAddressId());
        entity.setPayoutType(request.getPayoutType() != null ? request.getPayoutType().name() : null);
        entity.setPayoutMethodId(request.getPayoutMethodId());
        entity.setAmount(amountInMinorUnits);
        entity.setDestinationCurrency(request.getCurrency());
        entity.setSourceCurrency(request.getCurrency());
        entity.setDescription(request.getDescription());
        entity.setRecurring(request.getRecurring() != null ? request.getRecurring() : Boolean.FALSE);
        entity.setAutoFulfill(request.getAutoFulfill() != null ? request.getAutoFulfill() : Boolean.FALSE);
        entity.setReturnUrl(request.getReturnUrl());
        entity.setEntityType(request.getEntityType() != null ? request.getEntityType() : "Individual");
        entity.setStatus(PayoutStatus.REQUIRES_CREATION.name());
        entity.setCreatedAt(now);
        entity.setLastModifiedAt(now);
        entity.setAttemptCount(0);
        entity.setProfileId(request.getProfileId());
        entity.setConfirm(request.getConfirm());
        
        String clientSecret = "payout_" + payoutId.getValue() + "_secret_" + UUID.randomUUID().toString().replace("-", "");
        entity.setClientSecret(clientSecret);
        
        serializeMetadata(entity, request);
        
        return entity;
    }
    
    /**
     * Convert amount to minor units
     */
    private Long convertAmountToMinorUnits(PayoutRequest request) {
        if (request.getAmount() == null) {
            return null;
        }
        return request.getAmount().getValue()
            .multiply(java.math.BigDecimal.valueOf(CURRENCY_MULTIPLIER))
            .longValue();
    }
    
    /**
     * Serialize metadata to JSON string
     */
    private void serializeMetadata(PayoutEntity entity, PayoutRequest request) {
        if (request.getMetadata() != null) {
            try {
                entity.setMetadata(objectMapper.writeValueAsString(request.getMetadata()));
            } catch (Exception e) {
                log.warn("Failed to serialize metadata", e);
            }
        }
    }
    
    /**
     * Handle payout link creation if requested
     */
    private Mono<Either<PaymentError, PayoutResponse>> handlePayoutLinkCreation(
            PayoutEntity entity, PayoutRequest request) {
        if (Boolean.TRUE.equals(request.getPayoutLink())) {
            return createPayoutLink(entity)
                .map(linkResult -> {
                    PayoutResponse response = PayoutMapper.toPayoutResponse(entity);
                    if (linkResult.isRight()) {
                        Map<String, String> linkData = linkResult.get();
                        response.setPayoutLinkId(linkData.get("payout_link_id"));
                        response.setPayoutLinkUrl(linkData.get("payout_link_url"));
                    }
                    return response;
                })
                .map(Either::<PaymentError, PayoutResponse>right);
        }
        return Mono.just(PayoutMapper.toPayoutResponse(entity))
            .map(Either::<PaymentError, PayoutResponse>right);
    }
    
    /**
     * Create payout link for a payout
     */
    private Mono<Either<PaymentError, Map<String, String>>> createPayoutLink(
            PayoutEntity payout) {
        log.info("Creating payout link for payout: {}", payout.getPayoutId());
        
        try {
            // Generate payout link ID
            String payoutLinkId = "polink_" + UUID.randomUUID().toString().replace("-", "");
            
            // Generate payout link URL
            String payoutLinkUrl = String.format("%s/payout_link/%s/%s",
                payoutLinkBaseUrl,
                payout.getMerchantId(),
                payout.getPayoutId());
            
            // In production, this would:
            // 1. Create a payout link entity in the database
            // 2. Store link configuration
            // 3. Generate secure link with expiration
            
            Map<String, String> linkData = new HashMap<>();
            linkData.put("payout_link_id", payoutLinkId);
            linkData.put("payout_link_url", payoutLinkUrl);
            
            // Update payout entity with payout link ID (if field exists)
            // In production, this would be stored in a separate payout_link table
            
            log.info("Payout link created: {} for payout: {}", payoutLinkId, payout.getPayoutId());
            return Mono.just(Either.<PaymentError, Map<String, String>>right(linkData));
        } catch (Exception e) {
            log.error("Error creating payout link", e);
            return Mono.just(Either.left(PaymentError.of("PAYOUT_LINK_CREATION_FAILED", e.getMessage())));
        }
    }

    @Override
    public Mono<Either<PaymentError, PayoutResponse>> getPayout(String merchantId, PayoutId payoutId) {
        log.info("Retrieving payout: {} for merchant: {}", payoutId.getValue(), merchantId);
        
        return payoutRepository.findByMerchantIdAndPayoutId(merchantId, payoutId.getValue())
            .map(PayoutMapper::toPayoutResponse)
            .map(Either::<PaymentError, PayoutResponse>right)
            .switchIfEmpty(Mono.just(Either.left(PaymentError.of("NOT_FOUND", PAYOUT_NOT_FOUND))));
    }

    @Override
    public Flux<PayoutResponse> listPayouts(String merchantId) {
        log.info("Listing payouts for merchant: {}", merchantId);
        
        return payoutRepository.findByMerchantIdOrderByCreatedAtDesc(merchantId)
            .map(PayoutMapper::toPayoutResponse);
    }

    @Override
    public Mono<Either<PaymentError, PayoutResponse>> confirmPayout(String merchantId, PayoutId payoutId, String clientSecret) {
        log.info("Confirming payout: {} for merchant: {}", payoutId.getValue(), merchantId);
        
        return payoutRepository.findByMerchantIdAndPayoutId(merchantId, payoutId.getValue())
            .flatMap(payout -> {
                // Validate client secret
                if (clientSecret != null && !clientSecret.equals(payout.getClientSecret())) {
                    return Mono.just(Either.<PaymentError, PayoutResponse>left(
                        PaymentError.of("INVALID_CLIENT_SECRET", "Invalid client secret")
                    ));
                }
                
                // Update status to pending
                payout.setStatus(PayoutStatus.PENDING.name());
                payout.setConfirm(true);
                payout.setLastModifiedAt(Instant.now());
                
                // Route payout to connector
                return routePayoutToConnector(payout)
                    .flatMap(routingResult -> {
                        if (routingResult.isLeft()) {
                            log.error("Failed to route payout to connector: {}", routingResult.getLeft().getMessage());
                            // Still save the payout with pending status
                            // The routing can be retried later
                        } else {
                            String connectorName = routingResult.get();
                            log.info("Payout routed to connector: {}", connectorName);
                            // In production, this would update payout attempt with connector info
                        }
                        
                        return payoutRepository.save(payout)
                            .map(saved -> {
                                PayoutResponse response = PayoutMapper.toPayoutResponse(saved);
                                return Either.<PaymentError, PayoutResponse>right(response);
                            });
                    });
            })
            .switchIfEmpty(Mono.just(Either.left(PaymentError.of("NOT_FOUND", PAYOUT_NOT_FOUND))));
    }
    
    /**
     * Route payout to appropriate connector
     * In production, this would use routing service similar to payments
     */
    private Mono<Either<PaymentError, String>> routePayoutToConnector(PayoutEntity payout) {
        log.info("Routing payout: {} to connector", payout.getPayoutId());
        
        // In production, this would:
        // 1. Use routing service to select best connector for payout
        // 2. Consider payout type, currency, amount, etc.
        // 3. Create payout attempt with selected connector
        // 4. Call connector's payout API
        
        // For now, use a default connector or get from payout metadata
        String connectorName = "stripe"; // Default connector
        
        // Check if connector is specified in metadata
        if (payout.getMetadata() != null) {
            try {
                Map<String, Object> metadata = objectMapper.readValue(
                    payout.getMetadata(),
                    new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {}
                );
                if (metadata.containsKey("connector") && metadata.get("connector") instanceof String connector) {
                    connectorName = connector;
                }
            } catch (Exception e) {
                log.warn("Failed to parse connector from metadata", e);
            }
        }
        
        // Verify connector is available
        if (!connectorService.getAvailableConnectors().contains(connectorName)) {
            log.warn("Connector {} not available, using default", connectorName);
            connectorName = "stripe";
        }
        
        log.info("Payout {} routed to connector: {}", payout.getPayoutId(), connectorName);
        return Mono.just(Either.<PaymentError, String>right(connectorName));
    }

    @Override
    public Mono<Either<PaymentError, PayoutResponse>> cancelPayout(String merchantId, PayoutId payoutId) {
        log.info("Cancelling payout: {} for merchant: {}", payoutId.getValue(), merchantId);
        
        return payoutRepository.findByMerchantIdAndPayoutId(merchantId, payoutId.getValue())
            .flatMap(payout -> {
                payout.setStatus(PayoutStatus.CANCELLED.name());
                payout.setLastModifiedAt(Instant.now());
                return payoutRepository.save(payout)
                    .map(saved -> {
                        PayoutResponse response = PayoutMapper.toPayoutResponse(saved);
                        return Either.<PaymentError, PayoutResponse>right(response);
                    });
            })
            .switchIfEmpty(Mono.just(Either.left(PaymentError.of("NOT_FOUND", PAYOUT_NOT_FOUND))));
    }
    
    @Override
    public Mono<Either<PaymentError, PayoutResponse>> fulfillPayout(String merchantId, PayoutId payoutId) {
        log.info("Fulfilling payout: {} for merchant: {}", payoutId.getValue(), merchantId);
        
        return payoutRepository.findByMerchantIdAndPayoutId(merchantId, payoutId.getValue())
            .flatMap(payout -> {
                // Check if payout can be fulfilled
                if (!PayoutStatus.PENDING.name().equals(payout.getStatus()) 
                    && !PayoutStatus.REQUIRES_CREATION.name().equals(payout.getStatus())) {
                    return Mono.just(Either.<PaymentError, PayoutResponse>left(
                        PaymentError.of("INVALID_STATUS", 
                            "Payout must be in PENDING or REQUIRES_CREATION status to fulfill. Current status: " + payout.getStatus())
                    ));
                }
                
                // Update status to fulfilled
                payout.setStatus(PayoutStatus.SUCCESS.name());
                payout.setLastModifiedAt(Instant.now());
                
                return payoutRepository.save(payout)
                    .map(saved -> {
                        PayoutResponse response = PayoutMapper.toPayoutResponse(saved);
                        return Either.<PaymentError, PayoutResponse>right(response);
                    });
            })
            .switchIfEmpty(Mono.just(Either.left(PaymentError.of("NOT_FOUND", PAYOUT_NOT_FOUND))))
            .onErrorResume(error -> {
                log.error("Error fulfilling payout", error);
                return Mono.just(Either.left(PaymentError.of("FULFILL_FAILED", error.getMessage())));
            });
    }
    
    @Override
    public Mono<Either<PaymentError, PayoutListResponse>> listPayoutsWithFilters(
            String merchantId,
            PayoutListFilterConstraints constraints) {
        log.info("Listing payouts for merchant: {} with filters", merchantId);
        
        constraints.setMerchantId(merchantId);
        
        return payoutRepository.findByMerchantIdOrderByCreatedAtDesc(merchantId)
            .filter(payout -> matchesPayoutFilters(payout, constraints))
            .collectList()
            .flatMap(payouts -> buildPayoutListResponse(payouts, constraints))
            .onErrorResume(error -> {
                log.error("Error listing payouts", error);
                return Mono.just(Either.<PaymentError, PayoutListResponse>left(
                    PaymentError.of("PAYOUT_LIST_FAILED",
                        "Failed to list payouts: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Check if payout matches filter constraints
     */
    private boolean matchesPayoutFilters(PayoutEntity payout, PayoutListFilterConstraints constraints) {
        return matchesPayoutIdFilter(payout, constraints)
            && matchesPaymentIdFilter(payout, constraints)
            && matchesStatusFilter(payout, constraints)
            && matchesConnectorFilter(payout, constraints)
            && matchesCurrencyFilter(payout, constraints)
            && matchesTimeRangeFilter(payout, constraints)
            && matchesAmountRangeFilter(payout, constraints);
    }
    
    private boolean matchesPayoutIdFilter(PayoutEntity payout, PayoutListFilterConstraints constraints) {
        return constraints.getPayoutId() == null || constraints.getPayoutId().isEmpty() 
            || constraints.getPayoutId().equals(payout.getPayoutId());
    }
    
    private boolean matchesPaymentIdFilter(PayoutEntity payout, PayoutListFilterConstraints constraints) {
        // PayoutEntity doesn't have paymentId field - skip this filter
        return constraints.getPaymentId() == null || constraints.getPaymentId().isEmpty();
    }
    
    private boolean matchesStatusFilter(PayoutEntity payout, PayoutListFilterConstraints constraints) {
        return constraints.getStatus() == null || constraints.getStatus().isEmpty() 
            || constraints.getStatus().equalsIgnoreCase(payout.getStatus());
    }
    
    private boolean matchesConnectorFilter(PayoutEntity payout, PayoutListFilterConstraints constraints) {
        // PayoutEntity doesn't have connector field - skip this filter
        return constraints.getConnector() == null || constraints.getConnector().isEmpty();
    }
    
    private boolean matchesCurrencyFilter(PayoutEntity payout, PayoutListFilterConstraints constraints) {
        return constraints.getCurrency() == null || constraints.getCurrency().isEmpty() 
            || constraints.getCurrency().equalsIgnoreCase(payout.getDestinationCurrency());
    }
    
    private boolean matchesTimeRangeFilter(PayoutEntity payout, PayoutListFilterConstraints constraints) {
        if (constraints.getStartTime() != null && payout.getCreatedAt() != null 
            && payout.getCreatedAt().isBefore(constraints.getStartTime())) {
            return false;
        }
        return constraints.getEndTime() == null || payout.getCreatedAt() == null 
            || !payout.getCreatedAt().isAfter(constraints.getEndTime());
    }
    
    private boolean matchesAmountRangeFilter(PayoutEntity payout, PayoutListFilterConstraints constraints) {
        if (constraints.getMinAmount() != null && payout.getAmount() != null 
            && payout.getAmount() < constraints.getMinAmount()) {
            return false;
        }
        return constraints.getMaxAmount() == null || payout.getAmount() == null 
            || payout.getAmount() <= constraints.getMaxAmount();
    }
    
    /**
     * Build payout list response with pagination
     */
    private Mono<Either<PaymentError, PayoutListResponse>> buildPayoutListResponse(
            List<PayoutEntity> payouts,
            PayoutListFilterConstraints constraints) {
        int totalCount = payouts.size();
        int offset = constraints.getOffset() != null ? constraints.getOffset() : 0;
        int limit = constraints.getLimit() != null ? constraints.getLimit() : 100;
        
        List<PayoutEntity> paginatedPayouts = payouts.stream()
            .skip(offset)
            .limit(limit)
            .toList();
        
        List<PayoutResponse> payoutData = paginatedPayouts.stream()
            .map(PayoutMapper::toPayoutResponse)
            .toList();
        
        PayoutListResponse response = new PayoutListResponse();
        response.setData(payoutData);
        response.setTotalCount((long) totalCount);
        response.setLimit(limit);
        response.setOffset(offset);
        
        return Mono.just(Either.<PaymentError, PayoutListResponse>right(response));
    }
    
    @Override
    public Mono<Either<PaymentError, PayoutFiltersResponse>> getPayoutFilters(String merchantId) {
        log.info("Getting payout filters for merchant: {}", merchantId);
        
        return payoutRepository.findByMerchantIdOrderByCreatedAtDesc(merchantId)
            .collectList()
            .map(payouts -> {
                Set<String> connectors = new HashSet<>();
                Set<String> currencies = new HashSet<>();
                Set<String> statuses = new HashSet<>();
                
                for (PayoutEntity payout : payouts) {
                    // PayoutEntity doesn't have connector field - extract from metadata if available
                    String connector = extractConnectorFromMetadata(payout);
                    if (connector != null) {
                        connectors.add(connector);
                    }
                    if (payout.getDestinationCurrency() != null) {
                        currencies.add(payout.getDestinationCurrency());
                    }
                    if (payout.getStatus() != null) {
                        statuses.add(payout.getStatus());
                    }
                }
                
                PayoutFiltersResponse response = new PayoutFiltersResponse();
                response.setConnector(new ArrayList<>(connectors));
                response.setCurrency(new ArrayList<>(currencies));
                response.setStatus(new ArrayList<>(statuses));
                
                return Either.<PaymentError, PayoutFiltersResponse>right(response);
            })
            .onErrorResume(error -> {
                log.error("Error getting payout filters", error);
                return Mono.just(Either.<PaymentError, PayoutFiltersResponse>left(
                    PaymentError.of("PAYOUT_FILTERS_FAILED",
                        "Failed to get payout filters: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Either<PaymentError, PayoutAggregatesResponse>> getPayoutAggregates(
            String merchantId,
            Instant startTime,
            Instant endTime) {
        log.info("Getting payout aggregates for merchant: {} from {} to {}", merchantId, startTime, endTime);
        
        Flux<PayoutEntity> payoutsFlux = payoutRepository.findByMerchantIdOrderByCreatedAtDesc(merchantId);
        
        return payoutsFlux
            .filter(payout -> {
                if (startTime == null && endTime == null) {
                    return true;
                }
                if (payout.getCreatedAt() == null) {
                    return false;
                }
                if (startTime != null && payout.getCreatedAt().isBefore(startTime)) {
                    return false;
                }
                return endTime == null || !payout.getCreatedAt().isAfter(endTime);
            })
            .collectList()
            .map(payouts -> {
                Map<String, Long> statusCounts = new HashMap<>();
                long total = 0;
                
                for (PayoutEntity payout : payouts) {
                    String status = payout.getStatus() != null ? payout.getStatus() : "UNKNOWN";
                    statusCounts.put(status, statusCounts.getOrDefault(status, 0L) + 1);
                    total++;
                }
                
                return new PayoutAggregatesResponse(statusCounts, total);
            })
            .map(Either::<PaymentError, PayoutAggregatesResponse>right)
            .onErrorResume(error -> {
                log.error("Error getting payout aggregates", error);
                return Mono.just(Either.<PaymentError, PayoutAggregatesResponse>left(
                    PaymentError.of("PAYOUT_AGGREGATES_FAILED",
                        "Failed to get payout aggregates: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Extract connector from payout metadata if available
     */
    private String extractConnectorFromMetadata(PayoutEntity payout) {
        if (payout.getMetadata() != null) {
            try {
                Map<String, Object> metadata = objectMapper.readValue(
                    payout.getMetadata(),
                    new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {}
                );
                if (metadata.containsKey("connector") && metadata.get("connector") instanceof String connector) {
                    return connector;
                }
            } catch (Exception e) {
                log.debug("Failed to parse connector from metadata", e);
            }
        }
        return null;
    }
}

