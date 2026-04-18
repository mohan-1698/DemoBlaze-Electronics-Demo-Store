package com.srm.hackathon.demoblaze.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.hackathon.demoblaze.base.BaseTest;
import com.srm.hackathon.demoblaze.pages.LoginModalPage;
import com.srm.hackathon.demoblaze.utils.ConfigReader;

public class AuthTest extends BaseTest {

    private LoginModalPage login() {
        LoginModalPage loginPage = new LoginModalPage();

        loginPage.login(
                ConfigReader.getProperty("validUsername"),
                ConfigReader.getProperty("validPassword")
        );

        return loginPage;
    }

    @Test(priority = 1, description = "Verify successful user registration")
    public void verifyUserRegistration() {

        LoginModalPage loginPage = new LoginModalPage();

        String alertMessage = loginPage.registerUser(
                ConfigReader.getProperty("baseUsername"),
                ConfigReader.getProperty("password")
        );

        Assert.assertTrue(
                alertMessage.contains("Sign up successful") ||
                alertMessage.contains("This user already exist"),
                "Signup failed. Actual message: " + alertMessage
        );
    }

    @Test(priority = 2, description = "Verify successful login")
    public void verifySuccessfulLogin() {

        LoginModalPage loginPage = login();

        Assert.assertTrue(
                loginPage.isUserLoggedIn(),
                "Login failed. User not visible in navbar"
        );
    }

    @Test(priority = 3, description = "Verify login failure with invalid credentials")
    public void verifyLoginFailure() {

        System.out.println("[TEST] Verifying invalid login scenario");

        LoginModalPage loginPage = new LoginModalPage();

        String alertMessage = loginPage.loginAndGetError(
                ConfigReader.getProperty("invalidUsername"),
                ConfigReader.getProperty("invalidPassword")
        );

        // Screenshot is already handled inside Page

        Assert.assertTrue(
                alertMessage.isEmpty() ||
                alertMessage.contains("Wrong password") ||
                alertMessage.contains("User does not exist"),
                "Unexpected alert message: " + alertMessage
        );
    }

    @Test(priority = 4, description = "Verify logout functionality")
    public void verifyLogout() {

        LoginModalPage loginPage = login();

        loginPage.logout();

        Assert.assertTrue(
                loginPage.isUserLoggedOut(),
                "Logout failed. Login button not visible"
        );
    }
}