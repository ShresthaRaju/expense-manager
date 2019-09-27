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

public class ExpCategoriesBottomSheet extends BottomSheetDialogFragment implements BSCategoriesAdapter.CategorySelectedListener {

    private RecyclerView allCategoriesContainer;
    private List<Category> allExpCat;
    private CategoryImpl categoryImpl;
    private String creator;
    private ExpBottomSheetListener expBottomSheetListener;

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
        allExpCat = new ArrayList<>();
        UserSession userSession = new UserSession(getActivity());
        creator = userSession.getUser().get_id();
    }

    @Override
    public void onResume() {
        super.onResume();

        BSCategoriesAdapter expCatAdapter = new BSCategoriesAdapter(getActivity(), getAllExpenseCategories());
        expCatAdapter.categorySelectedListener = this;
        allCategoriesContainer.setAdapter(expCatAdapter);

    }

    private List<Category> getAllExpenseCategories() {
        Helper.StrictMode();
        allExpCat = categoryImpl.getExpenseCategories();
        for (Category category : categoryImpl.getUserCategories(creator)) {
            if (category.getType().equals("Expense")) {
                allExpCat.add(category);
            }
        }
        return allExpCat;
    }

    @Override
    public void onCategorySelected(Category category) {
        expBottomSheetListener.onExpCatSelected(category);
    }

    public interface ExpBottomSheetListener {
        void onExpCatSelected(Category expCategory);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            expBottomSheetListener = (ExpBottomSheetListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException(context.toString() + " must implement Expense BottomSheetListener");
        }
    }
}
