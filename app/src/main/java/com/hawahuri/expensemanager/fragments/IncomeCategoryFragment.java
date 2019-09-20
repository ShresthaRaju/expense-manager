package com.hawahuri.expensemanager.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.utils.Helper;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeCategoryFragment extends Fragment {
    EditText incomename;
    Button addincomecat;
    private CategoryImpl categoryImp;


    public IncomeCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income_category, container, false);
        incomename = view.findViewById(R.id.etincomename);
        addincomecat = view.findViewById(R.id.addincomebutton);
        categoryImp = new CategoryImpl();
        addincomecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addincomecat();
            }
        });

        return view;

    }

    private void addincomecat() {
        Helper.StrictMode();
        String name = incomename.getText().toString().trim();
        Category category = new Category(name, "Expense", "5d83a78b66cb4c144c1e8b88");
        if (categoryImp.addNewCategory(category)!=null) {
            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

        }
    }

}
