package com.example.reporting_query_optimization.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "payments")
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private long amount;

    @Column(name = "paid_at", nullable = false)
    private LocalDateTime paidAt;

    @Column(name = "paid_date", nullable = false)
    private LocalDate paidDate;

    @Column(name = "paid_month", nullable = false)
    private int paidMonth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    public static Payment of(Order order, long amount, LocalDateTime paidAt, PaymentStatus status) {
        Payment p = new Payment();
        p.order = order;
        p.amount = amount;
        p.paidAt = paidAt;
        p.paidDate = paidAt.toLocalDate();
        p.paidMonth = paidAt.getYear() * 100 + paidAt.getMonthValue();
        p.status = status;
        return p;
    }
}
