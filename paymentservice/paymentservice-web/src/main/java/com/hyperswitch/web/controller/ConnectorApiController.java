package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.core.connectors.ConnectorApiService;
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
 * REST controller for real connector API integrations
 * Handles actual API calls to payment connectors (Stripe, PayPal, etc.)
 */
@RestController
@RequestMapping("/api/payments")
@Tag(name = "Connector API", description = "Real connector API integrations for payment processing")
public class ConnectorApiController {
    
    private final ConnectorApiService connectorApiService;
    
    @Autowired
    public ConnectorApiController(ConnectorApiService connectorApiService) {
        this.connectorApiService = connectorApiService;
    }
    
    /**
     * Create connector session for payment (with payment ID in path)
     * POST /api/payments/{payment_id}/connector_session
     */
    @PostMapping("/{payment_id}/connector_session")
    @Operation(
        summary = "Create connector session",
        description = "Creates a connector session for a payment. This generates a session token for the specified connector."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Connector session created successfully",
            content = @Content(schema = @Schema(implementation = ConnectorSessionResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorSessionResponse>> createConnectorSession(
            @Parameter(description = "Payment ID", required = true)
            @PathVariable("payment_id") String paymentId,
            @RequestBody ConnectorSessionRequest request) {
        return connectorApiService.createConnectorSession(paymentId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Create connector session for payment (without payment ID in path)
     * POST /api/payments/connector_session
     */
    @PostMapping("/connector_session")
    @Operation(
        summary = "Create connector session",
        description = "Creates a connector session for a payment. Payment ID should be included in the request body."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Connector session created successfully",
            content = @Content(schema = @Schema(implementation = ConnectorSessionResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorSessionResponse>> createConnectorSession(
            @RequestBody ConnectorSessionRequest request) {
        return connectorApiService.createConnectorSession(request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Execute payment through connector
     * POST /api/payments/{payment_id}/connector/execute
     */
    @PostMapping("/{payment_id}/connector/execute")
    @Operation(
        summary = "Execute payment through connector",
        description = "Executes a payment through the specified connector API"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment executed successfully",
            content = @Content(schema = @Schema(implementation = ConnectorPaymentResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorPaymentResponse>> executePayment(
            @Parameter(description = "Payment ID", required = true)
            @PathVariable("payment_id") String paymentId,
            @RequestBody ConnectorPaymentRequest request) {
        return connectorApiService.executePayment(paymentId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Authorize payment through connector
     * POST /api/payments/{payment_id}/connector/authorize
     */
    @PostMapping("/{payment_id}/connector/authorize")
    @Operation(
        summary = "Authorize payment through connector",
        description = "Authorizes a payment through the specified connector API"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment authorized successfully",
            content = @Content(schema = @Schema(implementation = ConnectorAuthorizationResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorAuthorizationResponse>> authorizePayment(
            @Parameter(description = "Payment ID", required = true)
            @PathVariable("payment_id") String paymentId,
            @RequestBody ConnectorAuthorizationRequest request) {
        return connectorApiService.authorizePayment(paymentId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Capture payment through connector
     * POST /api/payments/{payment_id}/connector/capture
     */
    @PostMapping("/{payment_id}/connector/capture")
    @Operation(
        summary = "Capture payment through connector",
        description = "Captures a payment through the specified connector API"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment captured successfully",
            content = @Content(schema = @Schema(implementation = ConnectorCaptureResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorCaptureResponse>> capturePayment(
            @Parameter(description = "Payment ID", required = true)
            @PathVariable("payment_id") String paymentId,
            @RequestBody ConnectorCaptureRequest request) {
        return connectorApiService.capturePayment(paymentId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Get payment status from connector
     * GET /api/payments/{payment_id}/connector/status
     */
    @GetMapping("/{payment_id}/connector/status")
    @Operation(
        summary = "Get payment status from connector",
        description = "Retrieves the current payment status from the connector API"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment status retrieved successfully",
            content = @Content(schema = @Schema(implementation = ConnectorPaymentStatusResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public Mono<ResponseEntity<ConnectorPaymentStatusResponse>> getPaymentStatus(
            @Parameter(description = "Payment ID", required = true)
            @PathVariable("payment_id") String paymentId,
            @Parameter(description = "Connector name", required = true)
            @RequestParam("connector") String connectorName) {
        return connectorApiService.getPaymentStatus(paymentId, connectorName)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Sync payment status from connector
     * POST /api/payments/{payment_id}/connector/sync
     */
    @PostMapping("/{payment_id}/connector/sync")
    @Operation(
        summary = "Sync payment status from connector",
        description = "Synchronizes the payment status from the connector API and updates local records"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payment status synced successfully",
            content = @Content(schema = @Schema(implementation = ConnectorPaymentStatusResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public Mono<ResponseEntity<ConnectorPaymentStatusResponse>> syncPaymentStatus(
            @Parameter(description = "Payment ID", required = true)
            @PathVariable("payment_id") String paymentId,
            @Parameter(description = "Connector name", required = true)
            @RequestParam("connector") String connectorName) {
        return connectorApiService.syncPaymentStatus(paymentId, connectorName)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Process refund through connector
     * POST /api/payments/refunds/{refund_id}/connector/process
     */
    @PostMapping("/refunds/{refund_id}/connector/process")
    @Operation(
        summary = "Process refund through connector",
        description = "Processes a refund through the specified connector API"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Refund processed successfully",
            content = @Content(schema = @Schema(implementation = ConnectorRefundResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorRefundResponse>> processRefund(
            @Parameter(description = "Refund ID", required = true)
            @PathVariable("refund_id") String refundId,
            @RequestBody ConnectorRefundRequest request) {
        return connectorApiService.processRefund(refundId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

