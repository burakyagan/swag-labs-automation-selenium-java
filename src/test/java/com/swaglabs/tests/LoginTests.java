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
}
