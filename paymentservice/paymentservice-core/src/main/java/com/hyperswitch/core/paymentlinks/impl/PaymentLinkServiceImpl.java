package com.hyperswitch.core.paymentlinks.impl;

import com.hyperswitch.common.dto.PaymentLinkRequest;
import com.hyperswitch.common.dto.PaymentLinkResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.PaymentLinkId;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.paymentlinks.PaymentLinkService;
import com.hyperswitch.core.payments.PaymentService;
import com.hyperswitch.storage.entity.PaymentLinkEntity;
import com.hyperswitch.storage.repository.PaymentLinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Implementation of PaymentLinkService
 */
@Service
public class PaymentLinkServiceImpl implements PaymentLinkService {

    private static final Logger log = LoggerFactory.getLogger(PaymentLinkServiceImpl.class);
    private static final String PAYMENT_LINK_NOT_FOUND_MSG = "Payment link not found";

    private final PaymentLinkRepository paymentLinkRepository;
    private final PaymentLinkMapper paymentLinkMapper;
    private final PaymentService paymentService;
    private final com.hyperswitch.core.metrics.PaymentMetrics paymentMetrics;
    
    @Value("${payment.link.base-url:http://localhost:8080}")
    private String baseUrl;

    @Autowired
    public PaymentLinkServiceImpl(
            PaymentLinkRepository paymentLinkRepository,
            PaymentLinkMapper paymentLinkMapper,
            PaymentService paymentService,
            com.hyperswitch.core.metrics.PaymentMetrics paymentMetrics) {
        this.paymentLinkRepository = paymentLinkRepository;
        this.paymentLinkMapper = paymentLinkMapper;
        this.paymentService = paymentService;
        this.paymentMetrics = paymentMetrics;
    }

    @Override
    public Mono<Result<PaymentLinkResponse, PaymentError>> createPaymentLink(PaymentLinkRequest request) {
        log.info("Creating payment link for merchant: {}", request.getMerchantId());
        
        // First create a payment intent
        return createPaymentForLink(request)
            .flatMap(paymentResult -> {
                if (paymentResult.isErr()) {
                    return Mono.just(Result.<PaymentLinkResponse, PaymentError>err(paymentResult.unwrapErr()));
                }
                
                String paymentId = paymentResult.unwrap().getPaymentId().getValue();
                PaymentLinkId paymentLinkId = PaymentLinkId.generate();
                Instant now = Instant.now();
                
                // Generate payment link URL
                String linkToPay = generatePaymentLinkUrl(request.getMerchantId(), paymentId);
                String secureLink = null;
                if (Boolean.TRUE.equals(request.getGenerateSecureLink())) {
                    secureLink = generateSecurePaymentLinkUrl(request.getMerchantId(), paymentId);
                }
                
                PaymentLinkEntity entity = PaymentLinkEntity.builder()
                    .paymentLinkId(paymentLinkId.getValue())
                    .paymentId(paymentId)
                    .linkToPay(linkToPay)
                    .secureLink(secureLink)
                    .merchantId(request.getMerchantId())
                    .amount(convertToMinorUnits(request.getAmount()))
                    .currency(request.getAmount().getCurrencyCode())
                    .description(request.getDescription())
                    .customMerchantName(request.getCustomMerchantName())
                    .paymentLinkConfig(request.getPaymentLinkConfig())
                    .profileId(request.getProfileId())
                    .expiresAt(request.getExpiresAt())
                    .createdAt(now)
                    .lastModifiedAt(now)
                    .build();
                
                return paymentLinkRepository.save(entity)
                    .map(saved -> {
                        PaymentLinkResponse response = paymentLinkMapper.toPaymentLinkResponse(saved);
                        paymentMetrics.incrementPaymentLinkCreated();
                        return Result.<PaymentLinkResponse, PaymentError>ok(response);
                    });
            })
            .onErrorResume(error -> {
                log.error("Error creating payment link", error);
                return Mono.just(Result.<PaymentLinkResponse, PaymentError>err(PaymentError.of(
                    "PAYMENT_LINK_CREATE_FAILED",
                    "Failed to create payment link: " + error.getMessage()
                )));
            });
    }

