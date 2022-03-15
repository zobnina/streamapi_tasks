package com.tasks.streamapi.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Month;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class CustomerMonthKey {
    private String customerId;
    private Month month;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerMonthKey that = (CustomerMonthKey) o;
        return Objects.equals(customerId, that.customerId) &&
                month == that.month;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, month);
    }
}
