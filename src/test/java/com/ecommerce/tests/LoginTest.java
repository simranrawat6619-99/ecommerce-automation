package com.ecommerce.tests;

import com.aventstack.extentreports.Status;
import com.ecommerce.base.BaseTest;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.utils.ExtentReportManager;
import com.ecommerce.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() throws Exception {
        ExtentReportManager.createTest("Valid Login Test");
        log.info("Starting valid login test");

        List<String[]> data = ExcelUtils.getTestData("LoginData");
        String username = data.get(0)[0];
        String password = data.get(0)[1];

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // Assertion: verify user lands on Products page after login
        String pageTitle = loginPage.getPageTitle();
        log.info("Page title after login: " + pageTitle);

        Assert.assertEquals(pageTitle, "Products",
            "Login failed — expected 'Products' page title");

        ExtentReportManager.getTest().log(Status.PASS,
            "Login successful. Page title verified: " + pageTitle);
    }

    @Test
    public void testInvalidLogin() {
        ExtentReportManager.createTest("Invalid Login Test");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "wrong_pass");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
            "Error message should appear for invalid credentials");

        String error = loginPage.getErrorMessage();
        log.warn("Error displayed: " + error);

        ExtentReportManager.getTest().log(Status.PASS,
            "Correct error shown: " + error);
    }
}
