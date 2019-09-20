package com.hawahuri.expensemanager.test;

import android.app.Activity;
import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.ui.SignInActivity;

import org.junit.Rule;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;

public class SignInStepdefs {

    @Rule
    public ActivityTestRule<SignInActivity> signInTestRule = new ActivityTestRule<>(SignInActivity.class);

    private Activity signInActivity;

    @Before("@login-feature")
    public void setup() {
        signInTestRule.launchActivity(new Intent());
        signInActivity = signInTestRule.getActivity();
    }

    @After("@login-feature")
    public void tearDown() {
        signInTestRule.finishActivity();
    }

    @cucumber.api.java.en.Given("^I am on the sign in screen$")
    public void iAmOnTheSignInScreen() {
        assertNotNull(signInActivity);
    }

    @cucumber.api.java.en.When("^I enter email (\\S+)$")
    public void iEnterEmailEmail(String email)  {
        onView(withId(R.id.et_sign_in_email_value)).perform(typeText(email));

    }

    @cucumber.api.java.en.And("^I enter password (\\S+)$")
    public void iEnterPasswordPassword(String password)  {
        closeSoftKeyboard();
        onView(withId(R.id.et_sign_in_password_value)).perform(typeText((password)));
        closeSoftKeyboard();

    }

    @cucumber.api.java.en.And("^I click on the sign in button$")
    public void iClickOnTheSignInButton() {
        onView(withId(R.id.fab_sign_in)).perform(click());
    }

    @cucumber.api.java.en.Then("^I am redirected to the dashboard$")
    public void iAmRedirectedToTheDashboard() {
        onView(withId(R.id.nav_home)).check(matches(withText(R.string.home)));
    }
}
