package com.hyperswitch.scheduler;

import java.time.Instant;
import java.util.Map;

/**
 * Represents a scheduled task
 */
public final class ScheduledTask {
    private final String taskId;
    private final String taskType;
    private final Instant scheduledAt;
    private final Map<String, Object> taskData;
    private final String merchantId;
    private final Integer retryCount;
    private final Integer maxRetries;

    private ScheduledTask(Builder builder) {
        this.taskId = builder.taskId;
        this.taskType = builder.taskType;
        this.scheduledAt = builder.scheduledAt;
        this.taskData = builder.taskData;
        this.merchantId = builder.merchantId;
        this.retryCount = builder.retryCount;
        this.maxRetries = builder.maxRetries;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTaskType() {
        return taskType;
    }

    public Instant getScheduledAt() {
        return scheduledAt;
    }

    public Map<String, Object> getTaskData() {
        return taskData;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public static class Builder {
        private String taskId;
        private String taskType;
        private Instant scheduledAt;
        private Map<String, Object> taskData;
        private String merchantId;
        private Integer retryCount;
        private Integer maxRetries;

        public Builder taskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public Builder taskType(String taskType) {
            this.taskType = taskType;
            return this;
        }

        public Builder scheduledAt(Instant scheduledAt) {
            this.scheduledAt = scheduledAt;
            return this;
        }

        public Builder taskData(Map<String, Object> taskData) {
            this.taskData = taskData;
            return this;
        }

        public Builder merchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder retryCount(Integer retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        public Builder maxRetries(Integer maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        public ScheduledTask build() {
            return new ScheduledTask(this);
        }
    }
}

