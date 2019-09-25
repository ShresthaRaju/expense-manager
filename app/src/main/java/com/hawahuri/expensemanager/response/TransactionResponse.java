package com.hawahuri.expensemanager.response;

import com.hawahuri.expensemanager.models.Transaction;

import java.util.List;

public class TransactionResponse {
    private Transaction transaction;
    private String message;
    private List<Transaction> myTransactions;

    public Transaction getTransaction() {
        return transaction;
    }

    public String getMessage() {
        return message;
    }

    public List<Transaction> getMyTransactions() {
        return myTransactions;
    }
}
