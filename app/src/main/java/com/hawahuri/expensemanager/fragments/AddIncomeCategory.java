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

public class AddIncomeCategory extends Fragment implements CategoryIconsAdapter.IconSelectedListener {
    private TextInputLayout etIncCategoryName;
    private CategoryImpl categoryImpl;
    private UserSession userSession;
    private List<Category> categoryList;
    private ImageView imgIncCat;
    private String icon = "others.png";

    public AddIncomeCategory() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View incCategoryView = inflater.inflate(R.layout.fragment_income_category, container, false);

        imgIncCat = incCategoryView.findViewById(R.id.img_inc_cat);
        etIncCategoryName = incCategoryView.findViewById(R.id.et_inc_cat);
        Button btnAddIncCat = incCategoryView.findViewById(R.id.btn_add_inc_cat);

        RecyclerView incCategoriesContainer = incCategoryView.findViewById(R.id.inc_categories_container);
        incCategoriesContainer.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        CategoryIconsAdapter categoryIconsAdapter = new CategoryIconsAdapter(getActivity(), categoryList);
        categoryIconsAdapter.iconSelectedListener = this;
        incCategoriesContainer.setAdapter(categoryIconsAdapter);

        btnAddIncCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addIncomeCategory();
            }
        });

        return incCategoryView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryImpl = new CategoryImpl();
        userSession = new UserSession(getActivity());

        categoryList = new ArrayList<>();
        showCategories();
    }

    private void addIncomeCategory() {
        if (!EditTextValidation.isEmpty(etIncCategoryName)) {
            Helper.StrictMode();
            String categoryName = etIncCategoryName.getEditText().getText().toString().trim();
            String creator = userSession.getUser().get_id();
            Category category = new Category(categoryName, "Income", icon, creator);

            CategoryResponse categoryResponse = categoryImpl.addNewCategory(category);
            if (categoryResponse != null) {
                Toast.makeText(getActivity(), categoryResponse.getMessage(), Toast.LENGTH_LONG).show();
                etIncCategoryName.getEditText().setText("");
            }
        }
    }

    private void showCategories() {
        Helper.StrictMode();
        categoryList = categoryImpl.getIncomeCategories();
    }

    @Override
    public void onResume() {
        super.onResume();
        categoryImpl.setCategoryListener(new CategoryImpl.CategoryListener() {
            @Override
            public void onError(Error error) {
                if (error.getField().equals("name")) {
                    etIncCategoryName.setError(error.getMessage());
                }
            }
        });
    }

    @Override
    public void onIconSelected(String iconName) {
        icon = iconName;
        Helper.setIcon(iconName, imgIncCat);
    }
}
