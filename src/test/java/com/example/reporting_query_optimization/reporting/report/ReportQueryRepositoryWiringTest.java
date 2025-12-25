package com.example.reporting_query_optimization.reporting.report;


import com.example.reporting_query_optimization.metrics.HibernateQueryCounter;
import com.example.reporting_query_optimization.report.ReportQueryRepository;
import com.example.reporting_query_optimization.report.dto.MonthlySignupStat;
import com.example.reporting_query_optimization.seed.SeedProperties;
import com.example.reporting_query_optimization.seed.TestDataSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class ReportQueryRepositoryWiringTest {

    @Autowired
    TestDataSeeder seeder;
    @Autowired
    SeedProperties props;
    @Autowired
    ReportQueryRepository repo;
    @Autowired
    HibernateQueryCounter queryCounter;

    @BeforeEach
    void setUp() {
        SeedProperties custom = SeedProperties.defaults().toBuilder()
                .months(3)
                .products(50)
                .members(3_000)
                .orders(5_000)
                .payments(3_000)
                .maxPaymentsPerOrder(2)
                .randomSeed(7L)
                .build();

        seeder.seed(custom);
        queryCounter.clear();
    }

    @Test
    @Transactional
    void monthlySignupStats_shouldWork_baseline() {
        List<MonthlySignupStat> result = repo.findMonthlySignupStats(202001, 209912);

        assertThat(result).isNotNull();
        assertThat(result.size()).isGreaterThan(0);

        assertThat(queryCounter.queryExecutionCount()).isGreaterThan(0);
    }
}
