package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;
import commons.BaseTest;
import commons.GlobalConstants;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import static utilities.TestLogger.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AllureTestListener implements ITestListener, IInvokedMethodListener {

	// Screenshot attachments for Allure
	@Attachment(value = "Screenshot of {0}", type = "image/png")
	public static byte[] saveScreenshotPNG(String testName, WebDriver driver) {
		return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		info("------------" + iTestResult.getName() + " is FAILED ------------");
		captureScreenshot(iTestResult.getName(), iTestResult);
		// Object testClass = iTestResult.getInstance();
		// WebDriver driver = ((BaseTest) testClass).getDriverInstance();
		// saveScreenshotPNG(iTestResult.getName(), driver);

	}

	@Override
	public void onStart(ITestContext iTestContext) {
		info("-------STARTED TEST " + iTestContext.getName() + "------");
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		info("------------" + iTestResult.getName() + " is SKIPPED ------------");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		info("-------FINISHED TEST " + iTestContext.getName() + "------");
		

	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		info("------------" + iTestResult.getName() + " is STARTED ------------");
		
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		info("------------" + iTestResult.getName() + " is PASSED ------------");
	}

	public String captureScreenshot(String screenshotName, ITestResult iTestResult) {
		try {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			Object testClass = iTestResult.getInstance();
			WebDriver driver = ((BaseTest) testClass).getDriverInstance();
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenPath = GlobalConstants.REPORTING_SCREENSHOT + screenshotName + "_" + formater.format(calendar.getTime()) + ".png";
			FileUtils.copyFile(source, new File(screenPath));
			return screenPath;
		} catch (IOException e) {
			info("Exception while taking screenshot: " + e.getMessage());
			return e.getMessage();
		}
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult result) {
		debug("Before invocation of " + method.getTestMethod().getMethodName());
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		debug("After invocation of " + method.getTestMethod().getMethodName());
		//Reporter.setCurrentTestResult(result);
		if (method.isTestMethod()) {
			VerificationFailures allFailures = VerificationFailures.getFailures();

			// Add an existing failure for the result to the failure list.
			if (result.getThrowable() != null) {
				allFailures.addFailureForTest(result, result.getThrowable());
			}

			List<Throwable> failures = allFailures.getFailuresForTest(result);
			int size = failures.size() - 1;

			if (size > 0) {
				result.setStatus(ITestResult.FAILURE);
				if (size == 1) {
					result.setThrowable(failures.get(0));
				} else {
					StringBuffer message = new StringBuffer("Multiple failures (").append(size).append("):\n");
					for (int failure = 0; failure < size - 1; failure++) {
						message.append("Failure ").append(failure + 1).append(" of ").append(size).append("\n");
						message.append(Utils.longStackTrace(failures.get(failure), false)).append("\n");
					}
					Throwable last = failures.get(size - 1);
					message.append("Failure ").append(size).append(" of ").append(size).append("\n");
					message.append(last.toString());
					Throwable merged = new Throwable(message.toString());
					merged.setStackTrace(last.getStackTrace());
					result.setThrowable(merged);
				}
			}
		}
	}
}


