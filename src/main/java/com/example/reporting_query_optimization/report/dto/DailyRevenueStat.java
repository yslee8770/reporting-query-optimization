package com.example.reporting_query_optimization.report.dto;

import java.time.LocalDate;

public record DailyRevenueStat(LocalDate date, long totalAmount, long paymentCount) {}
