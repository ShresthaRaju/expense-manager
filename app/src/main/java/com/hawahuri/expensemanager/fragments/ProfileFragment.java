package com.hawahuri.expensemanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.models.User;
import com.hawahuri.expensemanager.utils.UserSession;

public class ProfileFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private User currentUser;
    private TextView tvFullname, tvEmail;

    public ProfileFragment() {

    }

    public static ProfileFragment newInstance(String toolbarTitle) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, toolbarTitle);
        profileFragment.setArguments(args);
        return profileFragment;
    }

    private void initToolbar(View view) {
        Toolbar homeToolbar = view.findViewById(R.id.profile_toolbar);
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
        View profileView = inflater.inflate(R.layout.fragment_profile, container, false);
        initToolbar(profileView);

        tvFullname = profileView.findViewById(R.id.tv_fullname);
        tvEmail = profileView.findViewById(R.id.tv_profile_email);

        tvFullname.setText(currentUser.getFirstName() + " " + currentUser.getFamilyName());
        tvEmail.setText(currentUser.getEmail());

        return profileView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentUser = new UserSession(getActivity()).getUser();
    }
}
