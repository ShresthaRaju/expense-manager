package com.hawahuri.expensemanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hawahuri.expensemanager.R;

public class AboutFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    public AboutFragment() {

    }

    public static AboutFragment newInstance(String toolbarTitle) {
        AboutFragment aboutFragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, toolbarTitle);
        aboutFragment.setArguments(args);
        return aboutFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

}
