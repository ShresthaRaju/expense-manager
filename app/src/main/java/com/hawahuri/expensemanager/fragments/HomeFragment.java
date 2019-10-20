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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.adapters.TransactionAdapter;
import com.hawahuri.expensemanager.impl.AuthImpl;
import com.hawahuri.expensemanager.impl.TransactionImpl;
import com.hawahuri.expensemanager.models.TransactionR;
import com.hawahuri.expensemanager.response.TransactionResponse;
import com.hawahuri.expensemanager.response.UserResponse;
import com.hawahuri.expensemanager.ui.RecordTransactionActivity;
import com.hawahuri.expensemanager.ui.SignInActivity;
import com.hawahuri.expensemanager.utils.Helper;
import com.hawahuri.expensemanager.utils.UserSession;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private TransactionAdapter transactionAdapter;
    private List<TransactionR> userTransactions;
    private TransactionImpl transactionImpl;
    private UserSession userSession;
    private TextView incomeValue, expenseValue, balance, tvNoTransactions, tvRecord;

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
        userTransactions = new ArrayList<>();

        swipeRefreshLayout = dashboardView.findViewById(R.id.home_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorSignInAccent));
        swipeRefreshLayout.setOnRefreshListener(this);
        initToolbar(dashboardView);
        RecyclerView transactionsContainer = dashboardView.findViewById(R.id.main_recyclerview);
        transactionsContainer.setLayoutManager(new LinearLayoutManager(getActivity()));

        transactionAdapter = new TransactionAdapter(getActivity(), userTransactions);
        transactionsContainer.setAdapter(transactionAdapter);

        incomeValue = dashboardView.findViewById(R.id.tv_income_value);
        expenseValue = dashboardView.findViewById(R.id.tv_expense_value);
        balance = dashboardView.findViewById(R.id.tv_balance_value);
        tvNoTransactions = dashboardView.findViewById(R.id.tv_no_trans);
        tvRecord = dashboardView.findViewById(R.id.tv_record);
        tvRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RecordTransactionActivity.class));
            }
        });

        return dashboardView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userSession = new UserSession(getActivity());
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

    public void showTransactions() {
        userTransactions.clear();
        Helper.StrictMode();
        swipeRefreshLayout.setRefreshing(true);
        TransactionResponse transactionResponse = transactionImpl.getTransactions(userSession.getUser().get_id());
        if (transactionResponse == null) {
            transactionAdapter.updateTransactionsList(userTransactions);
            swipeRefreshLayout.setRefreshing(false);
            tvNoTransactions.setVisibility(View.VISIBLE);
            tvRecord.setVisibility(View.VISIBLE);
        } else {
            swipeRefreshLayout.setRefreshing(false);
            tvNoTransactions.setVisibility(View.GONE);
            tvRecord.setVisibility(View.GONE);
            userTransactions.addAll(transactionResponse.getMyTransactions());
            transactionAdapter.updateTransactionsList(userTransactions);
        }
    }

    public void showIncomeExpense() {
        Helper.StrictMode();
        AuthImpl authImpl = new AuthImpl();
        UserResponse userResponse = authImpl.getIncomeExpense(userSession.getUser().get_id());
        double income = userResponse.getUser().getTotalIncome();
        double expense = userResponse.getUser().getTotalExpense();
        incomeValue.setText(String.valueOf(income));
        expenseValue.setText(String.valueOf(expense));
        balance.setText(String.valueOf(income - expense));
    }

    @Override
    public void onResume() {
        super.onResume();
        showIncomeExpense();
        showTransactions();
    }

    @Override
    public void onRefresh() {
        showIncomeExpense();
        showTransactions();
    }
}
