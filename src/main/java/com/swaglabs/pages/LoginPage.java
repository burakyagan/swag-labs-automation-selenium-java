package com.swaglabs.pages;

import com.swaglabs.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public InventoryPage loginAs(String username, String password) {
        logger.info("Attempting to login with username: " + username);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
        logger.info("Login credentials submitted");

        // Dismiss any password save popups
        try {
            Thread.sleep(500);
            // Press Escape key to close any popups
            driver.findElement(org.openqa.selenium.By.tagName("body")).sendKeys(Keys.ESCAPE);
            logger.info("Attempted to dismiss popup with ESC key");
        } catch (Exception e) {
            logger.info("No popup to dismiss or ESC failed");
        }

        return new InventoryPage();
    }

    public String getErrorMessage() {
        logger.info("Getting error message text");
        return errorMessage.getText();
    }
}
