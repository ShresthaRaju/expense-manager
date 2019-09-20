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
import com.google.android.material.textfield.TextInputLayout;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.utils.Helper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpensesFragmentCategory extends Fragment {
    TextInputEditText name;
    Button btnCategory;
    private CategoryImpl categoryImpl;


    public ExpensesFragmentCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expenses_category, container, false);
        name = view.findViewById(R.id.expname);
        btnCategory = view.findViewById(R.id.button);
        categoryImpl = new CategoryImpl();
        btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcategory();
            }
        });


        return view;
    }

    private void addcategory() {
        Helper.StrictMode();
        String catname = name.getText().toString().trim();
        Category category = new Category(catname, "Income", "5d83a78b66cb4c144c1e8b88");
        if (categoryImpl.addNewCategory(category)!=null) {
            Toast.makeText(getActivity(), "Successfully Added", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }


    }

}
