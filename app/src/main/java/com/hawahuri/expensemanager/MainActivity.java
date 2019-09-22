package com.hawahuri.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.hawahuri.expensemanager.fragments.AboutFragment;
import com.hawahuri.expensemanager.fragments.AllCategoriesFragment;
import com.hawahuri.expensemanager.fragments.ChartFragment;
import com.hawahuri.expensemanager.fragments.HomeFragment;
import com.hawahuri.expensemanager.fragments.ProfileFragment;
import com.hawahuri.expensemanager.ui.NewTransactionActivity;

public class MainActivity extends AppCompatActivity implements BubbleNavigationChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BubbleNavigationConstraintView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setNavigationChangeListener(this);

        loadFragment(HomeFragment.newInstance("Dashboard"));
    }

    public void newTransaction(View view) {

        startActivity(new Intent(this, NewTransactionActivity.class));

    }

    private void loadFragment(Fragment activeFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, activeFragment).commit();
    }

    @Override
    public void onNavigationChanged(View view, int position) {
        Fragment activeFragment = null;
        switch (view.getId()) {
            case R.id.nav_categories:
                activeFragment = AllCategoriesFragment.newInstance("Categories");
                break;

            case R.id.nav_chart:
                activeFragment = ChartFragment.newInstance("Charts");
                break;

            case R.id.nav_home:
                activeFragment = HomeFragment.newInstance("Dashboard");
                break;

            case R.id.nav_profile:
                activeFragment = ProfileFragment.newInstance("Profile");
                break;

            case R.id.nav_more:
                activeFragment = AboutFragment.newInstance("About");
                break;
        }

        loadFragment(activeFragment);
    }
}
