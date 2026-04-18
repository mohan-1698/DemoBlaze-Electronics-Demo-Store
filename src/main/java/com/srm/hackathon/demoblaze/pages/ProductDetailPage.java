package com.srm.hackathon.demoblaze.pages;

import org.openqa.selenium.By;

import com.srm.hackathon.demoblaze.base.BasePage;

public class ProductDetailPage extends BasePage {

    private By productName = By.cssSelector(".name");
    private By productPrice = By.cssSelector(".price-container");
    private By addToCartBtn = By.xpath("//a[text()='Add to cart']");
    private By homeLogo = By.id("nava");

    public String getProductName() {
        return getText(productName);
    }

    public int getProductPrice() {
        String priceText = getText(productPrice).replaceAll("[^0-9]", "");
        return Integer.parseInt(priceText);
    }

    public void addToCart() {
        click(addToCartBtn);

        // wait for alert
        waitForAlertAndAccept();
    }

    public void goToHomePage() {
        click(homeLogo);
    }
}