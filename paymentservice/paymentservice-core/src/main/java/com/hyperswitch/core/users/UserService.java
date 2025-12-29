package com.hyperswitch.core.users;

import com.hyperswitch.common.dto.*;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.Result;
import reactor.core.publisher.Mono;

/**
 * Service interface for user management operations
 */
public interface UserService {
    
    /**
     * Get user details
     */
    Mono<Result<UserResponse, PaymentError>> getUserDetails(String userId);
    
    /**
     * User sign in
     */
    Mono<Result<AuthorizeResponse, PaymentError>> signIn(SignInRequest request);
    
    /**
     * User sign up
     */
    Mono<Result<AuthorizeResponse, PaymentError>> signUp(SignUpRequest request);
    
    /**
     * User sign up with merchant ID
     */
    Mono<Result<AuthorizeResponse, PaymentError>> signUpWithMerchantId(SignUpWithMerchantIdRequest request);
    
    /**
     * User sign out
     */
    Mono<Result<Void, PaymentError>> signOut(String userId);
    
    /**
     * Change password
     */
    Mono<Result<Void, PaymentError>> changePassword(String userId, ChangePasswordRequest request);
    
    /**
     * Rotate password
     */
    Mono<Result<Void, PaymentError>> rotatePassword(String userId, RotatePasswordRequest request);
    
    /**
     * Forgot password
     */
    Mono<Result<AuthorizeResponse, PaymentError>> forgotPassword(ForgotPasswordRequest request);
    
    /**
     * Reset password
     */
    Mono<Result<AuthorizeResponse, PaymentError>> resetPassword(ResetPasswordRequest request);
    
    /**
     * Update user account
     */
    Mono<Result<UserResponse, PaymentError>> updateUser(String userId, UpdateUserRequest request);
    
    /**
     * Get user from email
     */
    Mono<Result<UserResponse, PaymentError>> getUserFromEmail(String email);
    
    /**
     * List organizations for user
     */
    Mono<Result<java.util.List<com.hyperswitch.common.dto.OrganizationResponse>, PaymentError>> listOrganizations(String userId);
    
    /**
     * List merchants for user in organization
     */
    Mono<Result<java.util.List<com.hyperswitch.common.dto.MerchantAccountResponse>, PaymentError>> listMerchants(String userId, String orgId);
    
    /**
     * List profiles for user in organization and merchant
     */
    Mono<Result<java.util.List<com.hyperswitch.common.dto.ProfileResponse>, PaymentError>> listProfiles(String userId, String orgId, String merchantId);
    
    /**
     * Switch organization
     */
    Mono<Result<Void, PaymentError>> switchOrganization(String userId, SwitchOrganizationRequest request);
    
    /**
     * Switch merchant
     */
    Mono<Result<Void, PaymentError>> switchMerchant(String userId, SwitchMerchantRequest request);
    
    /**
     * Switch profile
     */
    Mono<Result<Void, PaymentError>> switchProfile(String userId, SwitchProfileRequest request);
    
    /**
     * Get dashboard metadata
     */
    Mono<Result<DashboardMetadataResponse, PaymentError>> getDashboardMetadata(String userId);
    
    /**
     * Set dashboard metadata
     */
    Mono<Result<DashboardMetadataResponse, PaymentError>> setDashboardMetadata(String userId, DashboardMetadataRequest request);
    
    /**
     * Create organization
     */
    Mono<Result<com.hyperswitch.common.dto.OrganizationResponse, PaymentError>> createOrganization(String userId, com.hyperswitch.common.dto.OrganizationRequest request);
    
    /**
     * Create merchant account
     */
    Mono<Result<com.hyperswitch.common.dto.MerchantAccountResponse, PaymentError>> createMerchant(String userId, com.hyperswitch.common.dto.MerchantAccountCreateRequest request);
    
    /**
     * Check 2FA status
     */
    Mono<Result<TwoFactorAuthStatusResponse, PaymentError>> checkTwoFactorAuthStatus(String userId);
    
    /**
     * Check 2FA status with attempts
     */
    Mono<Result<TwoFactorAuthStatusResponse, PaymentError>> checkTwoFactorAuthStatusWithAttempts(String userId);
    
