package com.example.reporting_query_optimization.metrics;

public record TimedResult<T>(
        String name,
        T value,
        long elapsedNanos
) {
    public long elapsedMillis() {
        return elapsedNanos / 1_000_000L;
    }
}
