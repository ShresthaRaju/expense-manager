package com.hawahuri.expensemanager.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hawahuri.expensemanager.R;

public class NewTransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        initToolbar();

    }

    private void initToolbar() {

        Toolbar transactionToolbar = findViewById(R.id.transaction_toolbar);
        setSupportActionBar(transactionToolbar);
        getSupportActionBar().setTitle("New Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
