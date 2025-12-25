package com.example.reporting_query_optimization.report;


import com.example.reporting_query_optimization.report.dto.DailyRevenueStat;
import com.example.reporting_query_optimization.report.dto.MonthlySignupStat;
import com.example.reporting_query_optimization.report.dto.StatusCountStat;

import java.util.List;

public interface ReportQueryRepository {
    List<MonthlySignupStat> findMonthlySignupStats(int fromMonthInclusive, int toMonthExclusive);
    List<DailyRevenueStat> findDailyRevenueStats(ReportSearchCondition condition);
    List<StatusCountStat> countPaymentsByStatus(ReportSearchCondition condition);
}
