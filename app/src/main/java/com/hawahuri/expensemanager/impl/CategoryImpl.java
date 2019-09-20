package com.hawahuri.expensemanager.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hawahuri.expensemanager.api.CategoryAPI;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.models.Error;
import com.hawahuri.expensemanager.response.CategoryResponse;
import com.hawahuri.expensemanager.utils.APIError;
import com.hawahuri.expensemanager.utils.RetrofitClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CategoryImpl {

    private Gson gson;
    private CategoryAPI categoryAPI;
    private APIError apiError;
    private CategoryListener categoryListener;

    public CategoryImpl() {
        categoryAPI = RetrofitClient.getInstance().create(CategoryAPI.class);
        gson = new GsonBuilder().create();
        apiError = new APIError();
    }

    public Category addNewCategory(Category category) {
        Category addcategory = null;
        Call<CategoryResponse> categoryResponseCall = categoryAPI.createNewCategory(category);
        try {
            Response<CategoryResponse> categoryResponses = categoryResponseCall.execute();
            if (!categoryResponses.isSuccessful()) {
                apiError = gson.fromJson(categoryResponses.errorBody().string(), APIError.class);
                categoryListener.onError(apiError.getError());
//                return  addcategory;
            } else if (categoryResponses.body().getCategory() != null) {
                addcategory = categoryResponses.body().getCategory();
//                addcategory=categoryResponses.body().getCategory();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return addcategory;
    }

    public List<Category> viewAllCategories() {
        List<Category> allCategories = new ArrayList<>();
        Call<CategoryResponse> catcall = categoryAPI.getAllCategories();
        try {
            Response<CategoryResponse> categoryResponse = catcall.execute();
            if (!categoryResponse.isSuccessful()) {
                return allCategories;
            } else if (categoryResponse.body().getCategories() != null) {
                allCategories = categoryResponse.body().getCategories();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allCategories;
    }

    public interface CategoryListener {
        void onError(Error error);
    }


}
