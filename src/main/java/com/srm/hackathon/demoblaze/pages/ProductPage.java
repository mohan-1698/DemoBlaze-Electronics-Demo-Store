package com.srm.hackathon.demoblaze.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.srm.hackathon.demoblaze.base.BasePage;

public class ProductPage extends BasePage {

    // ===== CATEGORY LOCATORS =====
    private By phonesCategory = By.xpath("//a[contains(text(),'Phones')]");
    private By laptopsCategory = By.xpath("//a[contains(text(),'Laptops')]");
    private By monitorsCategory = By.xpath("//a[contains(text(),'Monitors')]");

    // ===== PRODUCT LIST =====
    private By productCards = By.cssSelector("#tbodyid .card");
    private By productNames = By.cssSelector("#tbodyid .hrefch");

    // ===== PRODUCT DETAILS =====
    private By productPrice = By.cssSelector(".price-container");
    private By productDescription = By.cssSelector("#more-information");

    // ===== NAVBAR =====
    private By homeLink = By.xpath("//a[contains(text(),'Home')]");

    // ================= CATEGORY ACTIONS =================

    public void clickPhones() {
        click(phonesCategory);
        waitForProductsToLoad();
    }

    public void clickLaptops() {
        click(laptopsCategory);
        waitForProductsToLoad();
    }

    public void clickMonitors() {
        click(monitorsCategory);
        waitForProductsToLoad();
    }

    // ================= COMMON METHODS =================

    // 🔥 MOST IMPORTANT FIX (handles dynamic loading)
    public void waitForProductsToLoad() {
        wait.until(driver -> driver.findElements(productCards).size() > 0);
    }

    public int getProductCount() {
        return driver.findElements(productNames).size();
    }

    // 🔥 ROBUST dynamic product selection
    public void selectProduct(String productName) {
        By product = By.xpath(
            "//a[@class='hrefch' and normalize-space()='" + productName + "']"
        );
        click(product);
    }

    // Optional helper
    public void clickFirstProduct() {
        List<WebElement> products = driver.findElements(productNames);
        products.get(0).click();
    }

    // ================= PRODUCT DETAIL =================

    public boolean isProductDetailDisplayed() {
        try {
            waitForVisibility(productPrice);
            waitForVisibility(productDescription);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ================= HOME =================

    public void clickHome() {
        click(homeLink);
        waitForProductsToLoad();
    }
}