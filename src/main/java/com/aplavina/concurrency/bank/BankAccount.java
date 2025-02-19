package com.aplavina.concurrency.bank;

public class BankAccount {
    private final long id;
    private long balance;

    public BankAccount(long id, long balance) {
        this.id = id;
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance can not be less than 0");
        }
        this.balance = balance;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public void withdraw(long amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    public long getId() {
        return id;
    }

    public long getBalance() {
        return balance;
    }
}
