package com.hawahuri.expensemanager.ui;

import android.content.Context;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.impl.TransactionImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.models.Transaction;
import com.hawahuri.expensemanager.response.TransactionResponse;
import com.hawahuri.expensemanager.utils.EditTextValidation;
import com.hawahuri.expensemanager.utils.Helper;
import com.hawahuri.expensemanager.utils.UserSession;

public class NewTransactionActivity extends AppCompatActivity {

    private TextInputLayout etTransactionMemo;
    private TextInputLayout etTransactionAmount;
    private Button btnAddTransaction;
    private UserSession userSession;
    private TransactionImpl transactionImpls;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);
        userSession = new UserSession(this);
        transactionImpls=new TransactionImpl();
        initToolbar();

    }

    private void initToolbar() {

        Toolbar transactionToolbar = findViewById(R.id.transaction_toolbar);
        setSupportActionBar(transactionToolbar);
        getSupportActionBar().setTitle("New Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etTransactionAmount = findViewById(R.id.et_amount);
        etTransactionMemo = findViewById(R.id.et_memo);
        btnAddTransaction = findViewById(R.id.btn_add);
        btnAddTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewTransaction();
            }
        });

    }

    private void addNewTransaction() {
        if (!EditTextValidation.isEmpty(etTransactionMemo) && !EditTextValidation.isEmpty(etTransactionAmount)) {
            Helper.StrictMode();
            String memo = etTransactionMemo.getEditText().getText().toString().trim();
            Number amount = Integer.parseInt(etTransactionAmount.getEditText().getText().toString().trim());
            String creator = userSession.getUser().get_id();
            String category = "5d864412c5a49535f48bc9ee";
            String type = "Expense";
            String date = "2019-09-24";
            Transaction transaction = new Transaction(memo, type, category, creator, date, amount);
            TransactionResponse transactionResponse = transactionImpls.addNewTransaction(transaction);
            if (transactionResponse != null) {
                Toast.makeText(this, transactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                etTransactionMemo.getEditText().setText("");
                etTransactionAmount.getEditText().setText("");
            }

        }
    }
}
