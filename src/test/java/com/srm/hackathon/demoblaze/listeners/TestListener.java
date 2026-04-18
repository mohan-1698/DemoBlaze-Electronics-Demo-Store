package com.srm.hackathon.demoblaze.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.srm.hackathon.demoblaze.utils.ExtentManager;
import com.srm.hackathon.demoblaze.utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private ExtentReports extent = ExtentManager.getInstance();
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = result.getName();

        String screenshotPath = ScreenshotUtils.captureScreenshot(testName);

        test.get().fail(result.getThrowable());
        test.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("=== Test Suite Started ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // 🔥 VERY IMPORTANT
        System.out.println("=== Test Suite Finished ===");
    }
}