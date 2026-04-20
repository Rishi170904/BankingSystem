package com.bank;

public class BankAccount {

    int accountNumber;
    String accountHolder;
    double balance;

    public BankAccount(int accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }
}