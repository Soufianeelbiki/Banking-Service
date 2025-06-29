package com.skypay.bank;

/**
 * Abstraction of clock for real or testable dates.
 */
public interface Clock {
    /**
     * @return current date formatted dd-MM-yyyy
     */
    String todayAsString();
}