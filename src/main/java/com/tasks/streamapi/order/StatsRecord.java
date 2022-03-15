package com.tasks.streamapi.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StatsRecord {
    private String customerId;
    private Month month;
    private BigDecimal orderTotalSum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatsRecord that = (StatsRecord) o;
        return Objects.equals(customerId, that.customerId) &&
                month == that.month &&
                orderTotalSum.equals(that.orderTotalSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, month, orderTotalSum);
    }
}