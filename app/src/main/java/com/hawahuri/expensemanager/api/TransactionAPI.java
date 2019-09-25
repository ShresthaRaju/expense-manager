package com.hawahuri.expensemanager.api;

import com.hawahuri.expensemanager.models.Transaction;
import com.hawahuri.expensemanager.response.TransactionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TransactionAPI {

    //add a new Transactions
    @POST("transactions")
    Call<TransactionResponse> addNewTransaction(@Body Transaction transaction);

}
