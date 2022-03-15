package com.tasks.streamapi.test.order;

import com.tasks.streamapi.order.Order;
import com.tasks.streamapi.order.StatisticsService;
import com.tasks.streamapi.order.StatsRecord;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RunWith(Parameterized.class)
public class StatisticsServiceTest {

    private final String CUST1 = "cust-1";
    private final String CUST2 = "cust-2";
    private final String CUST3 = "cust-3";
    private final String CUST4 = "cust-4";
    private final String CUST5 = "cust-5";
    private final String CUST6 = "cust-6";

    private final StatisticsService statisticsService;

    @Parameterized.Parameters(name = "{index} - {0}")
    public static Collection<Object[]> data() {

        return Collections.singletonList(
                new Object[]{new StatisticsService()}
        );
    }

    public StatisticsServiceTest(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @Test
    public void testTopMonthlyCustomersInYear_OrderFromDifferentYear() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(CUST1, LocalDate.parse("2018-01-01"), BigDecimal.valueOf(30)));

        List<StatsRecord> topMonthlyCustomers = statisticsService.topMonthlyCustomersInYear(orders, 2019, 3);

        Assert.assertNotNull(topMonthlyCustomers);
        Assert.assertTrue(topMonthlyCustomers.isEmpty());
    }

    @Test
    public void testTopMonthlyCustomersInYear_OrderFromThisYear() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(CUST1, LocalDate.parse("2019-01-01"), BigDecimal.valueOf(30)));

        List<StatsRecord> topMonthlyCustomers = statisticsService.topMonthlyCustomersInYear(orders, 2019, 3);

        Assert.assertNotNull(topMonthlyCustomers);
        Assert.assertEquals(1, topMonthlyCustomers.size());
    }

    @Test
    public void testTopMonthlyCustomersInYear_2OrdersFromThisYearDifferentMonths() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(CUST1, LocalDate.parse("2019-01-01"), BigDecimal.valueOf(30)));
        orders.add(new Order(CUST1, LocalDate.parse("2019-02-01"), BigDecimal.valueOf(40)));

        List<StatsRecord> topMonthlyCustomers = statisticsService.topMonthlyCustomersInYear(orders, 2019, 3);


        Assert.assertNotNull(topMonthlyCustomers);

        List<StatsRecord> expected = new ArrayList<>();
        expected.add(new StatsRecord(CUST1, Month.FEBRUARY, BigDecimal.valueOf(40)));
        expected.add(new StatsRecord(CUST1, Month.JANUARY, BigDecimal.valueOf(30)));

        Assert.assertEquals(2, topMonthlyCustomers.size());
        Assert.assertEquals(expected.get(0), topMonthlyCustomers.get(0));
        Assert.assertEquals(expected.get(1), topMonthlyCustomers.get(1));
    }

    @Test
    public void testTopMonthlyCustomersInYear_2OrdersFromThisYearSameMonth() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(CUST1, LocalDate.parse("2019-02-01"), BigDecimal.valueOf(30)));
        orders.add(new Order(CUST1, LocalDate.parse("2019-02-21"), BigDecimal.valueOf(40)));

        List<StatsRecord> topMonthlyCustomers = statisticsService.topMonthlyCustomersInYear(orders, 2019, 3);

        Assert.assertNotNull(topMonthlyCustomers);
        Assert.assertEquals(1, topMonthlyCustomers.size());

        List<StatsRecord> expected = new ArrayList<>();
        expected.add(new StatsRecord(CUST1, Month.FEBRUARY, BigDecimal.valueOf(70)));

        Assert.assertEquals(expected.get(0), topMonthlyCustomers.get(0));
    }

    @Test
    public void testTopMonthlyCustomersInYear_ManyOrders() {
        List<StatsRecord> topMonthlyCustomers = statisticsService.topMonthlyCustomersInYear(findAll(), 2019, 3);

        Assert.assertNotNull(topMonthlyCustomers);
        Assert.assertEquals(3, topMonthlyCustomers.size());
        Assert.assertEquals(CUST6, topMonthlyCustomers.get(0).getCustomerId());
        Assert.assertEquals(CUST5, topMonthlyCustomers.get(1).getCustomerId());
        Assert.assertEquals(CUST4, topMonthlyCustomers.get(2).getCustomerId());

        Assert.assertEquals(Month.MAY, topMonthlyCustomers.get(0).getMonth());
        Assert.assertEquals(Month.FEBRUARY, topMonthlyCustomers.get(1).getMonth());
        Assert.assertEquals(Month.JUNE, topMonthlyCustomers.get(2).getMonth());

        Assert.assertEquals(BigDecimal.valueOf(36), topMonthlyCustomers.get(0).getOrderTotalSum());
        Assert.assertEquals(BigDecimal.valueOf(35), topMonthlyCustomers.get(1).getOrderTotalSum());
        Assert.assertEquals(BigDecimal.valueOf(34), topMonthlyCustomers.get(2).getOrderTotalSum());

        topMonthlyCustomers.forEach(System.out::println);
    }


    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();

        orders.add(new Order(CUST1, LocalDate.parse("2018-01-01"), BigDecimal.valueOf(30)));
        orders.add(new Order(CUST1, LocalDate.parse("2019-02-01"), BigDecimal.valueOf(10)));
        orders.add(new Order(CUST1, LocalDate.parse("2019-02-11"), BigDecimal.valueOf(21)));
        orders.add(new Order(CUST1, LocalDate.parse("2020-03-01"), BigDecimal.valueOf(10)));

        orders.add(new Order(CUST2, LocalDate.parse("2018-04-11"), BigDecimal.valueOf(40)));
        orders.add(new Order(CUST2, LocalDate.parse("2019-05-01"), BigDecimal.valueOf(10)));
        orders.add(new Order(CUST2, LocalDate.parse("2019-05-11"), BigDecimal.valueOf(22)));
        orders.add(new Order(CUST2, LocalDate.parse("2020-06-11"), BigDecimal.valueOf(10)));

        orders.add(new Order(CUST3, LocalDate.parse("2018-01-11"), BigDecimal.valueOf(30)));
        orders.add(new Order(CUST3, LocalDate.parse("2019-02-01"), BigDecimal.valueOf(10)));
        orders.add(new Order(CUST3, LocalDate.parse("2019-02-11"), BigDecimal.valueOf(23)));
        orders.add(new Order(CUST3, LocalDate.parse("2020-03-11"), BigDecimal.valueOf(10)));

        orders.add(new Order(CUST4, LocalDate.parse("2018-04-11"), BigDecimal.valueOf(40)));
        orders.add(new Order(CUST4, LocalDate.parse("2019-06-01"), BigDecimal.valueOf(10)));
        orders.add(new Order(CUST4, LocalDate.parse("2019-06-11"), BigDecimal.valueOf(24)));
        orders.add(new Order(CUST4, LocalDate.parse("2020-06-11"), BigDecimal.valueOf(10)));

        orders.add(new Order(CUST5, LocalDate.parse("2018-01-11"), BigDecimal.valueOf(30)));
        orders.add(new Order(CUST5, LocalDate.parse("2019-02-01"), BigDecimal.valueOf(10)));
        orders.add(new Order(CUST5, LocalDate.parse("2019-02-11"), BigDecimal.valueOf(25)));
        orders.add(new Order(CUST5, LocalDate.parse("2020-03-11"), BigDecimal.valueOf(10)));

        orders.add(new Order(CUST6, LocalDate.parse("2018-04-11"), BigDecimal.valueOf(40)));
        orders.add(new Order(CUST6, LocalDate.parse("2019-05-01"), BigDecimal.valueOf(10)));
        orders.add(new Order(CUST6, LocalDate.parse("2019-05-11"), BigDecimal.valueOf(26)));
        orders.add(new Order(CUST6, LocalDate.parse("2020-06-11"), BigDecimal.valueOf(10)));

        return orders;
    }
}