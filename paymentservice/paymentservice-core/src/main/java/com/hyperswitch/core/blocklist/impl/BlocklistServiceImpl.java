package com.hyperswitch.core.blocklist.impl;

import com.hyperswitch.common.dto.BlocklistRequest;
import com.hyperswitch.common.dto.BlocklistResponse;
import com.hyperswitch.common.dto.BlocklistToggleRequest;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.blocklist.BlocklistService;
import com.hyperswitch.storage.entity.BlocklistEntity;
import com.hyperswitch.storage.repository.BlocklistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of BlocklistService
 */
@Service
public class BlocklistServiceImpl implements BlocklistService {
    
    private static final Logger log = LoggerFactory.getLogger(BlocklistServiceImpl.class);
    
    private final BlocklistRepository blocklistRepository;
    
    @Autowired
    public BlocklistServiceImpl(BlocklistRepository blocklistRepository) {
        this.blocklistRepository = blocklistRepository;
    }
    
    @Override
    public Mono<Result<BlocklistResponse, PaymentError>> listBlocklist(
            String merchantId,
            String dataKind) {
        log.info("Listing blocklist for merchant: {}, dataKind: {}", merchantId, dataKind);
        
        reactor.core.publisher.Flux<BlocklistEntity> query;
        if (dataKind != null && !dataKind.isEmpty()) {
            query = blocklistRepository.findByMerchantIdAndDataKind(merchantId, dataKind);
        } else {
            query = blocklistRepository.findByMerchantId(merchantId);
        }
        
        return query
            .map(this::toBlocklistEntry)
            .collectList()
            .map(entries -> {
                BlocklistResponse response = new BlocklistResponse();
                response.setEntries(entries);
                response.setTotal(entries.size());
                return Result.<BlocklistResponse, PaymentError>ok(response);
            })
            .defaultIfEmpty(Result.ok(new BlocklistResponse() {
                {
                    setEntries(new ArrayList<>());
                    setTotal(0);
                }
            }))
            .onErrorResume(error -> {
                log.error("Error listing blocklist", error);
                return Mono.just(Result.<BlocklistResponse, PaymentError>err(
                    PaymentError.of("BLOCKLIST_LIST_FAILED",
                        "Failed to list blocklist: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<BlocklistResponse.BlocklistEntry, PaymentError>> addToBlocklist(
            String merchantId,
            BlocklistRequest request) {
        log.info("Adding to blocklist for merchant: {}, fingerprint: {}", merchantId, request.getFingerprintId());
        
        return blocklistRepository.findByMerchantIdAndFingerprintId(merchantId, request.getFingerprintId())
            .flatMap(existing -> {
                log.warn("Blocklist entry already exists for fingerprint: {}", request.getFingerprintId());
                return Mono.just(Result.<BlocklistResponse.BlocklistEntry, PaymentError>err(
                    PaymentError.of("BLOCKLIST_ENTRY_EXISTS",
                        "Blocklist entry already exists for fingerprint: " + request.getFingerprintId())
                ));
            })
            .switchIfEmpty(Mono.fromCallable(() -> {
                BlocklistEntity entity = new BlocklistEntity();
                entity.setMerchantId(merchantId);
                entity.setFingerprintId(request.getFingerprintId());
                entity.setDataKind(request.getDataKind());
                entity.setMetadata(request.getMetadata());
                entity.setCreatedAt(Instant.now());
                return entity;
            })
            .flatMap(blocklistRepository::save)
            .map(this::toBlocklistEntry)
            .map(Result::<BlocklistResponse.BlocklistEntry, PaymentError>ok))
            .onErrorResume(error -> {
                log.error("Error adding to blocklist", error);
                return Mono.just(Result.<BlocklistResponse.BlocklistEntry, PaymentError>err(
                    PaymentError.of("BLOCKLIST_ADD_FAILED",
                        "Failed to add to blocklist: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> removeFromBlocklist(
            String merchantId,
            String fingerprintId) {
        log.info("Removing from blocklist for merchant: {}, fingerprint: {}", merchantId, fingerprintId);
        
        return blocklistRepository.findByMerchantIdAndFingerprintId(merchantId, fingerprintId)
            .switchIfEmpty(Mono.error(new RuntimeException("Blocklist entry not found")))
            .flatMap(entity -> {
                return blocklistRepository.delete(entity)
                    .thenReturn(Result.<Void, PaymentError>ok(null));
            })
            .onErrorResume(error -> {
                log.error("Error removing from blocklist", error);
                if (error.getMessage() != null && error.getMessage().contains("not found")) {
                    return Mono.just(Result.<Void, PaymentError>err(
                        PaymentError.of("BLOCKLIST_ENTRY_NOT_FOUND",
                            "Blocklist entry not found for fingerprint: " + fingerprintId)
                    ));
                }
                return Mono.just(Result.<Void, PaymentError>err(
                    PaymentError.of("BLOCKLIST_REMOVE_FAILED",
                        "Failed to remove from blocklist: " + error.getMessage())
                ));
            });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> toggleBlocklistGuard(
            String merchantId,
            BlocklistToggleRequest request) {
        log.info("Toggling blocklist guard for merchant: {}, enabled: {}", merchantId, request.getEnabled());
        
        // In production, this would update merchant configuration
        // For now, just return success
        return Mono.just(Result.<Void, PaymentError>ok(null))
            .onErrorResume(error -> {
                log.error("Error toggling blocklist guard", error);
                return Mono.just(Result.<Void, PaymentError>err(
                    PaymentError.of("BLOCKLIST_TOGGLE_FAILED",
                        "Failed to toggle blocklist guard: " + error.getMessage())
                ));
            });
    }
    
    /**
     * Convert BlocklistEntity to BlocklistEntry
     */
    private BlocklistResponse.BlocklistEntry toBlocklistEntry(BlocklistEntity entity) {
        BlocklistResponse.BlocklistEntry entry = new BlocklistResponse.BlocklistEntry();
        entry.setId(entity.getId());
        entry.setFingerprintId(entity.getFingerprintId());
        entry.setDataKind(entity.getDataKind());
        entry.setMetadata(entity.getMetadata());
        entry.setCreatedAt(entity.getCreatedAt());
        return entry;
    }
}

