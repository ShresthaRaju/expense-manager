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
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CategoriesUnitTest {

    private CategoryImpl categoryImpl;

    @Before
    public void setup() {
        categoryImpl = new CategoryImpl();
    }

    @Test
    public void testA_emptyExpCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("", "Expense", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testB_validExpCatName_shouldAddNewACategory() {
        Category newCategory = new Category("Test Expense", "Expense", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertEquals(newCategory.getName(), categoryResponse.getCategory().getName());
    }

    @Test
    public void testC_existingExpCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("Test Expense", "Expense", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testD_emptyIncCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("", "Income", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testE_validIncCatName_shouldAddNewACategory() {
        Category newCategory = new Category("Test Income", "Income", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryImpl.addNewCategory(newCategory);
        assertEquals(newCategory.getName(), categoryResponse.getCategory().getName());
    }

    @Test
    public void testF_existingIncCatName_shouldNotAddANewCategory() {
        Category newCategory = new Category("Test Income", "Income", "others.png", "5da6c2393cc60c2cb021cdcb");
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

    @Test
    public void testI_validId_shouldUpdateCategory() {
        String categoryId = "5dad09f22194501f788ca86c";
        String newCategoryName = "Category Update Test";
        Category newCategory = new Category(newCategoryName, "Expense", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse updatedCategory = categoryImpl.updateUserCategory(categoryId, newCategory);
        assertEquals(newCategoryName, updatedCategory.getCategory().getName());
    }

    @Test
    public void testJ_invalidId_shouldReturnNull() {
        String categoryId = "5dad08492194501f788ca789";
        Category newCategory = new Category("Category Update Test", "Expense", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse updatedCategory = categoryImpl.updateUserCategory(categoryId, newCategory);
        assertNull(updatedCategory);
    }

    @Test
    public void testK_emptyCatName_shouldDenyUpdate() {
        String categoryId = "5dad09f22194501f788ca86c";
        Category newCategory = new Category("", "Income", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryImpl.updateUserCategory(categoryId, newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testL_existingCatName_shouldDenyUpdate() {
        String categoryId = "5dad09f22194501f788ca86c";
        Category newCategory = new Category("Rental", "Income", "others.png", "5da6c2393cc60c2cb021cdcb");
        CategoryResponse categoryResponse = categoryImpl.updateUserCategory(categoryId, newCategory);
        assertNull(categoryResponse);
    }

    @Test
    public void testM_validId_shouldDeleteUserCategory() {
        String categoryId = "5dad09f22194501f788ca86c";
        boolean categoryDeleted = categoryImpl.deleteUserCategory(categoryId);
        assertTrue(categoryDeleted);
    }

    @Test
    public void testN_invalidId_shouldReturnFalse() {
        String categoryId = "5dad09f22194501f788ca86c";
        boolean categoryDeleted = categoryImpl.deleteUserCategory(categoryId);
        assertFalse(categoryDeleted);
    }

}
