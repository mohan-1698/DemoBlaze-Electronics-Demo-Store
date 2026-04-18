package com.srm.hackathon.demoblaze.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.hackathon.demoblaze.base.BaseTest;
import com.srm.hackathon.demoblaze.pages.LoginModalPage;
import com.srm.hackathon.demoblaze.utils.ConfigReader;

public class AuthTest extends BaseTest {

    @Test(priority = 1, description = "Verify successful user registration")
    public void verifyUserRegistration() {

        LoginModalPage loginPage = new LoginModalPage();

        String baseUsername = ConfigReader.getProperty("baseUsername");
        String password = ConfigReader.getProperty("password");

        String alertMessage = loginPage.registerUser(baseUsername, password);

        Assert.assertTrue(
                alertMessage.contains("Sign up successful") ||
                alertMessage.contains("This user already exist"),
                "Signup failed. Actual message: " + alertMessage
        );
    }

    @Test(priority = 2, description = "Verify successful login")
    public void verifySuccessfulLogin() {

        LoginModalPage loginPage = new LoginModalPage();

        String username = ConfigReader.getProperty("validUsername");
        String password = ConfigReader.getProperty("validPassword");

        loginPage.login(username, password);

        Assert.assertTrue(
                loginPage.isUserLoggedIn(),
                "Login failed. User not visible in navbar"
        );
    }

    @Test(priority = 3, description = "Verify login failure with invalid credentials")
    public void verifyLoginFailure() {

        LoginModalPage loginPage = new LoginModalPage();

        String invalidUser = ConfigReader.getProperty("invalidUsername");
        String invalidPass = ConfigReader.getProperty("invalidPassword");

        String alertMessage = loginPage.loginAndGetError(invalidUser, invalidPass);

        // 📸 TAKE SCREENSHOT HERE (MANDATORY STEP)
        com.srm.hackathon.demoblaze.utils.ScreenshotUtils
                .captureScreenshot("LoginFailure");

        Assert.assertTrue(
                alertMessage.contains("Wrong password") ||
                alertMessage.contains("User does not exist"),
                "Unexpected alert message: " + alertMessage
        );
    }

    @Test(priority = 4, description = "Verify logout functionality")
    public void verifyLogout() {

        LoginModalPage loginPage = new LoginModalPage();

        String username = ConfigReader.getProperty("validUsername");
        String password = ConfigReader.getProperty("validPassword");

        loginPage.login(username, password);

        loginPage.logout();

        Assert.assertTrue(
                loginPage.isUserLoggedOut(),
                "Logout failed. Login button not visible"
        );
    }
}