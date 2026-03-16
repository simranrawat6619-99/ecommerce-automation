package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;

    // Targets first product's Add to Cart button on SauceDemo
    private By firstProductName    = By.cssSelector(".inventory_item_name");
    private By addToCartButton     = By.cssSelector("[data-test='add-to-cart-sauce-labs-backpack']");
    private By cartBadge           = By.className("shopping_cart_badge");
    private By cartIcon            = By.className("shopping_cart_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstProductName() {
        return driver.findElement(firstProductName).getText();
    }

    public void addFirstProductToCart() {
        driver.findElement(addToCartButton).click();
    }

    public int getCartCount() {
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public void goToCart() {
        driver.findElement(cartIcon).click();
    }
}
