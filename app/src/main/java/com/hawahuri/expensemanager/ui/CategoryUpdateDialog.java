package com.hawahuri.expensemanager.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.models.Error;
import com.hawahuri.expensemanager.response.CategoryResponse;
import com.hawahuri.expensemanager.utils.Helper;

public class CategoryUpdateDialog extends AppCompatDialogFragment {

    private TextInputLayout etCatName;
    private String categoryId = "";
    private Category category = null;
    private CategoryImpl categoryImpl = new CategoryImpl();

    @Override
    public void onResume() {
        super.onResume();
        categoryImpl.setCategoryListener(new CategoryImpl.CategoryListener() {
            @Override
            public void onError(Error error) {
                if (error.getField().equals("name")) {
                    etCatName.setError(error.getMessage());
                }
            }
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder updateCat = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_cat_update, null);
        updateCat.setView(view)
                .setCancelable(false)
                .setTitle("Update Category!");

        etCatName = view.findViewById(R.id.et_cat_update);
        if (category != null) {
            etCatName.getEditText().setText(category.getName());
        }

        Button btnUpdateCat = view.findViewById(R.id.btn_update_cat);
        btnUpdateCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCategory();
            }
        });

        return updateCat.create();
    }

    public void getCategory(String catId) {
        categoryId = catId;
        Helper.StrictMode();
        category = categoryImpl.getSingleCategory(catId);
    }

    public void updateCategory() {
        Helper.StrictMode();
        Category newCategory = new Category(etCatName.getEditText().getText().toString().trim(), category.getType(), category.getIcon(), category.getCreator());
        CategoryResponse categoryResponse = categoryImpl.updateUserCategory(categoryId, newCategory);
        if (categoryResponse != null && categoryResponse.getCategory() != null) {
            this.dismiss();
            Toast.makeText(getActivity(), "Category updated", Toast.LENGTH_SHORT).show();
        }
    }
}
