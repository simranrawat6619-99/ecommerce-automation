package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FeedbackPage {
    private WebDriver driver;

    // SauceDemo checkout doubles as feedback / review step
    private By firstNameField  = By.id("first-name");
    private By lastNameField   = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton  = By.id("continue");
    private By finishButton    = By.id("finish");
    private By confirmHeader   = By.className("complete-header");

    public FeedbackPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCheckoutInfo(String firstName, String lastName, String zip) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(zip);
        driver.findElement(continueButton).click();
    }

    public void finishOrder() {
        driver.findElement(finishButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmHeader).getText();
    }
}
