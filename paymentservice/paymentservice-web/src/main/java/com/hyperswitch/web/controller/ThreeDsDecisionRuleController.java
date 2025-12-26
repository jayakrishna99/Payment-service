package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.ThreeDsDecisionRuleExecuteRequest;
import com.hyperswitch.common.dto.ThreeDsDecisionRuleExecuteResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.threeds.ThreeDsDecisionRuleService;
import com.hyperswitch.web.controller.PaymentException;
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
 * Controller for 3DS Decision Rule endpoints
 */
@RestController
@RequestMapping("/api/three_ds_decision_rule")
@Tag(name = "Three DS Decision Rule", description = "3DS Decision Rule API endpoints")
public class ThreeDsDecisionRuleController {
    
    private final ThreeDsDecisionRuleService threeDsDecisionRuleService;
    
    @Autowired
    public ThreeDsDecisionRuleController(ThreeDsDecisionRuleService threeDsDecisionRuleService) {
        this.threeDsDecisionRuleService = threeDsDecisionRuleService;
    }
    
    @PostMapping("/execute")
    @Operation(
        summary = "Execute decision rule",
        description = "Execute 3DS decision rule"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Decision rule executed successfully",
            content = @Content(schema = @Schema(implementation = ThreeDsDecisionRuleExecuteResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "404", description = "Routing algorithm not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Mono<ResponseEntity<ThreeDsDecisionRuleExecuteResponse>> executeDecisionRule(
            @RequestHeader("X-Merchant-Id") String merchantId,
            @RequestBody ThreeDsDecisionRuleExecuteRequest request) {
        return threeDsDecisionRuleService.executeDecisionRule(merchantId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

