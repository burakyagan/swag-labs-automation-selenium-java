package com.swaglabs.base;

import com.swaglabs.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        String browser = ConfigReader.getInstance().getProperty("browser").toLowerCase();
        logger.info("Initializing " + browser + " driver");

        WebDriver webDriver = createDriver(browser);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        driver.set(webDriver);
        logger.info("Driver initialized successfully");
    }

    private static WebDriver createDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                return new ChromeDriver(chromeOptions);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                return new EdgeDriver(edgeOptions);

            default:
                logger.error("Unsupported browser: " + browser);
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
    }

    public static WebDriver getDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver == null) {
            throw new IllegalStateException("Driver not initialized. Call initDriver() first.");
        }
        return webDriver;
    }

    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            logger.info("Closing browser and quitting driver");
            webDriver.quit();
            driver.remove();
        }
    }
}
