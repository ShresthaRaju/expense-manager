package com.hawahuri.expensemanager.test;

import android.content.Intent;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;

import org.junit.Rule;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class FeatGEditTransactionStepdefs {
    @Rule
    private ActivityTestRule<MainActivity> mainTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before("@viewTransaction-features")
    public void setup() {
        mainTestRule.launchActivity(new Intent());
    }

    @After("@viewTransaction-features")
    public void tearDown() {
        mainTestRule.finishActivity();
    }

    @cucumber.api.java.en.Given("^I am on transaction details dashboard$")
    public void iAmOnTransactionDetailsDashboard() {
        onView(withId(R.id.main_recyclerview)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @cucumber.api.java.en.When("^I click edit button$")
    public void iClickEditButton() {
        onView(withId(R.id.nav_delete_trans)).perform(click());
    }

    @cucumber.api.java.en.And("^I should update all the details of the transaction$")
    public void iShouldUpdateAllTheDetailsOfTheTransaction() {

        onView(withId(R.id.et_memo_value)).perform(clearText());
        onView(withId(R.id.et_memo_value)).perform(typeText("Test Edit Memo"));
        closeSoftKeyboard();

    }


    @And("^I should click on save button$")
    public void iShouldClickOnSaveButton() {
        onView(withId(R.id.btn_add)).perform(click());
    }

    @cucumber.api.java.en.And("^I am taken to to the dashboard$")
    public void iAmTakenToToTheDashboard() {
        onView(withText("Type:")).check(matches(isDisplayed()));
        onView(isRoot()).perform(ViewActions.pressBack());
    }


    @And("^I leave a flied empty$")
    public void iLeaveAFliedEmpty() {
        onView(withId(R.id.et_amount_value)).perform(clearText());

    }

    @And("^I should see an  error message$")
    public void iShouldSeeAnErrorMessage() {
        onView(withText("Amount is required")).check(matches(isDisplayed()));
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(isRoot()).perform(ViewActions.pressBack());
    }
}
