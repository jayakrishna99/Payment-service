package com.hyperswitch.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Map;

/**
 * Response DTO for payout accounts
 */
@Schema(description = "Response for payout accounts")
public class PayoutAccountsResponse {
    
    @Schema(description = "List of payout accounts")
    @JsonProperty("accounts")
    private List<PayoutAccount> accounts;

    // Getters and Setters
    public List<PayoutAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<PayoutAccount> accounts) {
        this.accounts = accounts;
    }
    
    /**
     * Payout account structure
     */
    public static class PayoutAccount {
        @Schema(description = "Account ID", example = "acc_123")
        @JsonProperty("account_id")
        private String accountId;
        
        @Schema(description = "Account type", example = "bank_account")
        @JsonProperty("account_type")
        private String accountType;
        
        @Schema(description = "Account details")
        @JsonProperty("account_details")
        private Map<String, Object> accountDetails;
        
        @Schema(description = "Is default account")
        @JsonProperty("is_default")
        private Boolean isDefault;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getAccountType() {
            return accountType;
        }

        public void setAccountType(String accountType) {
            this.accountType = accountType;
        }

        public Map<String, Object> getAccountDetails() {
            return accountDetails;
        }

        public void setAccountDetails(Map<String, Object> accountDetails) {
            this.accountDetails = accountDetails;
        }

        public Boolean getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(Boolean isDefault) {
            this.isDefault = isDefault;
        }
    }
}

