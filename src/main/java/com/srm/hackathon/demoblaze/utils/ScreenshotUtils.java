package com.srm.hackathon.demoblaze.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.srm.hackathon.demoblaze.factory.DriverManager;

public class ScreenshotUtils {

    public static String captureScreenshot(String testName) {

        String path = "";

        try {
            WebDriver driver = DriverManager.getDriver();

            File folder = new File("screenshots");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());

            path = "screenshots/" + testName + "_" + timestamp + ".png";

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(path);

            FileUtils.copyFile(src, dest);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}