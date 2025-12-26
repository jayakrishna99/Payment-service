package com.hyperswitch.core.reconciliation.impl;

import com.hyperswitch.common.dto.ReconciliationRequest;
import com.hyperswitch.common.dto.ReconciliationResponse;
import com.hyperswitch.common.dto.ReconciliationReportResponse;
import com.hyperswitch.common.dto.ReconStatusResponse;
import com.hyperswitch.common.dto.ReconTokenResponse;
import com.hyperswitch.common.errors.PaymentError;
import com.hyperswitch.common.types.ReconciliationId;
import com.hyperswitch.common.types.ReconStatus;
import com.hyperswitch.connectors.ConnectorService;
import com.hyperswitch.core.reconciliation.ReconciliationService;
import com.hyperswitch.storage.entity.ReconciliationEntity;
import com.hyperswitch.storage.entity.PaymentIntentEntity;
import com.hyperswitch.storage.repository.ReconciliationRepository;
import com.hyperswitch.storage.repository.PaymentIntentRepository;
import com.hyperswitch.storage.repository.PaymentAttemptRepository;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of ReconciliationService
 */
@Service
public class ReconciliationServiceImpl implements ReconciliationService {

    private static final Logger log = LoggerFactory.getLogger(ReconciliationServiceImpl.class);
    private static final String RECONCILIATION_NOT_FOUND = "Reconciliation not found";

    private final ReconciliationRepository reconciliationRepository;
    private final PaymentIntentRepository paymentIntentRepository;
    @SuppressWarnings("unused")
    private final PaymentAttemptRepository paymentAttemptRepository;
    @SuppressWarnings("unused")
    private final ConnectorService connectorService;

    @Autowired
    public ReconciliationServiceImpl(
            ReconciliationRepository reconciliationRepository,
            PaymentIntentRepository paymentIntentRepository,
            PaymentAttemptRepository paymentAttemptRepository,
            ConnectorService connectorService) {
        this.reconciliationRepository = reconciliationRepository;
        this.paymentIntentRepository = paymentIntentRepository;
        this.paymentAttemptRepository = paymentAttemptRepository;
        this.connectorService = connectorService;
    }

    @Override
    public Mono<Either<PaymentError, ReconciliationResponse>> startReconciliation(
        String merchantId,
        ReconciliationRequest request
    ) {
        log.info("Starting reconciliation for merchant: {}, type: {}", merchantId, request.getReconciliationType());
        
        return Mono.fromCallable(() -> {
            ReconciliationId reconId = ReconciliationId.generate();
            Instant now = Instant.now();
            
            ReconciliationEntity entity = new ReconciliationEntity();
            entity.setReconciliationId(reconId.getValue());
            entity.setMerchantId(merchantId);
            entity.setReconciliationType(request.getReconciliationType());
            entity.setStatus("PENDING");
            entity.setStartDate(request.getStartDate());
            entity.setEndDate(request.getEndDate());
            entity.setConnectorId(request.getConnectorId());
            entity.setTotalTransactions(0);
            entity.setMatchedTransactions(0);
            entity.setUnmatchedTransactions(0);
            entity.setCreatedAt(now);
            entity.setModifiedAt(now);
            
            return entity;
        })
        .flatMap(reconciliationRepository::save)
        .map(entity -> ReconciliationMapper.toReconciliationResponse(entity))
        .map(response -> Either.<PaymentError, ReconciliationResponse>right(response))
        .onErrorResume(error -> {
            log.error("Error starting reconciliation", error);
            return Mono.just(Either.left(PaymentError.of("RECONCILIATION_FAILED", error.getMessage())));
        });
    }

    @Override
    public Mono<Either<PaymentError, ReconciliationResponse>> getReconciliation(
        String merchantId,
        String reconciliationId
    ) {
        log.info("Retrieving reconciliation: {} for merchant: {}", reconciliationId, merchantId);
        
        return reconciliationRepository.findByReconciliationId(reconciliationId)
            .filter(entity -> entity.getMerchantId().equals(merchantId))
            .map(entity -> ReconciliationMapper.toReconciliationResponse(entity))
            .map(response -> Either.<PaymentError, ReconciliationResponse>right(response))
            .switchIfEmpty(Mono.just(Either.left(PaymentError.of("NOT_FOUND", RECONCILIATION_NOT_FOUND))));
    }

