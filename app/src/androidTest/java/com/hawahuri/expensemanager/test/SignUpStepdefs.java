package com.hawahuri.expensemanager.test;

import android.app.Activity;
import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.hawahuri.expensemanager.R;
import com.hawahuri.expensemanager.ui.SignUpActivity;

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

public class SignUpStepdefs {

    private ActivityTestRule<SignUpActivity> signUpTestRule = new ActivityTestRule<>(SignUpActivity.class);
    private Activity signUpActivity;

    @Before("@register-feature")
    public void setup() {
        signUpTestRule.launchActivity(new Intent());
        signUpActivity = signUpTestRule.getActivity();
    }

    @After("@register-feature")
    public void tearDown() {
        signUpTestRule.finishActivity();
    }

    @cucumber.api.java.en.Given("^I am on the sign up screen$")
    public void iAmOnTheSignUpScreen() {
        assertNotNull(signUpActivity);
    }

    @cucumber.api.java.en.When("^I input firstName (\\S+)$")
    public void iInputFirstNameFirstName(String firstName) {
        onView(withId(R.id.et_firstName_value)).perform(typeText(firstName));
        closeSoftKeyboard();

    }

    @cucumber.api.java.en.And("^I input familyName (\\S+)$")
    public void iInputFamilyNameFamilyName(String familyName)  {

        onView(withId(R.id.et_familyName_value)).perform(typeText(familyName));

    }

    @cucumber.api.java.en.And("^I input email (\\S+)$")
    public void iInputEmailEmail(String email) {
        onView(withId(R.id.et_sign_up_email_value)).perform(typeText(email));

    }

    @cucumber.api.java.en.And("^I input password (\\S+)$")
    public void iInputPasswordPassword(String password){
        onView(withId(R.id.et_sign_up_password_value)).perform(typeText(password));

    }

    @cucumber.api.java.en.And("^I input confirmPassword (\\S+)$")
    public void iInputConfirmPasswordConfirmPassword(String confirmPassword) {
        onView(withId(R.id.et_confirm_password_value)).perform(typeText(confirmPassword));
        closeSoftKeyboard();

    }

    @cucumber.api.java.en.And("^I click on the get started button$")
    public void iClickOnTheGetStartedButton() {
        onView(withId(R.id.btn_get_started)).perform(click());
    }

    @cucumber.api.java.en.Then("^I should see the login screen$")
    public void iShouldSeeTheLoginScreen() {
        onView(withId(R.id.tv_login)).check(matches(withText(R.string.login_to_continue)));
    }
}
