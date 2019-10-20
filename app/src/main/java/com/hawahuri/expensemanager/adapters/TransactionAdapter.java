package com.hawahuri.expensemanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.models.TransactionR;
import com.hawahuri.expensemanager.ui.TransactionDetailsActivity;
import com.hawahuri.expensemanager.utils.Helper;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    private Context context;
    private List<TransactionR> transactionList;

    public TransactionAdapter(Context context, List<TransactionR> transactionList) {
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
        TransactionR transaction = transactionList.get(position);
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent transDetails = new Intent(context, TransactionDetailsActivity.class);
                    transDetails.putExtra("TRANSACTION_ID", transactionList.get(getAdapterPosition()).get_id());
                    context.startActivity(transDetails);
                }
            });
        }

        public void bindData(TransactionR transaction) {
            transType.setText(transaction.getType());
            if (transaction.getType().equals("Expense")) {
                transAmount.setText("-" + transaction.getAmount());
                transAmount.setTextColor(context.getResources().getColor(R.color.colorAccent));
            } else {
                transAmount.setText("+" + transaction.getAmount());
                transAmount.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }
            transCategory.setText(transaction.getCategory().getName());
            transDate.setText(Helper.formatDate("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "MM/dd", transaction.getDate()));
            Helper.setIcon(transaction.getCategory().getIcon(), transIcon);
        }
    }

    public void updateTransactionsList(List<TransactionR> transactionList){
        this.transactionList=transactionList;
        notifyDataSetChanged();
    }

}
