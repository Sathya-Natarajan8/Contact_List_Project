package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import base.ProjectSpecificationMethods;

public class ListenerClass extends ProjectSpecificationMethods implements ITestListener {

	private ExtentTest test;
	private static ExtentReports extent = ExtentReport.getReport();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		if (test != null) {
			test.log(Status.PASS, "Test passed");
			String filepath = screenshot(result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
		} else {
			System.out.println("ExtentTest instance is null, skipping pass logging.");
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		if (test != null) {
			test.log(Status.FAIL, "Test failed: " + result.getThrowable());

			String screenshotPath = UtilsClass.screenshot(result.getName());

			if (!screenshotPath.isEmpty()) {
				test.addScreenCaptureFromPath(screenshotPath);
			} else {
				System.out.println("Skipping screenshot attachment due to empty path.");
			}
		} else {
			System.out.println("ExtentTest instance is null, skipping failure logging.");
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
