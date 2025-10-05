package com.swaglabs.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static ConfigReader instance;
    private Properties properties;

    private ConfigReader() {
        properties = new Properties();
        loadProperties();
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            synchronized (ConfigReader.class) {
                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        try {
            String configPath = System.getProperty("user.dir") + "/src/main/resources/config.properties";
            FileInputStream fis = new FileInputStream(configPath);
            properties.load(fis);
            fis.close();
            logger.info("Configuration file loaded successfully");
        } catch (IOException e) {
            logger.error("Failed to load configuration file: " + e.getMessage());
            throw new RuntimeException("Configuration file not found at path: src/main/resources/config.properties");
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property '" + key + "' not found in config.properties");
        }
        return value;
    }
}
