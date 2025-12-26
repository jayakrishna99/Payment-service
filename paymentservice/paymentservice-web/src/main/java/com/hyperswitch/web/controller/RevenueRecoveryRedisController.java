package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.RevenueRecoveryRedisResponse;
import com.hyperswitch.core.revenuerecovery.RevenueRecoveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for revenue recovery redis operations
 */
@RestController
@RequestMapping("/api/revenue-recovery-redis")
@Tag(name = "Revenue Recovery Redis", description = "Revenue recovery redis data operations")
public class RevenueRecoveryRedisController {
    
    private final RevenueRecoveryService revenueRecoveryService;
    
    @Autowired
    public RevenueRecoveryRedisController(RevenueRecoveryService revenueRecoveryService) {
        this.revenueRecoveryService = revenueRecoveryService;
    }
    
    /**
     * Get revenue recovery redis data
     * GET /api/revenue-recovery-redis/{merchant_id}
     */
    @GetMapping("/{merchant_id}")
    @Operation(
        summary = "Get revenue recovery redis data",
        description = "Retrieves revenue recovery data from Redis for a merchant"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Revenue recovery redis data retrieved successfully",
            content = @Content(schema = @Schema(implementation = RevenueRecoveryRedisResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Data not found")
    })
    public Mono<ResponseEntity<RevenueRecoveryRedisResponse>> getRevenueRecoveryRedisData(
            @PathVariable("merchant_id") String merchantId,
            @RequestParam(value = "key_type", required = false) String keyType) {
        return revenueRecoveryService.getRevenueRecoveryRedisData(merchantId, keyType)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

