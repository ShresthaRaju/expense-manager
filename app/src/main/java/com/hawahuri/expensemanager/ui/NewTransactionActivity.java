package com.hawahuri.expensemanager.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.fragments.DatePickerFragment;
import com.hawahuri.expensemanager.fragments.ExpCategoriesBottomSheet;
import com.hawahuri.expensemanager.fragments.IncCategoriesBottomSheet;
import com.hawahuri.expensemanager.impl.TransactionImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.models.Error;
import com.hawahuri.expensemanager.models.Transaction;
import com.hawahuri.expensemanager.response.TransactionResponse;
import com.hawahuri.expensemanager.utils.EditTextValidation;
import com.hawahuri.expensemanager.utils.Helper;
import com.hawahuri.expensemanager.utils.UserSession;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewTransactionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, View.OnClickListener, ExpCategoriesBottomSheet.ExpBottomSheetListener, IncCategoriesBottomSheet.IncBottomSheetListener {

    private TextInputLayout etTransactionMemo, etTransactionAmount, etTransactionDate;
    private TransactionImpl transactionImpl;
    private Button tabExpense, tabIncome;
    private String transactionType = "", creator;
    private Calendar calendar;
    private CircleImageView categoryIcon;
    private ExpCategoriesBottomSheet expCategoriesBS;
    private IncCategoriesBottomSheet incCategoriesBS;
    private String category = "Others";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        initComponents();

    }

    private void initToolbar() {

        Toolbar transactionToolbar = findViewById(R.id.transaction_toolbar);
        setSupportActionBar(transactionToolbar);
        getSupportActionBar().setTitle("New Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponents() {

        initToolbar();

        UserSession userSession = new UserSession(this);
        creator = userSession.getUser().get_id();
        transactionImpl = new TransactionImpl();

        categoryIcon = findViewById(R.id.category_icon);
        etTransactionMemo = findViewById(R.id.et_memo);
        etTransactionAmount = findViewById(R.id.et_amount);
        etTransactionDate = findViewById(R.id.et_date);
        tabExpense = findViewById(R.id.tab_expense);
        tabIncome = findViewById(R.id.tab_income);
        Button btnAddTransaction = findViewById(R.id.btn_add);
        calendar = Calendar.getInstance();

        setToday();

        tabExpense.setOnClickListener(this);
        tabIncome.setOnClickListener(this);
        etTransactionDate.getEditText().setOnClickListener(this);
        btnAddTransaction.setOnClickListener(this);
    }


    private void addNewTransaction() {
        if (transactionType.equals("")) {
            Toast.makeText(this, "Please select a transaction type!", Toast.LENGTH_LONG).show();
        } else if (!EditTextValidation.isEmpty(etTransactionMemo) & !EditTextValidation.isEmpty(etTransactionAmount)) {
            Helper.StrictMode();
            String memo = etTransactionMemo.getEditText().getText().toString().trim();
            double amount = Double.parseDouble(etTransactionAmount.getEditText().getText().toString().trim());
            String transactionDate = etTransactionDate.getEditText().getText().toString().trim();
            Transaction transaction = new Transaction(memo, transactionType, category, creator, transactionDate, amount);
            TransactionResponse transactionResponse = transactionImpl.addNewTransaction(transaction);
            if (transactionResponse != null) {
                Toast.makeText(this, transactionResponse.getMessage(), Toast.LENGTH_SHORT).show();
                etTransactionMemo.getEditText().setText("");
                etTransactionAmount.getEditText().setText("");
                setToday();
                etTransactionMemo.requestFocus();
            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(year, month, dayOfMonth);
        setDate(calendar.getTime());
    }

    private void setDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd", Locale.getDefault());
        String selectedDate = dateFormat.format(date);
        etTransactionDate.getEditText().setText(selectedDate);
    }

    private void setToday() {
        calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        setDate(calendar.getTime());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_expense:
                transactionType = "Expense";
                tabExpense.setBackgroundColor(ContextCompat.getColor(NewTransactionActivity.this, R.color.signUpColor));
                tabExpense.setTextColor(ContextCompat.getColor(NewTransactionActivity.this, R.color.colorWhite));
                tabIncome.setBackgroundColor(ContextCompat.getColor(NewTransactionActivity.this, R.color.colorWhite));
                tabIncome.setTextColor(ContextCompat.getColor(NewTransactionActivity.this, R.color.colorBlack));
                expCategoriesBS = new ExpCategoriesBottomSheet();
                expCategoriesBS.show(getSupportFragmentManager(), "EXPENSE CATEGORIES");
                break;

            case R.id.tab_income:
                transactionType = "Income";
                tabIncome.setBackgroundColor(ContextCompat.getColor(NewTransactionActivity.this, R.color.signUpColor));
                tabIncome.setTextColor(ContextCompat.getColor(NewTransactionActivity.this, R.color.colorWhite));
                tabExpense.setBackgroundColor(ContextCompat.getColor(NewTransactionActivity.this, R.color.colorWhite));
                tabExpense.setTextColor(ContextCompat.getColor(NewTransactionActivity.this, R.color.colorBlack));
                incCategoriesBS = new IncCategoriesBottomSheet();
                incCategoriesBS.show(getSupportFragmentManager(), "INCOME CATEGORIES");
                break;

            case R.id.et_date_value:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "TRANSACTION DATE");
                break;

            case R.id.btn_add:
                addNewTransaction();
                break;
        }
    }

    @Override
    public void onExpCatSelected(Category expCategory) {
        category = expCategory.get_id();
        Helper.setIcon(expCategory.getIcon(), categoryIcon);
        expCategoriesBS.dismiss();
    }

    @Override
    public void onIncCatSelected(Category incCategory) {
        category = incCategory.get_id();
        Helper.setIcon(incCategory.getIcon(), categoryIcon);
        incCategoriesBS.dismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();

        transactionImpl.setTransactionListener(new TransactionImpl.TransactionListener() {
            @Override
            public void onError(Error error) {
                if (error.getField().equals("amount")) {
                    etTransactionAmount.setError(error.getMessage());
                }
            }
        });
    }
}
