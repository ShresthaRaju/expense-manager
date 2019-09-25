package com.hawahuri.expensemanager.models;

public class Transaction {
    private String memo, type, category, creator, date;
    private double amount;

    public Transaction(String memo, String type, String category, String creator, String date, double amount) {
        this.memo = memo;
        this.type = type;
        this.category = category;
        this.creator = creator;
        this.date = date;
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
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
