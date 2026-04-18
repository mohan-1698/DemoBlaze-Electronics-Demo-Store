# DemoBlaze Automation

## Project Overview

DemoBlaze Automation is a comprehensive test automation framework built using Selenium WebDriver and TestNG. It provides automated testing for the DemoBlaze e-commerce platform, covering authentication, product browsing, shopping cart management, order placement, and form validation scenarios.

## Technology Stack

- **Java**: Programming language
- **Selenium WebDriver**: Version 4.34.0 (Web automation framework)
- **TestNG**: Version 7.10.2 (Test framework)
- **WebDriverManager**: Version 5.9.2 (WebDriver management)
- **Apache POI**: Version 5.4.1 (Excel handling)
- **Maven**: Build automation tool

## Project Structure

```
demoblaze-automation/
├── pom.xml
├── testng.xml
├── README.md
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/srm/hackathon/demoblaze/
│   │           ├── base/
│   │           │   └── BasePage.java
│   │           ├── factory/
│   │           │   ├── DriverFactory.java
│   │           │   └── DriverManager.java
│   │           ├── pages/
│   │           │   ├── CartPage.java
│   │           │   ├── HomePage.java
│   │           │   ├── LoginModalPage.java
│   │           │   ├── OrderPage.java
│   │           │   ├── ProductDetailPage.java
│   │           │   └── ProductPage.java
│   │           └── utils/
│   │               ├── ConfigReader.java
│   │               ├── ExtentManager.java
│   │               ├── JsonUtil.java
│   │               ├── ScreenshotUtils.java
│   │               └── WaitUtils.java
│   │
│   └── test/
│       ├── java/
│       │   └── com/srm/hackathon/demoblaze/
│       │       ├── base/
│       │       │   └── BaseTest.java
│       │       ├── dataprovider/
│       │       │   └── DataProviders.java
│       │       ├── listeners/
│       │       │   └── TestListener.java
│       │       └── tests/
│       │           ├── AuthTest.java
│       │           ├── CartTest.java
│       │           ├── FormValidationTest.java
│       │           ├── OrderTest.java
│       │           └── ProductTest.java
│       └── resources/
│
├── target/
│   ├── classes/
│   └── test-classes/
│
├── reports/
│   └── ExtentReport.html
│
├── screenshots/
│   └── [Test execution screenshots]
│
└── test-output/
    └── [TestNG reports]
```

## Features and Test Cases

### 1. Authentication Module (AuthTest.java)

Handles all user authentication scenarios.

**Test Cases:**
- **verifyUserRegistration**: Validates successful user registration. Tests both new user registration and existing user scenarios.
- **verifySuccessfulLogin**: Verifies that registered users can successfully log in and appear in the navigation bar.

**Approach:**
- Uses LoginModalPage to interact with login/signup forms
- Validates user presence in navbar after successful login
- Handles alerts for registration status messages

### 2. Product Module (ProductTest.java)

Tests product browsing and category filtering functionality.

**Test Cases:**
- **verifyPhonesCategory**: Validates that the phones category displays products correctly.
- **verifyLaptopsCategory**: Verifies laptops category functionality.
- **verifyMonitorsCategory**: Tests monitors category display.

**Approach:**
- User login is performed before product tests
- Tests category filtering by clicking category buttons
- Verifies product count for each category
- Uses ProductPage to interact with product listings

### 3. Shopping Cart Module (CartTest.java)

Comprehensive testing of cart functionality including product addition and removal.

**Test Cases:**
- **verifyAddSingleProduct**: Tests adding a single product to cart.
- **verifyAddMultipleProducts**: Validates adding multiple different products.
- **verifyDeleteProductFromCart**: Tests product removal from cart.
- **verifyUpdateProductQuantity**: Verifies cart quantity updates.
- **verifyEmptyCartMessage**: Confirms empty cart displays appropriate message.

**Approach:**
- Uses dynamic username generation with timestamps to ensure unique user sessions
- Registers new user before each test to avoid conflicts
- Performs login after registration
- Tests add/remove/update operations with cart page interactions
- Validates cart updates and product quantities

### 4. Order Module (OrderTest.java)

Tests complete order placement workflow.

**Test Cases:**
- **verifyPlaceOrderSuccess**: Complete order placement with success confirmation.
- **verifyOrderDetails**: Validates order confirmation displays correct details.
- **verifyOrderID**: Confirms order ID is generated and displayed.
- **verifyOrderWithMultipleProducts**: Tests ordering multiple items.

