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
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.utils.Helper;

import java.util.List;

public class BSCategoriesAdapter extends RecyclerView.Adapter<BSCategoriesAdapter.BSCategoriesViewHolder> {

    private Context context;
    private List<Category> categoryList;
    public CategorySelectedListener categorySelectedListener;

    public BSCategoriesAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public BSCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View allCategoriesView = LayoutInflater.from(context).inflate(R.layout.layout_bottom_sheet_categories, parent, false);
        return new BSCategoriesViewHolder(allCategoriesView);
    }

    @Override
    public void onBindViewHolder(@NonNull BSCategoriesViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.bindIcon(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class BSCategoriesViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCatIcon;
        private TextView tvCatName;

        public BSCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCatIcon = itemView.findViewById(R.id.img_bs_cat_icon);
            tvCatName = itemView.findViewById(R.id.tv_bs_cat_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categorySelectedListener.onCategorySelected(categoryList.get(getAdapterPosition()));
                }
            });

        }

        private void bindIcon(Category category) {
            Helper.setIcon(category.getIcon(), imgCatIcon);
            tvCatName.setText(category.getName());
        }
    }

    public interface CategorySelectedListener {
        void onCategorySelected(Category category);
    }
}
