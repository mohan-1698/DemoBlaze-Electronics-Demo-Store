package com.srm.hackathon.demoblaze.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.hackathon.demoblaze.base.BaseTest;
import com.srm.hackathon.demoblaze.pages.LoginModalPage;
import com.srm.hackathon.demoblaze.utils.ConfigReader;

public class FormValidationTest extends BaseTest {

    // ✅ 1. Existing user signup validation
    @Test(priority = 1)
    public void verifySignupWithExistingUser() {

        LoginModalPage loginPage = new LoginModalPage();

        String username = ConfigReader.getProperty("validUsername");
        String password = ConfigReader.getProperty("validPassword");

        String alert = loginPage.registerUserWithUsername(username, password);

        Assert.assertTrue(
                alert.contains("This user already exist"),
                "Expected duplicate user alert, got: " + alert
        );
    }

    // ✅ 2. Empty username login validation
    @Test(priority = 2)
    public void verifyLoginWithEmptyUsername() {

        LoginModalPage loginPage = new LoginModalPage();

        String password = ConfigReader.getProperty("validPassword");

        String alert = loginPage.loginWithEmptyUsername(password);

        Assert.assertTrue(
                alert.isEmpty() || alert.contains("Please fill") || alert.contains("User does not exist"),
                "Unexpected alert behavior: " + alert
        );
    }

    // ✅ 3. Modal retains entered data
    @Test(priority = 3)
    public void verifyModalRetainsData() {

        LoginModalPage loginPage = new LoginModalPage();

        loginPage.clickSignUp();
        loginPage.waitForSignUpModal();

        String testUser = "TestUser123";
        String testPass = "TestPass123";

        loginPage.enterUsername(testUser);
        loginPage.enterPassword(testPass);

        // 🔥 Verify values are retained
        String actualUser = loginPage.getEnteredUsername();
        String actualPass = loginPage.getEnteredPassword();

        Assert.assertEquals(actualUser, testUser, "Username not retained");
        Assert.assertEquals(actualPass, testPass, "Password not retained");
    }
}