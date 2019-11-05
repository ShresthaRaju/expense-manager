package com.hawahuri.expensemanager.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hawahuri.expensemanager.api.TransactionAPI;
import com.hawahuri.expensemanager.models.Error;
import com.hawahuri.expensemanager.models.Transaction;
import com.hawahuri.expensemanager.response.TransactionResponse;
import com.hawahuri.expensemanager.utils.APIError;
import com.hawahuri.expensemanager.utils.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class TransactionImpl {
    private Gson gson;
    private APIError apiError;
    private TransactionAPI transactionAPI;
    private TransactionListener transactionListener;

    public TransactionImpl() {
        transactionAPI = RetrofitClient.getInstance().create(TransactionAPI.class);
        gson = new GsonBuilder().create();
        apiError = new APIError();
    }

    public TransactionResponse addNewTransaction(Transaction transaction) {
        TransactionResponse transactionResponse = null;
        Call<TransactionResponse> addTransactionCall = transactionAPI.addNewTransaction(transaction);
        try {
            Response<TransactionResponse> addTransactionResponse = addTransactionCall.execute();
            if (!addTransactionResponse.isSuccessful()) {
//                apiError = gson.fromJson(addTransactionResponse.errorBody().string(), APIError.class);
//                transactionListener.onError(apiError.getError());
                return transactionResponse;
            } else if (addTransactionResponse.body().getTransaction() != null) {
                transactionResponse = addTransactionResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return transactionResponse;
    }

    public TransactionResponse getTransactions(String creator) {
        TransactionResponse transactionResponse = null;
        Call<TransactionResponse> myTransactionsCall = transactionAPI.getMyTransactions(creator);
        try {
            Response<TransactionResponse> myTransactionsResponse = myTransactionsCall.execute();
            if (!myTransactionsResponse.isSuccessful()) {
                return transactionResponse;
            }
            transactionResponse = myTransactionsResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionResponse;
    }

    public TransactionResponse getExpenseTransactions(String creator) {
        TransactionResponse expenses = null;
        Call<TransactionResponse> expenseTransactionsCall = transactionAPI.getExpenses(creator);
        try {
            Response<TransactionResponse> expenseTransactionsResponse = expenseTransactionsCall.execute();
            if (!expenseTransactionsResponse.isSuccessful()) {
                return expenses;
            }
            expenses = expenseTransactionsResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public TransactionResponse getIncomeTransactions(String creator) {
        TransactionResponse incomes = null;
        Call<TransactionResponse> incomeTransactionsCall = transactionAPI.getIncomes(creator);
        try {
            Response<TransactionResponse> incomeTransactionsResponse = incomeTransactionsCall.execute();
            if (!incomeTransactionsResponse.isSuccessful()) {
                return incomes;
            }
            incomes = incomeTransactionsResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return incomes;
    }

    public TransactionResponse getSingleTransaction(String transactionId) {
        TransactionResponse transactionResponse = null;
        Call<TransactionResponse> singleTransactionCall = transactionAPI.fetchSingleTransaction(transactionId);
        try {
            Response<TransactionResponse> singleTransactionsResponse = singleTransactionCall.execute();
            if (!singleTransactionsResponse.isSuccessful()) {
                return transactionResponse;
            }
            transactionResponse = singleTransactionsResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionResponse;
    }

    public TransactionResponse updateTransaction(String transactionId, Transaction transaction) {
        TransactionResponse transactionResponse = null;
        Call<TransactionResponse> updateTransactionCall = transactionAPI.updateTransaction(transactionId, transaction);
        try {
            Response<TransactionResponse> updateTransactionResponse = updateTransactionCall.execute();
            if (!updateTransactionResponse.isSuccessful()) {
//                apiError = gson.fromJson(updateTransactionResponse.errorBody().string(), APIError.class);
//                transactionListener.onError(apiError.getError());
                return transactionResponse;
            }
            if (updateTransactionResponse.body().getTransaction() != null) {
                transactionResponse = updateTransactionResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionResponse;
    }

    public boolean deleteTransaction(String transactionId) {
        boolean transactionDeleted = false;
        Call<TransactionResponse> deleteTransactionCall = transactionAPI.deleteTransaction(transactionId);
        try {
            Response<TransactionResponse> deleteTransactionResponse = deleteTransactionCall.execute();
            if (!deleteTransactionResponse.isSuccessful()) {
                return transactionDeleted;
            }
            if (deleteTransactionResponse.body() != null) {
                transactionDeleted = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactionDeleted;
    }


    public interface TransactionListener {
        void onError(Error error);
    }

    public void setTransactionListener(TransactionListener transactionListener) {
        this.transactionListener = transactionListener;
    }
}
