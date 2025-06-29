package com.skypay.bank;

/**
 * Public Account interface (cannot be modified).
 */
public interface AccountService {
    void deposit(int amount);
    void withdraw(int amount);
    void printStatement();
}