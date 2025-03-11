package utils;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import base.ProjectSpecificationMethods;

public class ListenerClass extends ProjectSpecificationMethods implements ITestListener {

    private ExtentTest test;
    private static ExtentReports extent = ExtentReport.getReport(); // Ensure ExtentReports is initialized once
    
    @BeforeMethod
    public void setUp(Method method) {
        test = extent.createTest(method.getName()); // Ensures test instance is created before each test
    }
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName()); // ✅ Ensures test instance is created
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test != null) {
            test.log(Status.PASS, "Test passed");
            try {
                String filepath = screenshot(result.getMethod().getMethodName());
                test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
            } catch (IOException e) {
                test.log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
            }
        } else {
            System.out.println("ExtentTest instance is null, skipping pass logging.");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test != null) {
            test.fail(result.getThrowable()); // ✅ Ensures test instance is not null
            try {
                String filepath = screenshot(result.getMethod().getMethodName());
                test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
            } catch (IOException e) {
                test.log(Status.WARNING, "Screenshot capture failed: " + e.getMessage());
            }
        } else {
            System.out.println("ExtentTest instance is null, skipping failure logging.");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // ✅ Ensures Extent report is saved at the end
    }
}
