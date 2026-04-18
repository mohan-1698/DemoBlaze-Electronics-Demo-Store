package com.srm.hackathon.demoblaze.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.srm.hackathon.demoblaze.factory.DriverManager;
import com.srm.hackathon.demoblaze.utils.ConfigReader;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    protected void sendKeys(WebElement element, String value) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(value);
    }

    protected String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    protected void waitForAlertAndAccept() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    protected String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert().getText();
    }
}