    @Override
    public Mono<Result<PaymentLinkResponse, PaymentError>> getPaymentLink(PaymentLinkId paymentLinkId) {
        log.info("Getting payment link: {}", paymentLinkId);
        
        return paymentLinkRepository.findByPaymentLinkId(paymentLinkId.getValue())
            .map(paymentLinkMapper::toPaymentLinkResponse)
            .map(Result::<PaymentLinkResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<PaymentLinkResponse, PaymentError>err(
                PaymentError.of("PAYMENT_LINK_NOT_FOUND", PAYMENT_LINK_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error getting payment link", error);
                return Mono.just(Result.<PaymentLinkResponse, PaymentError>err(PaymentError.of(
                    "PAYMENT_LINK_GET_FAILED",
                    "Failed to get payment link: " + error.getMessage()
                )));
            });
    }

    @Override
    public Mono<Result<PaymentLinkResponse, PaymentError>> getPaymentLinkByPaymentId(String paymentId) {
        log.info("Getting payment link by payment ID: {}", paymentId);
        
        return paymentLinkRepository.findByPaymentId(paymentId)
            .map(paymentLinkMapper::toPaymentLinkResponse)
            .map(Result::<PaymentLinkResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.<PaymentLinkResponse, PaymentError>err(
                PaymentError.of("PAYMENT_LINK_NOT_FOUND", PAYMENT_LINK_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error getting payment link by payment ID", error);
                return Mono.just(Result.<PaymentLinkResponse, PaymentError>err(PaymentError.of(
                    "PAYMENT_LINK_GET_FAILED",
                    "Failed to get payment link: " + error.getMessage()
                )));
            });
    }

    @Override
    public Flux<PaymentLinkResponse> listPaymentLinks(String merchantId, int page, int size) {
        log.info("Listing payment links for merchant: {}, page: {}, size: {}", merchantId, page, size);
        
        Pageable pageable = PageRequest.of(page, size);
        return paymentLinkRepository.findByMerchantIdOrderByCreatedAtDesc(merchantId, pageable)
            .map(paymentLinkMapper::toPaymentLinkResponse)
            .onErrorResume(error -> {
                log.error("Error listing payment links", error);
                return Flux.empty();
            });
    }

    @Override
    public Mono<Result<PaymentLinkResponse, PaymentError>> validatePaymentLink(PaymentLinkId paymentLinkId) {
        log.info("Validating payment link: {}", paymentLinkId);
        
        return paymentLinkRepository.findByPaymentLinkId(paymentLinkId.getValue())
            .flatMap(entity -> {
                // Check if link is expired
                if (entity.getExpiresAt() != null && entity.getExpiresAt().isBefore(Instant.now())) {
                    return Mono.just(Result.<PaymentLinkResponse, PaymentError>err(PaymentError.of(
                        "PAYMENT_LINK_EXPIRED",
                        "Payment link has expired"
                    )));
                }
                
                PaymentLinkResponse response = paymentLinkMapper.toPaymentLinkResponse(entity);
                return Mono.just(Result.<PaymentLinkResponse, PaymentError>ok(response));
            })
            .switchIfEmpty(Mono.just(Result.<PaymentLinkResponse, PaymentError>err(
                PaymentError.of("PAYMENT_LINK_NOT_FOUND", PAYMENT_LINK_NOT_FOUND_MSG)
            )))
            .onErrorResume(error -> {
                log.error("Error validating payment link", error);
                return Mono.just(Result.<PaymentLinkResponse, PaymentError>err(PaymentError.of(
                    "PAYMENT_LINK_VALIDATION_FAILED",
                    "Failed to validate payment link: " + error.getMessage()
                )));
            });
    }

    /**
     * Create a payment intent for the payment link
     */
    private Mono<Result<com.hyperswitch.core.payments.PaymentIntent, PaymentError>> createPaymentForLink(
            PaymentLinkRequest request) {
        com.hyperswitch.common.dto.CreatePaymentRequest paymentRequest = 
            com.hyperswitch.common.dto.CreatePaymentRequest.builder()
                .amount(request.getAmount())
                .merchantId(request.getMerchantId())
                .description(request.getDescription())
                .build();
        
        return paymentService.createPayment(paymentRequest);
    }

    /**
     * Generate payment link URL
     */
    private String generatePaymentLinkUrl(String merchantId, String paymentId) {
        return String.format("%s/payment_link/%s/%s", baseUrl, merchantId, paymentId);
    }

    /**
     * Generate secure payment link URL
     */
    private String generateSecurePaymentLinkUrl(String merchantId, String paymentId) {
        return String.format("%s/payment_link/s/%s/%s", baseUrl, merchantId, paymentId);
    }

    /**
     * Convert amount to minor units (cents)
     */
    private Long convertToMinorUnits(com.hyperswitch.common.types.Amount amount) {
        return amount.getValue()
            .multiply(java.math.BigDecimal.valueOf(100))
            .longValue();
    }
}

