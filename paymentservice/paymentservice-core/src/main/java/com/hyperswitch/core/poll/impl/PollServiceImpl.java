package com.hyperswitch.core.poll.impl;

import com.hyperswitch.common.dto.PollResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.poll.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Implementation of PollService
 */
@Service
public class PollServiceImpl implements PollService {
    
    private static final Logger log = LoggerFactory.getLogger(PollServiceImpl.class);
    
    private final ReactiveRedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    public PollServiceImpl(ReactiveRedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    @Override
    public Mono<Result<PollResponse, PaymentError>> getPollStatus(
            String merchantId,
            String pollId) {
        log.info("Getting poll status for merchant: {}, pollId: {}", merchantId, pollId);
        
        // In production, this would prepend 'poll_{merchant_id}_' to the poll_id
        String redisKey = "poll_" + merchantId + "_" + pollId;
        
        return redisTemplate.opsForValue().get(redisKey)
            .cast(String.class)
            .map(status -> {
                PollResponse response = new PollResponse();
                response.setPollId(pollId);
                response.setStatus(status != null ? status : "NOT_FOUND");
                return Result.<PollResponse, PaymentError>ok(response);
            })
            .defaultIfEmpty(Result.ok(new PollResponse() {
                {
                    setPollId(pollId);
                    setStatus("NOT_FOUND");
                }
            }))
            .onErrorResume(error -> {
                log.error("Error getting poll status", error);
                return Mono.just(Result.<PollResponse, PaymentError>err(
                    PaymentError.of("POLL_STATUS_RETRIEVAL_FAILED",
                        "Failed to get poll status: " + error.getMessage())
                ));
            });
    }
}

