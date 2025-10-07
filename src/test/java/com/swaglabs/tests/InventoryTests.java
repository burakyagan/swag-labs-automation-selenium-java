package com.swaglabs.tests;

import com.swaglabs.base.BaseTest;
import com.swaglabs.pages.InventoryPage;
import com.swaglabs.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryTests extends BaseTest {

    @BeforeMethod
    public void login() {
        logger.info("Logging in before inventory test");
        new LoginPage().loginAs("standard_user", "secret_sauce");
    }

    @Test
    public void testAddOneProductToCartShouldUpdateBadgeCount() {
        logger.info("Starting add product to cart test");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addProductToCartByName("Sauce Labs Backpack");

        String badgeCount = inventoryPage.getCartBadgeCount();
        assertThat(badgeCount).isEqualTo("1");

        logger.info("Add product to cart test completed successfully");
    }

    @Test
    public void testRemoveProductFromCartShouldClearBadge() {
        logger.info("Starting remove product from cart test");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.addProductToCartByName("Sauce Labs Fleece Jacket");

        String badgeCount = inventoryPage.getCartBadgeCount();
        assertThat(badgeCount).isEqualTo("1");

        inventoryPage.removeProductFromCartByName("Sauce Labs Fleece Jacket");

        boolean isBadgeDisplayed = inventoryPage.isCartBadgeDisplayed();
        assertThat(isBadgeDisplayed).isFalse();

        logger.info("Remove product from cart test completed successfully");
    }

    @Test
    public void testProductSortByPriceLowToHigh() throws InterruptedException {
        logger.info("Starting product sort by price low to high test");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.sortProductsBy("Price (low to high)");

        Thread.sleep(2000); // Wait to see the sorted results

        List<Double> displayedPrices = inventoryPage.getProductPrices();

        List<Double> sortedPrices = new ArrayList<>(displayedPrices);
        sortedPrices.sort(Double::compareTo);

        assertThat(displayedPrices).isEqualTo(sortedPrices);

        logger.info("Product sort by price low to high test completed successfully");
        Thread.sleep(3000); // Wait before closing browser to see the result
    }
}
