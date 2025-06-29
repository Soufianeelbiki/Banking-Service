package com.skypay.bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Real Clock implementation using LocalDate.
 */
public class SystemClock implements Clock {
    private static final DateTimeFormatter FMT =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public String todayAsString() {
        return LocalDate.now().format(FMT);
    }
}