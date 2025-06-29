package com.skypay.bank;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Account.
 */
class AccountTest {
    @Test
    void depositWithdrawBalance() {
        MockClock clock = new MockClock("26-06-2025", "28-06-2025");
        StatementPrinter printer = new StatementPrinter();
        Account account = new Account(clock, printer, 2);

        account.deposit(500);
        account.withdraw(200);

        List<Transaction> tx = account.getTransactions();
        assertEquals(2, tx.size());
        assertEquals(500, tx.get(0).getAmount());
        assertEquals(300, tx.get(1).getBalance());
    }

    @Test
    void overflowDepositThrows() {
        MockClock clock = new MockClock("27-06-2025");
        Account account = new Account(clock, new StatementPrinter(), 1);
        account.deposit(Integer.MAX_VALUE - 10);
        assertThrows(ArithmeticException.class, () -> account.deposit(20));
    }

    @Test
    void withdrawMoreThanBalanceThrows() {
        MockClock clock = new MockClock("30-06-2025");
        Account account = new Account(clock, new StatementPrinter(), 1);
        account.deposit(100);
        assertThrows(IllegalStateException.class, () -> account.withdraw(200));
    }

    @Test
    void invalidAmountsThrow() {
        Account account = new Account(new SystemClock(), new StatementPrinter(), 1);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-5));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-10));
    }
}