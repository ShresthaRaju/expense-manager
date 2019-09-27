package com.hawahuri.expensemanager.api;

import com.hawahuri.expensemanager.models.Transaction;
import com.hawahuri.expensemanager.response.TransactionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TransactionAPI {

    //add a new Transactions
    @POST("transactions")
    Call<TransactionResponse> addNewTransaction(@Body Transaction transaction);

    //    get user transactions
    @GET("transactions/users/{creator}")
    Call<TransactionResponse> getMyTransactions(@Path("creator") String creator);
}
