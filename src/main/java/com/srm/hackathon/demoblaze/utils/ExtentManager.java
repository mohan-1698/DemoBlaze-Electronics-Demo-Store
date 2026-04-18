package com.srm.hackathon.demoblaze.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            // 🔥 Ensure reports folder exists
            File folder = new File("reports");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // ✅ SINGLE REPORT (overwrite every run)
            String reportPath = "reports/ExtentReport.html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // 🔥 IMPORTANT: overwrite old report
            sparkReporter.config().setReportName("DemoBlaze Automation Report");
            sparkReporter.config().setDocumentTitle("Execution Report");
            sparkReporter.config().setEncoding("utf-8");
            sparkReporter.config().setTimelineEnabled(true);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // 🔥 SYSTEM INFO (good for interview/demo)
            extent.setSystemInfo("Project", "DemoBlaze Automation");
            extent.setSystemInfo("Module", "E2E Testing");
            extent.setSystemInfo("Tester", "Mickey");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }

        return extent;
    }
}