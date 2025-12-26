package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.PaymentAttemptEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PaymentAttemptRepository extends ReactiveCrudRepository<PaymentAttemptEntity, String> {
    
    Mono<PaymentAttemptEntity> findByPaymentIdAndMerchantIdAndId(
        String paymentId, 
        String merchantId, 
        String attemptId
    );
    
    Flux<PaymentAttemptEntity> findByPaymentIdAndMerchantId(String paymentId, String merchantId);
    
    Mono<PaymentAttemptEntity> findFirstByPaymentIdAndMerchantIdOrderByCreatedAtDesc(
        String paymentId, 
        String merchantId
    );
}

