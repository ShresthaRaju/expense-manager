package com.hawahuri.expensemanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.impl.AuthImpl;
import com.hawahuri.expensemanager.models.Error;
import com.hawahuri.expensemanager.models.User;
import com.hawahuri.expensemanager.utils.EditTextValidation;
import com.hawahuri.expensemanager.utils.Helper;
import com.hawahuri.expensemanager.utils.UserSession;

public class SignInActivity extends AppCompatActivity {

    private TextInputLayout etEmail, etPassword;
    private AuthImpl authImpl;
    private FloatingActionButton fabSignIn;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userSession = new UserSession(this);
        if (userSession.isActive()) {
            navigateDashboard();
        }

        initComponents();

    }

    private void initComponents() {

        authImpl = new AuthImpl();

        etEmail = findViewById(R.id.et_sign_in_email);
        etPassword = findViewById(R.id.et_sign_in_password);
        fabSignIn = findViewById(R.id.fab_sign_in);

        fabSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        if (isSignInDetailsValid()) {
            Helper.StrictMode();

            String email = etEmail.getEditText().getText().toString().trim();
            String password = etPassword.getEditText().getText().toString().trim();

            User authUser = authImpl.loginUser(email, password);

            if (authUser != null) {
                userSession.startSession(authUser);
                navigateDashboard();
            }
        }
    }

    private boolean isSignInDetailsValid() {
        if (EditTextValidation.isEmpty(etEmail) | EditTextValidation.isEmpty(etPassword)) {
            return false;
        }
        return true;
    }

    private void navigateDashboard() {
        Intent mainActivity = new Intent(this, MainActivity.class);
        mainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(mainActivity);

        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        authImpl.setAuthListener(new AuthImpl.AuthListener() {
            @Override
            public void onError(Error error) {
                if (error.getField().equals("email")) {
                    etEmail.setError(error.getMessage());
                } else {
                    etPassword.setError(error.getMessage());
                }
            }
        });
    }

    public void showSignUpForm(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
