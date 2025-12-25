package com.example.reporting_query_optimization.seed;

import com.example.reporting_query_optimization.domain.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

@RequiredArgsConstructor
@Component
public class TestDataSeeder {

    private final EntityManager em;

    @Transactional
    public void seed(SeedProperties p) {
        SplittableRandom r = new SplittableRandom(p.randomSeed());

        List<Product> products = createProducts(p, r);
        flushClear();

        List<Member> members = createMembers(p, r);
        flushClear();

        List<Order> orders = createOrders(p, r, members);
        flushClear();

        createOrderItems(p, r, orders, products);
        flushClear();

        createPayments(p, r, orders);
        flushClear();
    }

    private List<Product> createProducts(SeedProperties p, SplittableRandom r) {
        List<Product> list = new ArrayList<>(p.products());
        for (int i = 0; i < p.products(); i++) {
            ProductStatus status = pick(r, 90, ProductStatus.SELLING, 8, ProductStatus.STOPPED, 2, ProductStatus.DELETED);
            Product product = Product.of("product-" + i, status);
            em.persist(product);
            list.add(product);
        }
        return list;
    }

    private List<Member> createMembers(SeedProperties p, SplittableRandom r) {
        List<Member> list = new ArrayList<>(p.members());
        for (int i = 0; i < p.members(); i++) {
            LocalDateTime createdAt = randomDateTimeWithinMonths(r, p.months());
            MemberStatus status = pickMemberStatus(p, r);
            Member member = Member.of(createdAt, status);
            em.persist(member);
            list.add(member);
        }
        return list;
    }

    private List<Order> createOrders(SeedProperties p, SplittableRandom r, List<Member> members) {
        List<Order> list = new ArrayList<>(p.orders());
        for (int i = 0; i < p.orders(); i++) {
            Member member = members.get(r.nextInt(members.size()));
            LocalDateTime orderedAt = randomDateTimeWithinMonths(r, p.months());
            OrderStatus status = pick(r, 75, OrderStatus.COMPLETED, 20, OrderStatus.CREATED, 5, OrderStatus.CANCELED);
            Order order = Order.of(member, orderedAt, status);
            em.persist(order);
            list.add(order);
        }
        return list;
    }

    private void createOrderItems(SeedProperties p, SplittableRandom r, List<Order> orders, List<Product> products) {
        int totalItems = Math.min(p.orders() * 2, 600_000); // 너무 과도한 폭증 방지
        for (int i = 0; i < totalItems; i++) {
            Order order = orders.get(r.nextInt(orders.size()));
            Product product = products.get(r.nextInt(products.size()));
            int qty = 1 + r.nextInt(5);
            long price = 1_000L + (r.nextInt(500) * 100L);
            OrderItem oi = OrderItem.of(order, product, qty, price);
            em.persist(oi);
        }
    }

    private void createPayments(SeedProperties p, SplittableRandom r, List<Order> orders) {
        int created = 0;
        while (created < p.payments()) {
            Order order = orders.get(r.nextInt(orders.size()));
            int n = 1 + r.nextInt(Math.max(1, p.maxPaymentsPerOrder()));
            for (int i = 0; i < n && created < p.payments(); i++) {
                LocalDateTime paidAt = randomDateTimeWithinMonths(r, p.months());
                PaymentStatus status = pickPaymentStatus(p, r);
                long amount = 5_000L + (r.nextInt(2_000) * 100L);
                Payment payment = Payment.of(order, amount, paidAt, status);
                em.persist(payment);
                created++;
            }
        }
    }

    private MemberStatus pickMemberStatus(SeedProperties p, SplittableRandom r) {
        return pick(r,
                p.memberActivePct(), MemberStatus.ACTIVE,
                p.memberDormantPct(), MemberStatus.DORMANT,
                p.memberWithdrawnPct(), MemberStatus.WITHDRAWN);
    }

    private PaymentStatus pickPaymentStatus(SeedProperties p, SplittableRandom r) {
        return pick(r,
                p.paymentPaidPct(), PaymentStatus.PAID,
                p.paymentFailedPct(), PaymentStatus.FAILED,
                p.paymentRefundedPct(), PaymentStatus.REFUNDED);
    }

    private LocalDateTime randomDateTimeWithinMonths(SplittableRandom r, int monthsBack) {
        // 최근 monthsBack 개월 범위 내 랜덤(일 단위)
        int totalDays = Math.max(1, monthsBack * 30);
        int dayOffset = r.nextInt(totalDays);
        int hour = r.nextInt(24);
        int min = r.nextInt(60);
        return LocalDateTime.now().minusDays(dayOffset).withHour(hour).withMinute(min).withSecond(0).withNano(0);
    }

    private void flushClear() {
        em.flush();
        em.clear();
    }

    private <T> T pick(SplittableRandom r, int w1, T v1, int w2, T v2, int w3, T v3) {
        int total = w1 + w2 + w3;
        int x = r.nextInt(total);
        if (x < w1) return v1;
        if (x < w1 + w2) return v2;
        return v3;
    }
}
