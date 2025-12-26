package com.hyperswitch.core.payoutlink;

import com.hyperswitch.common.dto.PayoutLinkResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for payout link operations
 */
public interface PayoutLinkService {
    
    /**
     * Render payout link
     */
    Mono<Result<PayoutLinkResponse, PaymentError>> renderPayoutLink(
            String merchantId,
            String payoutId);
}

