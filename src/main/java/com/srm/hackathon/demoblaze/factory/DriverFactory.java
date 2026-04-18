package com.srm.hackathon.demoblaze.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.srm.hackathon.demoblaze.utils.ConfigReader;

public class DriverFactory {

    public static WebDriver initDriver(String browser) {

        WebDriver driver;

        boolean isHeadless = Boolean.parseBoolean(
                ConfigReader.getProperty("headless")
        );

        switch (browser.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();

                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }

                chromeOptions.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (isHeadless) {
                    firefoxOptions.addArguments("-headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        return driver;
    }
}