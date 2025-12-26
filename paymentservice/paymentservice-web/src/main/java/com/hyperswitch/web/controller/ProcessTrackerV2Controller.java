package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ProcessTrackerResponse;
import com.hyperswitch.common.dto.ResumeRecoveryRequest;
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
 * REST controller for process tracker operations (v2 API)
 */
@RestController
@RequestMapping("/api/v2/process-trackers")
@Tag(name = "Process Tracker V2", description = "Process tracker operations (v2)")
public class ProcessTrackerV2Controller {
    
    private final RevenueRecoveryService revenueRecoveryService;
    
    @Autowired
    public ProcessTrackerV2Controller(RevenueRecoveryService revenueRecoveryService) {
        this.revenueRecoveryService = revenueRecoveryService;
    }
    
    /**
     * Get revenue recovery process tracker
     * GET /api/v2/process-trackers/revenue-recovery-workflow/{revenue_recovery_id}
     */
    @GetMapping("/revenue-recovery-workflow/{revenue_recovery_id}")
    @Operation(
        summary = "Get revenue recovery process tracker",
        description = "Retrieves process tracker data for a revenue recovery workflow"
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
    
    /**
     * Resume revenue recovery
     * POST /api/v2/process-trackers/revenue-recovery-workflow/{revenue_recovery_id}/resume
     */
    @PostMapping("/revenue-recovery-workflow/{revenue_recovery_id}/resume")
    @Operation(
        summary = "Resume revenue recovery",
        description = "Resumes a revenue recovery process"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Recovery resumed successfully",
            content = @Content(schema = @Schema(implementation = ProcessTrackerResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ProcessTrackerResponse>> resumeRevenueRecovery(
            @RequestHeader("merchant_id") String merchantId,
            @PathVariable("revenue_recovery_id") String revenueRecoveryId,
            @RequestBody ResumeRecoveryRequest request) {
        return revenueRecoveryService.resumeRecovery(merchantId, revenueRecoveryId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

