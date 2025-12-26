package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.ScheduledTaskEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Repository
public interface ScheduledTaskRepository extends ReactiveCrudRepository<ScheduledTaskEntity, String> {
    
    Flux<ScheduledTaskEntity> findByStatusAndScheduledAtLessThanEqual(String status, Instant scheduledAt);
    
    Flux<ScheduledTaskEntity> findByStatusAndScheduledAtLessThanEqualOrderByScheduledAtAsc(
        String status, 
        Instant scheduledAt
    );
    
    Mono<ScheduledTaskEntity> findByTaskId(String taskId);
    
    Flux<ScheduledTaskEntity> findByMerchantIdAndStatus(String merchantId, String status);
}

