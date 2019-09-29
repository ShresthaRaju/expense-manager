package com.hawahuri.expensemanager.response;

import com.hawahuri.expensemanager.models.TransactionR;

import java.util.List;

public class TransactionResponse {
    private TransactionR transaction;
    private String message;
    private List<TransactionR> myTransactions;

    public TransactionR getTransaction() {
        return transaction;
    }

    public String getMessage() {
        return message;
    }

    public List<TransactionR> getMyTransactions() {
        return myTransactions;
    }
}
