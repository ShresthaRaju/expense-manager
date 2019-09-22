package com.hawahuri.expensemanager.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.adapters.CategoriesAdapter;
import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.ui.AddCategoryActivity;
import com.hawahuri.expensemanager.utils.Helper;
import com.hawahuri.expensemanager.utils.UserSession;

import java.util.ArrayList;
import java.util.List;

public class AllCategoriesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private List<Category> defaultCategories;
    private List<Category> userCategories;
    private CategoryImpl categoryImpl;
    private UserSession userSession;
    private View divider;
    private TextView myCategories;
    private RecyclerView defaultCategoriesContainer;
    private RecyclerView userCategoriesContainer;

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

        userSession = new UserSession(getActivity());
        categoryImpl = new CategoryImpl();
        defaultCategories = new ArrayList<>();
        userCategories = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        showDefaultCategories();
        showUserCategories();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View categoriesView = inflater.inflate(R.layout.fragment_all_categories, container, false);

        initToolbar(categoriesView);

        divider = categoriesView.findViewById(R.id.divider);
        myCategories = categoriesView.findViewById(R.id.tv_my_categories);

        defaultCategoriesContainer = categoriesView.findViewById(R.id.def_categories_container);
        defaultCategoriesContainer.setLayoutManager(new LinearLayoutManager(getActivity()));
        userCategoriesContainer = categoriesView.findViewById(R.id.user_categories_container);
        userCategoriesContainer.setLayoutManager(new LinearLayoutManager(getActivity()));

        return categoriesView;
    }

    private void initToolbar(View view) {
        Toolbar categoriesToolbar = view.findViewById(R.id.categories_toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(categoriesToolbar);
        if (getArguments() != null) {
            String toolbarTitle = getArguments().getString(ARG_PARAM1);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(toolbarTitle);
            setHasOptionsMenu(true);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_category, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mi_add_category) {
            Intent addCategoryIntent = new Intent(getActivity(), AddCategoryActivity.class);
            startActivity(addCategoryIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDefaultCategories() {
        Helper.StrictMode();
        defaultCategories = categoryImpl.getExpenseCategories();
        defaultCategories.addAll(categoryImpl.getIncomeCategories());
        CategoriesAdapter defCategoriesAdapter = new CategoriesAdapter(getActivity(), defaultCategories, "");
        defaultCategoriesContainer.setAdapter(defCategoriesAdapter);
    }

    public void showUserCategories() {
        Helper.StrictMode();
        userCategories = categoryImpl.getUserCategories(userSession.getUser().get_id());
        toggleVisibility();
        CategoriesAdapter userCategoriesAdapter = new CategoriesAdapter(getActivity(), userCategories, "user");
        userCategoriesContainer.setAdapter(userCategoriesAdapter);
    }

    private void toggleVisibility() {
        if (userCategories.size() < 1) {
            divider.setVisibility(View.GONE);
            myCategories.setVisibility(View.GONE);
            userCategoriesContainer.setVisibility(View.GONE);
        } else {
            divider.setVisibility(View.VISIBLE);
            myCategories.setVisibility(View.VISIBLE);
            userCategoriesContainer.setVisibility(View.VISIBLE);
        }
    }
}
