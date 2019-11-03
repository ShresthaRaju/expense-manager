package com.hawahuri.expensemanager.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.api.AuthAPI;
import com.hawahuri.expensemanager.impl.AuthImpl;
import com.hawahuri.expensemanager.impl.TransactionImpl;
import com.hawahuri.expensemanager.models.User;
import com.hawahuri.expensemanager.response.TransactionResponse;
import com.hawahuri.expensemanager.response.UserResponse;
import com.hawahuri.expensemanager.utils.UserSession;

import java.util.ArrayList;

public class ChartFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private PieChart pieChart;
    private UserSession userSession;
    private AuthImpl authImpl;
    private UserResponse userResponse;

    private TransactionResponse transactionResponse;
    private TransactionImpl transactionImpl;



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
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        pieChart = view.findViewById(R.id.piechart_transcation);

        userSession = new UserSession(getActivity());
        userResponse = new UserResponse();
        authImpl = new AuthImpl();

        transactionResponse=new TransactionResponse();
        transactionImpl=new TransactionImpl();

        Chart();
        return view;
    }

    public void Chart() {
//        userResponse = authImpl.getIncomeExpense(userSession.getUser().get_id());
//
//        transactionResponse=transactionImpl.getTransactions(userSession.getUser().get_id());
//
//
//        pieChart.setUsePercentValues(true);
//        pieChart.getDescription().setEnabled(false);
//        pieChart.setExtraOffsets(5, 10, 5, 5);
//
//        pieChart.setDragDecelerationFrictionCoef(0.95f);
//
//        pieChart.setDrawHoleEnabled(true);
//        pieChart.setHoleColor(Color.WHITE);
//        pieChart.setTransparentCircleRadius(61f);
//
//        ArrayList<PieEntry> pieChartArrayList = new ArrayList<>();
////        float income = Float.valueOf(String.valueOf(userResponse.getUser().getTotalIncome()));
////        float expense = Float.valueOf(String.valueOf(userResponse.getUser().getTotalExpense()));
////        float fexpense = Float.valueOf(String.valueOf(transactionResponse.getTransaction().getAmount()));
//        String sexpense= transactionResponse.getMyTransactions().toString();
//        pieChartArrayList.add(new PieEntry(250, sexpense));
//        pieChart.animateY(1000, Easing.EaseInOutCubic);
//
//        PieDataSet pieDataSet = new PieDataSet(pieChartArrayList, "Transactions");
//        pieDataSet.setSliceSpace(3f);
//        pieDataSet.setSelectionShift(5f);
//        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//
//        PieData pieData = new PieData(pieDataSet);
//        pieData.setValueTextSize(10f);
//        pieData.setValueTextColor(Color.YELLOW);
//
//        pieChart.setData(pieData);

        userResponse = authImpl.getIncomeExpense(userSession.getUser().get_id());

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> pieChartArrayList = new ArrayList<>();
        float income = Float.valueOf(String.valueOf(userResponse.getUser().getTotalIncome()));
        float expense = Float.valueOf(String.valueOf(userResponse.getUser().getTotalExpense()));
        pieChartArrayList.add(new PieEntry(income, "Income"));
        pieChartArrayList.add(new PieEntry(expense, "Expense"));

        pieChart.animateY(1000, Easing.EaseInOutCubic);

        PieDataSet pieDataSet = new PieDataSet(pieChartArrayList, "Transactions");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.YELLOW);

        pieChart.setData(pieData);
    }

}
