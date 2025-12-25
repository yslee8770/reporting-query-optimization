package com.example.reporting_query_optimization.domain.payment;

import com.example.reporting_query_optimization.domain.order.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "payments",
        indexes = {
                @Index(
                        name = "idx_payment_paid_status",
                        columnList = "paid_at,status"
                ),
                @Index(
                        name = "idx_payment_order_paid",
                        columnList = "order_id,paid_at"
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Order order;

    @Column(nullable = false)
    private long amount;

    @Column(name = "paid_at", nullable = false)
    private LocalDateTime paidAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    public static Payment of(
            Order order,
            long amount,
            LocalDateTime paidAt,
            PaymentStatus status
    ) {
        Payment payment = new Payment();
        payment.order = order;
        payment.amount = amount;
        payment.paidAt = paidAt;
        payment.status = status;
        return payment;
    }
}