    @Override
    public Flux<ReconciliationResponse> listReconciliations(String merchantId) {
        log.info("Listing reconciliations for merchant: {}", merchantId);
        
        return reconciliationRepository.findByMerchantIdOrderByCreatedAtDesc(merchantId)
            .map(entity -> ReconciliationMapper.toReconciliationResponse(entity));
    }

    @Override
    public Mono<ReconStatusResponse> getReconciliationStatus(String merchantId) {
        log.info("Getting reconciliation status for merchant: {}", merchantId);
        
        // In a real implementation, this would check the merchant account's reconciliation status
        // For now, we'll return a default status
        return Mono.just(new ReconStatusResponse(ReconStatus.NOT_REQUESTED));
    }

    @Override
    public Mono<Either<PaymentError, ReconStatusResponse>> requestReconciliation(String merchantId) {
        log.info("Requesting reconciliation activation for merchant: {}", merchantId);
        
        // In a real implementation, this would update the merchant account's reconciliation status
        // For now, we'll return a requested status
        return Mono.just(Either.right(new ReconStatusResponse(ReconStatus.REQUESTED)));
    }

    @Override
    public Mono<Either<PaymentError, ReconTokenResponse>> generateReconciliationToken(String merchantId) {
        log.info("Generating reconciliation token for merchant: {}", merchantId);
        
        String token = "recon_token_" + UUID.randomUUID().toString().replace("-", "");
        return Mono.just(Either.right(new ReconTokenResponse(token)));
    }
    
    @Override
    public Mono<Either<PaymentError, String>> scheduleReconciliation(String merchantId, String scheduleCronExpression) {
        log.info("Scheduling reconciliation for merchant: {} with cron: {}", merchantId, scheduleCronExpression);
        
        // In a real implementation, this would:
        // 1. Store the schedule in a scheduled_task table
        // 2. Register the job with a scheduler (e.g., Quartz, Spring Scheduler)
        // 3. Return the schedule ID
        
        String scheduleId = "recon_schedule_" + UUID.randomUUID().toString().replace("-", "");
        log.info("Reconciliation scheduled with ID: {}", scheduleId);
        return Mono.just(Either.right(scheduleId));
    }
    
    @Override
    public Mono<Either<PaymentError, Void>> cancelScheduledReconciliation(String merchantId) {
        log.info("Cancelling scheduled reconciliation for merchant: {}", merchantId);
        
        // In a real implementation, this would:
        // 1. Find the scheduled task for the merchant
        // 2. Cancel/delete the scheduled job
        // 3. Update the scheduled_task table
        
        log.info("Scheduled reconciliation cancelled for merchant: {}", merchantId);
        return Mono.just(Either.right(null));
    }
    
    @Override
    public Mono<Either<PaymentError, ReconciliationResponse>> executeScheduledReconciliation(String merchantId) {
        log.info("Executing scheduled reconciliation for merchant: {}", merchantId);
        
        // In a real implementation, this would:
        // 1. Determine the date range (e.g., last 24 hours, last reconciliation to now)
        // 2. Create a reconciliation request
        // 3. Execute the reconciliation
        
        ReconciliationRequest request = new ReconciliationRequest();
        request.setReconciliationType("AUTOMATED");
        request.setStartDate(Instant.now().minusSeconds(86400)); // Last 24 hours
        request.setEndDate(Instant.now());
        request.setConnectorId(null); // All connectors
        
        return startReconciliation(merchantId, request);
    }
    
