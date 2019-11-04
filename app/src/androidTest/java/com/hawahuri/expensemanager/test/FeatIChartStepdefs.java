package com.hawahuri.expensemanager.test;


import android.app.Activity;
import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;

import org.junit.Rule;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;

public class FeatIChartStepdefs {
    @Rule
    private ActivityTestRule<MainActivity> mainTestRule = new ActivityTestRule<>(MainActivity.class);
    private Activity mainActivity;

    @Before("@ViewChart-feature")
    public void setup() {
        mainTestRule.launchActivity(new Intent());
        mainActivity = mainTestRule.getActivity();
    }

    @After("@ViewChart-feature")
    public void tearDown() {
        mainTestRule.finishActivity();
    }

    @cucumber.api.java.en.Given("^I am on dashboard$")
    public void iAmOnDashboard() {
        assertNotNull(mainActivity);

    }

    @cucumber.api.java.en.When("^I click charts tab$")
    public void iClickChartsTab() {
        onView(withId(R.id.nav_chart)).perform(click());

    }

    @cucumber.api.java.en.And("^I select on expense type$")
    public void iSelectOnExpenseType() {
        onView(withId(R.id.sp_trans_type)).perform(click());
        onView(withText("Expense")).perform(click());

    }

    @cucumber.api.java.en.Then("^i should see an expense pie chart$")
    public void iShouldSeeAnExpensePieChart() {
        onView(withText("Charts")).check(matches(isDisplayed()));

    }

    @cucumber.api.java.en.And("^I select on income type$")
    public void iSelectOnIncomeType() {
        onView(withId(R.id.sp_trans_type)).perform(click());
        onView(withText("Income")).perform(click());
    }

    @cucumber.api.java.en.Then("^i should see an income pie chart$")
    public void iShouldSeeAnIncomePieChart() {

        onView(withText("Charts")).check(matches(isDisplayed()));
    }
}
