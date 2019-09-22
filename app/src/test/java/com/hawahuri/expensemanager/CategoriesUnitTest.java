package com.hawahuri.expensemanager;

import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.response.CategoryResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class CategoriesUnitTest {

    private CategoryImpl categoryImpl;

    @Before
    public void setup() {
        categoryImpl = new CategoryImpl();
    }

    @Test
    public void testCategory_ValidName_ShouldAddNewACategory() {
        Category newCategory = new Category("Test Category", "Expense", "others.png", "5d87173613079e1a6c591e1e");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertEquals(newCategory.getName(), categoryResponse.getCategory().getName());
    }

    @Test
    public void testCategory_EmptyName_ShouldNotAddANewCategory() {
        Category newCategory = new Category("", "Expense", "others.png", "5d87173613079e1a6c591e1e");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testCategory_ExistingName_ShouldNotAddANewCategory() {
        Category newCategory = new Category("Test Category", "Expense", "others.png", "5d87173613079e1a6c591e1e");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testCategory_DefExpense_Categories() {
        List<Category> expCategories = categoryImpl.getExpenseCategories();
        assertEquals(12, expCategories.size());
    }

    @Test
    public void testCategory_DefIncome_Categories() {
        List<Category> incCategories = categoryImpl.getIncomeCategories();
        assertEquals(4, incCategories.size());
    }

}
