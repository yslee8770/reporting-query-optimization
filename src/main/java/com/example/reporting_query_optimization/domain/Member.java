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
@Table(name = "members")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    // yyyyMM (ex. 202512)
    @Column(name = "created_month", nullable = false)
    private int createdMonth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberStatus status;

    public static Member of(LocalDateTime createdAt, MemberStatus status) {
        Member m = new Member();
        m.createdAt = createdAt;
        m.createdDate = createdAt.toLocalDate();
        m.createdMonth = createdAt.getYear() * 100 + createdAt.getMonthValue();
        m.status = status;
        return m;
    }
}


