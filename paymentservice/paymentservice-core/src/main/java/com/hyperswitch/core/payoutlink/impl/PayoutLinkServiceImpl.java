package com.hyperswitch.core.payoutlink.impl;

import com.hyperswitch.common.dto.PayoutLinkResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.payoutlink.PayoutLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * Implementation of PayoutLinkService
 */
@Service
public class PayoutLinkServiceImpl implements PayoutLinkService {
    
    private static final Logger log = LoggerFactory.getLogger(PayoutLinkServiceImpl.class);
    
    @Value("${hyperswitch.payout-link.base-url:https://hyperswitch.io/payout}")
    private String baseUrl;
    
    @Override
    public Mono<Result<PayoutLinkResponse, PaymentError>> renderPayoutLink(
            String merchantId,
            String payoutId) {
        
        log.info("Rendering payout link for merchant: {}, payout: {}", merchantId, payoutId);
        
        return Mono.fromCallable(() -> {
            PayoutLinkResponse response = new PayoutLinkResponse();
            response.setPayoutId(payoutId);
            response.setMerchantId(merchantId);
            response.setLinkUrl(baseUrl + "/" + merchantId + "/" + payoutId);
            response.setStatus("ACTIVE");
            response.setCreatedAt(Instant.now());
            response.setExpiresAt(Instant.now().plusSeconds(86400)); // 24 hours
            
            // In production, this would:
            // 1. Validate payout exists
            // 2. Generate secure link with token
            // 3. Store link in database
            // 4. Return link URL
            
            return Result.<PayoutLinkResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error rendering payout link: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("PAYOUT_LINK_RENDER_FAILED",
                "Failed to render payout link: " + error.getMessage())));
        });
    }
}

