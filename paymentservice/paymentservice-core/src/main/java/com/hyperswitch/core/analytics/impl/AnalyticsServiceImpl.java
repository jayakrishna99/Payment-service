package com.hyperswitch.core.analytics.impl;

import com.hyperswitch.common.dto.ConnectorAnalyticsResponse;
import com.hyperswitch.common.dto.CustomerAnalyticsResponse;
import com.hyperswitch.common.dto.PaymentAnalyticsResponse;
import com.hyperswitch.common.dto.RevenueAnalyticsResponse;
import com.hyperswitch.common.enums.Connector;
import com.hyperswitch.common.analytics.AnalyticsService;
import com.hyperswitch.storage.entity.ConnectorSuccessRateEntity;
import com.hyperswitch.storage.entity.PaymentAttemptEntity;
import com.hyperswitch.storage.entity.PaymentIntentEntity;
import com.hyperswitch.storage.entity.SuccessRateWindowEntity;
import com.hyperswitch.storage.repository.ConnectorSuccessRateRepository;
import com.hyperswitch.storage.repository.PaymentAttemptRepository;
import com.hyperswitch.storage.repository.PaymentIntentRepository;
import com.hyperswitch.storage.repository.RefundRepository;
import com.hyperswitch.storage.repository.SuccessRateWindowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Implementation of AnalyticsService
 */
