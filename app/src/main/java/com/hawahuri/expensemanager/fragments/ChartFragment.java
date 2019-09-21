package com.hawahuri.expensemanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hawahuri.expensemanager.R;

public class ChartFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    public ChartFragment() {

    }

    public static ChartFragment newInstance(String toolbarTitle) {
        ChartFragment chartFragment = new ChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, toolbarTitle);
        chartFragment.setArguments(args);
        return chartFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

}
