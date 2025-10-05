package com.swaglabs.pages;

import com.swaglabs.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InventoryPage extends BasePage {

    @FindBy(className = "title")
    private WebElement productTitle;

    public boolean isProductTitleDisplayed() {
        try {
            boolean isDisplayed = productTitle.isDisplayed();
            String titleText = productTitle.getText();
            logger.info("Product title is displayed: " + isDisplayed + " with text: " + titleText);
            return isDisplayed && titleText.equalsIgnoreCase("Products");
        } catch (Exception e) {
            logger.error("Product title not found: " + e.getMessage());
            return false;
        }
    }
}
