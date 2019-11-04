package com.hawahuri.expensemanager.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.impl.TransactionImpl;
import com.hawahuri.expensemanager.models.TransactionR;
import com.hawahuri.expensemanager.response.TransactionResponse;
import com.hawahuri.expensemanager.utils.ConfirmationDialog;
import com.hawahuri.expensemanager.utils.Helper;

public class TransactionDetailsActivity extends AppCompatActivity implements ConfirmationDialog.ConfirmationDialogListener {

    private TextView tvCategory, tvType, tvAmount, tvDate, tvMemo;
    private ImageView transactionImage;
    private TransactionImpl transactionImpl;
    private String transactionId = "";
    private ConfirmationDialog deleteTransDialog;
    private TransactionR transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_details);

        initComponents();

        transactionId = getIntent().getStringExtra("TRANSACTION_ID");
        transactionImpl = new TransactionImpl();

    }

    private void initComponents() {
        initToolbar();
        transactionImage = findViewById(R.id.iv_trans_icon);
        tvCategory = findViewById(R.id.trans_cat_name);
        tvType = findViewById(R.id.dt_type_value);
        tvAmount = findViewById(R.id.dt_amount_value);
        tvDate = findViewById(R.id.dt_date_value);
        tvMemo = findViewById(R.id.dt_memo_value);
        ImageButton btnDeleteTransaction = findViewById(R.id.btn_delete_transaction);
        btnDeleteTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTransDialog = new ConfirmationDialog("Delete Transaction!", "Are you sure you want to delete this transaction? " +
                        "This action cannot be undone!");
                deleteTransDialog.show(getSupportFragmentManager(), "DELETE TRANSACTION");
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.trans_det_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Transaction Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_transaction, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_update_trans) {
            Intent updateTransaction = new Intent(this, RecordTransactionActivity.class);
            updateTransaction.putExtra("UPDATE_TRANSACTION", transaction);
            startActivity(updateTransaction);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTransactionDetail();
    }

    private void getTransactionDetail() {
        Helper.StrictMode();
        if (!transactionId.equals("")) {
            TransactionResponse transactionResponse = transactionImpl.getSingleTransaction(transactionId);
            if (transactionResponse != null) {
                transaction = transactionResponse.getTransaction();
                populateDetails(transaction);
            }
        }
    }

    private void populateDetails(TransactionR transaction) {
        tvCategory.setText(transaction.getCategory().getName());
        tvType.setText(transaction.getType());
        tvAmount.setText(String.valueOf(transaction.getAmount()));
        tvDate.setText(Helper.formatDate("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "MM/dd/YYYY", transaction.getDate()));
        tvMemo.setText(transaction.getMemo());
        Helper.setIcon(transaction.getCategory().getIcon(), transactionImage);
    }

    @Override
    public void onSure() {
        Helper.StrictMode();
        if (transactionImpl.deleteTransaction(transactionId)) {
            Toast.makeText(this, "Transaction successfully deleted!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onCancel() {
        deleteTransDialog.dismiss();
    }
}
