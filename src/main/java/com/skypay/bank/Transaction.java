package com.skypay.bank;

/**
 * Immutable model of a bank transaction.
 */
public final class Transaction {
    private final String date;
    private final int amount;
    private final int balance;

    public Transaction(String date, int amount, int balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public String getDate() { return date; }
    public int getAmount() { return amount; }
    public int getBalance() { return balance; }
}