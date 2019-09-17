package com.hawahuri.expensemanager;

import com.hawahuri.expensemanager.impl.AuthImpl;
import com.hawahuri.expensemanager.models.User;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public class AuthenticationUnitTest {
    private AuthImpl authImpl;

    @Before
    public void setup() {
        authImpl = new AuthImpl();
    }

    // Sign Up Test
    @Test
    public void testSignUp_ValidDetails_ShouldCreateNewUser() {
        User user = new User("test", "test", "test@example.com", "password");
        boolean signedUp = authImpl.registerUser(user);
        assertTrue(signedUp);
    }

    @Test
    public void testSignUp_EmptyField_ShouldNotCreateNewUser() {
        User user = new User("", "test", "test@example.com", "password");
        boolean signedUp = authImpl.registerUser(user);
        assertFalse(signedUp);
    }

    @Test
    public void testSignUp_InvalidDetails_ShouldNotCreateNewUser() {
        User user = new User("t", "test", "test@example.com", "password");
        boolean signedUp = authImpl.registerUser(user);
        assertFalse(signedUp);
    }

    @Test
    public void testSignUp_ExistingEmail_ShouldNotCreateNewUser() {
        User user = new User("test", "test", "test@example.com", "password");
        boolean signedUp = authImpl.registerUser(user);
        assertFalse(signedUp);
    }

    // Sign In Test

    @Test
    public void testLogin_ValidDetails_ShouldReturnAuthUser() {

        User authUser = authImpl.loginUser("test@example.com", "password");

        assertEquals("test@example.com", authUser.getEmail());
    }

    @Test
    public void testLogin_EmptyField_ShouldDenyLogin() {

        User authUser = authImpl.loginUser(" ", "password");

        assertNull(authUser);
    }

    @Test
    public void testLogin_InvalidDetails_ShouldDenyLogin() {

        User authUser = authImpl.loginUser("rest@example.com", "password");

        assertNull(authUser);
    }

}
