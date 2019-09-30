package com.hawahuri.expensemanager.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hawahuri.expensemanager.api.AuthAPI;
import com.hawahuri.expensemanager.models.Error;
import com.hawahuri.expensemanager.models.User;
import com.hawahuri.expensemanager.response.UserResponse;
import com.hawahuri.expensemanager.utils.APIError;
import com.hawahuri.expensemanager.utils.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class AuthImpl {

    private AuthAPI authAPI;
    private Gson gson;
    private APIError apiError;
    private AuthListener authListener;

    public AuthImpl() {

        authAPI = RetrofitClient.getInstance().create(AuthAPI.class);
        gson = new GsonBuilder().create();
        apiError = new APIError();
    }

    public boolean registerUser(User user) {
        boolean isSignUpSuccessful = false;

        Call<UserResponse> signUpCall = authAPI.registerUser(user);

        try {
            Response<UserResponse> signUpResponse = signUpCall.execute();
            if (!signUpResponse.isSuccessful()) {
                apiError = gson.fromJson(signUpResponse.errorBody().string(), APIError.class);
                authListener.onError(apiError.getError());
//                return isSignUpSuccessful;
            } else if (signUpResponse.body().getUser() != null) {
                isSignUpSuccessful = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSignUpSuccessful;
    }

    public User loginUser(String email, String password) {
        User user = null;
        Call<UserResponse> loginCall = authAPI.loginUser(email, password);

        try {
            Response<UserResponse> loginResponse = loginCall.execute();
            if (!loginResponse.isSuccessful()) {
                apiError = gson.fromJson(loginResponse.errorBody().string(), APIError.class);
                authListener.onError(apiError.getError());
//                return user;
            } else if (loginResponse.body().getUser() != null) {
                user = loginResponse.body().getUser();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    public UserResponse getIncomeExpense(String id) {
        UserResponse userResponse = null;
        Call<UserResponse> userResponseCall = authAPI.getIncomeExpense(id);
        try {
            Response<UserResponse> incomeExpenseCall = userResponseCall.execute();
            if (!incomeExpenseCall.isSuccessful()) {
                return userResponse;
            }
            userResponse = incomeExpenseCall.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return userResponse;
    }

    public interface AuthListener {
        void onError(Error error);
    }

    public void setAuthListener(AuthListener authListener) {
        this.authListener = authListener;
    }

}
