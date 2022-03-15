package com.tasks.streamapi.test.order;

import com.tasks.streamapi.order.StatsRecord;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Month;

public class StatsRecordTest {

    @Test
    public void testHashCode_Equals() {
        StatsRecord stats1 = new StatsRecord("cust1", Month.DECEMBER, BigDecimal.ONE);
        StatsRecord stats2 = new StatsRecord("cust1", Month.DECEMBER, BigDecimal.ONE);

        Assert.assertEquals(stats1, stats2);
        Assert.assertEquals(stats1.hashCode(), stats2.hashCode());
    }

    @Test
    public void testHashCode_NotEquals() {
        StatsRecord stats1 = new StatsRecord("cust1", Month.DECEMBER, BigDecimal.ONE);
        StatsRecord stats2 = new StatsRecord("cust2", Month.JANUARY, BigDecimal.ZERO);

        Assert.assertNotEquals(stats1, stats2);
        Assert.assertNotEquals(stats1.hashCode(), stats2.hashCode());
    }

    @Test
    public void testEquals_Reflective() {
        StatsRecord stats1 = new StatsRecord("cust1", Month.DECEMBER, BigDecimal.ONE);

        Assert.assertEquals(stats1, stats1);
    }

    @Test
    public void testEquals_Symmetrical() {
        StatsRecord stats1 = new StatsRecord("cust1", Month.DECEMBER, BigDecimal.ONE);
        StatsRecord stats2 = new StatsRecord("cust1", Month.DECEMBER, BigDecimal.ONE);

        Assert.assertEquals(stats1, stats2);
        Assert.assertEquals(stats2, stats1);
    }

    @Test
    public void testEquals_Trasitive() {
        StatsRecord stats1 = new StatsRecord("cust1", Month.DECEMBER, BigDecimal.ONE);
        StatsRecord stats2 = new StatsRecord("cust1", Month.DECEMBER, BigDecimal.ONE);
        StatsRecord stats3 = new StatsRecord("cust1", Month.DECEMBER, BigDecimal.ONE);

        Assert.assertEquals(stats1, stats3);
        Assert.assertEquals(stats2, stats3);
        Assert.assertEquals(stats3, stats1);
    }

}
