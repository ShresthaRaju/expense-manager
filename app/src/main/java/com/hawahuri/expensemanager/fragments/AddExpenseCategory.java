package com.hawahuri.expensemanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.adapters.CategoryIconsAdapter;
import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.models.Error;
import com.hawahuri.expensemanager.response.CategoryResponse;
import com.hawahuri.expensemanager.utils.EditTextValidation;
import com.hawahuri.expensemanager.utils.Helper;
import com.hawahuri.expensemanager.utils.UserSession;

import java.util.ArrayList;
import java.util.List;

public class AddExpenseCategory extends Fragment implements CategoryIconsAdapter.IconSelectedListener {
    private TextInputLayout etExpCategoryName;
    private CategoryImpl categoryImpl;
    private UserSession userSession;
    private List<Category> categoryList;
    private ImageView imgExpCat;
    private String icon = "others.png";

    public AddExpenseCategory() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View expCategoryView = inflater.inflate(R.layout.fragment_expense_category, container, false);

        imgExpCat = expCategoryView.findViewById(R.id.img_exp_cat);
        etExpCategoryName = expCategoryView.findViewById(R.id.et_exp_cat);
        Button btnAddExpCat = expCategoryView.findViewById(R.id.btn_add_exp_cat);

        RecyclerView expCategoriesContainer = expCategoryView.findViewById(R.id.exp_categories_container);
        expCategoriesContainer.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        CategoryIconsAdapter categoryIconsAdapter = new CategoryIconsAdapter(getActivity(), categoryList);
        categoryIconsAdapter.iconSelectedListener = this;
        expCategoriesContainer.setAdapter(categoryIconsAdapter);

        btnAddExpCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addExpenseCategory();
            }
        });

        return expCategoryView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryImpl = new CategoryImpl();
        userSession = new UserSession(getActivity());
        categoryList = new ArrayList<>();

        showCategories();
    }

    private void addExpenseCategory() {
        if (!EditTextValidation.isEmpty(etExpCategoryName)) {
            Helper.StrictMode();
            String categoryName = etExpCategoryName.getEditText().getText().toString().trim();
            String creator = userSession.getUser().get_id();
            Category category = new Category(categoryName, "Expense", icon, creator);

            CategoryResponse categoryResponse = categoryImpl.addNewCategory(category);
            if (categoryResponse != null) {
                Toast.makeText(getActivity(), categoryResponse.getMessage(), Toast.LENGTH_LONG).show();
                etExpCategoryName.getEditText().setText("");
            }
        }
    }

    private void showCategories() {
        Helper.StrictMode();
        categoryList = categoryImpl.getExpenseCategories();
    }

    @Override
    public void onResume() {
        super.onResume();
        categoryImpl.setCategoryListener(new CategoryImpl.CategoryListener() {
            @Override
            public void onError(Error error) {
                if (error.getField().equals("name")) {
                    etExpCategoryName.setError(error.getMessage());
                }
            }
        });
    }

    @Override
    public void onIconSelected(String iconName) {
        icon = iconName;
        Helper.setIcon(iconName, imgExpCat);
    }

}
