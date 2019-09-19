package com.hawahuri.expensemanager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.hawahuri.expensemanager.fragments.AllCategoriesFragment;

public class MainActivity extends AppCompatActivity {

    private BubbleNavigationConstraintView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.bottom_navigation);

        navigationView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                if (view.getId() == R.id.nav_categories) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, AllCategoriesFragment.newInstance("Categories")).commit();
                }
            }
        });
    }
}
