package com.example.reporting_query_optimization.report.dto;

import java.time.LocalDate;

public record DailySalesStat(LocalDate day, long totalAmount, long paymentCount) {
}
