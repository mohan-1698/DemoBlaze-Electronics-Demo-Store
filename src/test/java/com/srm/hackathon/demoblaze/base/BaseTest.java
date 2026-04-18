package com.srm.hackathon.demoblaze.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.srm.hackathon.demoblaze.factory.DriverFactory;
import com.srm.hackathon.demoblaze.factory.DriverManager;
import com.srm.hackathon.demoblaze.utils.ConfigReader;

public class BaseTest {

    @BeforeMethod
    public void setUp() {

        String browser = ConfigReader.getProperty("browser");
        String url = ConfigReader.getProperty("baseURL");
        int timeout = Integer.parseInt(ConfigReader.getProperty("timeout"));

        WebDriver driver = DriverFactory.initDriver(browser);
        DriverManager.setDriver(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}