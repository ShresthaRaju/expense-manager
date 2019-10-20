package com.hawahuri.expensemanager.models;

import java.io.Serializable;

public class TransactionR implements Serializable {
    private String _id,memo, type, creator, date;
    private double amount;
    private Category category;

    public String get_id() {
        return _id;
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
