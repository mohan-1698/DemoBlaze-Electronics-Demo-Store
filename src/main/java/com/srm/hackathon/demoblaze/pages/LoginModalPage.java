package com.srm.hackathon.demoblaze.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.srm.hackathon.demoblaze.base.BasePage;
import com.srm.hackathon.demoblaze.utils.ScreenshotUtils;

public class LoginModalPage extends BasePage {

    // ===== SIGN UP LOCATORS =====
    private By signUpLink = By.id("signin2");
    private By signUpModal = By.id("signInModal");
    private By usernameInput = By.id("sign-username");
    private By passwordInput = By.id("sign-password");
    private By signUpButton = By.xpath("//button[text()='Sign up']");

    // ===== LOGIN LOCATORS =====
    private By loginLink = By.id("login2");
    private By loginModal = By.id("logInModal");
    private By loginUsername = By.id("loginusername");
    private By loginPassword = By.id("loginpassword");
    private By loginButton = By.xpath("//button[text()='Log in']");

    // ===== NAVBAR LOCATORS =====
    private By loggedInUser = By.id("nameofuser");
    private By logoutButton = By.id("logout2");

    // ================= SIGNUP =================

    public void clickSignUp() {
        click(signUpLink);
    }

    public void waitForSignUpModal() {
        waitForVisibility(signUpModal);
    }

    public void enterUsername(String username) {
        sendKeys(usernameInput, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordInput, password);
    }

    public void clickRegister() {
        click(signUpButton);
    }

    // 🔥 Signup with random username
    public String registerUser(String baseUsername, String password) {

        clickSignUp();
        waitForSignUpModal();

        String uniqueUsername = baseUsername + System.currentTimeMillis();

        enterUsername(uniqueUsername);
        enterPassword(password);

        clickRegister();

        String alertText = getAlertText();
        waitForAlertAndAccept();

        return alertText;
    }

    // 🔥 Signup with specific username (used in tests)
    public String registerUserWithUsername(String username, String password) {

        clickSignUp();
        waitForSignUpModal();

        enterUsername(username);
        enterPassword(password);

        clickRegister();

        String alertText = getAlertText();
        waitForAlertAndAccept();

        return alertText;
    }

    // ================= LOGIN =================

    public void clickLogin() {
        click(loginLink);
    }

    public void waitForLoginModal() {
        waitForVisibility(loginModal);
    }

    public void enterLoginUsername(String username) {
        sendKeys(loginUsername, username);
    }

    public void enterLoginPassword(String password) {
        sendKeys(loginPassword, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    // 🔥 Successful login
    public void login(String username, String password) {

        clickLogin();
        waitForLoginModal();

        enterLoginUsername(username);
        enterLoginPassword(password);

        clickLoginButton();

        waitForVisibility(loggedInUser);
    }

    // 🔥 Wait for login confirmation (used after register+login)
    public void waitForUserToBeVisible() {
        waitForVisibility(loggedInUser);
    }

    // 🔥 Login failure (with screenshot)
    public String loginAndGetError(String username, String password) {

        clickLogin();
        waitForLoginModal();

        enterLoginUsername(username);
        enterLoginPassword(password);

        clickLoginButton();

        // 📸 Screenshot before alert
        String screenshotPath = ScreenshotUtils.captureScreenshot("LoginFailure");

        System.out.println("[INFO] Invalid login attempted");
        System.out.println("[INFO] Screenshot captured: " + screenshotPath);

        String alertText = "";

        try {
            wait.until(ExpectedConditions.alertIsPresent());

            alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

        } catch (Exception e) {
            System.out.println("[INFO] No alert appeared after login attempt");
        }

        return alertText;
    }

    // 🔥 Login with empty username (validation test)
    public String loginWithEmptyUsername(String password) {

        clickLogin();
        waitForLoginModal();

        enterLoginUsername("");
        enterLoginPassword(password);

        clickLoginButton();

        String alertText = "";

        try {
            wait.until(ExpectedConditions.alertIsPresent());
            alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("[INFO] No alert appeared for empty login");
        }

        return alertText;
    }

    // ================= NAVBAR =================

    public String getLoggedInUsername() {
        waitForVisibility(loggedInUser);
        return getText(loggedInUser);
    }

    public boolean isUserLoggedIn() {
        try {
            waitForVisibility(loggedInUser);
            waitForVisibility(logoutButton);

            return driver.findElement(loggedInUser).isDisplayed()
                    && driver.findElement(logoutButton).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        click(logoutButton);
        waitForVisibility(loginLink);
    }

    public boolean isUserLoggedOut() {
        try {
            waitForVisibility(loginLink);
            return driver.findElement(loginLink).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ================= FORM VALIDATION HELPERS =================

    public String getEnteredUsername() {
        return driver.findElement(usernameInput).getAttribute("value");
    }

    public String getEnteredPassword() {
        return driver.findElement(passwordInput).getAttribute("value");
    }
}