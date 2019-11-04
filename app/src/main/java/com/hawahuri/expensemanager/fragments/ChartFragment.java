package com.hawahuri.expensemanager.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.impl.TransactionImpl;
import com.hawahuri.expensemanager.models.TransactionR;
import com.hawahuri.expensemanager.response.TransactionResponse;
import com.hawahuri.expensemanager.utils.Helper;
import com.hawahuri.expensemanager.utils.UserSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChartFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private PieChart pieChart;
    private UserSession userSession;
    private TransactionImpl transactionImpl;
    private HashMap<String, Double> myTransactions;

    public ChartFragment() {

    }

    public static ChartFragment newInstance(String toolbarTitle) {
        ChartFragment chartFragment = new ChartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, toolbarTitle);
        chartFragment.setArguments(args);
        return chartFragment;
    }

    private void initToolbar(View view) {
        Toolbar homeToolbar = view.findViewById(R.id.chart_toolbar);
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
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        initToolbar(view);

        pieChart = view.findViewById(R.id.transactions_piechart);
        return view;
    }

    public void drawChart() {

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(58f);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDrawCenterText(true);
        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        pieChart.animateY(1200, Easing.EaseInOutQuad);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setTextSize(14f);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(12f);

        ArrayList<PieEntry> pieChartArrayList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : myTransactions.entrySet()) {
            pieChartArrayList.add(new PieEntry(entry.getValue().floatValue(), entry.getKey()+""));
        }

        PieDataSet pieDataSet = new PieDataSet(pieChartArrayList, "My Transactions");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieData.setValueTextSize(15f);
        pieData.setValueTypeface(Typeface.DEFAULT_BOLD);
        pieData.setValueTextColor(Color.WHITE);

        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userSession = new UserSession(getActivity());
        transactionImpl = new TransactionImpl();
        myTransactions = new HashMap<>();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_chart, menu);

        MenuItem menuItem = menu.findItem(R.id.sp_trans_type);
        Spinner spinner = (Spinner) menuItem.getActionView();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.transactions_type, R.layout.spinner_trans_type);
        adapter.setDropDownViewResource(R.layout.spinner_trans_type);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String transType = String.valueOf(parent.getItemAtPosition(position));
                if (transType.equals("Expense")) {
                    getExpenseTransactions();
                } else if (transType.equals("Income")) {
                    getIncomeTransactions();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Nothing selected!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getExpenseTransactions() {
        myTransactions.clear();
        Helper.StrictMode();
        TransactionResponse expenses = transactionImpl.getExpenseTransactions(userSession.getUser().get_id());
        if (expenses != null) {
            for (TransactionR transaction : expenses.getMyTransactions()) {
                String key = transaction.getCategory().getName();
                if (myTransactions.containsKey(key)) {
                    myTransactions.put(key, myTransactions.get(key) + transaction.getAmount());
                } else {
                    myTransactions.put(key, transaction.getAmount());
                }
            }

            drawChart();
        }
    }

    private void getIncomeTransactions() {
        myTransactions.clear();
        Helper.StrictMode();
        TransactionResponse incomes = transactionImpl.getIncomeTransactions(userSession.getUser().get_id());
        if (incomes != null) {
            for (TransactionR transaction : incomes.getMyTransactions()) {
                String key = transaction.getCategory().getName();
                if (myTransactions.containsKey(key)) {
                    myTransactions.put(key, myTransactions.get(key) + transaction.getAmount());
                } else {
                    myTransactions.put(key, transaction.getAmount());
                }
            }

            drawChart();
        }
    }
}
