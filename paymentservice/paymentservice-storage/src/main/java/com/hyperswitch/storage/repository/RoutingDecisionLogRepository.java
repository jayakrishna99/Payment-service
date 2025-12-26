package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.RoutingDecisionLogEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Repository for routing decision log entities
 */
@Repository
public interface RoutingDecisionLogRepository extends R2dbcRepository<RoutingDecisionLogEntity, String> {
    
    /**
     * Find routing decision log by payment ID
     */
    Mono<RoutingDecisionLogEntity> findByPaymentId(String paymentId);
    
    /**
     * Find routing decision log by attempt ID
     */
    Mono<RoutingDecisionLogEntity> findByAttemptId(String attemptId);
}

