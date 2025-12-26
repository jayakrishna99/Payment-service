package com.hyperswitch.web.controller;

import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * REST controller for cache operations
 */
@RestController
@RequestMapping("/api/cache")
@Tag(name = "Cache", description = "Cache management operations")
public class CacheController {
    
    private final ReactiveRedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    public CacheController(ReactiveRedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    /**
     * Invalidate cache entry
     * POST /api/cache/invalidate/{key}
     */
    @PostMapping("/invalidate/{key}")
    @Operation(
        summary = "Invalidate cache entry",
        description = "Invalidates a cache entry by key"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Cache entry invalidated successfully"
        )
    })
    public Mono<ResponseEntity<Map<String, String>>> invalidateCache(@PathVariable("key") String key) {
        return redisTemplate.delete(key)
            .map(deletedCount -> {
                Map<String, String> response = Map.of(
                    "key", key,
                    "status", deletedCount > 0 ? "invalidated" : "not_found"
                );
                return ResponseEntity.ok(response);
            })
            .defaultIfEmpty(ResponseEntity.ok(Map.of(
                "key", key,
                "status", "not_found"
            )));
    }
}

