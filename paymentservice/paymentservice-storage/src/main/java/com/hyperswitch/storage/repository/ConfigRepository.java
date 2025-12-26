package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.ConfigEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for Config entity
 */
@Repository
public interface ConfigRepository extends R2dbcRepository<ConfigEntity, String> {
    
    Mono<ConfigEntity> findByKey(String key);
}