    /**
     * Begin TOTP setup
     */
    Mono<Result<BeginTotpResponse, PaymentError>> beginTotp(String userId);
    
    /**
     * Reset TOTP
     */
    Mono<Result<BeginTotpResponse, PaymentError>> resetTotp(String userId);
    
    /**
     * Verify TOTP
     */
    Mono<Result<AuthorizeResponse, PaymentError>> verifyTotp(String userId, VerifyTotpRequest request);
    
    /**
     * Update TOTP
     */
    Mono<Result<AuthorizeResponse, PaymentError>> updateTotp(String userId, VerifyTotpRequest request);
    
    /**
     * Verify recovery code
     */
    Mono<Result<AuthorizeResponse, PaymentError>> verifyRecoveryCode(String userId, VerifyRecoveryCodeRequest request);
    
    /**
     * Generate recovery codes
     */
    Mono<Result<RecoveryCodesResponse, PaymentError>> generateRecoveryCodes(String userId);
    
    /**
     * Terminate 2FA
     */
    Mono<Result<Void, PaymentError>> terminateTwoFactorAuth(String userId, Boolean skipTwoFactorAuth);
    
    /**
     * Verify email
     */
    Mono<Result<AuthorizeResponse, PaymentError>> verifyEmail(VerifyEmailRequest request);
    
    /**
     * Request email verification
     */
    Mono<Result<AuthorizeResponse, PaymentError>> requestEmailVerification(String email);
    
    /**
     * Internal user signup
     */
    Mono<Result<AuthorizeResponse, PaymentError>> internalSignup(InternalSignupRequest request);
    
    /**
     * Create tenant user
     */
    Mono<Result<AuthorizeResponse, PaymentError>> createTenantUser(InternalSignupRequest request);
    
    /**
     * Connect account
     */
    Mono<Result<AuthorizeResponse, PaymentError>> connectAccount(ConnectAccountRequest request);
    
    /**
     * Invite multiple users
     */
    Mono<Result<java.util.List<InviteMultipleUserResponse>, PaymentError>> inviteMultipleUsers(String userId, java.util.List<InviteUserRequest> requests);
    
    /**
     * Resend invitation
     */
    Mono<Result<AuthorizeResponse, PaymentError>> resendInvite(String userId, ReInviteUserRequest request);
    
    /**
     * Accept invitation from email
     */
    Mono<Result<AuthorizeResponse, PaymentError>> acceptInviteFromEmail(AcceptInviteFromEmailRequest request);
    
    /**
     * Terminate accept invite
     */
    Mono<Result<Void, PaymentError>> terminateAcceptInvite(String userId, AcceptInviteFromEmailRequest request);
    
    /**
     * List user roles details
     */
    Mono<Result<java.util.List<UserRoleDetailsResponse>, PaymentError>> listUserRolesDetails(String userId, GetUserRoleDetailsRequest request);
    
    /**
     * List users in lineage
     */
    Mono<Result<java.util.List<UserResponse>, PaymentError>> listUsersInLineage(String userId);
    
    /**
     * Update user role
     */
    Mono<Result<UserRoleDetailsResponse, PaymentError>> updateUserRole(String userId, String targetUserId, UpdateUserRoleRequest request);
    
    /**
     * Delete user role
     */
    Mono<Result<Void, PaymentError>> deleteUserRole(String userId, String targetUserId);
    
    /**
     * Get role from token
     */
    Mono<Result<RoleResponse, PaymentError>> getRoleFromToken(String userId);
    
    /**
     * Create role
     */
    Mono<Result<RoleResponse, PaymentError>> createRole(String userId, CreateRoleRequest request);
    
    /**
     * Get role
     */
    Mono<Result<RoleResponse, PaymentError>> getRole(String userId, String roleId);
    
    /**
     * Update role
     */
    Mono<Result<RoleResponse, PaymentError>> updateRole(String userId, String roleId, CreateRoleRequest request);
    
    /**
     * List roles
     */
    Mono<Result<java.util.List<RoleResponse>, PaymentError>> listRoles(String userId);
    
    /**
     * Get theme using lineage
     */
    Mono<Result<ThemeResponse, PaymentError>> getThemeUsingLineage(String userId, String entityType);
    
