package com.hawahuri.expensemanager.api;

import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.response.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CategoryAPI {

    @POST("categories")
    Call<CategoryResponse> createNewCategory(@Body Category category);

}
