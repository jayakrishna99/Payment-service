package com.hyperswitch.core.poll;

import com.hyperswitch.common.dto.PollResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for poll operations
 */
public interface PollService {
    
    /**
     * Retrieve poll status
     */
    Mono<Result<PollResponse, PaymentError>> getPollStatus(
            String merchantId,
            String pollId);
}

