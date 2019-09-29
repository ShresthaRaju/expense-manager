package com.hawahuri.expensemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.models.Transaction;
import com.hawahuri.expensemanager.utils.Helper;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    private Context context;
    private List<Transaction> transactionList;

    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View transactionView = LayoutInflater.from(context).inflate(R.layout.layout_transaction_list, parent, false);
        return new TransactionViewHolder(transactionView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.TransactionViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
        holder.bindData(transaction);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        private ImageView transIcon;
        private TextView transType, transDate, transCategory, transAmount;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            transIcon = itemView.findViewById(R.id.iv_trans_icon);
            transType = itemView.findViewById(R.id.tv_trans_type);
            transDate = itemView.findViewById(R.id.tv_trans_date);
            transCategory = itemView.findViewById(R.id.tv_trans_category);
            transAmount = itemView.findViewById(R.id.tv_trans_amount);
        }

        public void bindData(Transaction transaction) {
            transType.setText(transaction.getType());
            transAmount.setText(Double.toString(transaction.getAmount()));
            transCategory.setText(transaction.getCategory().getName());
            transDate.setText(Helper.formatDate("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "MM/dd", transaction.getDate()));
            Helper.setIcon(transaction.getCategory().getIcon(), transIcon);
        }
    }

}