**Approach:**
- Registers and logs in new user for each order test
- Adds products to cart before checkout
- Completes order form with customer details
- Validates order success message and confirmation details
- Uses OrderPage to handle checkout process

### 5. Form Validation Module (FormValidationTest.java)

Tests form validation and input error handling.

**Test Cases:**
- **verifySignupWithExistingUser**: Validates duplicate user alert when registering with existing username.
- **verifyLoginWithEmptyUsername**: Tests login form validation with empty username.
- **verifyLoginWithEmptyPassword**: Validates password field requirement.
- **verifyLoginWithInvalidCredentials**: Tests login failure with incorrect credentials.

**Approach:**
- Tests form field validations
- Verifies alert messages for invalid inputs
- Tests edge cases like empty fields and invalid credentials
- Ensures appropriate error handling and user feedback

## Framework Architecture

### Base Classes

**BasePage.java**: 
- Provides common operations for all page objects
- Handles element interactions and waits

**BaseTest.java**:
- Setup and teardown for test execution
- Driver initialization
- Browser launch and closure

### Page Object Model (POM)

All page interactions are encapsulated in respective page classes:
- **LoginModalPage**: Login and registration forms
- **HomePage**: Home page interactions
- **ProductPage**: Product listing and category filtering
- **ProductDetailPage**: Individual product details
- **CartPage**: Shopping cart operations
- **OrderPage**: Order placement and confirmation

### Utility Classes

- **DriverFactory**: Creates WebDriver instances
- **DriverManager**: Manages driver lifecycle
- **ConfigReader**: Reads configuration properties
- **WaitUtils**: Explicit wait operations
- **ScreenshotUtils**: Captures screenshots on test failures
- **ExtentManager**: Manages Extent Report generation
- **JsonUtil**: Handles JSON data operations

### Test Listeners

**TestListener.java**:
- Logs test execution details
- Captures screenshots on test failures
- Generates Extent Reports

## Running Tests

### Run All Tests
```
mvn clean test
```

### Run Specific Test Class
```
mvn test -Dtest=AuthTest
```

### Run Specific Test Method
```
mvn test -Dtest=AuthTest#verifySuccessfulLogin
```

### Run with TestNG XML
```
mvn clean test -DsuiteXmlFile=testng.xml
```

## Test Configuration

The test execution is configured in `testng.xml`:
- Parallel execution enabled with 3 threads
- Tests organized into logical modules (Auth, Product, Cart, Order, FormValidation)
- Custom TestListener for enhanced reporting

## Reports

### Extent Report
- Location: `reports/ExtentReport.html`
- Provides detailed test execution summary with pass/fail status

### TestNG Report
- Location: `test-output/`
- Standard TestNG HTML reports

### Screenshots
- Location: `screenshots/`
- Captured on test failures for debugging

## Key Testing Strategies

### Data Management
- Dynamic username generation with timestamps prevents test data conflicts
- Properties file-based configuration for test credentials
- DataProviders used for parameterized testing

### Wait Strategies
- Explicit waits for element visibility and clickability
- Timeout handling for robustness
- WaitUtils utility for consistent wait implementations

### Error Handling
- Comprehensive assertions with meaningful messages
- Alert handling for modal dialogs
- Exception logging and screenshot capture

### Test Isolation
- Unique user creation per test execution
- Independent test cases without dependencies
- Fresh browser session for each test

## Best Practices Implemented

1. **Page Object Model**: Separates test logic from UI element locators
2. **Reusable Components**: Common operations in base classes
3. **Configuration Externalization**: Test data in configuration files
4. **Enhanced Reporting**: Extent Reports for detailed test analysis
5. **Screenshot Capture**: Visual evidence on test failures
6. **Parallel Execution**: Faster test suite execution
7. **Listener Pattern**: Centralized test event handling

## Dependencies and Versions

Refer to `pom.xml` for complete dependency list including:
- Selenium WebDriver 4.34.0
- TestNG 7.10.2
- WebDriverManager 5.9.2
- Apache POI 5.4.1

## Notes

- Ensure valid test credentials are configured in the properties file
- Browser must have internet access for DemoBlaze website
- WebDriver executables are managed automatically via WebDriverManager
- Screenshots and reports are generated in respective directories
