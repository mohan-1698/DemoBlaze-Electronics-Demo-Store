package com.srm.hackathon.demoblaze.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.srm.hackathon.demoblaze.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getName();

        String screenshotPath = ScreenshotUtils.captureScreenshot(testName);

        System.out.println("Test Failed: " + testName);
        System.out.println("Screenshot saved at: " + screenshotPath);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Test Suite Started ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("=== Test Suite Finished ===");
    }
}