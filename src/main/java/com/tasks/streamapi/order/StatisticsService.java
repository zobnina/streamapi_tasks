package com.tasks.streamapi.order;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


// TODO: Rename this class - because  *Impl name is not the the best naming practice
// e.g rename to StreamStatisticsService

/**
 * Implementation of the StatisticsService using Stream API as much as possible
 */
public class StatisticsService {

    /**
     * The method returns list of the customers whose monthly total order sum is the largest
     * only the given year should be considered
     * the list should include only the given number of customers
     *
     * @param orders  - the list of orders to process
     * @param year    - year for which to display the top
     * @param topSize - number of records to be in the top
     * @return
     */
    public List<StatsRecord> topMonthlyCustomersInYear(List<Order> orders, int year, int topSize) {

        return Optional.ofNullable(orders).orElse(Collections.emptyList()).stream()
                .filter(Objects::nonNull)
                .filter(order -> order.getOrderDate().getYear() == year)
                .collect(Collectors.groupingBy(Order::getCustomerId))
                .entrySet().stream()
                .map(custIdOrderEntry -> Map.entry(custIdOrderEntry.getKey(), custIdOrderEntry.getValue().stream()
                        .collect(Collectors.groupingBy(order -> order.getOrderDate().getMonth()))))
                .map(custIdMapEntry -> Map.entry(custIdMapEntry.getKey(), custIdMapEntry.getValue().entrySet().stream()
                        .map(monthListEntry -> Map.entry(monthListEntry.getKey(), monthListEntry.getValue().stream()
                                .map(Order::getOrderTotal)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))))
                .flatMap(custIdMapEntry -> custIdMapEntry.getValue().entrySet().stream()
                        .map(monthBigDecimalEntry -> new StatsRecord(custIdMapEntry.getKey(),
                                monthBigDecimalEntry.getKey(),
                                monthBigDecimalEntry.getValue())))
                .sorted((o1, o2) -> o2.getOrderTotalSum().compareTo(o1.getOrderTotalSum()))
                .limit(topSize)
                .collect(Collectors.toList());

    }
}