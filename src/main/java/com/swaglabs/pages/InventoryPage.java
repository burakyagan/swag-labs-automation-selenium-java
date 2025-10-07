package com.swaglabs.pages;

import com.swaglabs.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "title")
    private WebElement productTitle;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = "[data-test='product_sort_container']")
    private WebElement sortDropdown;

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

    public String getCartBadgeCount() {
        logger.info("Getting cart badge count");
        return cartBadge.getText();
    }

    public InventoryPage addProductToCartByName(String productName) {
        logger.info("Adding product to cart: " + productName);
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Product added to cart successfully");
        return this;
    }

    public InventoryPage removeProductFromCartByName(String productName) {
        logger.info("Removing product from cart: " + productName);
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", productName);
        driver.findElement(By.xpath(xpath)).click();
        logger.info("Product removed from cart successfully");
        return this;
    }

    public boolean isCartBadgeDisplayed() {
        logger.info("Checking if cart badge is displayed");
        boolean isDisplayed = driver.findElements(By.className("shopping_cart_badge")).size() > 0;
        logger.info("Cart badge displayed: " + isDisplayed);
        return isDisplayed;
    }

    public void sortProductsBy(String optionText) {
        logger.info("Sorting products by: " + optionText);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sortElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.className("product_sort_container")));
        Select select = new Select(sortElement);
        select.selectByVisibleText(optionText);
        logger.info("Products sorted successfully");
    }

    public List<Double> getProductPrices() {
        logger.info("Getting all product prices");
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        List<Double> prices = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();
            String priceWithoutDollar = priceText.replace("$", "");
            Double price = Double.parseDouble(priceWithoutDollar);
            prices.add(price);
        }

        logger.info("Found " + prices.size() + " product prices: " + prices);
        return prices;
    }
}
