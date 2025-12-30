package com.hyperswitch.web.controller;

import com.hyperswitch.core.connectors.ConnectorWebhookVerifier;
import com.hyperswitch.core.connectors.MerchantConnectorAccountService;
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

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for connector webhook signature verification
 */
@RestController
@RequestMapping("/api/webhooks")
@Tag(name = "Connector Webhooks", description = "Webhook signature verification for connectors")
public class ConnectorWebhookController {
    
    private final ConnectorWebhookVerifier webhookVerifier;
    private final MerchantConnectorAccountService connectorAccountService;
    
    @Autowired
    public ConnectorWebhookController(
            ConnectorWebhookVerifier webhookVerifier,
            MerchantConnectorAccountService connectorAccountService) {
        this.webhookVerifier = webhookVerifier;
        this.connectorAccountService = connectorAccountService;
    }
    
    /**
     * Verify webhook signature
     * POST /api/webhooks/verify
     */
    @PostMapping("/verify")
    @Operation(
        summary = "Verify webhook signature",
        description = "Verifies the webhook signature for a connector using connector-specific algorithms"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Webhook signature verification result"
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public Mono<ResponseEntity<Map<String, Object>>> verifyWebhook(
            @Parameter(description = "Connector name", required = true)
            @RequestParam("connector") String connectorName,
            @Parameter(description = "Webhook payload", required = true)
            @RequestBody String payload,
            @Parameter(description = "Webhook signature", required = true)
            @RequestHeader("X-Webhook-Signature") String signature,
            @Parameter(description = "Merchant ID", required = false)
            @RequestParam(value = "merchant_id", required = false) String merchantId) {
        
        return Mono.fromCallable(() -> {
            // Get webhook secret from connector account
            Map<String, String> credentials = getWebhookCredentials(connectorName, merchantId);
            String secret = webhookVerifier.getWebhookSecret(connectorName, credentials);
            
            if (secret == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("verified", false);
                response.put("error", "Webhook secret not found");
                return ResponseEntity.badRequest().body(response);
            }
            
            // Verify signature
            boolean verified = webhookVerifier.verifyWebhookSignature(
                connectorName, payload, signature, secret);
            
            Map<String, Object> response = new HashMap<>();
            response.put("verified", verified);
            response.put("connector", connectorName);
            
            if (!verified) {
                response.put("error", "Signature verification failed");
            }
            
            return ResponseEntity.ok(response);
        })
        .onErrorResume(error -> {
            Map<String, Object> response = new HashMap<>();
            response.put("verified", false);
            response.put("error", error.getMessage());
            return Mono.just(ResponseEntity.badRequest().body(response));
        });
    }
    
    /**
     * Get webhook credentials for a connector
     * TODO: In production, fetch from database
     */
    private Map<String, String> getWebhookCredentials(String connectorName, String merchantId) {
        Map<String, String> credentials = new HashMap<>();
        
        // TODO: Fetch from MerchantConnectorAccountService
        // For now, use environment variables
        String envKey = "CONNECTOR_" + connectorName.toUpperCase() + "_WEBHOOK_SECRET";
        String secret = System.getenv(envKey);
        if (secret != null) {
            credentials.put("webhook_secret", secret);
        }
        
        return credentials;
    }
}

