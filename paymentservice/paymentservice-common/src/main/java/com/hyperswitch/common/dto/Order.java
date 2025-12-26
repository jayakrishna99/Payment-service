package com.hyperswitch.common.dto;

/**
 * Order specification for sorting payment lists
 */
public class Order {
    private SortOn on = SortOn.CREATED_AT;
    private SortBy by = SortBy.DESC;
    
    public Order() {
    }
    
    public SortOn getOn() {
        return on;
    }
    
    public void setOn(SortOn on) {
        this.on = on;
    }
    
    public SortBy getBy() {
        return by;
    }
    
    public void setBy(SortBy by) {
        this.by = by;
    }
    
    public enum SortOn {
        CREATED_AT,
        AMOUNT,
        STATUS
    }
    
    public enum SortBy {
        ASC,
        DESC
    }
}

