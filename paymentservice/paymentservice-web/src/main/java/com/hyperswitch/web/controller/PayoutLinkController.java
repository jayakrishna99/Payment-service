package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.PayoutLinkResponse;
import com.hyperswitch.core.payoutlink.PayoutLinkService;
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
 * REST controller for payout link operations
 */
@RestController
@RequestMapping("/api/payout_link")
@Tag(name = "Payout Link", description = "Payout link rendering operations")
public class PayoutLinkController {
    
    private final PayoutLinkService payoutLinkService;
    
    @Autowired
    public PayoutLinkController(PayoutLinkService payoutLinkService) {
        this.payoutLinkService = payoutLinkService;
    }
    
    /**
     * Render payout link
     * GET /api/payout_link/{merchant_id}/{payout_id}
     */
    @GetMapping("/{merchant_id}/{payout_id}")
    @Operation(
        summary = "Render payout link",
        description = "Renders a payout link for the specified merchant and payout"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Payout link rendered successfully",
            content = @Content(schema = @Schema(implementation = PayoutLinkResponse.class))
        ),
        @ApiResponse(responseCode = "404", description = "Payout not found")
    })
    public Mono<ResponseEntity<PayoutLinkResponse>> renderPayoutLink(
            @PathVariable("merchant_id") String merchantId,
            @PathVariable("payout_id") String payoutId) {
        return payoutLinkService.renderPayoutLink(merchantId, payoutId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

