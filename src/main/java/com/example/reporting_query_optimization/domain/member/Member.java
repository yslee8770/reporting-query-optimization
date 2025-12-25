package com.example.reporting_query_optimization.domain.member;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "members",
        indexes = @Index(
                name = "idx_member_created_status",
                columnList = "created_at,status"
        )
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberStatus status;

    public static Member of(LocalDateTime createdAt, MemberStatus status) {
        Member member = new Member();
        member.createdAt = createdAt;
        member.status = status;
        return member;
    }
}

