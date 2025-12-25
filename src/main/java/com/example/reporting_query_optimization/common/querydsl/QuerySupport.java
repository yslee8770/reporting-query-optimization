package com.example.reporting_query_optimization.common.querydsl;


import com.querydsl.core.types.dsl.*;
import java.time.LocalDateTime;
import java.util.Collection;

public abstract class QuerySupport {

    protected <T> BooleanExpression inIfNotEmpty(
            SimpleExpression<T> path,
            Collection<T> values
    ) {
        return (values == null || values.isEmpty()) ? null : path.in(values);
    }

    protected BooleanExpression betweenIfNotNull(
            DateTimePath<LocalDateTime> path,
            LocalDateTime from,
            LocalDateTime to
    ) {
        if (from == null && to == null) return null;
        if (from != null && to != null) return path.between(from, to);
        return from != null ? path.goe(from) : path.loe(to);
    }
}

