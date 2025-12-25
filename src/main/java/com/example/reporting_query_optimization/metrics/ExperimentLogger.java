package com.example.reporting_query_optimization.metrics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExperimentLogger {

    public void log(String caseName, long elapsedMillis, long queryCount, long rows) {
        log.info("[exp] case={} timeMs={} queryCount={} rows={}", caseName, elapsedMillis, queryCount, rows);
    }
}
