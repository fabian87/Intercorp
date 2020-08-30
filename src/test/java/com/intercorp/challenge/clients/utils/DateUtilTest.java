package com.intercorp.challenge.clients.utils;

import org.junit.Test;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    @Test
    public void ceroYearsPassSinceLastMonth() {
        LocalDate lastMonthDate = LocalDate.now().minusMonths(1);
        assertEquals(0, DateUtil.yearsSince(lastMonthDate));
    }

    @Test
    public void shouldCalculateYearsSince() {
        final int years = ThreadLocalRandom.current().nextInt(200);
        LocalDate pastDate = LocalDate.now().minusYears(years);
        assertEquals(years, DateUtil.yearsSince(pastDate));
        assertEquals(years, DateUtil.yearsSince(DateUtil.from(pastDate)));
    }
}
