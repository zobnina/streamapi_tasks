package com.tasks.streamapi.order;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsService {

    public List<StatsRecord> topMonthlyCustomersInYear(List<Order> orders, int year, int topSize) {

        return Optional.ofNullable(orders).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .filter(order -> order.getOrderDate().getYear() == year)
                .collect(Collectors.groupingBy(order -> new CustomerMonthKey(order.getCustomerId(),
                                order.getOrderDate().getMonth()),
                        Collectors.mapping(Order::getOrderTotal, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(topSize)
                .map(entry -> new StatsRecord(entry.getKey().getCustomerId(),
                        entry.getKey().getMonth(),
                        entry.getValue()))
                .collect(Collectors.toList());
    }
}