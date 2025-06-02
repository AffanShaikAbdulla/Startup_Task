package com.shipping.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@PostLoad
    private void setDefaultStatusIfNull() {
        if (this.status == null) {
            this.status = Status.NEW;
        }
    }

    // Enum representing order statuses and allowed transitions
    public enum Status {
        NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED;

        private static final Map<Status, Set<Status>> RULES = Map.of(
                NEW, Set.of(PROCESSING, CANCELLED),
                PROCESSING, Set.of(SHIPPED, CANCELLED),
                SHIPPED, Set.of(DELIVERED)
        );

        public boolean canTransitionTo(Status next) {
            return RULES.getOrDefault(this, Set.of()).contains(next);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderStatusHistory> history = new ArrayList<>();

    // Business method to change status
    public void changeStatus(Status newStatus, String changedBy) {
        if (!this.status.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    String.format("Invalid status transition: %s → %s", this.status, newStatus)
            );
        }

        this.history.add(new OrderStatusHistory(this, this.status, newStatus, changedBy));
        this.status = newStatus;
    }
}
