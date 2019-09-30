package com.hawahuri.expensemanager;

import com.hawahuri.expensemanager.impl.CategoryImpl;
import com.hawahuri.expensemanager.models.Category;
import com.hawahuri.expensemanager.response.CategoryResponse;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CategoriesUnitTest {

    private CategoryImpl categoryImpl;

    @Before
    public void setup() {
        categoryImpl = new CategoryImpl();
    }

    @Test
    public void testA_emptyExpCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("", "Expense", "others.png", "5d87173613079e1a6c591e1e");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testB_validExpCatName_shouldAddNewACategory() {
        Category newCategory = new Category("Test Expense", "Expense", "others.png", "5d87173613079e1a6c591e1e");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertEquals(newCategory.getName(), categoryResponse.getCategory().getName());
    }

    @Test
    public void testC_existingExpCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("Test Expense", "Expense", "others.png", "5d87173613079e1a6c591e1e");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testD_emptyIncCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("", "Income", "others.png", "5d87173613079e1a6c591e1e");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testE_validIncCatName_shouldAddNewACategory() {
        Category newCategory = new Category("Test Income", "Income", "others.png", "5d87173613079e1a6c591e1e");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertEquals(newCategory.getName(), categoryResponse.getCategory().getName());
    }

    @Test
    public void testF_existingIncCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("Test Income", "Income", "others.png", "5d87173613079e1a6c591e1e");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testG_defaultExpenseCategoriesSize() {
        List<Category> expCategories = categoryImpl.getExpenseCategories();
        assertEquals(12, expCategories.size());
    }

    @Test
    public void testH_defaultIncomeCategoriesSize() {
        List<Category> incCategories = categoryImpl.getIncomeCategories();
        assertEquals(4, incCategories.size());
    }

}