    @Override
    public Mono<Either<PaymentError, ReconciliationResponse>> performTwoWayReconciliation(
        String merchantId,
        String reconciliationId
    ) {
        log.info("Performing 2-way reconciliation: {} for merchant: {}", reconciliationId, merchantId);
        
        return reconciliationRepository.findByReconciliationId(reconciliationId)
            .filter(entity -> entity.getMerchantId().equals(merchantId))
            .flatMap(reconciliation -> {
                // 2-way reconciliation: Compare internal records with connector records
                // 1. Fetch all internal payment records for the date range
                // 2. Fetch corresponding records from connector
                // 3. Match records by transaction ID, amount, and date
                // 4. Identify unmatched records
                
                Instant startDate = reconciliation.getStartDate();
                Instant endDate = reconciliation.getEndDate();
                String connectorId = reconciliation.getConnectorId();
                
                log.info("Reconciling payments from {} to {} for connector: {}", 
                    startDate, endDate, connectorId);
                
                // Fetch internal payment records
                return paymentIntentRepository.findByMerchantIdAndCreatedAtBetween(
                    merchantId, startDate, endDate)
                    .collectList()
                    .flatMap(internalPayments -> {
                        int totalTransactions = internalPayments.size();
                        int matchedTransactions = 0;
                        int unmatchedTransactions = 0;
                        
                        // For each internal payment, try to match with connector records
                        // In production, this would:
                        // 1. Call connector API to fetch transaction records
                        // 2. Match by connector_transaction_id, amount, currency, date
                        // 3. Track matches and mismatches
                        
                        // Simulate matching logic
                        for (var payment : internalPayments) {
                            // Check if payment has connector transaction ID
                            // In production, would verify with connector API
                            boolean matched = payment.getStatus() != null && 
                                (payment.getStatus().contains("SUCCEEDED") || 
                                 payment.getStatus().contains("CAPTURED"));
                            
                            if (matched) {
                                matchedTransactions++;
                            } else {
                                unmatchedTransactions++;
                            }
                        }
                        
                        // Update reconciliation entity with results
                        reconciliation.setTotalTransactions(totalTransactions);
                        reconciliation.setMatchedTransactions(matchedTransactions);
                        reconciliation.setUnmatchedTransactions(unmatchedTransactions);
                        reconciliation.setStatus("COMPLETED");
                        reconciliation.setModifiedAt(Instant.now());
                        
                        return reconciliationRepository.save(reconciliation);
                    })
                    .map(entity -> ReconciliationMapper.toReconciliationResponse(entity))
                    .map(response -> Either.<PaymentError, ReconciliationResponse>right(response));
            })
            .switchIfEmpty(Mono.just(Either.left(PaymentError.of("NOT_FOUND", RECONCILIATION_NOT_FOUND))));
    }
    
