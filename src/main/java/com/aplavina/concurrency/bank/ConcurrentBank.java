package com.aplavina.concurrency.bank;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentBank {
    private AtomicLong idCounter = new AtomicLong(1);
    private List<BankAccount> accounts = new CopyOnWriteArrayList<>();

    public BankAccount createAccount(long initialBalance) {
        long id = idCounter.incrementAndGet();
        BankAccount account = new BankAccount(id, initialBalance);
        accounts.add(account);
        return account;
    }

    public void transfer(BankAccount account1, BankAccount account2, long amount) {
        BankAccount first = account1.getId() < account2.getId() ? account1 : account2;
        BankAccount second = account1.getId() < account2.getId() ? account2 : account1;
        synchronized (first) {
            synchronized (second) {
                account1.withdraw(amount);
                account2.deposit(amount);
            }
        }
    }

    public long getTotalBalance() {
        return accounts.stream().mapToLong(BankAccount::getBalance).sum();
    }

    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, 200));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, 100));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());
    }
}
