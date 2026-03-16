package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    private By cartItems      = By.className("cart_item");
    private By cartItemName   = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By removeButton   = By.cssSelector("[data-test^='remove']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public String getFirstItemName() {
        return driver.findElement(cartItemName).getText();
    }

    public boolean isProductInCart(String productName) {
        return driver.findElements(cartItemName)
                     .stream()
                     .anyMatch(e -> e.getText().equalsIgnoreCase(productName));
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
