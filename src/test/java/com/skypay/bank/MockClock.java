package com.skypay.bank;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Simulates dates for acceptance tests.
 */
public class MockClock implements Clock {
    private final Queue<String> dates = new LinkedList<>();

    public MockClock(String... dates) {
        Collections.addAll(this.dates, dates);
    }

    @Override
    public String todayAsString() {
        return dates.poll();
    }
}