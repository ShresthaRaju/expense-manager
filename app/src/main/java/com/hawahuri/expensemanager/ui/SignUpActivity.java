package com.hawahuri.expensemanager.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.impl.AuthImpl;
import com.hawahuri.expensemanager.models.Error;
import com.hawahuri.expensemanager.models.User;
import com.hawahuri.expensemanager.utils.EditTextValidation;
import com.hawahuri.expensemanager.utils.Helper;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private TextInputLayout etFirstName, etFamilyName, etEmail, etPassword, etConfirmPassword;
    private Button btnGetStarted;
    private HashMap<String, TextInputLayout> errorMap;
    private AuthImpl authImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getWindow().setBackgroundDrawableResource(R.drawable.bg_sign_up);

        initComponents();
    }

    private void initComponents() {

        authImpl = new AuthImpl();

        etFirstName = findViewById(R.id.et_firstName);
        etFamilyName = findViewById(R.id.et_familyName);
        etEmail = findViewById(R.id.et_sign_up_email);
        etPassword = findViewById(R.id.et_sign_up_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);

        errorMap = new HashMap<>();
        errorMap.put("firstName", etFirstName);
        errorMap.put("familyName", etFamilyName);
        errorMap.put("email", etEmail);
        errorMap.put("password", etPassword);

        btnGetStarted = findViewById(R.id.btn_get_started);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {

        if (isSignUpDetailsValid() && isPasswordConfirmed()) {

            Helper.StrictMode();

            String firstName = etFirstName.getEditText().getText().toString().trim();
            String familyName = etFamilyName.getEditText().getText().toString().trim();
            String email = etEmail.getEditText().getText().toString().trim();
            String password = etPassword.getEditText().getText().toString().trim();

            User user = new User(firstName, familyName, email, password);

            if (authImpl.registerUser(user)) {
                Toast.makeText(this, "Successfully registered !", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (!isPasswordConfirmed()) {
            etConfirmPassword.setError("Password did not match !");
        }

    }

    private boolean isSignUpDetailsValid() {
        if (EditTextValidation.isEmpty(etFirstName) | EditTextValidation.isEmpty(etFamilyName) |
                EditTextValidation.isEmpty(etEmail) | EditTextValidation.isEmpty(etPassword) |
                EditTextValidation.isEmpty(etConfirmPassword)) {
            return false;
        }
        return true;
    }

    private boolean isPasswordConfirmed() {
        return etPassword.getEditText().getText().toString().trim().equals(etConfirmPassword.getEditText()
                .getText().toString().trim());
    }

    @Override
    protected void onResume() {
        super.onResume();

        authImpl.setAuthListener(new AuthImpl.AuthListener() {
            @Override
            public void onError(Error error) {
                for (String key : errorMap.keySet()) {
                    if (error.getField().equals(key)) {
                        errorMap.get(error.getField()).setError(error.getMessage());
                    }
                }
            }
        });
    }
}
