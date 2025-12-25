package com.example.reporting_query_optimization.seed;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.seed")
public record SeedProperties(
        boolean enabled,

        int months,
        int members,
        int orders,
        int payments,
        int products,

        int memberActivePct,
        int memberDormantPct,
        int memberWithdrawnPct,

        int paymentPaidPct,
        int paymentFailedPct,
        int paymentRefundedPct,

        int maxPaymentsPerOrder,
        long randomSeed
) {
    public static SeedProperties defaults() {
        return new SeedProperties(
                false,
                12, 50_000, 200_000, 150_000, 5_000,
                80, 15, 5,
                80, 15, 5,
                3,
                42L
        );
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public static final class Builder {
        private boolean enabled;
        private int months;
        private int members;
        private int orders;
        private int payments;
        private int products;

        private int memberActivePct;
        private int memberDormantPct;
        private int memberWithdrawnPct;

        private int paymentPaidPct;
        private int paymentFailedPct;
        private int paymentRefundedPct;

        private int maxPaymentsPerOrder;
        private long randomSeed;

        private Builder(SeedProperties base) {
            this.enabled = base.enabled;
            this.months = base.months;
            this.members = base.members;
            this.orders = base.orders;
            this.payments = base.payments;
            this.products = base.products;

            this.memberActivePct = base.memberActivePct;
            this.memberDormantPct = base.memberDormantPct;
            this.memberWithdrawnPct = base.memberWithdrawnPct;

            this.paymentPaidPct = base.paymentPaidPct;
            this.paymentFailedPct = base.paymentFailedPct;
            this.paymentRefundedPct = base.paymentRefundedPct;

            this.maxPaymentsPerOrder = base.maxPaymentsPerOrder;
            this.randomSeed = base.randomSeed;
        }

        public Builder enabled(boolean v) { this.enabled = v; return this; }
        public Builder months(int v) { this.months = v; return this; }
        public Builder members(int v) { this.members = v; return this; }
        public Builder orders(int v) { this.orders = v; return this; }
        public Builder payments(int v) { this.payments = v; return this; }
        public Builder products(int v) { this.products = v; return this; }

        public Builder maxPaymentsPerOrder(int v) { this.maxPaymentsPerOrder = v; return this; }
        public Builder randomSeed(long v) { this.randomSeed = v; return this; }

        public SeedProperties build() {
            return new SeedProperties(
                    enabled,
                    months, members, orders, payments, products,
                    memberActivePct, memberDormantPct, memberWithdrawnPct,
                    paymentPaidPct, paymentFailedPct, paymentRefundedPct,
                    maxPaymentsPerOrder,
                    randomSeed
            );
        }
    }
}