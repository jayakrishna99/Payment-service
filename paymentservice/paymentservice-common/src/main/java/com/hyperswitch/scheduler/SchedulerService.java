package com.hyperswitch.scheduler;

import reactor.core.publisher.Mono;

/**
 * Scheduler service for background job processing
 * Implements producer/consumer pattern similar to Hyperswitch
 */
public interface SchedulerService {
    
    /**
     * Start the scheduler producer (job scheduler)
     */
    Mono<Void> startProducer();
    
    /**
     * Start the scheduler consumer (job executor)
     */
    Mono<Void> startConsumer();
    
    /**
     * Schedule a task for execution
     */
    Mono<String> scheduleTask(ScheduledTask task);
    
    /**
     * Stop the scheduler
     */
    Mono<Void> stop();
}

