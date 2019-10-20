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

    public CategoryResponse addNewCategory(Category category) {
        CategoryResponse categoryResponse = null;
        Call<CategoryResponse> addCategoryCall = categoryAPI.createNewCategory(category);
        try {
            Response<CategoryResponse> addCategoryResponse = addCategoryCall.execute();
            if (!addCategoryResponse.isSuccessful()) {
                apiError = gson.fromJson(addCategoryResponse.errorBody().string(), APIError.class);
                categoryListener.onError(apiError.getError());
//                return  categoryResponse;
            } else if (addCategoryResponse.body().getCategory() != null) {
                categoryResponse = addCategoryResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categoryResponse;
    }

    public List<Category> getExpenseCategories() {
        List<Category> expenseCategories = new ArrayList<>();
        Call<CategoryResponse> expenseCategoriesCall = categoryAPI.fetchExpenseCategories();
        try {
            Response<CategoryResponse> expenseCategoriesResponse = expenseCategoriesCall.execute();
            if (!expenseCategoriesResponse.isSuccessful()) {
                return expenseCategories;
            } else if (expenseCategoriesResponse.body().getCategories() != null) {
                expenseCategories = expenseCategoriesResponse.body().getCategories();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expenseCategories;
    }

    public List<Category> getIncomeCategories() {
        List<Category> incomeCategories = new ArrayList<>();
        Call<CategoryResponse> incomeCategoriesCall = categoryAPI.fetchIncomeCategories();
        try {
            Response<CategoryResponse> incomeCategoriesResponse = incomeCategoriesCall.execute();
            if (!incomeCategoriesResponse.isSuccessful()) {
                return incomeCategories;
            } else if (incomeCategoriesResponse.body().getCategories() != null) {
                incomeCategories = incomeCategoriesResponse.body().getCategories();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return incomeCategories;
    }

    public List<Category> getUserCategories(String userId) {
        List<Category> userCategories = new ArrayList<>();
        Call<CategoryResponse> userCategoriesCall = categoryAPI.fetchUserCategories(userId);
        try {
            Response<CategoryResponse> userCategoriesResponse = userCategoriesCall.execute();
            if (!userCategoriesResponse.isSuccessful()) {
                return userCategories;
            } else if (userCategoriesResponse.body().getCategories() != null) {
                userCategories = userCategoriesResponse.body().getCategories();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userCategories;
    }

    public Category getSingleCategory(String categoryId) {
        Category category = null;
        Call<CategoryResponse> getACategoryCall = categoryAPI.fetchSingleCategory(categoryId);
        try {
            Response<CategoryResponse> getCategoryResponse = getACategoryCall.execute();
            if (!getCategoryResponse.isSuccessful()) {
                return category;
            }
            if (getCategoryResponse.body().getCategory() != null) {
                category = getCategoryResponse.body().getCategory();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return category;
    }

    public CategoryResponse updateUserCategory(String categoryId, Category category) {
        CategoryResponse categoryResponse = null;
        Call<CategoryResponse> updateCategoryCall = categoryAPI.updateCategory(categoryId, category);
        try {
            Response<CategoryResponse> updateCategoryResponse = updateCategoryCall.execute();
            if (!updateCategoryResponse.isSuccessful()) {
                apiError = gson.fromJson(updateCategoryResponse.errorBody().string(), APIError.class);
                categoryListener.onError(apiError.getError());
//                return categoryResponse;
            }
            if (updateCategoryResponse.body() != null) {
                categoryResponse = updateCategoryResponse.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoryResponse;
    }

    public boolean deleteUserCategory(String categoryId) {
        boolean categoryDeleted = false;
        Call<CategoryResponse> deleteCategoryCall = categoryAPI.deleteCategory(categoryId);
        try {
            Response<CategoryResponse> deleteCategoryResponse = deleteCategoryCall.execute();
            if (!deleteCategoryResponse.isSuccessful()) {
                return categoryDeleted;
            }
            if (deleteCategoryResponse.body().getCategory() != null) {
                categoryDeleted = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categoryDeleted;
    }

    public interface CategoryListener {
        void onError(Error error);
    }

    public void setCategoryListener(CategoryListener categoryListener) {
        this.categoryListener = categoryListener;
    }

}
