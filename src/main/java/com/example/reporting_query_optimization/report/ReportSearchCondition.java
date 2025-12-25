package com.example.reporting_query_optimization.report;


import com.example.reporting_query_optimization.domain.MemberStatus;
import com.example.reporting_query_optimization.domain.PaymentStatus;

import java.time.LocalDate;

public record ReportSearchCondition(
        LocalDate fromDate,
        LocalDate toDate,
        PaymentStatus paymentStatus,
        MemberStatus memberStatus,
        Long memberId,
        Long minAmount
) {
}
