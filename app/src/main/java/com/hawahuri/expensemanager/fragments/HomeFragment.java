package com.hawahuri.expensemanager.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.adapters.TransactionAdapter;
import com.hawahuri.expensemanager.impl.AuthImpl;
import com.hawahuri.expensemanager.impl.TransactionImpl;
import com.hawahuri.expensemanager.models.Transaction;
import com.hawahuri.expensemanager.models.User;
import com.hawahuri.expensemanager.response.TransactionResponse;
import com.hawahuri.expensemanager.response.UserResponse;
import com.hawahuri.expensemanager.ui.SignInActivity;
import com.hawahuri.expensemanager.utils.Helper;
import com.hawahuri.expensemanager.utils.UserSession;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView transactionsContainer;
    private List<Transaction> userTransactions;
    private TransactionImpl transactionImpl;
    private UserSession userSession;
    private TextView incomeValue,expenseValue,balance;

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
        transactionsContainer = dashboardView.findViewById(R.id.main_recyclerview);
        transactionsContainer.setLayoutManager(new LinearLayoutManager(getActivity()));
        incomeValue=dashboardView.findViewById(R.id.tv_income_value);
        expenseValue=dashboardView.findViewById(R.id.tv_expense_value);
        balance=dashboardView.findViewById(R.id.tv_balance_value);

        return dashboardView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userSession = new UserSession(getActivity());
        userTransactions = new ArrayList<>();
        transactionImpl = new TransactionImpl();
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

    public void showTransaction() {
        Helper.StrictMode();
        TransactionResponse transactionResponse = transactionImpl.getTransactions(userSession.getUser().get_id());
        if (transactionResponse == null) {
            Toast.makeText(getActivity(), "No transaction", Toast.LENGTH_SHORT).show();
        } else {
            userTransactions = transactionResponse.getMyTransactions();
            TransactionAdapter transactionAdapter = new TransactionAdapter(getActivity(), userTransactions);
            transactionsContainer.setAdapter(transactionAdapter);
        }
    }
    public void showIncomeExpense(){
        Helper.StrictMode();
        AuthImpl authImpl=new AuthImpl();
        UserResponse userResponse= authImpl.getIncomeExpense(userSession.getUser().get_id());
        incomeValue.setText(Double.toString(userResponse.getUser().getTotalIncome()));
        expenseValue.setText(Double.toString(userResponse.getUser().getTotalExpense()));
    }

    @Override
    public void onResume() {
        super.onResume();
        showTransaction();
        showIncomeExpense();
    }
}
