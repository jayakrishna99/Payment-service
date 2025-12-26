package com.hyperswitch.common.dto;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Detailed reconciliation report with discrepancy analysis
 */
public class ReconciliationReportResponse {
    private String reconciliationId;
    private String merchantId;
    private String reconciliationType;
    private String status;
    private Instant startDate;
    private Instant endDate;
    
    // Summary statistics
    private Integer totalTransactions;
    private Integer matchedTransactions;
    private Integer unmatchedTransactions;
    private Double matchRate; // Percentage
    
    // Discrepancy details
    private List<DiscrepancyDetail> discrepancies;
    private DiscrepancySummary discrepancySummary;
    
    // Breakdown by type
    private Map<String, Integer> discrepanciesByType; // Type -> Count
    private Map<String, Long> discrepanciesByAmount; // Type -> Total Amount
    
    // Export information
    private String reportGeneratedAt;
    private String reportFormat; // "JSON", "CSV", "PDF"
    
    // Getters and Setters
    public String getReconciliationId() {
        return reconciliationId;
    }

    public void setReconciliationId(String reconciliationId) {
        this.reconciliationId = reconciliationId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getReconciliationType() {
        return reconciliationType;
    }

    public void setReconciliationType(String reconciliationType) {
        this.reconciliationType = reconciliationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Integer totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public Integer getMatchedTransactions() {
        return matchedTransactions;
    }

    public void setMatchedTransactions(Integer matchedTransactions) {
        this.matchedTransactions = matchedTransactions;
    }

    public Integer getUnmatchedTransactions() {
        return unmatchedTransactions;
    }

    public void setUnmatchedTransactions(Integer unmatchedTransactions) {
        this.unmatchedTransactions = unmatchedTransactions;
    }

    public Double getMatchRate() {
        return matchRate;
    }

    public void setMatchRate(Double matchRate) {
        this.matchRate = matchRate;
    }

    public List<DiscrepancyDetail> getDiscrepancies() {
        return discrepancies;
    }

    public void setDiscrepancies(List<DiscrepancyDetail> discrepancies) {
        this.discrepancies = discrepancies;
    }

    public DiscrepancySummary getDiscrepancySummary() {
        return discrepancySummary;
    }

    public void setDiscrepancySummary(DiscrepancySummary discrepancySummary) {
        this.discrepancySummary = discrepancySummary;
    }

    public Map<String, Integer> getDiscrepanciesByType() {
        return discrepanciesByType;
    }

    public void setDiscrepanciesByType(Map<String, Integer> discrepanciesByType) {
        this.discrepanciesByType = discrepanciesByType;
    }

    public Map<String, Long> getDiscrepanciesByAmount() {
        return discrepanciesByAmount;
    }

    public void setDiscrepanciesByAmount(Map<String, Long> discrepanciesByAmount) {
        this.discrepanciesByAmount = discrepanciesByAmount;
    }

    public String getReportGeneratedAt() {
        return reportGeneratedAt;
    }

    public void setReportGeneratedAt(String reportGeneratedAt) {
        this.reportGeneratedAt = reportGeneratedAt;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    /**
     * Detailed discrepancy information
     */
    public static class DiscrepancyDetail {
        private String paymentId;
        private String attemptId;
        private String discrepancyType; // "AMOUNT_MISMATCH", "MISSING_IN_CONNECTOR", "MISSING_IN_INTERNAL", "STATUS_MISMATCH", "DATE_MISMATCH"
        private String description;
        private Long internalAmount;
        private Long connectorAmount;
        private String internalStatus;
        private String connectorStatus;
        private Instant internalDate;
        private Instant connectorDate;
        private String severity; // "HIGH", "MEDIUM", "LOW"
        private String recommendation;

        // Getters and Setters
        public String getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(String paymentId) {
            this.paymentId = paymentId;
        }

        public String getAttemptId() {
            return attemptId;
        }

        public void setAttemptId(String attemptId) {
            this.attemptId = attemptId;
        }

        public String getDiscrepancyType() {
            return discrepancyType;
        }

        public void setDiscrepancyType(String discrepancyType) {
            this.discrepancyType = discrepancyType;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Long getInternalAmount() {
            return internalAmount;
        }

        public void setInternalAmount(Long internalAmount) {
            this.internalAmount = internalAmount;
        }

        public Long getConnectorAmount() {
            return connectorAmount;
        }

        public void setConnectorAmount(Long connectorAmount) {
            this.connectorAmount = connectorAmount;
        }

        public String getInternalStatus() {
            return internalStatus;
        }

        public void setInternalStatus(String internalStatus) {
            this.internalStatus = internalStatus;
        }

        public String getConnectorStatus() {
            return connectorStatus;
        }

        public void setConnectorStatus(String connectorStatus) {
            this.connectorStatus = connectorStatus;
        }

        public Instant getInternalDate() {
            return internalDate;
        }

        public void setInternalDate(Instant internalDate) {
            this.internalDate = internalDate;
        }

        public Instant getConnectorDate() {
            return connectorDate;
        }

        public void setConnectorDate(Instant connectorDate) {
            this.connectorDate = connectorDate;
        }

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }

        public String getRecommendation() {
            return recommendation;
        }

        public void setRecommendation(String recommendation) {
            this.recommendation = recommendation;
        }
    }

    /**
     * Summary of discrepancies
     */
    public static class DiscrepancySummary {
        private Integer totalDiscrepancies;
        private Integer highSeverityCount;
        private Integer mediumSeverityCount;
        private Integer lowSeverityCount;
        private Long totalAmountDiscrepancy; // In minor units
        private Map<String, Integer> countByType; // Type -> Count

        // Getters and Setters
        public Integer getTotalDiscrepancies() {
            return totalDiscrepancies;
        }

        public void setTotalDiscrepancies(Integer totalDiscrepancies) {
            this.totalDiscrepancies = totalDiscrepancies;
        }

        public Integer getHighSeverityCount() {
            return highSeverityCount;
        }

        public void setHighSeverityCount(Integer highSeverityCount) {
            this.highSeverityCount = highSeverityCount;
        }

        public Integer getMediumSeverityCount() {
            return mediumSeverityCount;
        }

        public void setMediumSeverityCount(Integer mediumSeverityCount) {
            this.mediumSeverityCount = mediumSeverityCount;
        }

        public Integer getLowSeverityCount() {
            return lowSeverityCount;
        }

        public void setLowSeverityCount(Integer lowSeverityCount) {
            this.lowSeverityCount = lowSeverityCount;
        }

        public Long getTotalAmountDiscrepancy() {
            return totalAmountDiscrepancy;
        }

        public void setTotalAmountDiscrepancy(Long totalAmountDiscrepancy) {
            this.totalAmountDiscrepancy = totalAmountDiscrepancy;
        }

        public Map<String, Integer> getCountByType() {
            return countByType;
        }

        public void setCountByType(Map<String, Integer> countByType) {
            this.countByType = countByType;
        }
    }
}

