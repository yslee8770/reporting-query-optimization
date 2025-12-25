package com.example.reporting_query_optimization.report;

import com.example.reporting_query_optimization.domain.QMember;
import com.example.reporting_query_optimization.report.dto.DailyRevenueStat;
import com.example.reporting_query_optimization.report.dto.MonthlySignupStat;
import com.example.reporting_query_optimization.report.dto.StatusCountStat;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportQueryRepositoryImpl implements ReportQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MonthlySignupStat> findMonthlySignupStats(int fromMonthInclusive, int toMonthExclusive) {
        // Step 4에서 본격 튜닝/비교 실험 예정.
        // Step 3에서는 "QueryDSL 뼈대 + 컴파일/실행 가능"만 보장.
        QMember m = QMember.member;

        return queryFactory
                .select(Projections.constructor(MonthlySignupStat.class,
                        m.createdMonth,
                        m.id.count()))
                .from(m)
                .where(
                        m.createdMonth.goe(fromMonthInclusive),
                        m.createdMonth.lt(toMonthExclusive)
                )
                .groupBy(m.createdMonth)
                .fetch();
    }

    @Override
    public List<DailyRevenueStat> findDailyRevenueStats(ReportSearchCondition condition) {
        throw new UnsupportedOperationException("추후 구현");
    }

    @Override
    public List<StatusCountStat> countPaymentsByStatus(ReportSearchCondition condition) {
        throw new UnsupportedOperationException("추후 구현");
    }
}
