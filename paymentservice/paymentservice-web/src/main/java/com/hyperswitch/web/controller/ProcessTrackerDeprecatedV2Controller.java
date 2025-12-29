package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ProcessTrackerResponse;
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
 * REST controller for deprecated process tracker operations (v2 API)
 * 
 * @deprecated This controller is deprecated. Use ProcessTrackerV2Controller instead.
 */
@RestController
@RequestMapping("/api/v2/process_tracker/revenue_recovery_workflow")
@Tag(name = "Process Tracker Deprecated V2", description = "Deprecated process tracker operations (v2) - Use Process Tracker V2 instead")
@Deprecated
public class ProcessTrackerDeprecatedV2Controller {
    
    private final RevenueRecoveryService revenueRecoveryService;
    
    @Autowired
    public ProcessTrackerDeprecatedV2Controller(RevenueRecoveryService revenueRecoveryService) {
        this.revenueRecoveryService = revenueRecoveryService;
    }
    
    /**
     * Get revenue recovery process tracker (Deprecated - use /api/v2/process-trackers instead)
     * GET /api/v2/process_tracker/revenue_recovery_workflow/{revenue_recovery_id}
     * 
     * @deprecated This endpoint is deprecated. Use GET /api/v2/process-trackers/revenue-recovery-workflow/{revenue_recovery_id} instead.
     */
    @GetMapping("/{revenue_recovery_id}")
    @Deprecated
    @Operation(
        summary = "Get revenue recovery process tracker (Deprecated)",
        description = "Retrieves process tracker data for a revenue recovery workflow. This endpoint is deprecated. Use /api/v2/process-trackers/revenue-recovery-workflow/{revenue_recovery_id} instead."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Process tracker data retrieved successfully",
            content = @Content(schema = @Schema(implementation = ProcessTrackerResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Process tracker not found")
    })
    public Mono<ResponseEntity<ProcessTrackerResponse>> getRevenueRecoveryProcessTracker(
            @RequestHeader("merchant_id") String merchantId,
            @PathVariable("revenue_recovery_id") String revenueRecoveryId) {
        return revenueRecoveryService.getProcessTracker(merchantId, revenueRecoveryId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

