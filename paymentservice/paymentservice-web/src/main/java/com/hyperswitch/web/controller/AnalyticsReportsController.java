package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.DisputeReportRequest;
import com.hyperswitch.common.dto.DisputeReportResponse;
import com.hyperswitch.common.dto.RefundReportRequest;
import com.hyperswitch.common.dto.RefundReportResponse;
import com.hyperswitch.common.dto.PaymentReportRequest;
import com.hyperswitch.common.dto.PaymentReportResponse;
import com.hyperswitch.common.dto.PayoutReportRequest;
import com.hyperswitch.common.dto.PayoutReportResponse;
import com.hyperswitch.common.dto.AuthenticationReportRequest;
import com.hyperswitch.common.dto.AuthenticationReportResponse;
import com.hyperswitch.web.controller.PaymentException;
import com.hyperswitch.core.analytics.AnalyticsService;
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
 * Controller for analytics report generation endpoints
 */
@RestController
@RequestMapping("/api/analytics")
@Tag(name = "Analytics Reports", description = "Analytics report generation operations")
public class AnalyticsReportsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsReportsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // Dispute Reports
    @PostMapping("/v1/report/dispute")
    @Operation(summary = "Generate dispute report", description = "Generates a dispute report based on the provided filters and time range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dispute report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = DisputeReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<DisputeReportResponse>> generateDisputeReport(
            @RequestHeader(value = "merchant_id", required = false) String merchantId,
            @RequestBody DisputeReportRequest request) {
        return analyticsService.generateDisputeReport(
                merchantId != null ? merchantId : "default", request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/merchant/report/dispute")
    @Operation(summary = "Generate merchant dispute report", description = "Generates a dispute report for a specific merchant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Merchant dispute report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = DisputeReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<DisputeReportResponse>> generateMerchantDisputeReport(
            @RequestHeader("merchant_id") String merchantId,
            @RequestBody DisputeReportRequest request) {
        return analyticsService.generateMerchantDisputeReport(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/org/report/dispute")
    @Operation(summary = "Generate org dispute report", description = "Generates a dispute report for an organization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Org dispute report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = DisputeReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<DisputeReportResponse>> generateOrgDisputeReport(
            @RequestHeader("org_id") String orgId,
            @RequestBody DisputeReportRequest request) {
        return analyticsService.generateOrgDisputeReport(orgId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/profile/report/dispute")
    @Operation(summary = "Generate profile dispute report", description = "Generates a dispute report for a specific profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile dispute report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = DisputeReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<DisputeReportResponse>> generateProfileDisputeReport(
            @RequestHeader("profile_id") String profileId,
            @RequestBody DisputeReportRequest request) {
        return analyticsService.generateProfileDisputeReport(profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    // Refund Reports
    @PostMapping("/v1/report/refunds")
    @Operation(summary = "Generate refund report", description = "Generates a refund report based on the provided filters and time range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Refund report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = RefundReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<RefundReportResponse>> generateRefundReport(
            @RequestHeader(value = "merchant_id", required = false) String merchantId,
            @RequestBody RefundReportRequest request) {
        return analyticsService.generateRefundReport(
                merchantId != null ? merchantId : "default", request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/merchant/report/refunds")
    @Operation(summary = "Generate merchant refund report", description = "Generates a refund report for a specific merchant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Merchant refund report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = RefundReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<RefundReportResponse>> generateMerchantRefundReport(
            @RequestHeader("merchant_id") String merchantId,
            @RequestBody RefundReportRequest request) {
        return analyticsService.generateMerchantRefundReport(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/org/report/refunds")
    @Operation(summary = "Generate org refund report", description = "Generates a refund report for an organization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Org refund report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = RefundReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<RefundReportResponse>> generateOrgRefundReport(
            @RequestHeader("org_id") String orgId,
            @RequestBody RefundReportRequest request) {
        return analyticsService.generateOrgRefundReport(orgId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/profile/report/refunds")
    @Operation(summary = "Generate profile refund report", description = "Generates a refund report for a specific profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile refund report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = RefundReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<RefundReportResponse>> generateProfileRefundReport(
            @RequestHeader("profile_id") String profileId,
            @RequestBody RefundReportRequest request) {
        return analyticsService.generateProfileRefundReport(profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    // Payment Reports
    @PostMapping("/v1/report/payments")
    @Operation(summary = "Generate payment report", description = "Generates a payment report based on the provided filters and time range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = PaymentReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<PaymentReportResponse>> generatePaymentReport(
            @RequestHeader(value = "merchant_id", required = false) String merchantId,
            @RequestBody PaymentReportRequest request) {
        return analyticsService.generatePaymentReport(
                merchantId != null ? merchantId : "default", request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/merchant/report/payments")
    @Operation(summary = "Generate merchant payment report", description = "Generates a payment report for a specific merchant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Merchant payment report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = PaymentReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<PaymentReportResponse>> generateMerchantPaymentReport(
            @RequestHeader("merchant_id") String merchantId,
            @RequestBody PaymentReportRequest request) {
        return analyticsService.generateMerchantPaymentReport(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/org/report/payments")
    @Operation(summary = "Generate org payment report", description = "Generates a payment report for an organization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Org payment report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = PaymentReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<PaymentReportResponse>> generateOrgPaymentReport(
            @RequestHeader("org_id") String orgId,
            @RequestBody PaymentReportRequest request) {
        return analyticsService.generateOrgPaymentReport(orgId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/profile/report/payments")
    @Operation(summary = "Generate profile payment report", description = "Generates a payment report for a specific profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile payment report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = PaymentReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<PaymentReportResponse>> generateProfilePaymentReport(
            @RequestHeader("profile_id") String profileId,
            @RequestBody PaymentReportRequest request) {
        return analyticsService.generateProfilePaymentReport(profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    // Payout Reports
    @PostMapping("/v1/report/payouts")
    @Operation(summary = "Generate payout report", description = "Generates a payout report based on the provided filters and time range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payout report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = PayoutReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<PayoutReportResponse>> generatePayoutReport(
            @RequestHeader(value = "merchant_id", required = false) String merchantId,
            @RequestBody PayoutReportRequest request) {
        return analyticsService.generatePayoutReport(
                merchantId != null ? merchantId : "default", request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/merchant/report/payouts")
    @Operation(summary = "Generate merchant payout report", description = "Generates a payout report for a specific merchant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Merchant payout report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = PayoutReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<PayoutReportResponse>> generateMerchantPayoutReport(
            @RequestHeader("merchant_id") String merchantId,
            @RequestBody PayoutReportRequest request) {
        return analyticsService.generateMerchantPayoutReport(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/org/report/payouts")
    @Operation(summary = "Generate org payout report", description = "Generates a payout report for an organization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Org payout report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = PayoutReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<PayoutReportResponse>> generateOrgPayoutReport(
            @RequestHeader("org_id") String orgId,
            @RequestBody PayoutReportRequest request) {
        return analyticsService.generateOrgPayoutReport(orgId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/profile/report/payouts")
    @Operation(summary = "Generate profile payout report", description = "Generates a payout report for a specific profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile payout report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = PayoutReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<PayoutReportResponse>> generateProfilePayoutReport(
            @RequestHeader("profile_id") String profileId,
            @RequestBody PayoutReportRequest request) {
        return analyticsService.generateProfilePayoutReport(profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    // Authentication Reports
    @PostMapping("/v1/report/authentications")
    @Operation(summary = "Generate authentication report", description = "Generates an authentication report based on the provided filters and time range")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Authentication report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<AuthenticationReportResponse>> generateAuthenticationReport(
            @RequestHeader(value = "merchant_id", required = false) String merchantId,
            @RequestBody AuthenticationReportRequest request) {
        return analyticsService.generateAuthenticationReport(
                merchantId != null ? merchantId : "default", request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/merchant/report/authentications")
    @Operation(summary = "Generate merchant authentication report", description = "Generates an authentication report for a specific merchant")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Merchant authentication report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<AuthenticationReportResponse>> generateMerchantAuthenticationReport(
            @RequestHeader("merchant_id") String merchantId,
            @RequestBody AuthenticationReportRequest request) {
        return analyticsService.generateMerchantAuthenticationReport(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/org/report/authentications")
    @Operation(summary = "Generate org authentication report", description = "Generates an authentication report for an organization")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Org authentication report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<AuthenticationReportResponse>> generateOrgAuthenticationReport(
            @RequestHeader("org_id") String orgId,
            @RequestBody AuthenticationReportRequest request) {
        return analyticsService.generateOrgAuthenticationReport(orgId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/profile/report/authentications")
    @Operation(summary = "Generate profile authentication report", description = "Generates an authentication report for a specific profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile authentication report generation initiated successfully",
            content = @Content(schema = @Schema(implementation = AuthenticationReportResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<AuthenticationReportResponse>> generateProfileAuthenticationReport(
            @RequestHeader("profile_id") String profileId,
            @RequestBody AuthenticationReportRequest request) {
        return analyticsService.generateProfileAuthenticationReport(profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

