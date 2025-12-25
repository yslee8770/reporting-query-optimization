package com.example.reporting_query_optimization.domain.order;

import com.example.reporting_query_optimization.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "orders",
        indexes = {
                @Index(
                        name = "idx_order_member_ordered",
                        columnList = "member_id,ordered_at"
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member member;

    @Column(name = "ordered_at", nullable = false)
    private LocalDateTime orderedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    public static Order of(Member member, LocalDateTime orderedAt, OrderStatus status) {
        Order order = new Order();
        order.member = member;
        order.orderedAt = orderedAt;
        order.status = status;
        return order;
    }
}
