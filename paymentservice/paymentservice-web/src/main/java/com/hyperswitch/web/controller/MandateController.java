package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.MandateRequest;
import com.hyperswitch.common.dto.MandateResponse;
import com.hyperswitch.common.types.MandateId;
import com.hyperswitch.core.mandates.MandateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for mandate management
 */
@RestController
@RequestMapping("/api/mandates")
public class MandateController {

    private static final Logger log = LoggerFactory.getLogger(MandateController.class);

    private final MandateService mandateService;

    @Autowired
    public MandateController(MandateService mandateService) {
        this.mandateService = mandateService;
    }

    /**
     * Create a new mandate
     * POST /api/mandates
     */
    @PostMapping
    public Mono<ResponseEntity<MandateResponse>> createMandate(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody MandateRequest request) {
        log.info("Creating mandate for merchant: {}", merchantId);
        
        return mandateService.createMandate(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.status(HttpStatus.CREATED).body(result.unwrap());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            });
    }

    /**
     * Get mandate by ID
     * GET /api/mandates/{id}
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<MandateResponse>> getMandate(@PathVariable String id) {
        log.info("Getting mandate: {}", id);
        
        return mandateService.getMandate(MandateId.of(id))
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            });
    }

    /**
     * List mandates for a customer
     * GET /api/customers/{customerId}/mandates
     */
    @GetMapping("/customers/{customerId}")
    public Flux<MandateResponse> listCustomerMandates(@PathVariable String customerId) {
        log.info("Listing mandates for customer: {}", customerId);
        return mandateService.listCustomerMandates(customerId);
    }

    /**
     * Revoke a mandate
     * POST /api/mandates/{id}/revoke
     */
    @PostMapping("/{id}/revoke")
    public Mono<ResponseEntity<MandateResponse>> revokeMandate(@PathVariable String id) {
        log.info("Revoking mandate: {}", id);
        
        return mandateService.revokeMandate(MandateId.of(id))
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            });
    }
}

