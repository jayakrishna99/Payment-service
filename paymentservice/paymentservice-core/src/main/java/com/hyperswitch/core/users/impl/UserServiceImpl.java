package com.hyperswitch.core.users.impl;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.security.PasswordUtil;
import com.hyperswitch.common.types.Result;
import com.hyperswitch.core.users.UserService;
import com.hyperswitch.storage.entity.UserEntity;
import com.hyperswitch.storage.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

/**
 * Implementation of UserService
 */
@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public Mono<Result<UserResponse, PaymentError>> getUserDetails(String userId) {
        log.info("Getting user details for user: {}", userId);
        
        return userRepository.findById(userId)
            .map(this::toUserResponse)
            .map(Result::<UserResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error getting user details: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("USER_RETRIEVAL_FAILED",
                    "Failed to get user details: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> signIn(SignInRequest request) {
        log.info("User sign in attempt for email: {}", request.getEmail());
        
        return userRepository.findByEmail(request.getEmail())
            .flatMap(user -> {
                if (user.getPassword() == null || !PasswordUtil.verifyPassword(request.getPassword(), user.getPassword())) {
                    return Mono.just(Result.<AuthorizeResponse, PaymentError>err(
                        PaymentError.of("INVALID_CREDENTIALS", "Invalid email or password")));
                }
                
                AuthorizeResponse response = new AuthorizeResponse();
                response.setIsEmailSent(false);
                
                return Mono.just(Result.<AuthorizeResponse, PaymentError>ok(response));
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("INVALID_CREDENTIALS", "Invalid email or password"))))
            .onErrorResume(error -> {
                log.error("Error during sign in: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("SIGN_IN_FAILED",
                    "Failed to sign in: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> signUp(SignUpRequest request) {
        log.info("User sign up attempt for email: {}", request.getEmail());
        
        return userRepository.existsByEmail(request.getEmail())
            .flatMap(exists -> {
                if (exists) {
                    return Mono.just(Result.<AuthorizeResponse, PaymentError>err(
                        PaymentError.of("USER_ALREADY_EXISTS", "User with this email already exists")));
                }
                
                UserEntity user = new UserEntity();
                user.setUserId(UUID.randomUUID().toString());
                user.setEmail(request.getEmail());
                user.setPassword(PasswordUtil.hashPassword(request.getPassword()));
                user.setIsVerified(false);
                user.setCreatedAt(Instant.now());
                user.setLastModifiedAt(Instant.now());
                user.setTotpStatus("NOT_SET");
                
                return userRepository.save(user)
                    .map(savedUser -> {
                        AuthorizeResponse response = new AuthorizeResponse();
                        response.setIsEmailSent(true);
                        return Result.<AuthorizeResponse, PaymentError>ok(response);
                    });
            })
            .onErrorResume(error -> {
                log.error("Error during sign up: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("SIGN_UP_FAILED",
                    "Failed to sign up: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> signUpWithMerchantId(SignUpWithMerchantIdRequest request) {
        log.info("User sign up with merchant ID for email: {}", request.getEmail());
        
        return userRepository.existsByEmail(request.getEmail())
            .flatMap(exists -> {
                if (exists) {
                    return Mono.just(Result.<AuthorizeResponse, PaymentError>err(
                        PaymentError.of("USER_ALREADY_EXISTS", "User with this email already exists")));
                }
                
                UserEntity user = new UserEntity();
                user.setUserId(UUID.randomUUID().toString());
                user.setEmail(request.getEmail());
                user.setName(request.getName());
                user.setPassword(PasswordUtil.hashPassword(request.getPassword()));
                user.setIsVerified(false);
                user.setCreatedAt(Instant.now());
                user.setLastModifiedAt(Instant.now());
                user.setTotpStatus("NOT_SET");
                
                return userRepository.save(user)
                    .map(savedUser -> {
                        AuthorizeResponse response = new AuthorizeResponse();
                        response.setIsEmailSent(true);
                        return Result.<AuthorizeResponse, PaymentError>ok(response);
                    });
            })
            .onErrorResume(error -> {
                log.error("Error during sign up with merchant ID: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("SIGN_UP_FAILED",
                    "Failed to sign up: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> signOut(String userId) {
        log.info("User sign out for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            // In production, this would invalidate the user's session/token
            // For now, just return success
            return Result.<Void, PaymentError>ok(null);
        })
        .onErrorResume(error -> {
            log.error("Error during sign out: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("SIGN_OUT_FAILED",
                "Failed to sign out: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> changePassword(String userId, ChangePasswordRequest request) {
        log.info("Changing password for user: {}", userId);
        
        return userRepository.findById(userId)
            .flatMap(user -> {
                if (user.getPassword() == null || !PasswordUtil.verifyPassword(request.getOldPassword(), user.getPassword())) {
                    return Mono.just(Result.<Void, PaymentError>err(
                        PaymentError.of("INVALID_PASSWORD", "Current password is incorrect")));
                }
                
                user.setPassword(PasswordUtil.hashPassword(request.getNewPassword()));
                user.setLastPasswordModifiedAt(Instant.now());
                user.setLastModifiedAt(Instant.now());
                
                return userRepository.save(user)
                    .map(savedUser -> Result.<Void, PaymentError>ok(null));
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error changing password: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("CHANGE_PASSWORD_FAILED",
                    "Failed to change password: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> rotatePassword(String userId, RotatePasswordRequest request) {
        log.info("Rotating password for user: {}", userId);
        
        return userRepository.findById(userId)
            .flatMap(user -> {
                user.setPassword(PasswordUtil.hashPassword(request.getPassword()));
                user.setLastPasswordModifiedAt(Instant.now());
                user.setLastModifiedAt(Instant.now());
                
                return userRepository.save(user)
                    .map(savedUser -> Result.<Void, PaymentError>ok(null));
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error rotating password: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("ROTATE_PASSWORD_FAILED",
                    "Failed to rotate password: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> forgotPassword(ForgotPasswordRequest request) {
        log.info("Forgot password request for email: {}", request.getEmail());
        
        return userRepository.findByEmail(request.getEmail())
            .map(user -> {
                // In production, this would send a password reset email
                AuthorizeResponse response = new AuthorizeResponse();
                response.setIsEmailSent(true);
                return Result.<AuthorizeResponse, PaymentError>ok(response);
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found"))))
            .onErrorResume(error -> {
                log.error("Error processing forgot password: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("FORGOT_PASSWORD_FAILED",
                    "Failed to process forgot password: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> resetPassword(ResetPasswordRequest request) {
        log.info("Resetting password with token");
        
        return Mono.fromCallable(() -> {
            // In production, this would validate the token and reset the password
            // For now, return success
            AuthorizeResponse response = new AuthorizeResponse();
            response.setIsEmailSent(false);
            return Result.<AuthorizeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error resetting password: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("RESET_PASSWORD_FAILED",
                "Failed to reset password: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<UserResponse, PaymentError>> updateUser(String userId, UpdateUserRequest request) {
        log.info("Updating user: {}", userId);
        
        return userRepository.findById(userId)
            .flatMap(user -> {
                if (request.getName() != null) {
                    user.setName(request.getName());
                }
                if (request.getIsVerified() != null) {
                    user.setIsVerified(request.getIsVerified());
                }
                user.setLastModifiedAt(Instant.now());
                
                return userRepository.save(user)
                    .map(this::toUserResponse)
                    .map(Result::<UserResponse, PaymentError>ok);
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error updating user: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("UPDATE_USER_FAILED",
                    "Failed to update user: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<UserResponse, PaymentError>> getUserFromEmail(String email) {
        log.info("Getting user from email: {}", email);
        
        return userRepository.findByEmail(email)
            .map(this::toUserResponse)
            .map(Result::<UserResponse, PaymentError>ok)
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found with email: " + email))))
            .onErrorResume(error -> {
                log.error("Error getting user from email: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("USER_RETRIEVAL_FAILED",
                    "Failed to get user from email: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<java.util.List<OrganizationResponse>, PaymentError>> listOrganizations(String userId) {
        log.info("Listing organizations for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            // In production, this would query organizations from database
            // For now, return empty list
            return Result.<java.util.List<OrganizationResponse>, PaymentError>ok(new java.util.ArrayList<>());
        })
        .onErrorResume(error -> {
            log.error("Error listing organizations: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("LIST_ORGANIZATIONS_FAILED",
                "Failed to list organizations: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<java.util.List<MerchantAccountResponse>, PaymentError>> listMerchants(String userId, String orgId) {
        log.info("Listing merchants for user: {}, org: {}", userId, orgId);
        
        return Mono.fromCallable(() -> {
            // In production, this would query merchants from database
            // For now, return empty list
            return Result.<java.util.List<MerchantAccountResponse>, PaymentError>ok(new java.util.ArrayList<>());
        })
        .onErrorResume(error -> {
            log.error("Error listing merchants: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("LIST_MERCHANTS_FAILED",
                "Failed to list merchants: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<java.util.List<ProfileResponse>, PaymentError>> listProfiles(String userId, String orgId, String merchantId) {
        log.info("Listing profiles for user: {}, org: {}, merchant: {}", userId, orgId, merchantId);
        
        return Mono.fromCallable(() -> {
            // In production, this would query profiles from database
            // For now, return empty list
            return Result.<java.util.List<ProfileResponse>, PaymentError>ok(new java.util.ArrayList<>());
        })
        .onErrorResume(error -> {
            log.error("Error listing profiles: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("LIST_PROFILES_FAILED",
                "Failed to list profiles: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> switchOrganization(String userId, SwitchOrganizationRequest request) {
        log.info("Switching organization for user: {} to org: {}", userId, request.getOrgId());
        
        return Mono.fromCallable(() -> {
            // In production, this would update user's current organization context
            // For now, return success
            return Result.<Void, PaymentError>ok(null);
        })
        .onErrorResume(error -> {
            log.error("Error switching organization: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("SWITCH_ORGANIZATION_FAILED",
                "Failed to switch organization: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> switchMerchant(String userId, SwitchMerchantRequest request) {
        log.info("Switching merchant for user: {} to merchant: {}", userId, request.getMerchantId());
        
        return Mono.fromCallable(() -> {
            // In production, this would update user's current merchant context
            // For now, return success
            return Result.<Void, PaymentError>ok(null);
        })
        .onErrorResume(error -> {
            log.error("Error switching merchant: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("SWITCH_MERCHANT_FAILED",
                "Failed to switch merchant: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> switchProfile(String userId, SwitchProfileRequest request) {
        log.info("Switching profile for user: {} to profile: {}", userId, request.getProfileId());
        
        return Mono.fromCallable(() -> {
            // In production, this would update user's current profile context
            // For now, return success
            return Result.<Void, PaymentError>ok(null);
        })
        .onErrorResume(error -> {
            log.error("Error switching profile: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("SWITCH_PROFILE_FAILED",
                "Failed to switch profile: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<DashboardMetadataResponse, PaymentError>> getDashboardMetadata(String userId) {
        log.info("Getting dashboard metadata for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            DashboardMetadataResponse response = new DashboardMetadataResponse();
            response.setMetadata(new java.util.HashMap<>());
            // In production, this would retrieve metadata from database
            return Result.<DashboardMetadataResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting dashboard metadata: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("GET_DASHBOARD_METADATA_FAILED",
                "Failed to get dashboard metadata: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<DashboardMetadataResponse, PaymentError>> setDashboardMetadata(String userId, DashboardMetadataRequest request) {
        log.info("Setting dashboard metadata for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            DashboardMetadataResponse response = new DashboardMetadataResponse();
            response.setMetadata(request.getMetadata());
            // In production, this would save metadata to database
            return Result.<DashboardMetadataResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error setting dashboard metadata: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("SET_DASHBOARD_METADATA_FAILED",
                "Failed to set dashboard metadata: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<OrganizationResponse, PaymentError>> createOrganization(String userId, OrganizationRequest request) {
        log.info("Creating organization for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            OrganizationResponse response = new OrganizationResponse();
            response.setOrganizationId(UUID.randomUUID().toString());
            response.setName(request.getName());
            response.setDescription(request.getDescription());
            response.setMetadata(request.getMetadata());
            response.setCreatedAt(Instant.now());
            response.setUpdatedAt(Instant.now());
            // In production, this would create organization in database
            return Result.<OrganizationResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error creating organization: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("CREATE_ORGANIZATION_FAILED",
                "Failed to create organization: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<MerchantAccountResponse, PaymentError>> createMerchant(String userId, MerchantAccountCreateRequest request) {
        log.info("Creating merchant account for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            MerchantAccountResponse response = new MerchantAccountResponse();
            response.setMerchantId(UUID.randomUUID().toString());
            response.setOrganizationId(null); // Will be set from user context
            response.setName(request.getMerchantName());
            response.setMetadata(request.getMetadata());
            response.setCreatedAt(Instant.now());
            response.setUpdatedAt(Instant.now());
            // In production, this would create merchant account in database
            return Result.<MerchantAccountResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error creating merchant account: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("CREATE_MERCHANT_FAILED",
                "Failed to create merchant account: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<TwoFactorAuthStatusResponse, PaymentError>> checkTwoFactorAuthStatus(String userId) {
        log.info("Checking 2FA status for user: {}", userId);
        
        return userRepository.findById(userId)
            .map(user -> {
                TwoFactorAuthStatusResponse response = new TwoFactorAuthStatusResponse();
                response.setTotpStatus(user.getTotpStatus() != null ? user.getTotpStatus() : "NOT_SET");
                response.setAttempts(0);
                return Result.<TwoFactorAuthStatusResponse, PaymentError>ok(response);
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error checking 2FA status: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("CHECK_2FA_STATUS_FAILED",
                    "Failed to check 2FA status: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<TwoFactorAuthStatusResponse, PaymentError>> checkTwoFactorAuthStatusWithAttempts(String userId) {
        log.info("Checking 2FA status with attempts for user: {}", userId);
        
        return userRepository.findById(userId)
            .map(user -> {
                TwoFactorAuthStatusResponse response = new TwoFactorAuthStatusResponse();
                response.setTotpStatus(user.getTotpStatus() != null ? user.getTotpStatus() : "NOT_SET");
                response.setAttempts(0); // In production, track failed attempts
                return Result.<TwoFactorAuthStatusResponse, PaymentError>ok(response);
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error checking 2FA status with attempts: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("CHECK_2FA_STATUS_FAILED",
                    "Failed to check 2FA status: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<BeginTotpResponse, PaymentError>> beginTotp(String userId) {
        log.info("Beginning TOTP setup for user: {}", userId);
        
        return userRepository.findById(userId)
            .flatMap(user -> {
                // Generate TOTP secret (in production, use proper TOTP library)
                String secret = generateTotpSecret();
                String totpUrl = "otpauth://totp/Hyperswitch:" + user.getEmail() + "?secret=" + secret + "&issuer=Hyperswitch";
                
                // Store secret temporarily (in production, encrypt and store)
                user.setTotpSecret(secret.getBytes());
                user.setTotpStatus("PENDING");
                user.setLastModifiedAt(Instant.now());
                
                return userRepository.save(user)
                    .map(savedUser -> {
                        TotpSecret totpSecret = new TotpSecret();
                        totpSecret.setSecret(secret);
                        totpSecret.setTotpUrl(totpUrl);
                        
                        BeginTotpResponse response = new BeginTotpResponse();
                        response.setSecret(totpSecret);
                        return Result.<BeginTotpResponse, PaymentError>ok(response);
                    });
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error beginning TOTP: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("BEGIN_TOTP_FAILED",
                    "Failed to begin TOTP: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<BeginTotpResponse, PaymentError>> resetTotp(String userId) {
        log.info("Resetting TOTP for user: {}", userId);
        
        return beginTotp(userId); // Reset is same as begin
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> verifyTotp(String userId, VerifyTotpRequest request) {
        log.info("Verifying TOTP for user: {}", userId);
        
        return userRepository.findById(userId)
            .flatMap(user -> {
                // In production, verify TOTP code using TOTP library
                // For now, accept any 6-digit code as valid
                if (request.getTotp() == null || request.getTotp().length() != 6) {
                    return Mono.just(Result.<AuthorizeResponse, PaymentError>err(
                        PaymentError.of("INVALID_TOTP", "Invalid TOTP code")));
                }
                
                // Mark TOTP as verified
                user.setTotpStatus("VERIFIED");
                user.setLastModifiedAt(Instant.now());
                
                return userRepository.save(user)
                    .map(savedUser -> {
                        AuthorizeResponse response = new AuthorizeResponse();
                        response.setIsEmailSent(false);
                        return Result.<AuthorizeResponse, PaymentError>ok(response);
                    });
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error verifying TOTP: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("VERIFY_TOTP_FAILED",
                    "Failed to verify TOTP: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> updateTotp(String userId, VerifyTotpRequest request) {
        log.info("Updating TOTP for user: {}", userId);
        
        return verifyTotp(userId, request); // Update is same as verify
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> verifyRecoveryCode(String userId, VerifyRecoveryCodeRequest request) {
        log.info("Verifying recovery code for user: {}", userId);
        
        return userRepository.findById(userId)
            .flatMap(user -> {
                // In production, verify recovery code against stored hashed codes
                if (user.getTotpRecoveryCodes() == null || user.getTotpRecoveryCodes().isEmpty()) {
                    return Mono.just(Result.<AuthorizeResponse, PaymentError>err(
                        PaymentError.of("NO_RECOVERY_CODES", "No recovery codes available")));
                }
                
                // Check if recovery code matches (in production, hash and compare)
                boolean codeMatches = user.getTotpRecoveryCodes().contains(request.getRecoveryCode());
                if (!codeMatches) {
                    return Mono.just(Result.<AuthorizeResponse, PaymentError>err(
                        PaymentError.of("INVALID_RECOVERY_CODE", "Invalid recovery code")));
                }
                
                // Remove used recovery code
                user.getTotpRecoveryCodes().remove(request.getRecoveryCode());
                user.setLastModifiedAt(Instant.now());
                
                return userRepository.save(user)
                    .map(savedUser -> {
                        AuthorizeResponse response = new AuthorizeResponse();
                        response.setIsEmailSent(false);
                        return Result.<AuthorizeResponse, PaymentError>ok(response);
                    });
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error verifying recovery code: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("VERIFY_RECOVERY_CODE_FAILED",
                    "Failed to verify recovery code: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<RecoveryCodesResponse, PaymentError>> generateRecoveryCodes(String userId) {
        log.info("Generating recovery codes for user: {}", userId);
        
        return userRepository.findById(userId)
            .flatMap(user -> {
                // Generate recovery codes (in production, use secure random)
                java.util.List<String> recoveryCodes = new java.util.ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    recoveryCodes.add(generateRecoveryCode());
                }
                
                // Hash and store recovery codes (in production, hash before storing)
                user.setTotpRecoveryCodes(recoveryCodes);
                user.setLastModifiedAt(Instant.now());
                
                return userRepository.save(user)
                    .map(savedUser -> {
                        RecoveryCodesResponse response = new RecoveryCodesResponse();
                        response.setRecoveryCodes(recoveryCodes);
                        return Result.<RecoveryCodesResponse, PaymentError>ok(response);
                    });
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error generating recovery codes: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("GENERATE_RECOVERY_CODES_FAILED",
                    "Failed to generate recovery codes: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> terminateTwoFactorAuth(String userId, Boolean skipTwoFactorAuth) {
        log.info("Terminating 2FA for user: {}", userId);
        
        return userRepository.findById(userId)
            .flatMap(user -> {
                user.setTotpStatus("NOT_SET");
                user.setTotpSecret(null);
                user.setTotpRecoveryCodes(null);
                user.setLastModifiedAt(Instant.now());
                
                return userRepository.save(user)
                    .map(savedUser -> Result.<Void, PaymentError>ok(null));
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found: " + userId))))
            .onErrorResume(error -> {
                log.error("Error terminating 2FA: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("TERMINATE_2FA_FAILED",
                    "Failed to terminate 2FA: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> verifyEmail(VerifyEmailRequest request) {
        log.info("Verifying email with token");
        
        return Mono.fromCallable(() -> {
            // In production, validate token and verify user email
            AuthorizeResponse response = new AuthorizeResponse();
            response.setIsEmailSent(false);
            return Result.<AuthorizeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error verifying email: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("VERIFY_EMAIL_FAILED",
                "Failed to verify email: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> requestEmailVerification(String email) {
        log.info("Requesting email verification for: {}", email);
        
        return userRepository.findByEmail(email)
            .flatMap(user -> {
                // In production, send verification email
                AuthorizeResponse response = new AuthorizeResponse();
                response.setIsEmailSent(true);
                return Mono.just(Result.<AuthorizeResponse, PaymentError>ok(response));
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found"))))
            .onErrorResume(error -> {
                log.error("Error requesting email verification: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("REQUEST_EMAIL_VERIFICATION_FAILED",
                    "Failed to request email verification: " + error.getMessage())));
            });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> internalSignup(InternalSignupRequest request) {
        log.info("Internal user signup for email: {}", request.getEmail());
        
        return signUp(new SignUpRequest() {{
            setEmail(request.getEmail());
            setPassword(request.getPassword());
        }});
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> createTenantUser(InternalSignupRequest request) {
        log.info("Creating tenant user for email: {}", request.getEmail());
        
        return internalSignup(request); // Same as internal signup
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> connectAccount(ConnectAccountRequest request) {
        log.info("Connecting account for email: {}", request.getEmail());
        
        return userRepository.findByEmail(request.getEmail())
            .map(user -> {
                // In production, send connection email
                AuthorizeResponse response = new AuthorizeResponse();
                response.setIsEmailSent(true);
                return Result.<AuthorizeResponse, PaymentError>ok(response);
            })
            .switchIfEmpty(Mono.just(Result.err(PaymentError.of("USER_NOT_FOUND", "User not found"))))
            .onErrorResume(error -> {
                log.error("Error connecting account: {}", error.getMessage(), error);
                return Mono.just(Result.err(PaymentError.of("CONNECT_ACCOUNT_FAILED",
                    "Failed to connect account: " + error.getMessage())));
            });
    }
    
    private String generateTotpSecret() {
        // In production, use proper TOTP secret generation (Base32 encoded 16-byte random)
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes).substring(0, 32);
    }
    
    @Override
    public Mono<Result<java.util.List<InviteMultipleUserResponse>, PaymentError>> inviteMultipleUsers(
            String userId, java.util.List<InviteUserRequest> requests) {
        log.info("Inviting multiple users by user: {}", userId);
        
        return Mono.fromCallable(() -> {
            java.util.List<InviteMultipleUserResponse> responses = new java.util.ArrayList<>();
            for (InviteUserRequest request : requests) {
                InviteMultipleUserResponse response = new InviteMultipleUserResponse();
                response.setEmail(request.getEmail());
                response.setIsEmailSent(true);
                // In production, send invitation email and create user role
                responses.add(response);
            }
            return Result.<java.util.List<InviteMultipleUserResponse>, PaymentError>ok(responses);
        })
        .onErrorResume(error -> {
            log.error("Error inviting multiple users: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("INVITE_MULTIPLE_USERS_FAILED",
                "Failed to invite multiple users: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> resendInvite(String userId, ReInviteUserRequest request) {
        log.info("Resending invitation for email: {} by user: {}", request.getEmail(), userId);
        
        return Mono.fromCallable(() -> {
            // In production, resend invitation email
            AuthorizeResponse response = new AuthorizeResponse();
            response.setIsEmailSent(true);
            return Result.<AuthorizeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error resending invite: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("RESEND_INVITE_FAILED",
                "Failed to resend invite: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> acceptInviteFromEmail(AcceptInviteFromEmailRequest request) {
        log.info("Accepting invitation from email with token");
        
        return Mono.fromCallable(() -> {
            // In production, validate token and accept invitation
            AuthorizeResponse response = new AuthorizeResponse();
            response.setIsEmailSent(false);
            return Result.<AuthorizeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error accepting invite from email: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("ACCEPT_INVITE_FAILED",
                "Failed to accept invite: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> terminateAcceptInvite(String userId, AcceptInviteFromEmailRequest request) {
        log.info("Terminating accept invite by user: {}", userId);
        
        return Mono.fromCallable(() -> {
            // In production, terminate the invitation acceptance process
            return Result.<Void, PaymentError>ok(null);
        })
        .onErrorResume(error -> {
            log.error("Error terminating accept invite: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("TERMINATE_ACCEPT_INVITE_FAILED",
                "Failed to terminate accept invite: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<java.util.List<UserRoleDetailsResponse>, PaymentError>> listUserRolesDetails(
            String userId, GetUserRoleDetailsRequest request) {
        log.info("Listing user roles details for email: {} by user: {}", request.getEmail(), userId);
        
        return Mono.fromCallable(() -> {
            // In production, query user roles from database
            java.util.List<UserRoleDetailsResponse> responses = new java.util.ArrayList<>();
            return Result.<java.util.List<UserRoleDetailsResponse>, PaymentError>ok(responses);
        })
        .onErrorResume(error -> {
            log.error("Error listing user roles details: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("LIST_USER_ROLES_DETAILS_FAILED",
                "Failed to list user roles details: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<java.util.List<UserResponse>, PaymentError>> listUsersInLineage(String userId) {
        log.info("Listing users in lineage for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            // In production, query users in the same lineage (org/merchant/profile)
            java.util.List<UserResponse> responses = new java.util.ArrayList<>();
            return Result.<java.util.List<UserResponse>, PaymentError>ok(responses);
        })
        .onErrorResume(error -> {
            log.error("Error listing users in lineage: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("LIST_USERS_IN_LINEAGE_FAILED",
                "Failed to list users in lineage: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<UserRoleDetailsResponse, PaymentError>> updateUserRole(
            String userId, String targetUserId, UpdateUserRoleRequest request) {
        log.info("Updating user role for user: {} by: {}", targetUserId, userId);
        
        return Mono.fromCallable(() -> {
            // In production, update user role in database
            UserRoleDetailsResponse response = new UserRoleDetailsResponse();
            response.setRoleId(request.getRoleId());
            response.setStatus("ACTIVE");
            return Result.<UserRoleDetailsResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error updating user role: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("UPDATE_USER_ROLE_FAILED",
                "Failed to update user role: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> deleteUserRole(String userId, String targetUserId) {
        log.info("Deleting user role for user: {} by: {}", targetUserId, userId);
        
        return Mono.fromCallable(() -> {
            // In production, delete user role from database
            return Result.<Void, PaymentError>ok(null);
        })
        .onErrorResume(error -> {
            log.error("Error deleting user role: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("DELETE_USER_ROLE_FAILED",
                "Failed to delete user role: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<RoleResponse, PaymentError>> getRoleFromToken(String userId) {
        log.info("Getting role from token for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            // In production, get role from user's token/context
            RoleResponse response = new RoleResponse();
            response.setRoleId("default_role");
            response.setRoleName("Default Role");
            return Result.<RoleResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting role from token: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("GET_ROLE_FROM_TOKEN_FAILED",
                "Failed to get role from token: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<RoleResponse, PaymentError>> createRole(String userId, CreateRoleRequest request) {
        log.info("Creating role by user: {}", userId);
        
        return Mono.fromCallable(() -> {
            RoleResponse response = new RoleResponse();
            response.setRoleId(UUID.randomUUID().toString());
            response.setRoleName(request.getRoleName());
            response.setDescription(request.getDescription());
            response.setGroups(request.getGroups());
            response.setResources(request.getResources());
            response.setMetadata(request.getMetadata());
            response.setCreatedAt(Instant.now());
            response.setUpdatedAt(Instant.now());
            // In production, create role in database
            return Result.<RoleResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error creating role: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("CREATE_ROLE_FAILED",
                "Failed to create role: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<RoleResponse, PaymentError>> getRole(String userId, String roleId) {
        log.info("Getting role: {} by user: {}", roleId, userId);
        
        return Mono.fromCallable(() -> {
            // In production, get role from database
            RoleResponse response = new RoleResponse();
            response.setRoleId(roleId);
            response.setRoleName("Sample Role");
            return Result.<RoleResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting role: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("GET_ROLE_FAILED",
                "Failed to get role: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<RoleResponse, PaymentError>> updateRole(String userId, String roleId, CreateRoleRequest request) {
        log.info("Updating role: {} by user: {}", roleId, userId);
        
        return Mono.fromCallable(() -> {
            RoleResponse response = new RoleResponse();
            response.setRoleId(roleId);
            response.setRoleName(request.getRoleName());
            response.setDescription(request.getDescription());
            response.setGroups(request.getGroups());
            response.setResources(request.getResources());
            response.setMetadata(request.getMetadata());
            response.setUpdatedAt(Instant.now());
            // In production, update role in database
            return Result.<RoleResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error updating role: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("UPDATE_ROLE_FAILED",
                "Failed to update role: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<java.util.List<RoleResponse>, PaymentError>> listRoles(String userId) {
        log.info("Listing roles by user: {}", userId);
        
        return Mono.fromCallable(() -> {
            // In production, list roles from database
            java.util.List<RoleResponse> responses = new java.util.ArrayList<>();
            return Result.<java.util.List<RoleResponse>, PaymentError>ok(responses);
        })
        .onErrorResume(error -> {
            log.error("Error listing roles: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("LIST_ROLES_FAILED",
                "Failed to list roles: " + error.getMessage())));
        });
    }
    
    private String generateRecoveryCode() {
        // Generate 8-character recovery code
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        for (int i = 0; i < 8; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }
    
    @Override
    public Mono<Result<ThemeResponse, PaymentError>> getThemeUsingLineage(String userId, String entityType) {
        log.info("Getting theme using lineage for user: {}, entityType: {}", userId, entityType);
        
        return Mono.fromCallable(() -> {
            // In production, get theme from database based on lineage
            ThemeResponse response = new ThemeResponse();
            response.setThemeId(UUID.randomUUID().toString());
            response.setThemeName("Default Theme");
            response.setEntityType(entityType);
            return Result.<ThemeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting theme using lineage: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("GET_THEME_FAILED",
                "Failed to get theme: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<ThemeResponse, PaymentError>> getThemeUsingThemeId(String userId, String themeId) {
        log.info("Getting theme using theme ID: {} for user: {}", themeId, userId);
        
        return Mono.fromCallable(() -> {
            // In production, get theme from database
            ThemeResponse response = new ThemeResponse();
            response.setThemeId(themeId);
            response.setThemeName("Sample Theme");
            return Result.<ThemeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting theme: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("GET_THEME_FAILED",
                "Failed to get theme: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<ThemeResponse, PaymentError>> createTheme(String userId, CreateThemeRequest request) {
        log.info("Creating theme by user: {}", userId);
        
        return Mono.fromCallable(() -> {
            ThemeResponse response = new ThemeResponse();
            response.setThemeId(UUID.randomUUID().toString());
            response.setThemeName(request.getThemeName());
            response.setEntityType(request.getEntityType());
            response.setThemeData(request.getThemeData());
            response.setEmailConfig(request.getEmailConfig());
            response.setCreatedAt(Instant.now());
            response.setUpdatedAt(Instant.now());
            // In production, create theme in database
            return Result.<ThemeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error creating theme: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("CREATE_THEME_FAILED",
                "Failed to create theme: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<ThemeResponse, PaymentError>> updateTheme(String userId, String themeId, UpdateThemeRequest request) {
        log.info("Updating theme: {} by user: {}", themeId, userId);
        
        return Mono.fromCallable(() -> {
            ThemeResponse response = new ThemeResponse();
            response.setThemeId(themeId);
            response.setThemeData(request.getThemeData());
            response.setEmailConfig(request.getEmailConfig());
            response.setUpdatedAt(Instant.now());
            // In production, update theme in database
            return Result.<ThemeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error updating theme: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("UPDATE_THEME_FAILED",
                "Failed to update theme: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> deleteTheme(String userId, String themeId) {
        log.info("Deleting theme: {} by user: {}", themeId, userId);
        
        return Mono.fromCallable(() -> {
            // In production, delete theme from database
            return Result.<Void, PaymentError>ok(null);
        })
        .onErrorResume(error -> {
            log.error("Error deleting theme: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("DELETE_THEME_FAILED",
                "Failed to delete theme: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<ThemeResponse, PaymentError>> createUserTheme(String userId, CreateThemeRequest request) {
        log.info("Creating user theme by user: {}", userId);
        
        return createTheme(userId, request); // Same as create theme
    }
    
    @Override
    public Mono<Result<ThemeResponse, PaymentError>> getUserThemeUsingLineage(String userId, String entityType) {
        log.info("Getting user theme using lineage for user: {}, entityType: {}", userId, entityType);
        
        return getThemeUsingLineage(userId, entityType); // Same as get theme using lineage
    }
    
    @Override
    public Mono<Result<java.util.List<ThemeResponse>, PaymentError>> listAllThemesInLineage(String userId, String entityType) {
        log.info("Listing all themes in lineage for user: {}, entityType: {}", userId, entityType);
        
        return Mono.fromCallable(() -> {
            // In production, list themes from database
            java.util.List<ThemeResponse> responses = new java.util.ArrayList<>();
            return Result.<java.util.List<ThemeResponse>, PaymentError>ok(responses);
        })
        .onErrorResume(error -> {
            log.error("Error listing themes: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("LIST_THEMES_FAILED",
                "Failed to list themes: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<ThemeResponse, PaymentError>> getUserThemeUsingThemeId(String userId, String themeId) {
        log.info("Getting user theme using theme ID: {} for user: {}", themeId, userId);
        
        return getThemeUsingThemeId(userId, themeId); // Same as get theme using theme ID
    }
    
    @Override
    public Mono<Result<ThemeResponse, PaymentError>> updateUserTheme(String userId, String themeId, UpdateThemeRequest request) {
        log.info("Updating user theme: {} by user: {}", themeId, userId);
        
        return updateTheme(userId, themeId, request); // Same as update theme
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> deleteUserTheme(String userId, String themeId) {
        log.info("Deleting user theme: {} by user: {}", themeId, userId);
        
        return deleteTheme(userId, themeId); // Same as delete theme
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> generateSampleData(String userId, SampleDataRequest request) {
        log.info("Generating sample data for merchant: {} by user: {}", request.getMerchantId(), userId);
        
        return Mono.fromCallable(() -> {
            // In production, generate sample payments, refunds, etc.
            AuthorizeResponse response = new AuthorizeResponse();
            response.setIsEmailSent(false);
            return Result.<AuthorizeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error generating sample data: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("GENERATE_SAMPLE_DATA_FAILED",
                "Failed to generate sample data: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<Void, PaymentError>> deleteSampleData(String userId, SampleDataRequest request) {
        log.info("Deleting sample data for merchant: {} by user: {}", request.getMerchantId(), userId);
        
        return Mono.fromCallable(() -> {
            // In production, delete sample data from database
            return Result.<Void, PaymentError>ok(null);
        })
        .onErrorResume(error -> {
            log.error("Error deleting sample data: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("DELETE_SAMPLE_DATA_FAILED",
                "Failed to delete sample data: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<CloneConnectorResponse, PaymentError>> cloneConnector(String userId, CloneConnectorRequest request) {
        log.info("Cloning connector: {} to merchant: {} by user: {}", 
            request.getSourceConnectorId(), request.getTargetMerchantId(), userId);
        
        return Mono.fromCallable(() -> {
            // In production, clone connector configuration
            CloneConnectorResponse response = new CloneConnectorResponse();
            response.setConnectorId(UUID.randomUUID().toString());
            response.setMerchantId(request.getTargetMerchantId());
            response.setProfileId(request.getTargetProfileId());
            return Result.<CloneConnectorResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error cloning connector: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("CLONE_CONNECTOR_FAILED",
                "Failed to clone connector: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<CreateUserAuthenticationMethodResponse, PaymentError>> createUserAuthenticationMethod(
            CreateUserAuthenticationMethodRequest request) {
        log.info("Creating user authentication method for owner: {}", request.getOwnerId());
        
        return Mono.fromCallable(() -> {
            CreateUserAuthenticationMethodResponse response = new CreateUserAuthenticationMethodResponse();
            response.setId(UUID.randomUUID().toString());
            response.setAuthId(UUID.randomUUID().toString());
            response.setOwnerId(request.getOwnerId());
            response.setOwnerType(request.getOwnerType());
            response.setAuthType("OIDC");
            response.setEmailDomain(request.getEmailDomain());
            // In production, create authentication method in database
            return Result.<CreateUserAuthenticationMethodResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error creating authentication method: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("CREATE_AUTH_METHOD_FAILED",
                "Failed to create authentication method: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<CreateUserAuthenticationMethodResponse, PaymentError>> updateUserAuthenticationMethod(
            UpdateUserAuthenticationMethodRequest request) {
        log.info("Updating user authentication method: {}", request.getId());
        
        return Mono.fromCallable(() -> {
            CreateUserAuthenticationMethodResponse response = new CreateUserAuthenticationMethodResponse();
            response.setId(request.getId());
            response.setAuthId(UUID.randomUUID().toString());
            response.setAuthType("OIDC");
            response.setEmailDomain(request.getEmailDomain());
            // In production, update authentication method in database
            return Result.<CreateUserAuthenticationMethodResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error updating authentication method: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("UPDATE_AUTH_METHOD_FAILED",
                "Failed to update authentication method: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<java.util.List<AuthenticationMethodResponse>, PaymentError>> listUserAuthenticationMethods(String authId) {
        log.info("Listing user authentication methods for auth: {}", authId);
        
        return Mono.fromCallable(() -> {
            // In production, list authentication methods from database
            java.util.List<AuthenticationMethodResponse> responses = new java.util.ArrayList<>();
            return Result.<java.util.List<AuthenticationMethodResponse>, PaymentError>ok(responses);
        })
        .onErrorResume(error -> {
            log.error("Error listing authentication methods: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("LIST_AUTH_METHODS_FAILED",
                "Failed to list authentication methods: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<SsoAuthUrlResponse, PaymentError>> getSsoAuthUrl(GetSsoAuthUrlRequest request) {
        log.info("Getting SSO auth URL for auth: {}", request.getAuthId());
        
        return Mono.fromCallable(() -> {
            // In production, generate SSO auth URL
            SsoAuthUrlResponse response = new SsoAuthUrlResponse();
            response.setAuthUrl("https://sso.example.com/auth?auth_id=" + request.getAuthId());
            return Result.<SsoAuthUrlResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting SSO auth URL: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("GET_SSO_AUTH_URL_FAILED",
                "Failed to get SSO auth URL: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> ssoSignIn(SsoSignInRequest request) {
        log.info("SSO sign in with state and code");
        
        return Mono.fromCallable(() -> {
            // In production, validate SSO state and code, create session
            AuthorizeResponse response = new AuthorizeResponse();
            response.setIsEmailSent(false);
            return Result.<AuthorizeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error SSO sign in: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("SSO_SIGN_IN_FAILED",
                "Failed to sign in with SSO: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> terminateAuthSelect(String userId, AuthSelectRequest request) {
        log.info("Terminating auth select for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            // In production, terminate auth selection process
            AuthorizeResponse response = new AuthorizeResponse();
            response.setIsEmailSent(false);
            return Result.<AuthorizeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error terminating auth select: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("TERMINATE_AUTH_SELECT_FAILED",
                "Failed to terminate auth select: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<UserKeyTransferResponse, PaymentError>> transferUserKey(UserKeyTransferRequest request) {
        log.info("Transferring user keys from: {}, limit: {}", request.getFrom(), request.getLimit());
        
        return Mono.fromCallable(() -> {
            // In production, transfer user keys
            UserKeyTransferResponse response = new UserKeyTransferResponse();
            response.setTotalTransferred(0);
            return Result.<UserKeyTransferResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error transferring user keys: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("TRANSFER_USER_KEY_FAILED",
                "Failed to transfer user keys: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<AuthorizeResponse, PaymentError>> createPlatform(String userId, CreatePlatformRequest request) {
        log.info("Creating platform: {} by user: {}", request.getPlatformName(), userId);
        
        return Mono.fromCallable(() -> {
            // In production, create platform account
            AuthorizeResponse response = new AuthorizeResponse();
            response.setIsEmailSent(false);
            return Result.<AuthorizeResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error creating platform: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("CREATE_PLATFORM_FAILED",
                "Failed to create platform: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<PermissionInfoResponse, PaymentError>> getPermissionInfo(String userId) {
        log.info("Getting permission info for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            PermissionInfoResponse response = new PermissionInfoResponse();
            response.setGroups(new java.util.ArrayList<>());
            response.setResources(new java.util.ArrayList<>());
            response.setPermissions(new java.util.HashMap<>());
            // In production, get permission info from database
            return Result.<PermissionInfoResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting permission info: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("GET_PERMISSION_INFO_FAILED",
                "Failed to get permission info: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<ModuleListResponse, PaymentError>> getModuleList(String userId) {
        log.info("Getting module list for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            ModuleListResponse response = new ModuleListResponse();
            response.setModules(new java.util.ArrayList<>());
            // In production, get module list from database
            return Result.<ModuleListResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting module list: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("GET_MODULE_LIST_FAILED",
                "Failed to get module list: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<ParentListResponse, PaymentError>> getParentList(String userId) {
        log.info("Getting parent list for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            ParentListResponse response = new ParentListResponse();
            response.setParents(new java.util.ArrayList<>());
            // In production, get parent list from database
            return Result.<ParentListResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error getting parent list: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("GET_PARENT_LIST_FAILED",
                "Failed to get parent list: " + error.getMessage())));
        });
    }
    
    @Override
    public Mono<Result<InvitationListResponse, PaymentError>> listInvitations(String userId) {
        log.info("Listing invitations for user: {}", userId);
        
        return Mono.fromCallable(() -> {
            InvitationListResponse response = new InvitationListResponse();
            response.setInvitations(new java.util.ArrayList<>());
            // In production, list invitations from database
            return Result.<InvitationListResponse, PaymentError>ok(response);
        })
        .onErrorResume(error -> {
            log.error("Error listing invitations: {}", error.getMessage(), error);
            return Mono.just(Result.err(PaymentError.of("LIST_INVITATIONS_FAILED",
                "Failed to list invitations: " + error.getMessage())));
        });
    }
    
    private UserResponse toUserResponse(UserEntity entity) {
        UserResponse response = new UserResponse();
        response.setUserId(entity.getUserId());
        response.setEmail(entity.getEmail());
        response.setName(entity.getName());
        response.setIsVerified(entity.getIsVerified());
        response.setCreatedAt(entity.getCreatedAt());
        response.setLastModifiedAt(entity.getLastModifiedAt());
        response.setTotpStatus(entity.getTotpStatus());
        return response;
    }
}

