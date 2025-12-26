package com.hyperswitch.common.dto;

import com.hyperswitch.common.types.ReconStatus;

/**
 * Response DTO for reconciliation status
 */
public class ReconStatusResponse {
    private ReconStatus reconStatus;

    public ReconStatusResponse() {
    }

    public ReconStatusResponse(ReconStatus reconStatus) {
        this.reconStatus = reconStatus;
    }

    public ReconStatus getReconStatus() {
        return reconStatus;
    }

    public void setReconStatus(ReconStatus reconStatus) {
        this.reconStatus = reconStatus;
    }
}

