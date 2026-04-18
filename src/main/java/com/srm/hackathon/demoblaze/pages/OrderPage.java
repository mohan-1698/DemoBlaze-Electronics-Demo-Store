package com.srm.hackathon.demoblaze.pages;

import org.openqa.selenium.By;
import com.srm.hackathon.demoblaze.base.BasePage;

public class OrderPage extends BasePage {

    // ===== LOCATORS =====
    private By placeOrderBtn = By.xpath("//button[text()='Place Order']");

    private By orderModal = By.id("orderModal");

    private By name = By.id("name");
    private By country = By.id("country");
    private By city = By.id("city");
    private By card = By.id("card");
    private By month = By.id("month");
    private By year = By.id("year");

    private By purchaseBtn = By.xpath("//button[text()='Purchase']");

    // Success popup
    private By successMessage = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");
    private By successDetails = By.cssSelector(".sweet-alert p");
    private By okBtn = By.xpath("//button[text()='OK']");

    // ================= ACTIONS =================

    public void clickPlaceOrder() {
        click(placeOrderBtn);
        waitForVisibility(orderModal);
    }

    public void fillOrderDetails(String n, String ctry, String cty,
                                 String cardNum, String mon, String yr) {

        sendKeys(name, n);
        sendKeys(country, ctry);
        sendKeys(city, cty);
        sendKeys(card, cardNum);
        sendKeys(month, mon);
        sendKeys(year, yr);
    }

    public void clickPurchase() {
        click(purchaseBtn);
    }

    // ================= VALIDATIONS =================

    public boolean isOrderSuccessful() {
        try {
            waitForVisibility(successMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSuccessMessage() {
        waitForVisibility(successMessage);
        return getText(successMessage);
    }

    public String getOrderDetails() {
        waitForVisibility(successDetails);
        return getText(successDetails);
    }

    public void confirmOrder() {
        waitForVisibility(okBtn);
        click(okBtn);
    }
}