package com.hawahuri.expensemanager.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
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
import com.hawahuri.expensemanager.utils.RetrofitClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoriesView = LayoutInflater.from(context).inflate(R.layout.layout_single_category, parent, false);
        return new CategoryViewHolder(categoriesView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.categoryName.setText(category.getName());
        Helper.StrictMode();
        try {
            String imageURI = RetrofitClient.IMAGE_URL + category.getIcon();
            URL url = new URL(imageURI);
            holder.categoryIcon.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ImageView categoryIcon;
        private TextView categoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.iv_icon);
            categoryName = itemView.findViewById(R.id.tv_category_name);
        }
    }

}
