package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ReconciliationRequest;
import com.hyperswitch.common.dto.ReconciliationResponse;
import com.hyperswitch.web.controller.PaymentException;
import com.hyperswitch.common.dto.ReconciliationReportResponse;
import com.hyperswitch.common.dto.ReconStatusResponse;
import com.hyperswitch.common.dto.ReconTokenResponse;
import com.hyperswitch.core.reconciliation.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for reconciliation management
 */
@RestController
@RequestMapping("/api/reconciliation")
public class ReconciliationController {

    private final ReconciliationService reconciliationService;

    @Autowired
    public ReconciliationController(ReconciliationService reconciliationService) {
        this.reconciliationService = reconciliationService;
    }

    /**
     * Start a reconciliation run
     */
    @PostMapping
    public Mono<ResponseEntity<ReconciliationResponse>> startReconciliation(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody ReconciliationRequest request) {
        return reconciliationService.startReconciliation(merchantId, request)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Get a reconciliation by ID
     */
    @GetMapping("/{reconciliationId}")
    public Mono<ResponseEntity<ReconciliationResponse>> getReconciliation(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String reconciliationId) {
        return reconciliationService.getReconciliation(merchantId, reconciliationId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * List reconciliations for a merchant
     */
    @GetMapping
    public Mono<ResponseEntity<Flux<ReconciliationResponse>>> listReconciliations(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        Flux<ReconciliationResponse> reconciliations = reconciliationService.listReconciliations(merchantId);
        return Mono.just(ResponseEntity.ok(reconciliations));
    }

    /**
     * Get reconciliation status
     */
    @GetMapping("/status")
    public Mono<ResponseEntity<ReconStatusResponse>> getReconciliationStatus(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return reconciliationService.getReconciliationStatus(merchantId)
            .map(ResponseEntity::ok);
    }

    /**
     * Request reconciliation activation
     */
    @PostMapping("/request")
    public Mono<ResponseEntity<ReconStatusResponse>> requestReconciliation(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return reconciliationService.requestReconciliation(merchantId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }

    /**
     * Generate reconciliation token
     */
    @GetMapping("/token")
    public Mono<ResponseEntity<ReconTokenResponse>> generateReconciliationToken(
            @RequestHeader("X-Merchant-Id") String merchantId) {
        return reconciliationService.generateReconciliationToken(merchantId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Perform 2-way reconciliation - Compare internal records with connector records
     */
    @PostMapping("/{reconciliationId}/2-way")
    public Mono<ResponseEntity<ReconciliationResponse>> performTwoWayReconciliation(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String reconciliationId) {
        return reconciliationService.performTwoWayReconciliation(merchantId, reconciliationId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Perform 3-way reconciliation - Compare internal, connector, and bank records
     */
    @PostMapping("/{reconciliationId}/3-way")
    public Mono<ResponseEntity<ReconciliationResponse>> performThreeWayReconciliation(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String reconciliationId,
            @RequestBody(required = false) String bankRecords) {
        return reconciliationService.performThreeWayReconciliation(
                merchantId, 
                reconciliationId, 
                bankRecords != null ? bankRecords : "")
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Generate detailed reconciliation report with discrepancy analysis
     */
    @GetMapping("/{reconciliationId}/report")
    public Mono<ResponseEntity<ReconciliationReportResponse>> generateReconciliationReport(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String reconciliationId) {
        return reconciliationService.generateReconciliationReport(merchantId, reconciliationId)
            .map(result -> {
                if (result.isRight()) {
                    return ResponseEntity.ok(result.get());
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
    
    /**
     * Export reconciliation report in specified format (CSV, PDF, JSON)
     */
    @GetMapping("/{reconciliationId}/export")
    public Mono<ResponseEntity<byte[]>> exportReconciliationReport(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @PathVariable String reconciliationId,
            @RequestParam(defaultValue = "CSV") String format) {
        return reconciliationService.exportReconciliationReport(merchantId, reconciliationId, format)
            .map(result -> {
                if (result.isRight()) {
                    byte[] reportData = result.get();
                    HttpHeaders headers = new HttpHeaders();
                    
                    // Set content type based on format
                    MediaType mediaType;
                    String filename;
                    switch (format.toUpperCase()) {
                        case "PDF":
                            mediaType = MediaType.APPLICATION_PDF;
                            filename = "reconciliation_report_" + reconciliationId + ".pdf";
                            break;
                        case "JSON":
                            mediaType = MediaType.APPLICATION_JSON;
                            filename = "reconciliation_report_" + reconciliationId + ".json";
                            break;
                        case "CSV":
                        default:
                            mediaType = new MediaType("text", "csv");
                            filename = "reconciliation_report_" + reconciliationId + ".csv";
                            break;
                    }
                    
                    headers.setContentType(mediaType);
                    headers.setContentDispositionFormData("attachment", filename);
                    headers.setContentLength(reportData.length);
                    
                    return ResponseEntity.ok()
                        .headers(headers)
                        .body(reportData);
                } else {
                    throw new PaymentException(result.getLeft());
                }
            });
    }
}

