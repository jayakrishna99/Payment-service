package com.hyperswitch.web.controller;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.core.users.UserService;
import com.hyperswitch.web.controller.PaymentException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Controller for User Management v2 API endpoints
 */
@RestController
@RequestMapping("/api/v2")
public class UserV2Controller {
    
    @Autowired
    private UserService userService;
    
    /**
     * Create merchant (v2)
     * POST /api/v2/user/create_merchant
     */
    @PostMapping("/user/create_merchant")
    @Operation(
        summary = "Create merchant (v2)",
        description = "Creates a new merchant account (v2 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant created successfully",
            content = @Content(schema = @Schema(implementation = MerchantAccountResponse.class))
        )
    })
    public Mono<ResponseEntity<MerchantAccountResponse>> createMerchantV2(
            @RequestHeader("user_id") String userId,
            @RequestBody MerchantAccountCreateRequest request) {
        return userService.createMerchant(userId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * List merchants (v2)
     * GET /api/v2/user/list/merchant
     */
    @GetMapping("/user/list/merchant")
    @Operation(
        summary = "List merchants (v2)",
        description = "Lists all merchants for the user (v2 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchants retrieved successfully"
        )
    })
    public Mono<ResponseEntity<java.util.List<MerchantAccountResponse>>> listMerchantsV2(
            @RequestHeader("user_id") String userId,
            @RequestParam(required = false) String org_id) {
        return userService.listMerchants(userId, org_id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * List profiles (v2)
     * GET /api/v2/user/list/profile
     */
    @GetMapping("/user/list/profile")
    @Operation(
        summary = "List profiles (v2)",
        description = "Lists all profiles for the user (v2 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Profiles retrieved successfully"
        )
    })
    public Mono<ResponseEntity<java.util.List<ProfileResponse>>> listProfilesV2(
            @RequestHeader("user_id") String userId,
            @RequestParam(required = false) String org_id,
            @RequestParam(required = false) String merchant_id) {
        return userService.listProfiles(userId, org_id, merchant_id)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Switch merchant (v2)
     * POST /api/v2/user/switch/merchant
     */
    @PostMapping("/user/switch/merchant")
    @Operation(
        summary = "Switch merchant (v2)",
        description = "Switches the user's current merchant context (v2 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant switched successfully"
        )
    })
    public Mono<ResponseEntity<Void>> switchMerchantV2(
            @RequestHeader("user_id") String userId,
            @RequestBody SwitchMerchantRequest request) {
        return userService.switchMerchant(userId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Switch profile (v2)
     * POST /api/v2/user/switch/profile
     */
    @PostMapping("/user/switch/profile")
    @Operation(
        summary = "Switch profile (v2)",
        description = "Switches the user's current profile context (v2 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Profile switched successfully"
        )
    })
    public Mono<ResponseEntity<Void>> switchProfileV2(
            @RequestHeader("user_id") String userId,
            @RequestBody SwitchProfileRequest request) {
        return userService.switchProfile(userId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Get dashboard metadata (v2)
     * GET /api/v2/user/data
     */
    @GetMapping("/user/data")
    @Operation(
        summary = "Get dashboard metadata (v2)",
        description = "Gets dashboard metadata for the user (v2 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Dashboard metadata retrieved successfully",
            content = @Content(schema = @Schema(implementation = DashboardMetadataResponse.class))
        )
    })
    public Mono<ResponseEntity<DashboardMetadataResponse>> getDashboardMetadataV2(
            @RequestHeader("user_id") String userId) {
        return userService.getDashboardMetadata(userId)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok(result.unwrap());
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Set dashboard metadata (v2)
     * POST /api/v2/user/data
     */
    @PostMapping("/user/data")
    @Operation(
        summary = "Set dashboard metadata (v2)",
        description = "Sets dashboard metadata for the user (v2 API)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Dashboard metadata set successfully"
        )
    })
    public Mono<ResponseEntity<Void>> setDashboardMetadataV2(
            @RequestHeader("user_id") String userId,
            @RequestBody DashboardMetadataRequest request) {
        return userService.setDashboardMetadata(userId, request)
            .map(result -> {
                if (result.isOk()) {
                    return ResponseEntity.ok().build();
                } else {
                    throw new PaymentException(result.unwrapErr());
                }
            });
    }
    
    /**
     * Create merchant (v2 - alternative path)
     * POST /api/v2/users/create-merchant
     */
    @PostMapping("/users/create-merchant")
    @Operation(
        summary = "Create merchant (v2)",
        description = "Creates a new merchant account (v2 API - alternative path)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant created successfully",
            content = @Content(schema = @Schema(implementation = MerchantAccountResponse.class))
        )
    })
    public Mono<ResponseEntity<MerchantAccountResponse>> createMerchantV2Alt(
            @RequestHeader("user_id") String userId,
            @RequestBody MerchantAccountCreateRequest request) {
        return createMerchantV2(userId, request);
    }
    
    /**
     * List merchants (v2 - alternative path)
     * GET /api/v2/users/list/merchant
     */
    @GetMapping("/users/list/merchant")
    @Operation(
        summary = "List merchants (v2)",
        description = "Lists all merchants for the user (v2 API - alternative path)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchants retrieved successfully"
        )
    })
    public Mono<ResponseEntity<java.util.List<MerchantAccountResponse>>> listMerchantsV2Alt(
            @RequestHeader("user_id") String userId,
            @RequestParam(required = false) String org_id) {
        return listMerchantsV2(userId, org_id);
    }
    
    /**
     * List profiles (v2 - alternative path)
     * GET /api/v2/users/list/profile
     */
    @GetMapping("/users/list/profile")
    @Operation(
        summary = "List profiles (v2)",
        description = "Lists all profiles for the user (v2 API - alternative path)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Profiles retrieved successfully"
        )
    })
    public Mono<ResponseEntity<java.util.List<ProfileResponse>>> listProfilesV2Alt(
            @RequestHeader("user_id") String userId,
            @RequestParam(required = false) String org_id,
            @RequestParam(required = false) String merchant_id) {
        return listProfilesV2(userId, org_id, merchant_id);
    }
    
    /**
     * Switch merchant (v2 - alternative path)
     * POST /api/v2/users/switch/merchant
     */
    @PostMapping("/users/switch/merchant")
    @Operation(
        summary = "Switch merchant (v2)",
        description = "Switches the user's current merchant context (v2 API - alternative path)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Merchant switched successfully"
        )
    })
    public Mono<ResponseEntity<Void>> switchMerchantV2Alt(
            @RequestHeader("user_id") String userId,
            @RequestBody SwitchMerchantRequest request) {
        return switchMerchantV2(userId, request);
    }
    
    /**
     * Switch profile (v2 - alternative path)
     * POST /api/v2/users/switch/profile
     */
    @PostMapping("/users/switch/profile")
    @Operation(
        summary = "Switch profile (v2)",
        description = "Switches the user's current profile context (v2 API - alternative path)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Profile switched successfully"
        )
    })
    public Mono<ResponseEntity<Void>> switchProfileV2Alt(
            @RequestHeader("user_id") String userId,
            @RequestBody SwitchProfileRequest request) {
        return switchProfileV2(userId, request);
    }
    
    /**
     * Get dashboard metadata (v2 - alternative path)
     * GET /api/v2/users/data
     */
    @GetMapping("/users/data")
    @Operation(
        summary = "Get dashboard metadata (v2)",
        description = "Gets dashboard metadata for the user (v2 API - alternative path)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Dashboard metadata retrieved successfully",
            content = @Content(schema = @Schema(implementation = DashboardMetadataResponse.class))
        )
    })
    public Mono<ResponseEntity<DashboardMetadataResponse>> getDashboardMetadataV2Alt(
            @RequestHeader("user_id") String userId) {
        return getDashboardMetadataV2(userId);
    }
    
    /**
     * Set dashboard metadata (v2 - alternative path)
     * POST /api/v2/users/data
     */
    @PostMapping("/users/data")
    @Operation(
        summary = "Set dashboard metadata (v2)",
        description = "Sets dashboard metadata for the user (v2 API - alternative path)"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Dashboard metadata set successfully"
        )
    })
    public Mono<ResponseEntity<Void>> setDashboardMetadataV2Alt(
            @RequestHeader("user_id") String userId,
            @RequestBody DashboardMetadataRequest request) {
        return setDashboardMetadataV2(userId, request);
    }
}

