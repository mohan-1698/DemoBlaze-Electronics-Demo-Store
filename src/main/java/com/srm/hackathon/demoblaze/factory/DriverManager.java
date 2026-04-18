package com.srm.hackathon.demoblaze.factory;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Set driver for current thread
    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Get driver for current thread
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Quit driver for current thread
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // VERY IMPORTANT (prevents memory leaks)
        }
    }
}