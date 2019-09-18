package com.hawahuri.expensemanager.impl;

import com.hawahuri.expensemanager.api.CategoryAPI;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.utils.APIError;

import java.util.List;

public class CategoryImpl {

    private CategoryAPI categoryAPI;
    private APIError apiError;

    public CategoryImpl() {
        apiError = new APIError();
    }

    public Category addNewCategory(Category category) {
        return null;
    }

    public List<Category> viewDefaultCategories() {
        return null;
    }

}
