package com.example.reporting_query_optimization.metrics;

public class TimedExecutionException extends RuntimeException {
    private final String name;
    private final long elapsedNanos;

    public TimedExecutionException(String name, long elapsedNanos, Throwable cause) {
        super("Timed execution failed: " + name + " (" + (elapsedNanos / 1_000_000L) + " ms)", cause);
        this.name = name;
        this.elapsedNanos = elapsedNanos;
    }

    public String getName() { return name; }
    public long getElapsedNanos() { return elapsedNanos; }
}