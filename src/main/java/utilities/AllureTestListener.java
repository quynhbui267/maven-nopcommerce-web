package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import commons.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;

public class AllureTestListener extends BaseTest implements ITestListener {

	// Screenshot attachments for Allure
	@Attachment(value = "Screenshot of {0}", type = "image/png")
	public static byte[] saveScreenshotPNG(String testName, WebDriver driver) {
		return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		log.info("------------" + iTestResult.getName() + " is FAILED ------------");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((BaseTest) testClass).getDriverInstance();
		saveScreenshotPNG(iTestResult.getName(), driver);
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		log.info("-------STARTED TEST " + iTestContext.getName() + "------");
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		log.info("------------" + iTestResult.getName() + " is SKIPPED ------------");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		log.info("-------FINISHED TEST " + iTestContext.getName() + "------");

	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		log.info("------------" + iTestResult.getName() + " is STARTED ------------");
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		log.info("------------" + iTestResult.getName() + " is PASSED ------------");
	}

}
