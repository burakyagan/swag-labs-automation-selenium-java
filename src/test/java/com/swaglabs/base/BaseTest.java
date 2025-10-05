package com.swaglabs.base;

import com.swaglabs.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        logger.info("Starting test execution");
        DriverManager.initDriver();
        String baseUrl = ConfigReader.getInstance().getProperty("baseUrl");
        DriverManager.getDriver().get(baseUrl);
        logger.info("Navigated to: " + baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Ending test execution");
        DriverManager.quitDriver();
    }
}
