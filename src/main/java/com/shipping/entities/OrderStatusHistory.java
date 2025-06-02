package com.shipping.entities;

import java.time.LocalDateTime;

import com.shipping.entities.Order.Status;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_status_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Order.Status fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Order.Status toStatus;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    @Column(nullable = false)
    private String changedBy; // Stores user email/ID

    // Custom constructor for recording status change
    public OrderStatusHistory(Order order, 
                              Order.Status fromStatus, 
                              Order.Status toStatus, 
                              String changedBy) {
        this.order = order;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.changedBy = changedBy;
    }
}
