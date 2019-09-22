package com.hawahuri.expensemanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hawahuri.expensemanager.R;

public class ProfileFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance(String toolbarTitle) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, toolbarTitle);
        profileFragment.setArguments(args);
        return profileFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
