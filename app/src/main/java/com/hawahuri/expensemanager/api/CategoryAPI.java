package com.hawahuri.expensemanager.api;

import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryAPI {

    //    add a new category
    @POST("categories")
    Call<CategoryResponse> createNewCategory(@Body Category category);

    //    get all the default expense categories
    @GET("categories/expense")
    Call<CategoryResponse> fetchExpenseCategories();

    //    get all the default income categories
    @GET("categories/income")
    Call<CategoryResponse> fetchIncomeCategories();

    //    get all the user's categories
    @GET("categories/users/{userId}")
    Call<CategoryResponse> fetchUserCategories(@Path("userId") String userId);

    // get a single category
    @GET("categories/{id}")
    Call<CategoryResponse> fetchSingleCategory(@Path("id") String categoryId);

    // update a category
    @PUT("categories/{id}")
    Call<CategoryResponse> updateCategory(@Path("id") String categoryId, @Body Category category);

    // delete a category
    @DELETE("categories/{id}")
    Call<CategoryResponse> deleteCategory(@Path("id") String categoryId);
}
