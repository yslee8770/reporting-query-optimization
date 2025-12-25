package com.example.reporting_query_optimization.metrics;

public final class ExecutionTimer {

    private ExecutionTimer() {}

    public static <T> TimedResult<T> measure(String name, SupplierWithException<T> supplier) {
        long start = System.nanoTime();
        try {
            T result = supplier.get();
            long end = System.nanoTime();
            return new TimedResult<>(name, result, (end - start));
        } catch (Exception e) {
            long end = System.nanoTime();
            throw new TimedExecutionException(name, (end - start), e);
        }
    }

    @FunctionalInterface
    public interface SupplierWithException<T> {
        T get() throws Exception;
    }
}
