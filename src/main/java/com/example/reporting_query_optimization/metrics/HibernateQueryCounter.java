package com.example.reporting_query_optimization.metrics;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HibernateQueryCounter {

    private final EntityManagerFactory emf;

    public void clear() {
        statistics().clear();
    }

    public long queryExecutionCount() {
        return statistics().getQueryExecutionCount();
    }

    public long preparedStatementCount() {
        return statistics().getPrepareStatementCount();
    }

    private Statistics statistics() {
        SessionFactory sf = emf.unwrap(SessionFactory.class);
        return sf.getStatistics();
    }
}
