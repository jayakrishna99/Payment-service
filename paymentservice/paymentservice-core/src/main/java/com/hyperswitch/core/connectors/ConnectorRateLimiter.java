package com.hyperswitch.core.connectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Rate limiter for connector API calls
 * Implements token bucket algorithm per connector
 */
@Component
public class ConnectorRateLimiter {
    
    private static final Logger log = LoggerFactory.getLogger(ConnectorRateLimiter.class);
    
    // Rate limit configuration per connector
    private static final Map<String, RateLimitConfig> RATE_LIMITS = new ConcurrentHashMap<>();
    
    // Token buckets per connector
    private final Map<String, TokenBucket> tokenBuckets = new ConcurrentHashMap<>();
    
    static {
        // Default rate limits (requests per minute)
        RATE_LIMITS.put("stripe", new RateLimitConfig(100, Duration.ofMinutes(1)));
        RATE_LIMITS.put("paypal", new RateLimitConfig(200, Duration.ofMinutes(1)));
        RATE_LIMITS.put("razorpay", new RateLimitConfig(100, Duration.ofMinutes(1)));
        RATE_LIMITS.put("adyen", new RateLimitConfig(100, Duration.ofMinutes(1)));
        // Default for unknown connectors
        RATE_LIMITS.put("default", new RateLimitConfig(50, Duration.ofMinutes(1)));
    }
    
    /**
     * Check if a request is allowed (rate limit not exceeded)
     */
    public Mono<Boolean> isAllowed(String connectorName) {
        String normalizedName = connectorName.toLowerCase();
        RateLimitConfig config = RATE_LIMITS.getOrDefault(normalizedName, RATE_LIMITS.get("default"));
        
        TokenBucket bucket = tokenBuckets.computeIfAbsent(normalizedName,
            k -> new TokenBucket(config.maxRequests, config.window));
        
        return Mono.fromCallable(() -> {
            long now = System.currentTimeMillis();
            boolean allowed = bucket.tryConsume(now);
            
            if (!allowed) {
                log.warn("Rate limit exceeded for connector: {}", connectorName);
            }
            
            return allowed;
        });
    }
    
    /**
     * Wait if rate limit is exceeded
     */
    public Mono<Void> waitIfNeeded(String connectorName) {
        return isAllowed(connectorName)
            .flatMap(allowed -> {
                if (allowed) {
                    return Mono.empty();
                } else {
                    // Wait a bit before retrying
                    log.info("Rate limit exceeded, waiting before retry for connector: {}", connectorName);
                    return Mono.delay(Duration.ofSeconds(1))
                        .then();
                }
            });
    }
    
    /**
     * Update rate limit configuration for a connector
     */
    public void updateRateLimit(String connectorName, int maxRequests, Duration window) {
        String normalizedName = connectorName.toLowerCase();
        RATE_LIMITS.put(normalizedName, new RateLimitConfig(maxRequests, window));
        tokenBuckets.remove(normalizedName); // Reset bucket
        log.info("Updated rate limit for connector {}: {} requests per {}", 
            connectorName, maxRequests, window);
    }
    
    /**
     * Rate limit configuration
     */
    private static class RateLimitConfig {
        final int maxRequests;
        final Duration window;
        
        RateLimitConfig(int maxRequests, Duration window) {
            this.maxRequests = maxRequests;
            this.window = window;
        }
    }
    
    /**
     * Token bucket implementation
     */
    private static class TokenBucket {
        private final int capacity;
        private final long windowMillis;
        private final AtomicInteger tokens;
        private final AtomicLong lastRefill;
        
        TokenBucket(int capacity, Duration window) {
            this.capacity = capacity;
            this.windowMillis = window.toMillis();
            this.tokens = new AtomicInteger(capacity);
            this.lastRefill = new AtomicLong(System.currentTimeMillis());
        }
        
        synchronized boolean tryConsume(long now) {
            refill(now);
            
            int current = tokens.get();
            if (current > 0) {
                return tokens.compareAndSet(current, current - 1);
            }
            
            return false;
        }
        
        private void refill(long now) {
            long lastRefillTime = lastRefill.get();
            long elapsed = now - lastRefillTime;
            
            if (elapsed >= windowMillis) {
                // Refill tokens
                tokens.set(capacity);
                lastRefill.set(now);
            }
        }
    }
}

