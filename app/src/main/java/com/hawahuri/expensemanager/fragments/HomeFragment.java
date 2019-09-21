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

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.ui.SignInActivity;
import com.hawahuri.expensemanager.utils.UserSession;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String toolbarTitle) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, toolbarTitle);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    private void initToolbar(View view) {
        Toolbar homeToolbar = view.findViewById(R.id.home_toolbar);
        ((MainActivity) getActivity()).setSupportActionBar(homeToolbar);
        if (getArguments() != null) {
            String toolbarTitle = getArguments().getString(ARG_PARAM1);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(toolbarTitle);
            setHasOptionsMenu(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View dashboardView = inflater.inflate(R.layout.fragment_home, container, false);
        initToolbar(dashboardView);
        return dashboardView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mi_sign_out) {
            new UserSession(getActivity()).endSession();
            Intent signInIntent = new Intent(getActivity(), SignInActivity.class);
            signInIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(signInIntent);

            getActivity().finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
