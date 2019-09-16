package com.hawahuri.expensemanager;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginRegisterTest {

    //Login Test
    @Test
    public void testLoginUser() {
        LoginBLL loginBLL = new LoginBLL("ujjwal", "ujjwalnjr11");
        boolean result = loginBLL.checkLogin();
        assertEquals(true, result);
    }

    @Test
    public void testLoginFail() {
        LoginBLL loginBLL = new LoginBLL("ufdajfa", "fkakfjka");
        boolean result = loginBLL.checkLogin();
        assertEquals(false, result);
    }

    @Test
    public void testEmptyLogin() {
        LoginBLL loginBLL = new LoginBLL("", "");
        boolean result = loginBLL.checkLogin();
        assertEquals(false, result);
    }


    //Register Test
    @Test
    public void testRegister() {
        RegisterBLL registerBLL = new RegisterBLL("test", "test", "test@eg.com", "12345");
        boolean result = registerBLL.checkRegister();
        assertEquals(true, result);
    }

    @Test
    public void testInvalidRegister() {
        RegisterBLL registerBLL = new RegisterBLL("test ", "test", "test.com", "12345");
        boolean result = registerBLL.checkRegister();
        assertEquals(false, result);
    }

    @Test
    public void testEmptyRegister() {
        RegisterBLL registerBLL = new RegisterBLL(" ", "test", "test.com", "12345");
        boolean result = registerBLL.checkRegister();
        assertEquals(false, result);
    }

    @Test
    public void testExistingUser() {
        RegisterBLL registerBLL = new RegisterBLL("test", "test", "test@eg.com", "12345");
        boolean result = registerBLL.checkRegister();
        assertEquals(false, result);
    }

}
