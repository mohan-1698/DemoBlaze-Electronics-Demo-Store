package com.srm.hackathon.demoblaze.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.srm.hackathon.demoblaze.base.BaseTest;
import com.srm.hackathon.demoblaze.pages.CartPage;
import com.srm.hackathon.demoblaze.pages.LoginModalPage;
import com.srm.hackathon.demoblaze.pages.ProductDetailPage;
import com.srm.hackathon.demoblaze.pages.ProductPage;
import com.srm.hackathon.demoblaze.utils.ConfigReader;

public class CartTest extends BaseTest {

    // 🔥 Register + Login (FIXED)
    private void registerAndLogin() {

        LoginModalPage loginPage = new LoginModalPage();

        String baseUsername = ConfigReader.getProperty("baseUsername");
        String password = ConfigReader.getProperty("password");

        String uniqueUser = baseUsername + System.currentTimeMillis();

        // 🔥 FIXED HERE
        loginPage.registerUserWithUsername(uniqueUser, password);

        // 🔥 Login (MANDATORY)
        loginPage.login(uniqueUser, password);

        // 🔥 Wait for navbar
        loginPage.waitForUserToBeVisible();
    }

    // ✅ 1. Add single product
    @Test(priority = 1)
    public void verifyAddSingleProduct() {

        registerAndLogin();

        ProductPage productPage = new ProductPage();
        productPage.waitForProductsToLoad();
        productPage.selectProduct("Samsung galaxy s6");

        ProductDetailPage detailPage = new ProductDetailPage();

        String name = detailPage.getProductName();
        int price = detailPage.getProductPrice();

        detailPage.addToCart();

        CartPage cartPage = new CartPage();
        cartPage.openCart();

        Assert.assertTrue(cartPage.isProductPresent(name));
        Assert.assertEquals(cartPage.getTotalPrice(), price);
    }

    // ✅ 2. Add two products
    @Test(priority = 2)
    public void verifyAddTwoProducts() {

        registerAndLogin();

        ProductPage productPage = new ProductPage();

        productPage.waitForProductsToLoad();
        productPage.selectProduct("Samsung galaxy s6");

        ProductDetailPage detailPage = new ProductDetailPage();
        String name1 = detailPage.getProductName();
        detailPage.addToCart();

        detailPage.goToHomePage();

        productPage = new ProductPage();
        productPage.waitForProductsToLoad();
        productPage.selectProduct("Nexus 6");

        detailPage = new ProductDetailPage();
        String name2 = detailPage.getProductName();
        detailPage.addToCart();

        CartPage cartPage = new CartPage();
        cartPage.openCart();

        Assert.assertTrue(cartPage.isProductPresent(name1));
        Assert.assertTrue(cartPage.isProductPresent(name2));

        Assert.assertEquals(cartPage.getCartCount(), 2);
    }

    // ✅ 3. Delete product
    @Test(priority = 3)
    public void verifyDeleteProduct() {

        registerAndLogin();

        ProductPage productPage = new ProductPage();
        productPage.waitForProductsToLoad();
        productPage.selectProduct("Samsung galaxy s6");

        ProductDetailPage detailPage = new ProductDetailPage();
        String name = detailPage.getProductName();
        detailPage.addToCart();

        CartPage cartPage = new CartPage();
        cartPage.openCart();

        int before = cartPage.getCartCount();

        cartPage.deleteFirstProduct();
        cartPage.waitForCartUpdate(before);

        Assert.assertFalse(cartPage.isProductPresent(name));
    }

    // ✅ 4. Total validation after deletion
    @Test(priority = 4)
    public void verifyTotalAfterDeletion() {

        registerAndLogin();

        ProductPage productPage = new ProductPage();

        productPage.waitForProductsToLoad();
        productPage.selectProduct("Samsung galaxy s6");

        ProductDetailPage detailPage = new ProductDetailPage();
        detailPage.addToCart();

        detailPage.goToHomePage();

        productPage = new ProductPage();
        productPage.waitForProductsToLoad();
        productPage.selectProduct("Nexus 6");

        detailPage = new ProductDetailPage();
        detailPage.addToCart();

        CartPage cartPage = new CartPage();
        cartPage.openCart();

        int beforeTotal = cartPage.getTotalPrice();
        int beforeCount = cartPage.getCartCount();

        cartPage.deleteFirstProduct();
        cartPage.waitForCartUpdate(beforeCount);

        int afterTotal = cartPage.getTotalPrice();

        Assert.assertEquals(afterTotal, cartPage.calculateSumOfProducts());
        Assert.assertTrue(afterTotal < beforeTotal);
    }
}