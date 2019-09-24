package com.hawahuri.expensemanager.test;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.ui.AddCategoryActivity;

import org.junit.Rule;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class FeatCExpCategoryStepdefs {

    @Rule
    private ActivityTestRule<MainActivity> mainTestRule = new ActivityTestRule<>(MainActivity.class);
    private ActivityTestRule<AddCategoryActivity> addCategoryTestRule = new ActivityTestRule<>(AddCategoryActivity.class);

    @Before("@exp-feature")
    public void setup() {
        mainTestRule.launchActivity(new Intent());
    }

    @After("@exp-feature")
    public void tearDown() {
        mainTestRule.finishActivity();
    }

    @cucumber.api.java.en.Given("^I am on the add category dashboard$")
    public void iAmOnTheAddCategoryDashboard() {
        onView(withId(R.id.nav_categories)).perform(click());
        onView(withId(R.id.def_categories_container)).check(matches(isDisplayed()));
        addCategoryTestRule.launchActivity(new Intent());
    }

    @When("^I select expense type$")
    public void iSelectExpenseType() {
        onView(withText("Expense")).perform(click());
    }

    @And("^I enter category name$")
    public void iEnterCategoryName() {
        onView(withId(R.id.et_exp_cat_value)).perform(typeText("Test Expense Category"));
    }

    @And("^I click the add button$")
    public void iClickTheAddButton() {
        onView(withId(R.id.btn_add_exp_cat)).perform(click());
    }

    @Then("^I should see category added toast$")
    public void iShouldSeeCategoryAddedToast() {
        onView(withText("Category 'Test Expense Category' added!"))
                .inRoot(withDecorView(not(mainTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        addCategoryTestRule.finishActivity();
    }

    @Then("^I should see name required message$")
    public void iShouldSeeNameRequiredMessage() {
        onView(withText("Category Name is required")).check(matches(isDisplayed()));
        addCategoryTestRule.finishActivity();
    }

    @Then("^I should see category exist message$")
    public void iShouldSeeCategoryExistMessage() {
        onView(withText("Category already added!")).check(matches(isDisplayed()));
        addCategoryTestRule.finishActivity();
    }
}