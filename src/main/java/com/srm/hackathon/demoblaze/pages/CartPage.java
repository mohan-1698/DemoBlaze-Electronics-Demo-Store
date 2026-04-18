package com.srm.hackathon.demoblaze.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.srm.hackathon.demoblaze.base.BasePage;

public class CartPage extends BasePage {

    private By cartLink = By.id("cartur");
    private By cartTable = By.id("tbodyid");
    private By cartRows = By.cssSelector("#tbodyid tr");
    private By productNames = By.cssSelector("#tbodyid tr td:nth-child(2)");
    private By productPrices = By.cssSelector("#tbodyid tr td:nth-child(3)");
    private By deleteButtons = By.xpath("//a[text()='Delete']");
    private By totalPrice = By.id("totalp");

    // ================= NAVIGATION =================

    public void openCart() {

        click(cartLink);

        // Wait for table
        waitForVisibility(cartTable);

        // 🔥 Wait for at least one row to be visible
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartRows));
    }

    // ================= VALIDATIONS =================

    public boolean isProductPresent(String name) {
        return driver.findElements(productNames)
                .stream()
                .anyMatch(e -> e.getText().equalsIgnoreCase(name));
    }

    public int getCartCount() {
        return driver.findElements(cartRows).size();
    }

    public int getTotalPrice() {
        return Integer.parseInt(getText(totalPrice));
    }

    public int calculateSumOfProducts() {
        List<WebElement> prices = driver.findElements(productPrices);

        int sum = 0;
        for (WebElement p : prices) {
            sum += Integer.parseInt(p.getText());
        }
        return sum;
    }

    // ================= DELETE =================

    public void deleteFirstProduct() {
        driver.findElements(deleteButtons).get(0).click();
    }

    public void waitForCartUpdate(int previousCount) {
        wait.until(driver -> driver.findElements(cartRows).size() < previousCount);
    }
}