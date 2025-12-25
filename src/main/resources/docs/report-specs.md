# Reporting Query Optimization Specs

## 공통
- 기간 조건: [from, to) (LocalDate 기준)
- status 조건: optional
- 응답 DTO는 엔티티 반환 금지(Projection만 허용)

---

## 1) 월별 가입자 통계
- 입력: fromMonth(yyyyMM), toMonth(yyyyMM)
- 출력: month(yyyyMM), signupCount

---

## 2) 일별 매출 집계
- 입력: fromDate, toDate, paymentStatus(optional)
- 출력: date, totalAmount, paymentCount

---

## 3) 상태값별 분류 count
- 입력: fromDate, toDate
- 출력: status, count

---

## 4) group by + having
- 예: 일별 매출이 minAmount 이상인 날짜만
- 출력: date, totalAmount

---

## 5) 최신 1건 패턴
- 예: memberId의 최신 주문 1건
- 출력: orderId, orderedAt, status
