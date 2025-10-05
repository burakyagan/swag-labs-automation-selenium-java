# Swag Labs Automation - Selenium & Java

A comprehensive test automation framework for [Sauce Demo](https://www.saucedemo.com/) using Selenium WebDriver, Java, and TestNG with Page Object Model design pattern.

## 🚀 Features

- **Page Object Model (POM)** design pattern with Fluent Interface
- **ThreadLocal** WebDriver management for parallel test execution
- **Singleton Pattern** for configuration management
- **Factory Pattern** for cross-browser support (Chrome, Firefox, Edge)
- **Log4j2** for detailed logging
- **AssertJ** for fluent and readable assertions
- **Maven** for dependency management
- **TestNG** for test orchestration

## 📋 Prerequisites

- Java 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser

## 🛠️ Project Structure

```
swag-labs-automation-selenium-java/
├── src/
│   ├── main/
│   │   ├── java/com/swaglabs/
│   │   │   ├── base/
│   │   │   │   ├── BasePage.java
│   │   │   │   └── DriverManager.java
│   │   │   ├── pages/
│   │   │   │   ├── LoginPage.java
│   │   │   │   └── InventoryPage.java
│   │   │   └── utils/
│   │   │       └── ConfigReader.java
│   │   └── resources/
│   │       ├── config.properties
│   │       └── log4j2.xml
│   └── test/
│       └── java/com/swaglabs/
│           ├── base/
│           │   └── BaseTest.java
│           └── tests/
│               └── LoginTests.java
├── .gitignore
├── LICENSE
├── pom.xml
├── README.md
└── testng.xml
```

## ⚙️ Configuration

Edit `src/main/resources/config.properties` to customize test settings:

```properties
baseUrl=https://www.saucedemo.com/
browser=chrome
username=standard_user
password=secret_sauce
```

## 🏃 Running Tests

### Run all tests
```bash
mvn clean test
```

### Run specific test class
```bash
mvn test -Dtest=LoginTests
```

### Run with different browser
```bash
mvn test -Dbrowser=firefox
```

## 📊 Test Reports

After test execution, reports are generated in:
- `target/surefire-reports/` - TestNG HTML reports
- Console logs with Log4j2

## 🔑 Key Components

### DriverManager
Thread-safe WebDriver management using ThreadLocal for parallel execution support.

### ConfigReader
Singleton pattern implementation for reading configuration properties.

### BasePage
Parent class for all page objects with PageFactory initialization.

### BaseTest
Base test class with TestNG lifecycle hooks for setup and teardown.

## 🧪 Test Example

```java
@Test
public void testSuccessfulLogin() {
    boolean isLoginSuccessful = new LoginPage()
            .loginAs("standard_user", "secret_sauce")
            .isProductTitleDisplayed();

    assertThat(isLoginSuccessful).isTrue();
}
```

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

## 📧 Contact

For questions or feedback, please open an issue on this repository.
