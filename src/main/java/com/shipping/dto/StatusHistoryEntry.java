package com.shipping.dto;

import java.time.LocalDateTime;

import com.shipping.entities.Order;

public class StatusHistoryEntry {

    private Order.Status fromStatus;
    private Order.Status toStatus;
    private LocalDateTime timestamp;

    // Constructor
    public StatusHistoryEntry(Order.Status fromStatus, Order.Status toStatus, LocalDateTime timestamp) {
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.timestamp = timestamp;
    }

    // Getters and setters (or make fields public if it's DTO-only)
    public Order.Status getFromStatus() {
        return fromStatus;
    }

    public Order.Status getToStatus() {
        return toStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
