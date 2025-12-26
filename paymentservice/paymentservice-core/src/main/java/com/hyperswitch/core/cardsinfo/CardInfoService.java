package com.hyperswitch.core.cardsinfo;

import com.hyperswitch.common.dto.BatchCardInfoRequest;
import com.hyperswitch.common.dto.CardInfoRequest;
import com.hyperswitch.common.dto.CardInfoResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for card info operations
 */
public interface CardInfoService {
    
    /**
     * Create card info
     */
    Mono<Result<CardInfoResponse, PaymentError>> createCardInfo(CardInfoRequest request);
    
    /**
     * Update card info
     */
    Mono<Result<CardInfoResponse, PaymentError>> updateCardInfo(String cardIin, CardInfoRequest request);
    
    /**
     * Batch update card info
     */
    Flux<CardInfoResponse> batchUpdateCardInfo(BatchCardInfoRequest request);
    
    /**
     * Get card IIN info
     */
    Mono<Result<CardInfoResponse, PaymentError>> getCardInfo(String cardIin);
}