@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private static final Logger log = LoggerFactory.getLogger(AnalyticsServiceImpl.class);

    private final ConnectorSuccessRateRepository successRateRepository;
    private final PaymentIntentRepository paymentIntentRepository;
    private final PaymentAttemptRepository paymentAttemptRepository;
    private final RefundRepository refundRepository;
    private final SuccessRateWindowRepository successRateWindowRepository;

    @Autowired
    public AnalyticsServiceImpl(
            ConnectorSuccessRateRepository successRateRepository,
            PaymentIntentRepository paymentIntentRepository,
            PaymentAttemptRepository paymentAttemptRepository,
            RefundRepository refundRepository,
            SuccessRateWindowRepository successRateWindowRepository) {
        this.successRateRepository = successRateRepository;
        this.paymentIntentRepository = paymentIntentRepository;
        this.paymentAttemptRepository = paymentAttemptRepository;
        this.refundRepository = refundRepository;
        this.successRateWindowRepository = successRateWindowRepository;
    }

    @Override
    public Mono<Void> recordPaymentAttempt(
            String merchantId,
            String profileId,
            Connector connector,
            String paymentMethod,
            String currency,
            boolean success) {
        
        log.debug("Recording payment attempt: merchant={}, connector={}, success={}", 
            merchantId, connector, success);
        
        String connectorName = connector.name();
        String pm = paymentMethod != null ? paymentMethod : "unknown";
        String curr = currency != null ? currency : "unknown";
        
        return successRateRepository
            .findByMerchantIdAndConnectorAndProfileIdAndPaymentMethodAndCurrency(
                merchantId, connectorName, profileId, pm, curr
            )
            .switchIfEmpty(createNewSuccessRate(merchantId, profileId, connectorName, pm, curr))
            .flatMap(entity -> {
                // Update statistics
                entity.setTotalAttempts(entity.getTotalAttempts() + 1);
                if (success) {
                    entity.setSuccessfulAttempts(entity.getSuccessfulAttempts() + 1);
                } else {
                    entity.setFailedAttempts(entity.getFailedAttempts() + 1);
                }
                
                // Recalculate success rate
                if (entity.getTotalAttempts() > 0) {
                    BigDecimal rate = BigDecimal.valueOf(entity.getSuccessfulAttempts())
                        .divide(BigDecimal.valueOf(entity.getTotalAttempts()), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));
                    entity.setSuccessRate(rate);
                }
                
                entity.setLastCalculatedAt(Instant.now());
                entity.setModifiedAt(Instant.now());
                
                return successRateRepository.save(entity);
            })
            .then();
    }

    @Override
    public Mono<BigDecimal> getSuccessRate(
            String merchantId,
            String profileId,
            Connector connector,
            String paymentMethod,
            String currency) {
        
        String connectorName = connector.name();
        String pm = paymentMethod != null ? paymentMethod : "unknown";
        String curr = currency != null ? currency : "unknown";
        
        return successRateRepository
            .findByMerchantIdAndConnectorAndProfileIdAndPaymentMethodAndCurrency(
                merchantId, connectorName, profileId, pm, curr
            )
            .map(ConnectorSuccessRateEntity::getSuccessRate)
            .defaultIfEmpty(BigDecimal.ZERO);
    }

    @Override
    public Mono<Void> recalculateSuccessRates(String merchantId, String profileId) {
        log.info("Recalculating success rates for merchant: {}, profile: {}", merchantId, profileId);
        
        return successRateRepository
            .findByMerchantIdAndProfileId(merchantId, profileId)
            .flatMap(entity -> {
                if (entity.getTotalAttempts() > 0) {
                    BigDecimal rate = BigDecimal.valueOf(entity.getSuccessfulAttempts())
                        .divide(BigDecimal.valueOf(entity.getTotalAttempts()), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));
                    entity.setSuccessRate(rate);
                    entity.setLastCalculatedAt(Instant.now());
                    entity.setModifiedAt(Instant.now());
                    return successRateRepository.save(entity);
                }
                return Mono.just(entity);
            })
            .then();
    }

    private Mono<ConnectorSuccessRateEntity> createNewSuccessRate(
            String merchantId,
            String profileId,
            String connector,
            String paymentMethod,
            String currency) {
        
        ConnectorSuccessRateEntity entity = new ConnectorSuccessRateEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setMerchantId(merchantId);
        entity.setProfileId(profileId);
        entity.setConnector(connector);
        entity.setPaymentMethod(paymentMethod);
        entity.setCurrency(currency);
        entity.setTotalAttempts(0L);
        entity.setSuccessfulAttempts(0L);
        entity.setFailedAttempts(0L);
        entity.setSuccessRate(BigDecimal.ZERO);
        entity.setLastCalculatedAt(Instant.now());
        entity.setCreatedAt(Instant.now());
        entity.setModifiedAt(Instant.now());
        
        return Mono.just(entity);
    }

    @Override
    public Mono<PaymentAnalyticsResponse> getPaymentAnalytics(
            String merchantId,
            Instant startDate,
            Instant endDate,
            String currency) {
        log.info("Getting payment analytics for merchant: {}, from {} to {}", merchantId, startDate, endDate);
        
        return paymentIntentRepository.findAll()
            .filter(intent -> intent.getMerchantId().equals(merchantId))
            .filter(intent -> startDate == null || intent.getCreatedAt().isAfter(startDate))
            .filter(intent -> endDate == null || intent.getCreatedAt().isBefore(endDate))
            .filter(intent -> currency == null || currency.equals(intent.getCurrency()))
            .collectList()
            .map(intents -> buildPaymentAnalyticsResponse(intents, startDate, endDate, currency));
    }
    
    private PaymentAnalyticsResponse buildPaymentAnalyticsResponse(
            java.util.List<PaymentIntentEntity> intents,
            Instant startDate,
            Instant endDate,
            String currency) {
        PaymentAnalyticsResponse response = new PaymentAnalyticsResponse();
        response.setStartDate(startDate);
        response.setEndDate(endDate);
        response.setCurrency(currency);
        
        long total = intents.size();
        long successful = countSuccessfulPayments(intents);
        long failed = countFailedPayments(intents);
        long pending = countPendingPayments(intents);
        
        response.setTotalPayments(total);
        response.setSuccessfulPayments(successful);
        response.setFailedPayments(failed);
        response.setPendingPayments(pending);
        response.setSuccessRate(calculateSuccessRate(successful, total));
        
        response.setTotalVolume(calculateTotalVolume(intents));
        response.setSuccessfulVolume(calculateSuccessfulVolume(intents));
        response.setFailedVolume(calculateFailedVolume(intents));
        
        return response;
    }
    
    private long countSuccessfulPayments(java.util.List<PaymentIntentEntity> intents) {
        return intents.stream()
            .filter(i -> "SUCCEEDED".equals(i.getStatus()) || "PARTIALLY_CAPTURED".equals(i.getStatus()))
            .count();
    }
    
    private long countFailedPayments(java.util.List<PaymentIntentEntity> intents) {
        return intents.stream()
            .filter(i -> "FAILED".equals(i.getStatus()) || "CANCELLED".equals(i.getStatus()))
            .count();
    }
    
    private long countPendingPayments(java.util.List<PaymentIntentEntity> intents) {
        return intents.stream()
            .filter(i -> "REQUIRES_CONFIRMATION".equals(i.getStatus()) || "REQUIRES_CAPTURE".equals(i.getStatus()))
            .count();
    }
    
    private BigDecimal calculateSuccessRate(long successful, long total) {
        if (total > 0) {
            return BigDecimal.valueOf(successful)
                .divide(BigDecimal.valueOf(total), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        }
        return BigDecimal.ZERO;
    }
    
    private long calculateTotalVolume(java.util.List<PaymentIntentEntity> intents) {
        return intents.stream()
            .mapToLong(i -> i.getAmount() != null ? i.getAmount() : 0L)
            .sum();
    }
    
    private long calculateSuccessfulVolume(java.util.List<PaymentIntentEntity> intents) {
        return intents.stream()
            .filter(i -> "SUCCEEDED".equals(i.getStatus()) || "PARTIALLY_CAPTURED".equals(i.getStatus()))
            .mapToLong(i -> i.getAmount() != null ? i.getAmount() : 0L)
            .sum();
    }
    
    private long calculateFailedVolume(java.util.List<PaymentIntentEntity> intents) {
        return intents.stream()
            .filter(i -> "FAILED".equals(i.getStatus()) || "CANCELLED".equals(i.getStatus()))
            .mapToLong(i -> i.getAmount() != null ? i.getAmount() : 0L)
            .sum();
    }

    @Override
    public Flux<ConnectorAnalyticsResponse> getConnectorAnalytics(
            String merchantId,
            Instant startDate,
            Instant endDate) {
        log.info("Getting connector analytics for merchant: {}, from {} to {}", merchantId, startDate, endDate);
        
        return paymentAttemptRepository.findAll()
            .filter(attempt -> attempt.getMerchantId().equals(merchantId))
            .filter(attempt -> startDate == null || attempt.getCreatedAt().isAfter(startDate))
            .filter(attempt -> endDate == null || attempt.getCreatedAt().isBefore(endDate))
            .groupBy(PaymentAttemptEntity::getConnector)
            .flatMap(group -> {
                String connectorId = group.key();
                return group.collectList()
                    .map(attempts -> {
                        ConnectorAnalyticsResponse response = new ConnectorAnalyticsResponse();
                        response.setConnectorId(connectorId);
                        response.setConnectorName(connectorId);
                        response.setStartDate(startDate);
                        response.setEndDate(endDate);
                        
                        long total = attempts.size();
                        long successful = attempts.stream()
                            .filter(a -> "SUCCEEDED".equals(a.getStatus()))
                            .count();
                        long failed = attempts.stream()
                            .filter(a -> "FAILED".equals(a.getStatus()))
                            .count();
                        
                        response.setTotalAttempts(total);
                        response.setSuccessfulAttempts(successful);
                        response.setFailedAttempts(failed);
                        
                        if (total > 0) {
                            BigDecimal successRate = BigDecimal.valueOf(successful)
                                .divide(BigDecimal.valueOf(total), 4, RoundingMode.HALF_UP)
                                .multiply(BigDecimal.valueOf(100));
                            response.setSuccessRate(successRate);
                        } else {
                            response.setSuccessRate(BigDecimal.ZERO);
                        }
                        
                        // Note: Volume calculation would require joining with payment_intent
                        response.setTotalVolume(0L);
                        response.setSuccessfulVolume(0L);
                        
                        return response;
                    });
            });
    }

    @Override
    public Mono<RevenueAnalyticsResponse> getRevenueAnalytics(
            String merchantId,
            Instant startDate,
            Instant endDate) {
        log.info("Getting revenue analytics for merchant: {}, from {} to {}", merchantId, startDate, endDate);
        
        return paymentIntentRepository.findAll()
            .filter(intent -> intent.getMerchantId().equals(merchantId))
            .filter(intent -> startDate == null || intent.getCreatedAt().isAfter(startDate))
            .filter(intent -> endDate == null || intent.getCreatedAt().isBefore(endDate))
            .collectList()
            .flatMap(intents -> {
                RevenueAnalyticsResponse response = buildRevenueAnalyticsResponse(intents, startDate, endDate);
                return enrichWithRefundData(response, merchantId, startDate, endDate, intents);
            });
    }
    
    private RevenueAnalyticsResponse buildRevenueAnalyticsResponse(
            java.util.List<PaymentIntentEntity> intents,
            Instant startDate,
            Instant endDate) {
        RevenueAnalyticsResponse response = new RevenueAnalyticsResponse();
        response.setStartDate(startDate);
        response.setEndDate(endDate);
        
        long totalRevenue = calculateTotalRevenue(intents);
        response.setTotalRevenue(totalRevenue);
        response.setCapturedAmount(calculateCapturedAmount(intents));
        response.setAuthorizedAmount(calculateAuthorizedAmount(intents));
        
        return response;
    }
    
    private Mono<RevenueAnalyticsResponse> enrichWithRefundData(
            RevenueAnalyticsResponse response,
            String merchantId,
            Instant startDate,
            Instant endDate,
            java.util.List<PaymentIntentEntity> intents) {
        return refundRepository.findAll()
            .filter(refund -> refund.getMerchantId().equals(merchantId))
            .filter(refund -> startDate == null || refund.getCreatedAt().isAfter(startDate))
            .filter(refund -> endDate == null || refund.getCreatedAt().isBefore(endDate))
            .collectList()
            .map(refunds -> {
                long refunded = calculateRefundedAmount(refunds);
                long totalRevenue = response.getTotalRevenue();
                
                response.setRefundedAmount(refunded);
                response.setNetRevenue(totalRevenue - refunded);
                response.setRefundRate(calculateRefundRate(refunded, totalRevenue));
                response.setRevenueByCurrency(calculateRevenueByCurrency(intents));
                
                return response;
            });
    }
    
    private long calculateTotalRevenue(java.util.List<PaymentIntentEntity> intents) {
        return intents.stream()
            .filter(i -> "SUCCEEDED".equals(i.getStatus()) || "PARTIALLY_CAPTURED".equals(i.getStatus()))
            .mapToLong(i -> i.getAmount() != null ? i.getAmount() : 0L)
            .sum();
    }
    
    private long calculateCapturedAmount(java.util.List<PaymentIntentEntity> intents) {
        return intents.stream()
            .mapToLong(i -> i.getAmountCaptured() != null ? i.getAmountCaptured() : 0L)
            .sum();
    }
    
    private long calculateAuthorizedAmount(java.util.List<PaymentIntentEntity> intents) {
        return intents.stream()
            .filter(i -> "REQUIRES_CAPTURE".equals(i.getStatus()))
            .mapToLong(i -> i.getAmount() != null ? i.getAmount() : 0L)
            .sum();
    }
    
    private long calculateRefundedAmount(java.util.List<com.hyperswitch.storage.entity.RefundEntity> refunds) {
        return refunds.stream()
            .filter(r -> "SUCCEEDED".equals(r.getRefundStatus()))
            .mapToLong(r -> r.getRefundAmount() != null ? r.getRefundAmount() : 0L)
            .sum();
    }
    
    private BigDecimal calculateRefundRate(long refunded, long totalRevenue) {
        if (totalRevenue > 0) {
            return BigDecimal.valueOf(refunded)
                .divide(BigDecimal.valueOf(totalRevenue), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        }
        return BigDecimal.ZERO;
    }
    
    private Map<String, Long> calculateRevenueByCurrency(java.util.List<PaymentIntentEntity> intents) {
        Map<String, Long> revenueByCurrency = new HashMap<>();
        intents.stream()
            .filter(i -> "SUCCEEDED".equals(i.getStatus()) || "PARTIALLY_CAPTURED".equals(i.getStatus()))
            .forEach(i -> {
                String curr = i.getCurrency() != null ? i.getCurrency() : "unknown";
                revenueByCurrency.merge(curr, i.getAmount() != null ? i.getAmount() : 0L, Long::sum);
            });
        return revenueByCurrency;
    }

    @Override
    public Mono<CustomerAnalyticsResponse> getCustomerAnalytics(
            String merchantId,
            String customerId) {
        log.info("Getting customer analytics for merchant: {}, customer: {}", merchantId, customerId);
        
        return paymentIntentRepository.findAll()
            .filter(intent -> intent.getMerchantId().equals(merchantId))
            .filter(intent -> customerId.equals(intent.getCustomerId()))
            .collectList()
            .map(intents -> {
                CustomerAnalyticsResponse response = new CustomerAnalyticsResponse();
                response.setCustomerId(customerId);
                
                long total = intents.size();
                long successful = intents.stream()
                    .filter(i -> "SUCCEEDED".equals(i.getStatus()) || "PARTIALLY_CAPTURED".equals(i.getStatus()))
                    .count();
                long failed = intents.stream()
                    .filter(i -> "FAILED".equals(i.getStatus()) || "CANCELLED".equals(i.getStatus()))
                    .count();
                
                response.setTotalPayments(total);
                response.setSuccessfulPayments(successful);
                response.setFailedPayments(failed);
                
                if (total > 0) {
                    BigDecimal successRate = BigDecimal.valueOf(successful)
                        .divide(BigDecimal.valueOf(total), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));
                    response.setSuccessRate(successRate);
                } else {
                    response.setSuccessRate(BigDecimal.ZERO);
                }
                
                long totalSpent = intents.stream()
                    .filter(i -> "SUCCEEDED".equals(i.getStatus()) || "PARTIALLY_CAPTURED".equals(i.getStatus()))
                    .mapToLong(i -> i.getAmount() != null ? i.getAmount() : 0L)
                    .sum();
                response.setTotalSpent(totalSpent);
                response.setLifetimeValue(totalSpent);
                
                if (successful > 0) {
                    response.setAverageTransactionValue(totalSpent / successful);
                } else {
                    response.setAverageTransactionValue(0L);
                }
                
                if (!intents.isEmpty()) {
                    Instant first = intents.stream()
                        .map(PaymentIntentEntity::getCreatedAt)
                        .min(Instant::compareTo)
                        .orElse(Instant.now());
                    response.setFirstPaymentDate(first);
                    
                    Instant last = intents.stream()
                        .map(PaymentIntentEntity::getCreatedAt)
                        .max(Instant::compareTo)
                        .orElse(Instant.now());
                    response.setLastPaymentDate(last);
                }
                
                return response;
            });
    }
    
    @Override
    public Mono<Void> updateSuccessRateWindow(
            String profileId,
            String connector,
            String paymentMethod,
            String currency,
            boolean success,
            int windowDurationMinutes) {
        
        log.debug("Updating success rate window: profile={}, connector={}, success={}, window={}min",
            profileId, connector, success, windowDurationMinutes);
        
        Instant now = Instant.now();
        Instant windowStart = now.minusSeconds(windowDurationMinutes * 60L);
        Instant windowEnd = now;
        
        String pm = paymentMethod != null ? paymentMethod : "unknown";
        String curr = currency != null ? currency : "unknown";
        
        // Find or create window
        return successRateWindowRepository
            .findLatestByProfileIdAndConnector(profileId, connector)
            .filter(window -> {
                // Check if the latest window is still active (within window duration)
                Instant windowEndTime = window.getWindowEnd();
                return windowEndTime.isAfter(windowStart);
            })
            .switchIfEmpty(Mono.defer(() -> {
                // Create new window
                SuccessRateWindowEntity newWindow = new SuccessRateWindowEntity();
                newWindow.setId("window_" + UUID.randomUUID().toString().replace("-", ""));
                newWindow.setProfileId(profileId);
                newWindow.setConnector(connector);
                newWindow.setPaymentMethod(pm);
                newWindow.setCurrency(curr);
                newWindow.setWindowStart(windowStart);
                newWindow.setWindowEnd(windowEnd);
                newWindow.setTotalAttempts(0L);
                newWindow.setSuccessfulAttempts(0L);
                newWindow.setFailedAttempts(0L);
                newWindow.setSuccessRate(BigDecimal.ZERO);
                newWindow.setCreatedAt(now);
                newWindow.setModifiedAt(now);
                return Mono.just(newWindow);
            }))
            .flatMap(window -> {
                // Update window statistics
                window.setTotalAttempts(window.getTotalAttempts() + 1);
                if (success) {
                    window.setSuccessfulAttempts(window.getSuccessfulAttempts() + 1);
                } else {
                    window.setFailedAttempts(window.getFailedAttempts() + 1);
                }
                
                // Recalculate success rate
                if (window.getTotalAttempts() > 0) {
                    BigDecimal rate = BigDecimal.valueOf(window.getSuccessfulAttempts())
                        .divide(BigDecimal.valueOf(window.getTotalAttempts()), 4, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));
                    window.setSuccessRate(rate);
                }
                
                window.setModifiedAt(now);
                window.setWindowEnd(windowEnd); // Extend window end time
                
                return successRateWindowRepository.save(window);
            })
            .then();
    }
    
    @Override
    public Mono<BigDecimal> getWindowedSuccessRate(
            String profileId,
            String connector,
            String paymentMethod,
            String currency,
            int windowDurationMinutes) {
        
        log.debug("Getting windowed success rate: profile={}, connector={}, window={}min",
            profileId, connector, windowDurationMinutes);
        
        Instant now = Instant.now();
        Instant windowStart = now.minusSeconds(windowDurationMinutes * 60L);
        Instant windowEnd = now;
        
        String pm = paymentMethod != null ? paymentMethod : "unknown";
        String curr = currency != null ? currency : "unknown";
        
        return successRateWindowRepository
            .findByProfileIdAndConnectorAndPaymentMethodAndCurrencyAndTimeRange(
                profileId, connector, pm, curr, windowStart, windowEnd
            )
            .collectList()
            .map(windows -> {
                if (windows.isEmpty()) {
                    return BigDecimal.ZERO;
                }
                
                // Aggregate across all windows
                long totalAttempts = windows.stream()
                    .mapToLong(SuccessRateWindowEntity::getTotalAttempts)
                    .sum();
                long successfulAttempts = windows.stream()
                    .mapToLong(SuccessRateWindowEntity::getSuccessfulAttempts)
                    .sum();
                
                if (totalAttempts == 0) {
                    return BigDecimal.ZERO;
                }
                
                return BigDecimal.valueOf(successfulAttempts)
                    .divide(BigDecimal.valueOf(totalAttempts), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
            })
            .defaultIfEmpty(BigDecimal.ZERO);
    }
}

