package com.hawahuri.expensemanager.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hawahuri.expensemanager.CategoryeiActivity;
import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.adapters.CategoryAdapter;
import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class AllCategoriesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private String toolbarTitle;
    private CategoryAdapter categoryAdapter;
    private RecyclerView categoriesContainer;
    private List<Category> allCategories;
    private CategoryImpl categoryImpl;

    public AllCategoriesFragment() {

    }

    public static AllCategoriesFragment newInstance(String toolbarTitle) {
        AllCategoriesFragment allCategoriesFragment = new AllCategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, toolbarTitle);
        allCategoriesFragment.setArguments(args);
        return allCategoriesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categoryImpl = new CategoryImpl();
        allCategories = new ArrayList<>();
        viewAllCategory();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View categoriesView = inflater.inflate(R.layout.fragment_all_categories, container, false);

        initToolbar(categoriesView);

        categoriesContainer = categoriesView.findViewById(R.id.all_categories_container);
        categoriesContainer.setLayoutManager(new LinearLayoutManager(getActivity()));

        categoryAdapter = new CategoryAdapter(getActivity(), allCategories);
        categoriesContainer.setAdapter(categoryAdapter);

        return categoriesView;
    }

    private void initToolbar(View view) {
        Toolbar categoriesToolbar = view.findViewById(R.id.categories_toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(categoriesToolbar);
        if (getArguments() != null) {
            toolbarTitle = getArguments().getString(ARG_PARAM1);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(toolbarTitle);
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_category, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_add_category) {
            Intent intent = new Intent(getActivity(), CategoryeiActivity.class);
            startActivity(intent);


//            Toast.makeText(getActivity(), "New Category", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void viewAllCategory() {
        Helper.StrictMode();

        allCategories = categoryImpl.viewAllCategories();
//
//        if (categoryImpl.viewAllCategories() != null) {
//            allCategories = categoryImpl.viewAllCategories();
//        } else if (categoryImpl.viewAllCategories() == null) {
//            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//
//
//        }

    }
}
