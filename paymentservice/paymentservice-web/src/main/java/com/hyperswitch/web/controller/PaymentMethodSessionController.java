package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.core.paymentmethods.PaymentMethodSessionService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for Payment Method Session management (v2 API)
 */
@RestController
@RequestMapping("/api/v2/payment-method-sessions")
@Tag(name = "Payment Method Session", description = "Payment method session management operations for v2 API")
public class PaymentMethodSessionController {

    private final PaymentMethodSessionService paymentMethodSessionService;

    @Autowired
    public PaymentMethodSessionController(PaymentMethodSessionService paymentMethodSessionService) {
        this.paymentMethodSessionService = paymentMethodSessionService;
    }

    /**
     * Create a payment method session
     * POST /api/v2/payment-method-sessions
     */
    @PostMapping
    @Operation(
        summary = "Create a payment method session",
        description = "Create a payment method session for a customer. This is used to list the saved payment methods for the customer. The customer can also add a new payment method using this session."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment method session created successfully",
            content = @Content(schema = @Schema(implementation = PaymentMethodSessionResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request"
        )
    })
    public Mono<ResponseEntity<PaymentMethodSessionResponse>> createPaymentMethodSession(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody PaymentMethodSessionRequest request) {
        return paymentMethodSessionService.createPaymentMethodSession(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Retrieve a payment method session
     * GET /api/v2/payment-method-sessions/{id}
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Retrieve a payment method session",
        description = "Retrieve the payment method session by its ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment method session retrieved successfully",
            content = @Content(schema = @Schema(implementation = PaymentMethodSessionResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Payment method session not found"
        )
    })
    public Mono<ResponseEntity<PaymentMethodSessionResponse>> getPaymentMethodSession(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "The unique identifier for the Payment Method Session", required = true)
            @PathVariable("id") String sessionId) {
        return paymentMethodSessionService.getPaymentMethodSession(merchantId, sessionId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Update a payment method session
     * PUT /api/v2/payment-method-sessions/{id}
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "Update a payment method session",
        description = "Update a payment method session"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment method session updated successfully",
            content = @Content(schema = @Schema(implementation = PaymentMethodSessionResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Payment method session not found"
        )
    })
    public Mono<ResponseEntity<PaymentMethodSessionResponse>> updatePaymentMethodSession(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "The unique identifier for the Payment Method Session", required = true)
            @PathVariable("id") String sessionId,
            @RequestBody PaymentMethodSessionUpdateRequest request) {
        return paymentMethodSessionService.updatePaymentMethodSession(merchantId, sessionId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Delete a payment method session
     * DELETE /api/v2/payment-method-sessions/{id}
     */
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a payment method session",
        description = "Delete a payment method session"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Payment method session deleted successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Payment method session not found"
        )
    })
    public Mono<ResponseEntity<Void>> deletePaymentMethodSession(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "The unique identifier for the Payment Method Session", required = true)
            @PathVariable("id") String sessionId) {
        return paymentMethodSessionService.deletePaymentMethodSession(merchantId, sessionId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.noContent().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * List payment methods for a session
     * GET /api/v2/payment-method-sessions/{id}/list-payment-methods
     */
    @GetMapping("/{id}/list-payment-methods")
    @Operation(
        summary = "List payment methods for a session",
        description = "List payment methods for the given payment method session. This endpoint lists the enabled payment methods for the profile and the saved payment methods of the customer."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment methods retrieved successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Payment method session not found"
        )
    })
    public Mono<ResponseEntity<Flux<PaymentMethodResponse>>> listPaymentMethodsForSession(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "The unique identifier for the Payment Method Session", required = true)
            @PathVariable("id") String sessionId) {
        return paymentMethodSessionService.listPaymentMethodsForSession(merchantId, sessionId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Confirm a payment method session
     * POST /api/v2/payment-method-sessions/{id}/confirm
     */
    @PostMapping("/{id}/confirm")
    @Operation(
        summary = "Confirm a payment method session",
        description = "Confirm a payment method session by creating a payment method"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment method session confirmed successfully",
            content = @Content(schema = @Schema(implementation = PaymentMethodSessionResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Payment method session not found"
        )
    })
    public Mono<ResponseEntity<PaymentMethodSessionResponse>> confirmPaymentMethodSession(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "The unique identifier for the Payment Method Session", required = true)
            @PathVariable("id") String sessionId,
            @RequestBody PaymentMethodSessionConfirmRequest request) {
        return paymentMethodSessionService.confirmPaymentMethodSession(merchantId, sessionId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Update a saved payment method in a session
     * PUT /api/v2/payment-method-sessions/{id}/update-saved-payment-method
     */
    @PutMapping("/{id}/update-saved-payment-method")
    @Operation(
        summary = "Update a saved payment method in a session",
        description = "Update a saved payment method within a payment method session"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment method updated successfully",
            content = @Content(schema = @Schema(implementation = PaymentMethodResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Payment method session or payment method not found"
        )
    })
    public Mono<ResponseEntity<PaymentMethodResponse>> updateSavedPaymentMethodInSession(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "The unique identifier for the Payment Method Session", required = true)
            @PathVariable("id") String sessionId,
            @RequestBody PaymentMethodSessionUpdateSavedPaymentMethodRequest request) {
        return paymentMethodSessionService.updateSavedPaymentMethodInSession(merchantId, sessionId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    /**
     * Delete a saved payment method from a session
     * DELETE /api/v2/payment-method-sessions/{id} (with payment_method_id in body)
     */
    @DeleteMapping("/{id}/delete-saved-payment-method")
    @Operation(
        summary = "Delete a saved payment method from a session",
        description = "Delete a saved payment method from a payment method session"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Payment method deleted successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Payment method session or payment method not found"
        )
    })
    public Mono<ResponseEntity<Void>> deleteSavedPaymentMethodFromSession(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @Parameter(description = "The unique identifier for the Payment Method Session", required = true)
            @PathVariable("id") String sessionId,
            @RequestBody PaymentMethodSessionDeleteSavedPaymentMethodRequest request) {
        return paymentMethodSessionService.deleteSavedPaymentMethodFromSession(merchantId, sessionId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.noContent().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

