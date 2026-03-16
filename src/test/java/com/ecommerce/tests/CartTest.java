package com.ecommerce.tests;

import com.aventstack.extentreports.Status;
import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.*;
import com.ecommerce.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void testAddToCart() {
        ExtentReportManager.createTest("Add to Cart Test");
        log.info("Starting Add to Cart test");

        // Login first
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertEquals(loginPage.getPageTitle(), "Products",
            "Should be on Products page before adding to cart");

        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getFirstProductName();
        log.info("Adding product: " + productName);

        productPage.addFirstProductToCart();

        // Assertion: cart badge count should be 1
        int cartCount = productPage.getCartCount();
        Assert.assertEquals(cartCount, 1,
            "Cart count should be 1 after adding one item");

        ExtentReportManager.getTest().log(Status.PASS,
            "Product added. Cart count: " + cartCount);

        // Navigate to cart and verify product is there
        productPage.goToCart();
        CartPage cartPage = new CartPage(driver);

        Assert.assertTrue(cartPage.isProductInCart(productName),
            "Product '" + productName + "' should be present in cart");

        ExtentReportManager.getTest().log(Status.PASS,
            "Product verified in cart: " + productName);
    }
}
