package com.swaglabs.tests;

import com.swaglabs.base.BaseTest;
import com.swaglabs.pages.LoginPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        logger.info("Starting successful login test");

        boolean isLoginSuccessful = new LoginPage()
                .loginAs("standard_user", "secret_sauce")
                .isProductTitleDisplayed();

        assertThat(isLoginSuccessful).isTrue();
        logger.info("Login test completed successfully");
    }

    @Test
    public void testLoginWithInvalidPassword() {
        logger.info("Starting invalid password login test");

        LoginPage loginPage = new LoginPage();
        loginPage.loginAs("standard_user", "wrong_password");

        String errorMessage = loginPage.getErrorMessage();
        assertThat(errorMessage).contains("Epic sadface: Username and password do not match any user in this service");

        logger.info("Invalid password test completed successfully");
    }
}
