package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.web.controller.PaymentException;
import com.hyperswitch.common.types.DisputeId;
import com.hyperswitch.core.disputes.DisputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * REST controller for dispute management
 */
@RestController
@RequestMapping("/api/disputes")
public class DisputeController {

    private final DisputeService disputeService;

    @Autowired
    public DisputeController(DisputeService disputeService) {
        this.disputeService = disputeService;
    }

    /**
     * Get a dispute by ID
     */
    @GetMapping("/{disputeId}")
    public Mono<ResponseEntity<DisputeResponse>> getDispute(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String disputeId) {
        return disputeService.getDispute(merchantId, DisputeId.of(disputeId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * List all disputes for a merchant
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<DisputeResponse>>> listDisputes(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        Flux<DisputeResponse> disputes = disputeService.listDisputes(merchantId);
        return Mono.just(ResponseEntity.ok(disputes));
    }

    /**
     * List disputes for a specific payment
     */
    @GetMapping("/payment/{paymentId}")
    public Mono<ResponseEntity<Flux<DisputeResponse>>> listDisputesByPayment(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String paymentId) {
        Flux<DisputeResponse> disputes = disputeService.listDisputesByPayment(merchantId, paymentId);
        return Mono.just(ResponseEntity.ok(disputes));
    }

    /**
     * Accept a dispute
     */
    @PostMapping("/{disputeId}/accept")
    public Mono<ResponseEntity<DisputeResponse>> acceptDispute(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String disputeId) {
        return disputeService.acceptDispute(merchantId, DisputeId.of(disputeId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Submit evidence for a dispute
     */
    @PostMapping("/{disputeId}/evidence")
    public Mono<ResponseEntity<DisputeResponse>> submitEvidence(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String disputeId,
            @RequestBody SubmitEvidenceRequest request) {
        request.setDisputeId(disputeId);
        return disputeService.submitEvidence(merchantId, request)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Defend a dispute by submitting evidence and contesting it
     */
    @PostMapping("/{disputeId}/defend")
    public Mono<ResponseEntity<DisputeResponse>> defendDispute(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String disputeId,
            @RequestBody SubmitEvidenceRequest request) {
        return disputeService.defendDispute(merchantId, DisputeId.of(disputeId), request)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Sync dispute status with connector
     */
    @PostMapping("/{disputeId}/sync")
    public Mono<ResponseEntity<DisputeResponse>> syncDispute(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String disputeId) {
        return disputeService.syncDispute(merchantId, DisputeId.of(disputeId))
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * List disputes with filters
     * GET /api/disputes/list
     */
    @GetMapping("/list")
    public Mono<ResponseEntity<DisputeListResponse>> listDisputesWithFilters(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) String disputeId,
            @RequestParam(required = false) String paymentId,
            @RequestParam(required = false) String connector,
            @RequestParam(required = false) String disputeStatus,
            @RequestParam(required = false) String disputeStage,
            @RequestParam(required = false) String reason,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant receivedTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant receivedTimeLt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant receivedTimeGt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant receivedTimeLte,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant receivedTimeGte,
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset) {
        
        DisputeListFilterConstraints constraints = new DisputeListFilterConstraints();
        constraints.setDisputeId(disputeId);
        constraints.setPaymentId(paymentId);
        constraints.setConnector(connector);
        if (disputeStatus != null) {
            try {
                constraints.setDisputeStatus(com.hyperswitch.common.types.DisputeStatus.valueOf(disputeStatus));
            } catch (IllegalArgumentException e) {
                // Invalid status, will be ignored
            }
        }
        if (disputeStage != null) {
            try {
                constraints.setDisputeStage(com.hyperswitch.common.types.DisputeStage.valueOf(disputeStage));
            } catch (IllegalArgumentException e) {
                // Invalid stage, will be ignored
            }
        }
        constraints.setReason(reason);
        constraints.setCurrency(currency);
        constraints.setReceivedTime(receivedTime);
        constraints.setReceivedTimeLt(receivedTimeLt);
        constraints.setReceivedTimeGt(receivedTimeGt);
        constraints.setReceivedTimeLte(receivedTimeLte);
        constraints.setReceivedTimeGte(receivedTimeGte);
        constraints.setLimit(limit);
        constraints.setOffset(offset);
        
        return disputeService.listDisputesWithFilters(merchantId, constraints)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get dispute filters
     * GET /api/disputes/filter
     */
    @GetMapping("/filter")
    public Mono<ResponseEntity<DisputeFiltersResponse>> getDisputeFilters(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return disputeService.getDisputeFilters(merchantId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get dispute aggregates
     * GET /api/disputes/aggregate
     */
    @GetMapping("/aggregate")
    public Mono<ResponseEntity<DisputeAggregatesResponse>> getDisputeAggregates(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant endTime) {
        return disputeService.getDisputeAggregates(merchantId, startTime, endTime)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * List disputes for profile
     * GET /api/disputes/profile/list
     */
    @GetMapping("/profile/list")
    public Mono<ResponseEntity<DisputeListResponse>> listDisputesForProfile(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) String disputeId,
            @RequestParam(required = false) String paymentId,
            @RequestParam(required = false) String connector,
            @RequestParam(required = false) String disputeStatus,
            @RequestParam(required = false) String disputeStage,
            @RequestParam(required = false) String reason,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant receivedTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant receivedTimeLt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant receivedTimeGt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant receivedTimeLte,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant receivedTimeGte,
            @RequestParam(required = false, defaultValue = "100") Integer limit,
            @RequestParam(required = false, defaultValue = "0") Integer offset) {
        
        DisputeListFilterConstraints constraints = new DisputeListFilterConstraints();
        constraints.setDisputeId(disputeId);
        constraints.setPaymentId(paymentId);
        constraints.setConnector(connector);
        if (disputeStatus != null) {
            try {
                constraints.setDisputeStatus(com.hyperswitch.common.types.DisputeStatus.valueOf(disputeStatus));
            } catch (IllegalArgumentException e) {
                // Invalid status, will be ignored
            }
        }
        if (disputeStage != null) {
            try {
                constraints.setDisputeStage(com.hyperswitch.common.types.DisputeStage.valueOf(disputeStage));
            } catch (IllegalArgumentException e) {
                // Invalid stage, will be ignored
            }
        }
        constraints.setReason(reason);
        constraints.setCurrency(currency);
        constraints.setReceivedTime(receivedTime);
        constraints.setReceivedTimeLt(receivedTimeLt);
        constraints.setReceivedTimeGt(receivedTimeGt);
        constraints.setReceivedTimeLte(receivedTimeLte);
        constraints.setReceivedTimeGte(receivedTimeGte);
        constraints.setLimit(limit);
        constraints.setOffset(offset);
        
        // Profile listing is similar to regular listing but filtered by profile
        return disputeService.listDisputesWithFilters(merchantId, constraints)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get dispute filters for profile
     * GET /api/disputes/profile/filter
     */
    @GetMapping("/profile/filter")
    public Mono<ResponseEntity<DisputeFiltersResponse>> getDisputeFiltersForProfile(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        // Profile filters are similar to regular filters but filtered by profile
        return disputeService.getDisputeFilters(merchantId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Get dispute aggregates for profile
     * GET /api/disputes/profile/aggregate
     */
    @GetMapping("/profile/aggregate")
    public Mono<ResponseEntity<DisputeAggregatesResponse>> getDisputeAggregatesForProfile(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant endTime) {
        // Profile aggregates are similar to regular aggregates but filtered by profile
        return disputeService.getDisputeAggregates(merchantId, startTime, endTime)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Attach dispute evidence
     * PUT /api/disputes/evidence
     */
    @PutMapping("/evidence")
    public Mono<ResponseEntity<DisputeResponse>> attachDisputeEvidence(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody SubmitEvidenceRequest request) {
        // Attach evidence is similar to submit evidence
        return disputeService.submitEvidence(merchantId, request)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Retrieve dispute evidence
     * GET /api/disputes/evidence/{dispute_id}
     */
    @GetMapping("/evidence/{dispute_id}")
    public Mono<ResponseEntity<DisputeEvidenceResponse>> retrieveDisputeEvidence(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable("dispute_id") String disputeId) {
        // This would retrieve evidence for a dispute
        // For now, return a placeholder response
        return disputeService.getDispute(merchantId, DisputeId.of(disputeId))
            .map(result -> {
                if (result.isRight()) {
                    DisputeEvidenceResponse evidenceResponse = new DisputeEvidenceResponse();
                    evidenceResponse.setDisputeId(disputeId);
                    // In production, this would fetch actual evidence data
                    return ResponseEntity.ok(evidenceResponse);
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Delete dispute evidence
     * DELETE /api/disputes/evidence
     */
    @DeleteMapping("/evidence")
    public Mono<ResponseEntity<Void>> deleteDisputeEvidence(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestParam("file_id") String fileId) {
        // In production, this would delete the evidence file
        // For now, return success
        return Mono.just(ResponseEntity.ok().build());
    }
    
    /**
     * Fetch disputes from connector
     * GET /api/disputes/connector/{connector_id}/fetch
     */
    @GetMapping("/connector/{connector_id}/fetch")
    public Mono<ResponseEntity<Flux<DisputeResponse>>> fetchDisputesFromConnector(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable("connector_id") String connectorId) {
        // Fetch disputes from the connector and sync them
        // For now, return empty flux - in production this would call connector API
        Flux<DisputeResponse> disputes = Flux.empty();
        return Mono.just(ResponseEntity.ok(disputes));
    }
}

