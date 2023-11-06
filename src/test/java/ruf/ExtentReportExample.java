package ruf;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportExample implements  ITestListener,ISuiteListener{

	// Create ExtentSparkReporter and ExtentReports instances

	public void onStart(ITestContext testContext) {
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(".//reports//extent-report.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		// Create a test in the report
		 ExtentTest test = extent.createTest("My First Test", "This is a sample test");

		// Log test steps and details
		// test.log(Status.INFO, "Starting the test...");
		// test.log(Status.PASS, "Test passed!");

		// Add more test steps here...

		// Flush the report and close it
		extent.flush();

	}
}