package com.hawahuri.expensemanager;

import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.models.Category;

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
        Category newCategory = new Category("Test Category", "Expense", "others.png", "adfa823tdsgs");
        Category addedCategory = categoryImpl.addNewCategory(newCategory);
        assertEquals(newCategory.getName(), addedCategory.getName());
    }

    @Test
    public void testCategory_EmptyName_ShouldNotAddANewCategory() {
        Category newCategory = new Category("", "Expense", "others.png", "adfa823tdsgs");
        Category addedCategory = categoryImpl.addNewCategory(newCategory);
        assertNull(addedCategory);
    }

    @Test
    public void testCategory_ExistingName_ShouldNotAddANewCategory() {
        Category newCategory = new Category("Test Category", "Expense", "others.png", "adfa823tdsgs");
        Category addedCategory = categoryImpl.addNewCategory(newCategory);
        assertNull(addedCategory);
    }

    @Test
    public void testCategory_Prepopulate_Categories() {
        List<Category> defaultCategories = categoryImpl.viewDefaultCategories();
        assertEquals(12, defaultCategories.size());
    }

}
