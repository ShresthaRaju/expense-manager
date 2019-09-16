package com.hawahuri.expensemanager.utils;

import com.google.android.material.textfield.TextInputLayout;

public class EditTextValidation {

    public static boolean isEmpty(TextInputLayout textInputLayout) {
        if (textInputLayout.getEditText().getText().toString().trim().isEmpty()) {
            textInputLayout.setError(textInputLayout.getEditText().getHint().toString() + " is required");
            return true;
        } else {
            textInputLayout.setError(null);
            return false;
        }
    }

}
