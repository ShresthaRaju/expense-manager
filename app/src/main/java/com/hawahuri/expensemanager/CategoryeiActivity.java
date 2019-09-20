package com.hawahuri.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.hawahuri.expensemanager.adapters.EiFragmentAdapter;
import com.hawahuri.expensemanager.fragments.ExpensesFragmentCategory;
import com.hawahuri.expensemanager.fragments.IncomeCategoryFragment;

public class CategoryeiActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryei);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tablayout);
        EiFragmentAdapter eiFragmentAdapter=new EiFragmentAdapter(getSupportFragmentManager());
        eiFragmentAdapter.addFragment(new ExpensesFragmentCategory(),"Expenses");
        eiFragmentAdapter.addFragment(new IncomeCategoryFragment(),"Income");

        viewPager.setAdapter(eiFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
