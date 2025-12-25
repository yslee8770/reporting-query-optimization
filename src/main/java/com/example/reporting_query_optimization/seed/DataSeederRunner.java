package com.example.reporting_query_optimization.seed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataSeederRunner implements ApplicationRunner {

    private final SeedProperties props;
    private final TestDataSeeder seeder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (!props.enabled()) {
            log.info("[seed] disabled. (app.seed.enabled=false)");
            return;
        }
        log.info("[seed] start: {}", summarize(props));
        seeder.seed(props);
        log.info("[seed] done.");
    }

    private String summarize(SeedProperties p) {
        return "months=" + p.months()
                + ", products=" + p.products()
                + ", members=" + p.members()
                + ", orders=" + p.orders()
                + ", payments=" + p.payments()
                + ", maxPaymentsPerOrder=" + p.maxPaymentsPerOrder()
                + ", seed=" + p.randomSeed();
    }
}
