package com.swaglabs.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }
}
