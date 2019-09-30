package com.hawahuri.expensemanager;

import com.hawahuri.expensemanager.impl.AuthImpl;
import com.hawahuri.expensemanager.models.User;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AuthenticationUnitTest {
    private AuthImpl authImpl;

    @Before
    public void setup() {
        authImpl = new AuthImpl();
    }

    // Sign Up Test

    @Test
    public void testA_emptySignUpInput_shouldNotCreateANewUser() {
        User user = new User("", "Test", "unittest@example.com", "password");
        boolean signedUp = authImpl.registerUser(user);
        assertFalse(signedUp);
    }

    @Test
    public void testB_invalidSignUpInputs_shouldNotCreateANewUser() {
        User user = new User("U", "Test", "unittest@example.com", "password");
        boolean signedUp = authImpl.registerUser(user);
        assertFalse(signedUp);
    }

    @Test
    public void testC_validSignUpInputs_shouldCreateANewUser() {
        User user = new User("Unit", "Test", "unittest@example.com", "password");
        boolean signedUp = authImpl.registerUser(user);
        assertTrue(signedUp);
    }

    @Test
    public void testD_existingEmail_shouldNotCreateANewUser() {
        User user = new User("New", "Test", "unittest@example.com", "password");
        boolean signedUp = authImpl.registerUser(user);
        assertFalse(signedUp);
    }

    // Sign In Test

    @Test
    public void testE_emptySignInInputs_shouldDenyLogin() {

        User authUser = authImpl.loginUser("", "password");

        assertNull(authUser);
    }

    @Test
    public void testF_invalidSignInInputs_shouldDenyLogin() {

        User authUser = authImpl.loginUser("unittest@example.com", "passwords");

        assertNull(authUser);
    }

    @Test
    public void testG_validSignInInputs_shouldReturnAuthUser() {

        User authUser = authImpl.loginUser("unittest@example.com", "password");

        assertEquals("unittest@example.com", authUser.getEmail());
    }

}
