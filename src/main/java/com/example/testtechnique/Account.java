package com.example.testtechnique;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<>();
    }

    public void deposit(int amount, LocalDate date) {
        transactions.add(new Transaction("deposit", amount, date));
    }

    public void withdraw(int amount, LocalDate date) {
        transactions.add(new Transaction("withdrawal", -amount, date));
    }

    public String printStatement() {
        StringBuilder statement = new StringBuilder("Date       | Amount | Balance\n");
        int runningBalance = 0;
        for (Transaction t : transactions) {
            runningBalance += t.getAmount();
            statement.append(String.format("%s | %d | %d\n", t.getDate(), t.getAmount(), runningBalance));
        }
        return statement.toString();
    }

    private static class Transaction {
        private final String type;
        private final int amount;
        private final LocalDate date;

        public Transaction(String type, int amount, LocalDate date) {
            this.type = type;
            this.amount = amount;
            this.date = date;
        }

        public int getAmount() {
            return amount;
        }

        public LocalDate getDate() {
            return date;
        }
    }
}

