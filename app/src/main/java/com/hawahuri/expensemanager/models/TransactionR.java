package com.hawahuri.expensemanager.models;

public class TransactionR {
    private String memo, type, creator, date;
    private double amount;
    private Category category;

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
