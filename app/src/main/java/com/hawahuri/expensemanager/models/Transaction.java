package com.hawahuri.expensemanager.models;

public class Transaction {
    private String memo, type, creator, date;
    private double amount;
    private Category category;

    public Transaction(String memo, String type, String creator, String date, double amount, Category category) {
        this.memo = memo;
        this.type = type;
        this.creator = creator;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public Transaction(String memo, String type, String creator, String date, Category category) {
        this.memo = memo;
        this.type = type;
        this.creator = creator;
        this.date = date;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public String getMemo() {
        return memo;
    }

    public String getType() {
        return type;
    }

    public String getCreator() {
        return creator;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
