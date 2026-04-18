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

    // ===== PRODUCT DETAILS =====
    private By productNames = By.cssSelector(".hrefch");
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

    public void waitForProductsToLoad() {
        waitForVisibility(productCards);
    }

    public int getProductCount() {
        List<WebElement> products = driver.findElements(productCards);
        return products.size();
    }

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