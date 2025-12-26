package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.core.payments.PaymentService;
import com.hyperswitch.core.payments.Refund;
import com.hyperswitch.web.controller.PaymentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
 * REST controller for refund operations (v2 API)
 */
@RestController
@RequestMapping("/api/v2/refunds")
@Tag(name = "Refunds (v2)", description = "Refund operations using v2 API")
public class RefundController {

    private final PaymentService paymentService;

    @Autowired
    public RefundController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Create a refund (v2 API)
     * POST /api/v2/refunds
     */
    @PostMapping
    @Operation(
        summary = "Create a refund (v2)",
        description = "Creates a refund for a payment using v2 API. Supports merchant reference ID and enhanced metadata."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Refund created successfully",
            content = @Content(schema = @Schema(implementation = Refund.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid refund request"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Payment not found"
        )
    })
    public Mono<ResponseEntity<Refund>> createRefund(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "Refund creation request", required = true)
            @RequestBody RefundsCreateRequest request) {
        request.setMerchantId(merchantId);
        return paymentService.createRefundV2(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Retrieve a refund (v2 API)
     * GET /api/v2/refunds/{id}
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Retrieve a refund (v2)",
        description = "Retrieves a refund by its ID using v2 API. Supports force sync and gateway credentials."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Refund retrieved successfully",
            content = @Content(schema = @Schema(implementation = Refund.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Refund not found"
        )
    })
    public Mono<ResponseEntity<Refund>> retrieveRefund(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "Refund ID", required = true)
            @PathVariable String id,
            @RequestParam(required = false) Boolean forceSync,
            @RequestParam(required = false) Boolean returnRawConnectorResponse) {
        
        RefundsRetrieveRequest request = new RefundsRetrieveRequest();
        request.setRefundId(id);
        request.setForceSync(forceSync);
        request.setReturnRawConnectorResponse(returnRawConnectorResponse);
        
        return paymentService.retrieveRefundV2(id, merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Retrieve a refund with gateway credentials (v2 API)
     * POST /api/v2/refunds/{id}
     */
    @PostMapping("/{id}")
    @Operation(
        summary = "Retrieve a refund with gateway credentials (v2)",
        description = "Retrieves a refund by its ID with gateway credentials using v2 API."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Refund retrieved successfully",
            content = @Content(schema = @Schema(implementation = Refund.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Refund not found"
        )
    })
    public Mono<ResponseEntity<Refund>> retrieveRefundWithGatewayCreds(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "Refund ID", required = true)
            @PathVariable String id,
            @RequestBody RefundsRetrieveRequest request) {
        request.setRefundId(id);
        return paymentService.retrieveRefundV2(id, merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Update refund metadata (v2 API)
     * PUT /api/v2/refunds/{id}/update-metadata
     */
    @PutMapping("/{id}/update-metadata")
    @Operation(
        summary = "Update refund metadata (v2)",
        description = "Updates refund metadata (reason and metadata fields) using v2 API."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Refund metadata updated successfully",
            content = @Content(schema = @Schema(implementation = Refund.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Refund not found"
        )
    })
    public Mono<ResponseEntity<Refund>> updateRefundMetadata(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "Refund ID", required = true)
            @PathVariable String id,
            @RequestBody RefundMetadataUpdateRequest request) {
        return paymentService.updateRefundMetadataV2(id, merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * List refunds (v2 API)
     * POST /api/v2/refunds/list
     */
    @PostMapping("/list")
    @Operation(
        summary = "List refunds (v2)",
        description = "Lists refunds with filtering using v2 API."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Refunds retrieved successfully",
            content = @Content(schema = @Schema(implementation = RefundListResponse.class))
        )
    })
    public Mono<ResponseEntity<RefundListResponse>> listRefunds(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody RefundListFilterConstraints constraints) {
        constraints.setMerchantId(merchantId);
        return paymentService.listRefundsV2(merchantId, constraints)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

