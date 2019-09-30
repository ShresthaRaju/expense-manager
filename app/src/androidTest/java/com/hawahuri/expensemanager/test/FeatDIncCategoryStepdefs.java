package com.hawahuri.expensemanager.test;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.ui.AddCategoryActivity;

import org.junit.Rule;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class FeatDIncCategoryStepdefs {

    @Rule
    private ActivityTestRule<MainActivity> mainTestRule = new ActivityTestRule<>(MainActivity.class);
    private ActivityTestRule<AddCategoryActivity> addCategoryTestRule = new ActivityTestRule<>(AddCategoryActivity.class);

    @Before("@inc-feature")
    public void setup() {
        mainTestRule.launchActivity(new Intent());
    }

    @After("@inc-feature")
    public void tearDown() {
        mainTestRule.finishActivity();
    }

    @cucumber.api.java.en.Given("^I am on the add custom category dashboard$")
    public void iAmOnTheAddCustomCategoryDashboard() {
        onView(withId(com.hawahuri.expensemanager.R.id.nav_categories)).perform(click());
        onView(withId(R.id.def_categories_container)).check(matches(isDisplayed()));
        addCategoryTestRule.launchActivity(new Intent());
    }

    @cucumber.api.java.en.When("^I select income type$")
    public void iSelectIncomeType() {
        onView(withText("Income")).perform(click());
    }

    @cucumber.api.java.en.And("^I enter income category name$")
    public void iEnterIncomeCategoryName() {
        onView(withId(R.id.et_inc_cat_value)).perform(typeText("Test Income Category"));
    }

    @cucumber.api.java.en.And("^I click the add category button$")
    public void iClickTheAddCategoryButton() {
        onView(withId(R.id.btn_add_inc_cat)).perform(click());
    }

    @cucumber.api.java.en.Then("^I should see income category added toast$")
    public void iShouldSeeIncomeCategoryAddedToast() {
        onView(withText("Category 'Test Income Category' added!"))
                .inRoot(withDecorView(not(mainTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        addCategoryTestRule.finishActivity();
    }

    @cucumber.api.java.en.Then("^I should see category name required message$")
    public void iShouldSeeCategoryNameRequiredMessage() {
        onView(withText("Category Name is required")).check(matches(isDisplayed()));
        addCategoryTestRule.finishActivity();
    }

    @cucumber.api.java.en.Then("^I should see income category exist message$")
    public void iShouldSeeIncomeCategoryExistMessage() {
        onView(withText("Category already added!")).check(matches(isDisplayed()));
    }
}
