package com.ecommerce.tests;

import com.aventstack.extentreports.Status;
import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.*;
import com.ecommerce.utils.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FeedbackTest extends BaseTest {

    @Test
    public void testOrderFeedbackFlow() {
        ExtentReportManager.createTest("Order Feedback / Checkout Test");
        log.info("Starting feedback/order completion test");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Add product to cart
        ProductPage productPage = new ProductPage(driver);
        productPage.addFirstProductToCart();
        productPage.goToCart();

        // Proceed to checkout (feedback step)
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        FeedbackPage feedbackPage = new FeedbackPage(driver);
        feedbackPage.fillCheckoutInfo("John", "Doe", "411001");
        feedbackPage.finishOrder();

        // Assertion: order confirmation message
        String confirmMsg = feedbackPage.getConfirmationMessage();
        log.info("Confirmation: " + confirmMsg);

        Assert.assertEquals(confirmMsg, "Thank you for your order!",
            "Order confirmation message mismatch");

        ExtentReportManager.getTest().log(Status.PASS,
            "Order placed. Confirmation: " + confirmMsg);
    }
}
