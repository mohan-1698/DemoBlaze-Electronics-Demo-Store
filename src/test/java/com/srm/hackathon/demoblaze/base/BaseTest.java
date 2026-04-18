package com.srm.hackathon.demoblaze.base;

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

        

        WebDriver driver = DriverFactory.initDriver(browser);
        DriverManager.setDriver(driver);

        driver.manage().window().maximize();

        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
     
        DriverManager.quitDriver();
    }
}