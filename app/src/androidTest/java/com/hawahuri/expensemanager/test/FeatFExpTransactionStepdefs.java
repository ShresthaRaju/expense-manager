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

public class FeatFExpTransactionStepdefs {
    @Rule
    private ActivityTestRule<MainActivity> mainTestRule = new ActivityTestRule<>(MainActivity.class);
    private Activity mainActivity;
    private ActivityTestRule<RecordTransactionActivity> transrule = new ActivityTestRule<>(RecordTransactionActivity.class);

    @Before("@expenseTransaction-feature")
    public void setup() {
        mainTestRule.launchActivity(new Intent());
        mainActivity = mainTestRule.getActivity();
    }

    @After("@expenseTransaction-feature")
    public void tearDown() {
        mainTestRule.finishActivity();
    }

    @cucumber.api.java.en.Given("^I am on main dashboard$")
    public void iAmOnMainDashboard() {
        assertNotNull(mainActivity);

    }

    @cucumber.api.java.en.When("^I click on add button on dashboard$")
    public void iClickOnAddButtonOnDashboard() {
//        onView(withId(R.id.fab_new_transaction)).perform(click());
        transrule.launchActivity(new Intent());
    }

    @cucumber.api.java.en.And("^I choose the category type for expense$")
    public void iChooseTheCategoryTypeForExpense() {
        onView(withText("Food")).perform(click());
    }

    @cucumber.api.java.en.And("^I enter a memo$")
    public void iEnterAMemo() {
        onView(withId(R.id.et_memo_value)).perform(typeText("Test Expense Trans"));
    }

    @cucumber.api.java.en.And("^I enter a expense amount$")
    public void iEnterAExpenseAmount() {
        onView(withId(R.id.et_amount_value)).perform(typeText("150"));
        closeSoftKeyboard();
    }

    @cucumber.api.java.en.And("^I click on add button$")
    public void iClickOnAddButton() {
        onView(withId(R.id.btn_add)).perform(click());
    }

    @cucumber.api.java.en.Then("^I should see the added message$")
    public void iShouldSeeTheAddedMessage() {
        onView(withText("Transaction recorded!"))
                .inRoot(withDecorView(not(mainTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @And("^I do not enter a expense amount$")
    public void iDoNotEnterAExpenseAmount() {
        onView(withId(R.id.et_amount_value)).perform(typeText(""));
        closeSoftKeyboard();
    }

    @Then("^I should see error message$")
    public void iShouldSeeErrorMessage() {
        onView(withText("Amount is required")).check(matches(isDisplayed()));
        transrule.finishActivity();
    }

    @And("^I do not enter a memo$")
    public void iDoNotEnterAMemo() {
        onView(withId(R.id.et_memo_value)).perform(typeText(""));
        closeSoftKeyboard();
    }

    @Then("^I should see error message for memo$")
    public void iShouldSeeErrorMessageForMemo() {
        onView(withText("Memo is required")).check(matches(isDisplayed()));
        transrule.finishActivity();
    }
}
