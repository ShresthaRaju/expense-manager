package com.hawahuri.expensemanager.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.adapters.BSCategoriesAdapter;
import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.utils.Helper;
import com.hawahuri.expensemanager.utils.UserSession;

import java.util.ArrayList;
import java.util.List;

public class IncCategoriesBottomSheet extends BottomSheetDialogFragment implements BSCategoriesAdapter.CategorySelectedListener {

    private RecyclerView allCategoriesContainer;
    private List<Category> allIncCat;
    private CategoryImpl categoryImpl;
    private String creator;
    private IncBottomSheetListener incBottomSheetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View categoriesView = inflater.inflate(R.layout.layout_bottom_sheet, container, false);
        allCategoriesContainer = categoriesView.findViewById(R.id.all_categories_container);
        allCategoriesContainer.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        return categoriesView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryImpl = new CategoryImpl();
        allIncCat = new ArrayList<>();
        UserSession userSession = new UserSession(getActivity());
        creator = userSession.getUser().get_id();
    }

    @Override
    public void onResume() {
        super.onResume();

        BSCategoriesAdapter incCatAdapter = new BSCategoriesAdapter(getActivity(), getAllIncomeCategories());
        incCatAdapter.categorySelectedListener = this;
        allCategoriesContainer.setAdapter(incCatAdapter);

    }

    private List<Category> getAllIncomeCategories() {
        Helper.StrictMode();
        allIncCat = categoryImpl.getIncomeCategories();
        for (Category category : categoryImpl.getUserCategories(creator)) {
            if (category.getType().equals("Income")) {
                allIncCat.add(category);
            }
        }
        return allIncCat;
    }

    @Override
    public void onCategorySelected(Category category) {
        incBottomSheetListener.onIncCatSelected(category);
    }

    public interface IncBottomSheetListener {
        void onIncCatSelected(Category incCategory);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {

            incBottomSheetListener = (IncBottomSheetListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.toString() + " must implement Income BottomSheetListener");
        }
    }
}
