package com.skypay.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implements AccountInterface for deposit, withdraw, printStatement.
 * Uses ArrayList directly, thread-safe and high performance.
 */
public class Account implements AccountService {
    private final Clock clock;
    private final List<Transaction> transactions;
    private final ReentrantLock lock = new ReentrantLock();
    private int balance = 0;
    private final StatementPrinter printer;

    /**
     * Production constructor.
     */
    public Account() {
        this.clock = new SystemClock();
        this.transactions = new ArrayList<>(256);
        this.printer = new StatementPrinter();
    }

    /**
     * Test constructor (dependency injection).
     */
    Account(Clock clock, StatementPrinter printer, int initialCapacity) {
        this.clock = clock;
        this.printer = printer;
        this.transactions = new ArrayList<>(initialCapacity);
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be > 0");
        }
        lock.lock();
        try {
            if (balance > Integer.MAX_VALUE - amount) {
                throw new ArithmeticException("Balance would overflow");
            }
            balance += amount;
            transactions.add(new Transaction(
                clock.todayAsString(), amount, balance));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be > 0");
        }
        lock.lock();
        try {
            if (amount > balance) {
                throw new IllegalStateException("Insufficient funds");
            }
            balance -= amount;
            transactions.add(new Transaction(
                clock.todayAsString(), -amount, balance));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void printStatement() {
        printer.print(Collections.unmodifiableList(transactions));
    }

    /**
     * For tests: access raw transaction list.
     */
    List<Transaction> getTransactions() {
        return transactions;
    }
}