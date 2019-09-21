package com.hawahuri.expensemanager.response;

import com.hawahuri.expensemanager.models.Category;

import java.util.List;

public class CategoryResponse {

    private List<Category> categories;
    private Category category;
    private String message;

    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategory() {
        return category;
    }

    public String getMessage() {
        return message;
    }
}
