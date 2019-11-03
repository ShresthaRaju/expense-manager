package com.hawahuri.expensemanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.hawahuri.expensemanager.fragments.AboutFragment;
import com.hawahuri.expensemanager.fragments.AllCategoriesFragment;
import com.hawahuri.expensemanager.fragments.ChartFragment;
import com.hawahuri.expensemanager.fragments.HomeFragment;
import com.hawahuri.expensemanager.fragments.ProfileFragment;
import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.ui.CategoryUpdateDialog;
import com.hawahuri.expensemanager.ui.RecordTransactionActivity;
import com.hawahuri.expensemanager.utils.ConfirmationDialog;
import com.hawahuri.expensemanager.utils.Helper;

public class MainActivity extends AppCompatActivity implements BubbleNavigationChangeListener, ConfirmationDialog.ConfirmationDialogListener {

    private String categoryId = "";
    private ConfirmationDialog confirmationDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BubbleNavigationConstraintView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setNavigationChangeListener(this);

        loadFragment(HomeFragment.newInstance("Dashboard"));

    }

    public void newTransaction(View view) {

        startActivity(new Intent(this, RecordTransactionActivity.class));

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

    public void showCatUpdateDialog(String catId) {
        CategoryUpdateDialog dialog = new CategoryUpdateDialog();
        dialog.getCategory(catId);
        dialog.show(getSupportFragmentManager(), "UPDATE CATEGORY");
    }

    public void confirmCategoryDelete(String catId) {
        categoryId = catId;
        confirmationDialog = new ConfirmationDialog("Delete Category?", "Are you sure you want to delete this category?");
        confirmationDialog.show(getSupportFragmentManager(), "DET");
    }

    @Override
    public void onSure() {
        Helper.StrictMode();
        if (new CategoryImpl().deleteUserCategory(categoryId)) {
            Toast.makeText(this, "Category Deleted !", Toast.LENGTH_SHORT).show();
            loadFragment(AllCategoriesFragment.newInstance("Categories"));
        }
    }

    @Override
    public void onCancel() {
        confirmationDialog.dismiss();
    }
}
