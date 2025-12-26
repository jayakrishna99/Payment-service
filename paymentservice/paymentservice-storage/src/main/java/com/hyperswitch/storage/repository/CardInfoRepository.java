package com.hyperswitch.storage.repository;

import com.hyperswitch.storage.entity.CardInfoEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Reactive repository for CardInfo entity
 */
@Repository
public interface CardInfoRepository extends R2dbcRepository<CardInfoEntity, String> {
    
    Mono<CardInfoEntity> findByCardIin(String cardIin);
}