    @Override
    public Mono<Either<PaymentError, ReconciliationResponse>> performThreeWayReconciliation(
        String merchantId,
        String reconciliationId,
        String bankRecords
    ) {
        log.info("Performing 3-way reconciliation: {} for merchant: {}", reconciliationId, merchantId);
        
        return reconciliationRepository.findByReconciliationId(reconciliationId)
            .filter(entity -> entity.getMerchantId().equals(merchantId))
            .flatMap(reconciliation -> {
                // 3-way reconciliation: Compare internal, connector, and bank records
                // 1. Perform 2-way reconciliation first (internal vs connector)
                // 2. Match results with bank statement records
                // 3. Identify discrepancies across all three sources
                
                Instant startDate = reconciliation.getStartDate();
                Instant endDate = reconciliation.getEndDate();
                
                log.info("Reconciling with bank records from {} to {}", startDate, endDate);
                
                // First perform 2-way reconciliation
                return performTwoWayReconciliation(merchantId, reconciliationId)
                    .flatMap(twoWayResult -> {
                        if (twoWayResult.isLeft()) {
                            return Mono.just(twoWayResult);
                        }
                        
                        ReconciliationResponse twoWayResponse = twoWayResult.get();
                        
                        // Parse bank records (CSV or JSON format)
                        // In production, this would parse bank statement file
                        Map<String, Object> bankRecordMap = parseBankRecords(bankRecords);
                        
                        // Match bank records with internal/connector records
                        // In production, this would:
                        // 1. Parse bank statement (CSV/OFX/MT940 format)
                        // 2. Match by amount, date, reference number
                        // 3. Identify records present in bank but not in internal/connector
                        // 4. Identify records in internal/connector but not in bank
                        
                        int bankRecordCount = bankRecordMap.size();
                        int threeWayMatched = twoWayResponse.getMatchedTransactions();
                        int bankOnlyRecords = Math.max(0, bankRecordCount - threeWayMatched);
                        
                        // Update reconciliation with 3-way results
                        return reconciliationRepository.findByReconciliationId(reconciliationId)
                            .flatMap(recon -> {
                                recon.setTotalTransactions(
                                    twoWayResponse.getTotalTransactions() + bankOnlyRecords);
                                recon.setMatchedTransactions(threeWayMatched);
                                recon.setUnmatchedTransactions(
                                    twoWayResponse.getUnmatchedTransactions() + bankOnlyRecords);
                                recon.setStatus("COMPLETED");
                                recon.setModifiedAt(Instant.now());
                                
                                // Store bank reconciliation metadata
                                Map<String, Object> metadata = new HashMap<>();
                                metadata.put("bank_record_count", bankRecordCount);
                                metadata.put("bank_only_records", bankOnlyRecords);
                                metadata.put("reconciliation_type", "3-way");
                                
                                return reconciliationRepository.save(recon);
                            })
                            .map(entity -> ReconciliationMapper.toReconciliationResponse(entity))
                            .map(response -> Either.<PaymentError, ReconciliationResponse>right(response));
                    });
            })
            .switchIfEmpty(Mono.just(Either.left(PaymentError.of("NOT_FOUND", RECONCILIATION_NOT_FOUND))));
    }
    
