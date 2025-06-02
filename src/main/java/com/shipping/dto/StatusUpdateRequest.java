package com.shipping.dto;

import com.shipping.entities.Order;
import jakarta.validation.constraints.NotNull;

public class StatusUpdateRequest {

    @NotNull
    private Order.Status newStatus;

    public Order.Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Order.Status newStatus) {
        this.newStatus = newStatus;
    }
}
