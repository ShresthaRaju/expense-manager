package com.hawahuri.expensemanager.test;

import android.content.Intent;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.ui.TransactionDetailsActivity;

import org.junit.Rule;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

public class FeatHDeleteTransactionStepdefs {
    @Rule
    private ActivityTestRule<MainActivity> mainTestRule = new ActivityTestRule<>(MainActivity.class);
    private ActivityTestRule<TransactionDetailsActivity> transDetailRule = new ActivityTestRule<>(TransactionDetailsActivity.class);

    @Before("@viewTransaction-feature")
    public void setup() {
        mainTestRule.launchActivity(new Intent());
    }

    @After("@viewTransaction-feature")
    public void tearDown() {
        mainTestRule.finishActivity();
    }

    @Given("^I am on transaction details of dashboard$")
    public void iAmOnTransactionDetailsOfDashboard() {
        onView(withId(R.id.main_recyclerview)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @cucumber.api.java.en.When("^I click delete button$")
    public void iClickDeleteButton() {
        onView(withId(R.id.btn_delete_transaction)).perform(click());
    }

    @cucumber.api.java.en.And("^I confirm the deletion$")
    public void iConfirmTheDeletion() {
        onView(withText("YES")).perform(click());

    }

    @cucumber.api.java.en.Then("^i should see transaction deleted message$")
    public void iShouldSeeTransactionDeletedMessage() {
        onView(withText("Transaction successfully deleted!"))
                .inRoot(withDecorView(not(mainTestRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
        transDetailRule.finishActivity();

    }

    @cucumber.api.java.en.And("^I discard the confirmation for deletion$")
    public void iDiscardTheConfirmationForDeletion() {
        onView(withText("CANCEL")).perform(click());
    }

    @cucumber.api.java.en.Then("^I am shown the transaction details dashboard$")
    public void iAmShownTheTransactionDetailsDashboard() {
        onView(withText("Type:")).check(matches(isDisplayed()));
        onView(isRoot()).perform(ViewActions.pressBack());
    }

}
