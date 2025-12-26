package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.FeatureMatrixResponse;
import com.hyperswitch.core.featurematrix.FeatureMatrixService;
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
 * REST controller for feature matrix operations
 */
@RestController
@RequestMapping("/api/feature_matrix")
@Tag(name = "Feature Matrix", description = "Connector feature matrix operations")
public class FeatureMatrixController {
    
    private final FeatureMatrixService featureMatrixService;
    
    @Autowired
    public FeatureMatrixController(FeatureMatrixService featureMatrixService) {
        this.featureMatrixService = featureMatrixService;
    }
    
    /**
     * Get feature matrix
     * GET /api/feature_matrix
     */
    @GetMapping
    @Operation(
        summary = "Get feature matrix",
        description = "Retrieves the feature matrix for all connectors"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Feature matrix retrieved successfully",
            content = @Content(schema = @Schema(implementation = FeatureMatrixResponse.class))
        )
    })
    public Mono<ResponseEntity<FeatureMatrixResponse>> getFeatureMatrix() {
        return featureMatrixService.getFeatureMatrix()
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
}

