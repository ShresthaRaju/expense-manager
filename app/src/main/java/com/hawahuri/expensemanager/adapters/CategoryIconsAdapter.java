package com.hawahuri.expensemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.utils.Helper;

import java.util.List;

public class CategoryIconsAdapter extends RecyclerView.Adapter<CategoryIconsAdapter.CategoryIconsViewHolder> {

    private Context context;
    private List<Category> categoryList;
    public IconSelectedListener iconSelectedListener;

    public CategoryIconsAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryIconsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryIconsView = LayoutInflater.from(context).inflate(R.layout.layout_category_icon, parent, false);
        return new CategoryIconsViewHolder(categoryIconsView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryIconsViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.bindIcon(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryIconsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCategoryIcon;

        public CategoryIconsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategoryIcon = itemView.findViewById(R.id.img_cat_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iconSelectedListener.onIconSelected(categoryList.get(getAdapterPosition()).getIcon());
                }
            });

        }

        private void bindIcon(Category category) {
            Helper.setIcon(category.getIcon(), imgCategoryIcon);
        }
    }

    public interface IconSelectedListener {
        void onIconSelected(String iconName);
    }
}
