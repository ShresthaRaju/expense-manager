package com.hawahuri.expensemanager.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.models.User;

public class UserSession {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String IS_USER_LOGGED_IN = "IS_USER_LOGGED_IN";
    private static final String AUTH_USER = "LOGGED_IN_USER";

    public UserSession(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.user_session), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void startSession(User user) {

        String userJson = new Gson().toJson(user);

        editor.putBoolean(IS_USER_LOGGED_IN, true);
        editor.putString(AUTH_USER, userJson);
        editor.commit();
    }

    public boolean isActive() {
        return sharedPreferences.getBoolean(IS_USER_LOGGED_IN, false);
    }

    public User getUser() {

        String userJson = sharedPreferences.getString(AUTH_USER, "");
        User user = new Gson().fromJson(userJson, User.class);

        return user;
    }

    public void endSession() {

        editor.putBoolean(IS_USER_LOGGED_IN, false);
        editor.remove(AUTH_USER);
        editor.commit();
    }
}
