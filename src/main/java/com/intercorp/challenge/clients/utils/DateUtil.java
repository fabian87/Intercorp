package com.intercorp.challenge.clients.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {

    public static int yearsSince(final LocalDate date) {
        return Period.between(date, LocalDate.now()).getYears();
    }

    public static int yearsSince(Date date) {
        return yearsSince(from(date));
    }

    public static Date from(LocalDate localDate) {
        final Instant instant = localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }

    public static LocalDate from(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
