package com.srm.hackathon.demoblaze.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.hackathon.demoblaze.base.BaseTest;
import com.srm.hackathon.demoblaze.pages.LoginModalPage;
import com.srm.hackathon.demoblaze.pages.ProductPage;
import com.srm.hackathon.demoblaze.utils.ConfigReader;

public class ProductTest extends BaseTest {

    private void login() {
        LoginModalPage loginPage = new LoginModalPage();

        String username = ConfigReader.getProperty("validUsername");
        String password = ConfigReader.getProperty("validPassword");

        loginPage.login(username, password);
    }

    // 1️⃣ Phones category
    @Test
    public void verifyPhonesCategory() {

        login();

        ProductPage productPage = new ProductPage();

        productPage.clickPhones();

        int count = productPage.getProductCount();

        Assert.assertTrue(count > 0, "No phones displayed");
    }

    // 2️⃣ Laptops category
    @Test
    public void verifyLaptopsCategory() {

        login();

        ProductPage productPage = new ProductPage();

        productPage.clickLaptops();

        int count = productPage.getProductCount();

        Assert.assertTrue(count > 0, "No laptops displayed");
    }

    // 3️⃣ Product details
    @Test
    public void verifyProductDetails() {

        login();

        ProductPage productPage = new ProductPage();

        productPage.clickPhones();
        productPage.clickFirstProduct();

        Assert.assertTrue(
                productPage.isProductDetailDisplayed(),
                "Product details not displayed"
        );
    }

    // 4️⃣ Home reload
    @Test
    public void verifyHomeReload() {

        login();

        ProductPage productPage = new ProductPage();

        productPage.clickPhones();
        productPage.clickHome();

        int count = productPage.getProductCount();

        Assert.assertTrue(count > 0, "Home page did not reload products");
    }
}