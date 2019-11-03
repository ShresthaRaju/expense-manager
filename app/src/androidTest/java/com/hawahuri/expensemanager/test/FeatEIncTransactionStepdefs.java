package com.hawahuri.expensemanager.test;

import android.app.Activity;
import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.ui.RecordTransactionActivity;

import org.junit.Rule;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.not;

public class FeatEIncTransactionStepdefs {
    @Rule
    private ActivityTestRule<MainActivity> mainTestRule = new ActivityTestRule<>(MainActivity.class);
    private Activity mainActivity;
    private ActivityTestRule<RecordTransactionActivity> transactionActivityTestRule = new ActivityTestRule<>(RecordTransactionActivity.class);

    @Before("@incomeTransaction-feature")
    public void setup() {
        mainTestRule.launchActivity(new Intent());
        mainActivity = mainTestRule.getActivity();
    }

    @After("@incomeTransaction-feature")
    public void tearDown() {
        mainTestRule.finishActivity();
    }


    @cucumber.api.java.en.Given("^I am on the dashboard$")
    public void iAmOnTheDashboard() {
        assertNotNull(mainActivity);
    }

    @When("^I hit the add button$")
    public void iHitTheAddButton() {
        transactionActivityTestRule.launchActivity(new Intent());
    }

    @cucumber.api.java.en.And("^I choose the category type$")
    public void iChooseTheCategoryType() {
        onView(withText("Salary")).perform(click());
    }

    @And("^I input a memo$")
    public void iInputAMemo() {
        onView(withId(R.id.et_memo_value)).perform(typeText("Test Income Trans"));
    }

    @And("^I do not enter an income amount$")
    public void iDoNotEnterAnIncomeAmount() {
        onView(withId(R.id.et_amount_value)).perform(typeText(""));
        closeSoftKeyboard();
    }

    @And("^I hit the add button on income$")
    public void iHitTheAddButtonOnIncome() {
        onView(withId(R.id.btn_add)).perform(click());
    }

    @Then("^I receive an error message$")
    public void iReceiveAnErrorMessage() {
        onView(withText("Amount is required")).check(matches(isDisplayed()));
        transactionActivityTestRule.finishActivity();
    }

    @And("^I input an income amount$")
    public void iInputAnIncomeAmount() {
        onView(withId(R.id.et_amount_value)).perform(typeText("150"));
        closeSoftKeyboard();
    }

    @Then("^I should see the added income transaction in the dashboard$")
    public void iShouldSeeTheAddedIncomeTransactionInTheDashboard() {
        onView(withText("Transaction recorded!"))
                .inRoot(withDecorView(not(mainTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        transactionActivityTestRule.finishActivity();
    }
}
