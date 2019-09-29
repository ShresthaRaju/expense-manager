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
                apiError = gson.fromJson(addTransactionResponse.errorBody().string(), APIError.class);
                transactionListener.onError(apiError.getError());
//                return transactionResponse;
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

    public interface TransactionListener {
        void onError(Error error);
    }

    public void setTransactionListener(TransactionListener transactionListener) {
        this.transactionListener = transactionListener;
    }
}
