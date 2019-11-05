package com.hawahuri.expensemanager.test;

import android.app.Activity;
import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.hawahuri.expensemanager.MainActivity;
import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.utils.UserSession;

import org.junit.Rule;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class FeatJProfileStepdefs {
    @Rule
    private ActivityTestRule<MainActivity> mainTestRule = new ActivityTestRule<>(MainActivity.class);
    private Activity mainActivity;
    private UserSession userSession;

    @Before("@ViewProfile-feature")
    public void setup() {
        mainTestRule.launchActivity(new Intent());
        mainActivity = mainTestRule.getActivity();
        userSession = new UserSession(mainActivity);
    }

    @After("@ViewProfile-feature")
    public void tearDown() {
        mainTestRule.finishActivity();
    }

    @cucumber.api.java.en.Given("^I am on the homepage$")
    public void iAmOnTheHomepage() {
        assertNotNull(mainActivity);
    }

    @cucumber.api.java.en.When("^I click profile tab$")
    public void iClickProfileTab() {
        onView(withId(R.id.nav_profile)).perform(click());

    }

    @cucumber.api.java.en.Then("^i should see my details$")
    public void iShouldSeeMyDetails() {

        assertEquals(userSession.getUser().getEmail(), "hawahuri@example.com ");


    }
}