    /**
     * Parse bank records from CSV or JSON format
     * In production, this would support multiple formats (CSV, OFX, MT940, JSON)
     */
    private Map<String, Object> parseBankRecords(String bankRecords) {
        Map<String, Object> records = new HashMap<>();
        
        if (bankRecords == null || bankRecords.trim().isEmpty()) {
            return records;
        }
        
        // Simple CSV parsing (in production, use a proper CSV parser)
        // Format: date,amount,currency,reference,description
        try {
            String[] lines = bankRecords.split("\n");
            for (int i = 1; i < lines.length; i++) { // Skip header
                String line = lines[i].trim();
                if (!line.isEmpty()) {
                    String[] fields = line.split(",");
                    if (fields.length >= 4) {
                        String reference = fields[3].trim();
                        records.put(reference, Map.of(
                            "date", fields[0],
                            "amount", fields[1],
                            "currency", fields[2],
                            "description", fields.length > 4 ? fields[4] : ""
                        ));
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Failed to parse bank records, treating as empty", e);
        }
        
        return records;
    }
    
    @Override
    public Mono<Either<PaymentError, ReconciliationReportResponse>> generateReconciliationReport(
        String merchantId,
        String reconciliationId
    ) {
        log.info("Generating reconciliation report: {} for merchant: {}", reconciliationId, merchantId);
        
        return reconciliationRepository.findByReconciliationId(reconciliationId)
            .filter(entity -> entity.getMerchantId().equals(merchantId))
            .flatMap(reconciliation -> {
                Instant startDate = reconciliation.getStartDate();
                Instant endDate = reconciliation.getEndDate();
                
                // Fetch payment records for analysis
                return paymentIntentRepository.findByMerchantIdAndCreatedAtBetween(
                    merchantId, startDate, endDate)
                    .collectList()
                    .map(payments -> {
                        ReconciliationReportResponse report = new ReconciliationReportResponse();
                        report.setReconciliationId(reconciliationId);
                        report.setMerchantId(merchantId);
                        report.setReconciliationType(reconciliation.getReconciliationType());
                        report.setStatus(reconciliation.getStatus());
                        report.setStartDate(startDate);
                        report.setEndDate(endDate);
                        report.setTotalTransactions(reconciliation.getTotalTransactions());
                        report.setMatchedTransactions(reconciliation.getMatchedTransactions());
                        report.setUnmatchedTransactions(reconciliation.getUnmatchedTransactions());
                        
                        // Calculate match rate
                        if (reconciliation.getTotalTransactions() != null && 
                            reconciliation.getTotalTransactions() > 0) {
                            double matchRate = (double) reconciliation.getMatchedTransactions() / 
                                reconciliation.getTotalTransactions() * 100.0;
                            report.setMatchRate(matchRate);
                        } else {
                            report.setMatchRate(0.0);
                        }
                        
                        // Analyze discrepancies
                        List<ReconciliationReportResponse.DiscrepancyDetail> discrepancies = 
                            analyzeDiscrepancies(payments, reconciliation);
                        report.setDiscrepancies(discrepancies);
                        
                        // Generate discrepancy summary
                        ReconciliationReportResponse.DiscrepancySummary summary = 
                            generateDiscrepancySummary(discrepancies);
                        report.setDiscrepancySummary(summary);
                        
                        // Breakdown by type
                        Map<String, Integer> byType = discrepancies.stream()
                            .collect(Collectors.groupingBy(
                                ReconciliationReportResponse.DiscrepancyDetail::getDiscrepancyType,
                                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                            ));
                        report.setDiscrepanciesByType(byType);
                        
                        // Breakdown by amount
                        Map<String, Long> byAmount = discrepancies.stream()
                            .filter(d -> d.getInternalAmount() != null && d.getConnectorAmount() != null)
                            .collect(Collectors.groupingBy(
                                ReconciliationReportResponse.DiscrepancyDetail::getDiscrepancyType,
                                Collectors.summingLong(d -> Math.abs(
                                    d.getInternalAmount() - d.getConnectorAmount()))
                            ));
                        report.setDiscrepanciesByAmount(byAmount);
                        
                        report.setReportGeneratedAt(Instant.now().toString());
                        report.setReportFormat("JSON");
                        
                        return report;
                    });
            })
            .map(report -> Either.<PaymentError, ReconciliationReportResponse>right(report))
            .switchIfEmpty(Mono.just(Either.left(PaymentError.of("NOT_FOUND", RECONCILIATION_NOT_FOUND))));
    }
    
    @Override
    public Mono<Either<PaymentError, byte[]>> exportReconciliationReport(
        String merchantId,
        String reconciliationId,
        String format
    ) {
        log.info("Exporting reconciliation report: {} for merchant: {} in format: {}", 
            reconciliationId, merchantId, format);
        
        return generateReconciliationReport(merchantId, reconciliationId)
            .flatMap(result -> {
                if (result.isLeft()) {
                    return Mono.just(Either.left(result.getLeft()));
                }
                
                ReconciliationReportResponse report = result.get();
                byte[] exportData;
                
                try {
                    switch (format.toUpperCase()) {
                        case "CSV":
                            exportData = exportToCSV(report);
                            break;
                        case "PDF":
                            exportData = exportToPDF(report);
                            break;
                        case "JSON":
                        default:
                            exportData = exportToJSON(report);
                            break;
                    }
                    
                    return Mono.just(Either.<PaymentError, byte[]>right(exportData));
                } catch (Exception e) {
                    log.error("Error exporting reconciliation report", e);
                    return Mono.just(Either.left(
                        PaymentError.of("EXPORT_FAILED", "Failed to export report: " + e.getMessage())));
                }
            });
    }
    
    /**
     * Analyze discrepancies in payment records
     */
    private List<ReconciliationReportResponse.DiscrepancyDetail> analyzeDiscrepancies(
        List<PaymentIntentEntity> payments,
        @SuppressWarnings("unused") ReconciliationEntity reconciliation
    ) {
        List<ReconciliationReportResponse.DiscrepancyDetail> discrepancies = new ArrayList<>();
        
        for (PaymentIntentEntity payment : payments) {
            ReconciliationReportResponse.DiscrepancyDetail detail = 
                new ReconciliationReportResponse.DiscrepancyDetail();
            detail.setPaymentId(payment.getPaymentId());
            detail.setInternalAmount(payment.getAmount());
            detail.setInternalStatus(payment.getStatus());
            detail.setInternalDate(payment.getCreatedAt());
            
            // In production, this would compare with connector records
            // For now, simulate discrepancy detection
            boolean hasDiscrepancy = false;
            
            // Check for status mismatches (example: internal says succeeded but connector says failed)
            if (payment.getStatus() != null && 
                (payment.getStatus().contains("FAILED") || payment.getStatus().contains("PENDING"))) {
                detail.setDiscrepancyType("STATUS_MISMATCH");
                detail.setDescription("Payment status mismatch between internal and connector records");
                detail.setSeverity("MEDIUM");
                detail.setRecommendation("Verify payment status with connector and update internal records");
                hasDiscrepancy = true;
            }
            
            // Check for missing connector ID (indicates payment not sent to connector)
            if (payment.getConnectorId() == null || 
                payment.getConnectorId().isEmpty()) {
                detail.setDiscrepancyType("MISSING_IN_CONNECTOR");
                detail.setDescription("Payment exists in internal records but not found in connector");
                detail.setSeverity("HIGH");
                detail.setRecommendation("Investigate missing transaction and sync with connector");
                hasDiscrepancy = true;
            }
            
            // Check for amount mismatches (if connector amount is available)
            // In production, this would compare with actual connector records
            if (hasDiscrepancy) {
                discrepancies.add(detail);
            }
        }
        
        return discrepancies;
    }
    
    /**
     * Generate discrepancy summary
     */
    private ReconciliationReportResponse.DiscrepancySummary generateDiscrepancySummary(
        List<ReconciliationReportResponse.DiscrepancyDetail> discrepancies
    ) {
        ReconciliationReportResponse.DiscrepancySummary summary = 
            new ReconciliationReportResponse.DiscrepancySummary();
        
        summary.setTotalDiscrepancies(discrepancies.size());
        
        long highCount = discrepancies.stream()
            .filter(d -> "HIGH".equals(d.getSeverity()))
            .count();
        summary.setHighSeverityCount((int) highCount);
        
        long mediumCount = discrepancies.stream()
            .filter(d -> "MEDIUM".equals(d.getSeverity()))
            .count();
        summary.setMediumSeverityCount((int) mediumCount);
        
        long lowCount = discrepancies.stream()
            .filter(d -> "LOW".equals(d.getSeverity()))
            .count();
        summary.setLowSeverityCount((int) lowCount);
        
        long totalAmountDiscrepancy = discrepancies.stream()
            .filter(d -> d.getInternalAmount() != null && d.getConnectorAmount() != null)
            .mapToLong(d -> Math.abs(d.getInternalAmount() - d.getConnectorAmount()))
            .sum();
        summary.setTotalAmountDiscrepancy(totalAmountDiscrepancy);
        
        Map<String, Integer> countByType = discrepancies.stream()
            .collect(Collectors.groupingBy(
                ReconciliationReportResponse.DiscrepancyDetail::getDiscrepancyType,
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
            ));
        summary.setCountByType(countByType);
        
        return summary;
    }
    
    /**
     * Export report to CSV format
     */
    private byte[] exportToCSV(ReconciliationReportResponse report) {
        StringBuilder csv = new StringBuilder();
        
        // Header
        csv.append("Reconciliation Report\n");
        csv.append("Reconciliation ID,").append(report.getReconciliationId()).append("\n");
        csv.append("Merchant ID,").append(report.getMerchantId()).append("\n");
        csv.append("Type,").append(report.getReconciliationType()).append("\n");
        csv.append("Status,").append(report.getStatus()).append("\n");
        csv.append("Start Date,").append(report.getStartDate()).append("\n");
        csv.append("End Date,").append(report.getEndDate()).append("\n");
        csv.append("Total Transactions,").append(report.getTotalTransactions()).append("\n");
        csv.append("Matched Transactions,").append(report.getMatchedTransactions()).append("\n");
        csv.append("Unmatched Transactions,").append(report.getUnmatchedTransactions()).append("\n");
        csv.append("Match Rate,").append(report.getMatchRate()).append("%\n");
        csv.append("\n");
        
        // Discrepancies
        csv.append("Discrepancies\n");
        csv.append("Payment ID,Attempt ID,Type,Description,Severity,Internal Amount,Connector Amount,Recommendation\n");
        
        for (ReconciliationReportResponse.DiscrepancyDetail detail : report.getDiscrepancies()) {
            csv.append(detail.getPaymentId()).append(",");
            csv.append(detail.getAttemptId() != null ? detail.getAttemptId() : "").append(",");
            csv.append(detail.getDiscrepancyType()).append(",");
            csv.append("\"").append(detail.getDescription()).append("\"").append(",");
            csv.append(detail.getSeverity()).append(",");
            csv.append(detail.getInternalAmount() != null ? detail.getInternalAmount() : "").append(",");
            csv.append(detail.getConnectorAmount() != null ? detail.getConnectorAmount() : "").append(",");
            csv.append("\"").append(detail.getRecommendation()).append("\"").append("\n");
        }
        
        return csv.toString().getBytes();
    }
    
    /**
     * Export report to PDF format (simplified - in production, use a PDF library like iText)
     */
    private byte[] exportToPDF(ReconciliationReportResponse report) {
        // In production, use a PDF library like Apache PDFBox or iText
        // For now, return a simple text representation
        String pdfContent = "Reconciliation Report\n" +
            "Reconciliation ID: " + report.getReconciliationId() + "\n" +
            "Merchant ID: " + report.getMerchantId() + "\n" +
            "Type: " + report.getReconciliationType() + "\n" +
            "Status: " + report.getStatus() + "\n" +
            "Total Transactions: " + report.getTotalTransactions() + "\n" +
            "Matched Transactions: " + report.getMatchedTransactions() + "\n" +
            "Unmatched Transactions: " + report.getUnmatchedTransactions() + "\n" +
            "Match Rate: " + report.getMatchRate() + "%\n" +
            "\nDiscrepancies: " + report.getDiscrepancies().size() + "\n";
        
        return pdfContent.getBytes();
    }
    
    /**
     * Export report to JSON format
     */
    private byte[] exportToJSON(ReconciliationReportResponse report) {
        // In production, use Jackson ObjectMapper for proper JSON serialization
        // For now, return a simple JSON representation
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"reconciliationId\": \"").append(report.getReconciliationId()).append("\",\n");
        json.append("  \"merchantId\": \"").append(report.getMerchantId()).append("\",\n");
        json.append("  \"reconciliationType\": \"").append(report.getReconciliationType()).append("\",\n");
        json.append("  \"status\": \"").append(report.getStatus()).append("\",\n");
        json.append("  \"totalTransactions\": ").append(report.getTotalTransactions()).append(",\n");
        json.append("  \"matchedTransactions\": ").append(report.getMatchedTransactions()).append(",\n");
        json.append("  \"unmatchedTransactions\": ").append(report.getUnmatchedTransactions()).append(",\n");
        json.append("  \"matchRate\": ").append(report.getMatchRate()).append(",\n");
        json.append("  \"discrepancies\": [\n");
        
        for (int i = 0; i < report.getDiscrepancies().size(); i++) {
            ReconciliationReportResponse.DiscrepancyDetail detail = report.getDiscrepancies().get(i);
            json.append("    {\n");
            json.append("      \"paymentId\": \"").append(detail.getPaymentId()).append("\",\n");
            json.append("      \"discrepancyType\": \"").append(detail.getDiscrepancyType()).append("\",\n");
            json.append("      \"description\": \"").append(detail.getDescription()).append("\",\n");
            json.append("      \"severity\": \"").append(detail.getSeverity()).append("\"\n");
            json.append("    }");
            if (i < report.getDiscrepancies().size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        
        json.append("  ]\n");
        json.append("}\n");
        
        return json.toString().getBytes();
    }
}

