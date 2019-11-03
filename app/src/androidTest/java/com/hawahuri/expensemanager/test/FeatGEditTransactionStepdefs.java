package com.hawahuri.expensemanager.test;

import android.app.Activity;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.ui.RecordTransactionActivity;
import com.hawahuri.expensemanager.ui.TransactionDetailsActivity;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNot;
import org.junit.Rule;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static org.hamcrest.Matchers.not;

public class FeatGEditTransactionStepdefs {
    @Rule
    private ActivityTestRule<MainActivity> mainTestRule = new ActivityTestRule<>(MainActivity.class);
    private ActivityTestRule<TransactionDetailsActivity> transDetailRule = new ActivityTestRule<>(TransactionDetailsActivity.class);
    private ActivityTestRule<RecordTransactionActivity> recordTransRule = new ActivityTestRule<>(RecordTransactionActivity.class);
    private Activity TransDetailActivity;


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

//        onView(withId(R.id.tv_trans_amount)).perform(click());
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
      //  Espresso.onView(ViewMatchers.withText("Transaction updated!")).inRoot(RootMatchers.withDecorView().getWindow().getDecorView()))).check(matches(isDisplayed()));
       // onView(isRoot()).perform(ViewActions.pressBack());
//        Espresso.onView(ViewMatchers.withText("Transaction updated!")).inRoot(RootMatchers.withDecorView(IsNot.not(Matchers.is(recordTransRule.getActivity().getWindow().getDecorView())))).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
//        transDetailRule.finishActivity();
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
