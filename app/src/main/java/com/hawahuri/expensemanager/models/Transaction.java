package com.hawahuri.expensemanager.models;

public class Transaction {
    private String memo, type, creator, date, category;
    private double amount;

    public Transaction(String memo, String type, String creator, String date, String category, double amount) {
        this.memo = memo;
        this.type = type;
        this.creator = creator;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public Transaction(String memo, String type, String creator, String date, String category) {
        this.memo = memo;
        this.type = type;
        this.creator = creator;
        this.date = date;
        this.category = category;
    }

    public Transaction(String memo, String type, String date, double amount) {
        this.memo = memo;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }
}
