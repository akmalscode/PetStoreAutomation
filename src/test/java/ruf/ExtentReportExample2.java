package ruf;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportExample2 implements ITestListener, ISuiteListener {

	// Create ExtentSparkReporter and ExtentReports instances

	public void onStart(ITestContext testContext) {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//reports//extent-report.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		ExtentTest test = extent.createTest("My First Test", "This is a sample test");

		test.log(Status.INFO, "Starting the test...");
		test.log(Status.PASS, "Test passed!");
		extent.flush();

	}
	public void onFlush(ITestContext testContext) {
		// not generate report if did not call	
		ExtentReports extent = new ExtentReports();
		extent.flush();
	}
}
