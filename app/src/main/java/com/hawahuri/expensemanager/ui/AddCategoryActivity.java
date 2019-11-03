package com.hawahuri.expensemanager.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.adapters.AddCategoryAdapter;
import com.hawahuri.expensemanager.fragments.AddExpenseCategory;
import com.hawahuri.expensemanager.fragments.AddIncomeCategory;

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        AddCategoryAdapter addCategoryAdapter = new AddCategoryAdapter(getSupportFragmentManager());
        addCategoryAdapter.addFragment(new AddExpenseCategory(), "Expense");
        addCategoryAdapter.addFragment(new AddIncomeCategory(), "Income");

        viewPager.setAdapter(addCategoryAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar addCategoryToolbar = findViewById(R.id.add_category_toolbar);
        setSupportActionBar(addCategoryToolbar);
        getSupportActionBar().setTitle("Add New Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
