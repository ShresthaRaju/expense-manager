package com.hawahuri.expensemanager.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ConfirmationDialog extends AppCompatDialogFragment {

    private String title, message;
    private ConfirmationDialogListener confirmationDialogListener;

    public ConfirmationDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder confirmDialog = new AlertDialog.Builder(getActivity());
        confirmDialog.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        confirmationDialogListener.onSure();
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmationDialogListener.onCancel();
            }
        });

        return confirmDialog.create();
    }

    public interface ConfirmationDialogListener {
        void onSure();

        void onCancel();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            confirmationDialogListener = (ConfirmationDialogListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.toString() + " must implement ConfirmationDialogListener");
        }
    }
}
