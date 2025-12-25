package com.example.reporting_query_optimization.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false)
    private long unitPrice;

    @Column(name = "ordered_date", nullable = false)
    private LocalDate orderedDate;

    public static OrderItem of(Order order, Product product, int quantity, long unitPrice) {
        OrderItem oi = new OrderItem();
        oi.order = order;
        oi.product = product;
        oi.quantity = quantity;
        oi.unitPrice = unitPrice;
        oi.orderedDate = order.getOrderedDate();
        return oi;
    }

    public long lineAmount() {
        return (long) quantity * unitPrice;
    }
}