package utilities;

import static utilities.ExtentTestManager.getTest;
import static utilities.ExtentTestManager.saveToReport;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import static utilities.TestLogger.*;
import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.GlobalConstants;

public class ExtentTestListener extends BaseTest implements ITestListener {

	@Override
	public void onStart(ITestContext iTestContext) {
		info("-------STARTED TEST " + iTestContext.getName() + "------");
		iTestContext.setAttribute("WebDriver", this.getDriverInstance());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		info("-------FINISHED TEST " + iTestContext.getName() + "------");
		ExtentManager.extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		info("------------" + iTestResult.getName() + " is STARTED ------------");
		saveToReport(iTestResult.getName(), iTestResult.getMethod().getDescription());

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		info("------------" + iTestResult.getName() + " is PASSED ------------");
		getTest().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		error("------------" + iTestResult.getName() + " is FAILED ------------");
		info("Please refer screenshot in Extent Test Report");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((BaseTest) testClass).getDriverInstance();
		captureScreenshot(driver, iTestResult.getName());
		// Take screenshot to Extent report
		// String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot)
		// driver).getScreenshotAs(OutputType.BASE64);
		// getTest().log(Status.FAIL, "Test Failed",
		// getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));

	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		warn("------------" + iTestResult.getName() + " is SKIPPED ------------");
		getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	}

	public String captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenPath = GlobalConstants.REPORTING_SCREENSHOT + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
			FileUtils.copyFile(source, new File(screenPath));
			return screenPath;
		} catch (IOException e) {
			info("Exception while taking screenshot: " + e.getMessage());
			return e.getMessage();
		}
	}
}
