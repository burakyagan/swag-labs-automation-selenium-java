package com.swaglabs.pages;

import com.swaglabs.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public InventoryPage loginAs(String username, String password) {
        logger.info("Attempting to login with username: " + username);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
        logger.info("Login credentials submitted");
        return new InventoryPage();
    }
}
