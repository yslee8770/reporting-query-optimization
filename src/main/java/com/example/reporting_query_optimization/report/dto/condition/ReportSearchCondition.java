package com.example.reporting_query_optimization.report.dto.condition;

import java.time.LocalDateTime;
import java.util.List;

public record ReportSearchCondition(
        LocalDateTime from,
        LocalDateTime to,
        List<?> statuses,
        Long memberId
) {}
