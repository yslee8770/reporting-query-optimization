package com.example.reporting_query_optimization.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class OrderSummaryDto {
    private final Long orderId;
    private final LocalDateTime orderedAt;
    private final long amount;

}

