package com.hawahuri.expensemanager.api;

import com.hawahuri.expensemanager.models.User;
import com.hawahuri.expensemanager.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthAPI {

    @POST("sign-up")
    Call<UserResponse> registerUser(@Body User user);

    @FormUrlEncoded
    @POST("sign-in")
    Call<UserResponse> loginUser(@Field("email") String email, @Field("password") String password);

}
