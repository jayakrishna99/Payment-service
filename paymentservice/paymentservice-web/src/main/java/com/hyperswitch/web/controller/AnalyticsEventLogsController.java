package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ApiEventLogsRequest;
import com.hyperswitch.common.dto.ApiEventLogsResponse;
import com.hyperswitch.common.dto.SdkEventLogsRequest;
import com.hyperswitch.common.dto.SdkEventLogsResponse;
import com.hyperswitch.common.dto.ConnectorEventLogsRequest;
import com.hyperswitch.common.dto.ConnectorEventLogsResponse;
import com.hyperswitch.common.dto.RoutingEventLogsRequest;
import com.hyperswitch.common.dto.RoutingEventLogsResponse;
import com.hyperswitch.common.dto.OutgoingWebhookEventLogsRequest;
import com.hyperswitch.common.dto.OutgoingWebhookEventLogsResponse;
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
 * Controller for analytics event logs endpoints
 */
@RestController
@RequestMapping("/api/analytics")
@Tag(name = "Analytics Event Logs", description = "Analytics event logs operations")
public class AnalyticsEventLogsController {

    private final AnalyticsService analyticsService;

    @Autowired
    public AnalyticsEventLogsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // API Event Logs
    @GetMapping("/v1/api_event_logs")
    @Operation(summary = "Get API event logs", description = "Retrieves API event logs based on the provided query parameters")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "API event logs retrieved successfully",
            content = @Content(schema = @Schema(implementation = ApiEventLogsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ApiEventLogsResponse>> getApiEventLogs(
            @RequestHeader(value = "merchant_id", required = false) String merchantId,
            @ModelAttribute ApiEventLogsRequest request) {
        return analyticsService.getApiEventLogs(
                merchantId != null ? merchantId : "default", request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @GetMapping("/v1/profile/api_event_logs")
    @Operation(summary = "Get profile API event logs", description = "Retrieves API event logs for a specific profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile API event logs retrieved successfully",
            content = @Content(schema = @Schema(implementation = ApiEventLogsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ApiEventLogsResponse>> getProfileApiEventLogs(
            @RequestHeader("profile_id") String profileId,
            @ModelAttribute ApiEventLogsRequest request) {
        return analyticsService.getProfileApiEventLogs(profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    // SDK Event Logs
    @PostMapping("/v1/sdk_event_logs")
    @Operation(summary = "Get SDK event logs", description = "Retrieves SDK event logs based on the provided request")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "SDK event logs retrieved successfully",
            content = @Content(schema = @Schema(implementation = SdkEventLogsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<SdkEventLogsResponse>> getSdkEventLogs(
            @RequestHeader(value = "merchant_id", required = false) String merchantId,
            @RequestBody SdkEventLogsRequest request) {
        return analyticsService.getSdkEventLogs(
                merchantId != null ? merchantId : "default", request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @PostMapping("/v1/profile/sdk_event_logs")
    @Operation(summary = "Get profile SDK event logs", description = "Retrieves SDK event logs for a specific profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile SDK event logs retrieved successfully",
            content = @Content(schema = @Schema(implementation = SdkEventLogsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<SdkEventLogsResponse>> getProfileSdkEventLogs(
            @RequestHeader("profile_id") String profileId,
            @RequestBody SdkEventLogsRequest request) {
        return analyticsService.getProfileSdkEventLogs(profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    // Connector Event Logs
    @GetMapping("/v1/connector_event_logs")
    @Operation(summary = "Get connector event logs", description = "Retrieves connector event logs based on the provided query parameters")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Connector event logs retrieved successfully",
            content = @Content(schema = @Schema(implementation = ConnectorEventLogsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorEventLogsResponse>> getConnectorEventLogs(
            @RequestHeader(value = "merchant_id", required = false) String merchantId,
            @ModelAttribute ConnectorEventLogsRequest request) {
        return analyticsService.getConnectorEventLogs(
                merchantId != null ? merchantId : "default", request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @GetMapping("/v1/profile/connector_event_logs")
    @Operation(summary = "Get profile connector event logs", description = "Retrieves connector event logs for a specific profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile connector event logs retrieved successfully",
            content = @Content(schema = @Schema(implementation = ConnectorEventLogsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<ConnectorEventLogsResponse>> getProfileConnectorEventLogs(
            @RequestHeader("profile_id") String profileId,
            @ModelAttribute ConnectorEventLogsRequest request) {
        return analyticsService.getProfileConnectorEventLogs(profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    // Routing Event Logs
    @GetMapping("/v1/routing_event_logs")
    @Operation(summary = "Get routing event logs", description = "Retrieves routing event logs based on the provided query parameters")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Routing event logs retrieved successfully",
            content = @Content(schema = @Schema(implementation = RoutingEventLogsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<RoutingEventLogsResponse>> getRoutingEventLogs(
            @RequestHeader(value = "merchant_id", required = false) String merchantId,
            @ModelAttribute RoutingEventLogsRequest request) {
        return analyticsService.getRoutingEventLogs(
                merchantId != null ? merchantId : "default", request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @GetMapping("/v1/profile/routing_event_logs")
    @Operation(summary = "Get profile routing event logs", description = "Retrieves routing event logs for a specific profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile routing event logs retrieved successfully",
            content = @Content(schema = @Schema(implementation = RoutingEventLogsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<RoutingEventLogsResponse>> getProfileRoutingEventLogs(
            @RequestHeader("profile_id") String profileId,
            @ModelAttribute RoutingEventLogsRequest request) {
        return analyticsService.getProfileRoutingEventLogs(profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    // Outgoing Webhook Event Logs
    @GetMapping("/v1/outgoing_webhook_event_logs")
    @Operation(summary = "Get outgoing webhook event logs", description = "Retrieves outgoing webhook event logs based on the provided query parameters")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Outgoing webhook event logs retrieved successfully",
            content = @Content(schema = @Schema(implementation = OutgoingWebhookEventLogsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<OutgoingWebhookEventLogsResponse>> getOutgoingWebhookEventLogs(
            @RequestHeader(value = "merchant_id", required = false) String merchantId,
            @ModelAttribute OutgoingWebhookEventLogsRequest request) {
        return analyticsService.getOutgoingWebhookEventLogs(
                merchantId != null ? merchantId : "default", request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }

    @GetMapping("/v1/profile/outgoing_webhook_event_logs")
    @Operation(summary = "Get profile outgoing webhook event logs", description = "Retrieves outgoing webhook event logs for a specific profile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Profile outgoing webhook event logs retrieved successfully",
            content = @Content(schema = @Schema(implementation = OutgoingWebhookEventLogsResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<OutgoingWebhookEventLogsResponse>> getProfileOutgoingWebhookEventLogs(
            @RequestHeader("profile_id") String profileId,
            @ModelAttribute OutgoingWebhookEventLogsRequest request) {
        return analyticsService.getProfileOutgoingWebhookEventLogs(profileId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