    /**
     * Get theme using theme ID
     */
    Mono<Result<ThemeResponse, PaymentError>> getThemeUsingThemeId(String userId, String themeId);
    
    /**
     * Create theme
     */
    Mono<Result<ThemeResponse, PaymentError>> createTheme(String userId, CreateThemeRequest request);
    
    /**
     * Update theme
     */
    Mono<Result<ThemeResponse, PaymentError>> updateTheme(String userId, String themeId, UpdateThemeRequest request);
    
    /**
     * Delete theme
     */
    Mono<Result<Void, PaymentError>> deleteTheme(String userId, String themeId);
    
    /**
     * Create user theme
     */
    Mono<Result<ThemeResponse, PaymentError>> createUserTheme(String userId, CreateThemeRequest request);
    
    /**
     * Get user theme using lineage
     */
    Mono<Result<ThemeResponse, PaymentError>> getUserThemeUsingLineage(String userId, String entityType);
    
    /**
     * List all themes in lineage
     */
    Mono<Result<java.util.List<ThemeResponse>, PaymentError>> listAllThemesInLineage(String userId, String entityType);
    
    /**
     * Get user theme using theme ID
     */
    Mono<Result<ThemeResponse, PaymentError>> getUserThemeUsingThemeId(String userId, String themeId);
    
    /**
     * Update user theme
     */
    Mono<Result<ThemeResponse, PaymentError>> updateUserTheme(String userId, String themeId, UpdateThemeRequest request);
    
    /**
     * Delete user theme
     */
    Mono<Result<Void, PaymentError>> deleteUserTheme(String userId, String themeId);
    
    /**
     * Generate sample data
     */
    Mono<Result<AuthorizeResponse, PaymentError>> generateSampleData(String userId, SampleDataRequest request);
    
    /**
     * Delete sample data
     */
    Mono<Result<Void, PaymentError>> deleteSampleData(String userId, SampleDataRequest request);
    
    /**
     * Clone connector
     */
    Mono<Result<CloneConnectorResponse, PaymentError>> cloneConnector(String userId, CloneConnectorRequest request);
    
    /**
     * Create user authentication method
     */
    Mono<Result<CreateUserAuthenticationMethodResponse, PaymentError>> createUserAuthenticationMethod(CreateUserAuthenticationMethodRequest request);
    
    /**
     * Update user authentication method
     */
    Mono<Result<CreateUserAuthenticationMethodResponse, PaymentError>> updateUserAuthenticationMethod(UpdateUserAuthenticationMethodRequest request);
    
    /**
     * List user authentication methods
     */
    Mono<Result<java.util.List<AuthenticationMethodResponse>, PaymentError>> listUserAuthenticationMethods(String authId);
    
    /**
     * Get SSO auth URL
     */
    Mono<Result<SsoAuthUrlResponse, PaymentError>> getSsoAuthUrl(GetSsoAuthUrlRequest request);
    
    /**
     * SSO sign in
     */
    Mono<Result<AuthorizeResponse, PaymentError>> ssoSignIn(SsoSignInRequest request);
    
    /**
     * Terminate auth select
     */
    Mono<Result<AuthorizeResponse, PaymentError>> terminateAuthSelect(String userId, AuthSelectRequest request);
    
    /**
     * Transfer user key
     */
    Mono<Result<UserKeyTransferResponse, PaymentError>> transferUserKey(UserKeyTransferRequest request);
    
    /**
     * Create platform
     */
    Mono<Result<AuthorizeResponse, PaymentError>> createPlatform(String userId, CreatePlatformRequest request);
    
    /**
     * Get permission info
     */
    Mono<Result<PermissionInfoResponse, PaymentError>> getPermissionInfo(String userId);
    
    /**
     * Get module list
     */
    Mono<Result<ModuleListResponse, PaymentError>> getModuleList(String userId);
    
    /**
     * Get parent list
     */
    Mono<Result<ParentListResponse, PaymentError>> getParentList(String userId);
    
    /**
     * List invitations
     */
    Mono<Result<InvitationListResponse, PaymentError>> listInvitations(String userId);
}

