package com.hawahuri.expensemanager.response;

import com.hawahuri.expensemanager.models.Transaction;

public class TransactionResponse {
    private Transaction transaction;
    private String message;

    public Transaction getTransaction() {
        return transaction;
    }

    public String getMessage() {
        return message;
    }
}
