package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import commons.BaseTest;
import static utilities.ExtentTestManager.*;

public class ExtentTestListener extends BaseTest implements ITestListener {

	public String getTestName(ITestResult iTestresult) {
		return iTestresult.getTestName() != null ? iTestresult.getTestName() : iTestresult.getMethod().getConstructorOrMethod().getName();
	}

	public String getTestDescription(ITestResult iTestresult) {
		return iTestresult.getMethod().getDescription() != null ? iTestresult.getMethod().getDescription() : getTestName(iTestresult);
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		log.info("-------STARTED TEST " + iTestContext.getName() + "------");
		iTestContext.setAttribute("WebDriver", this.getDriverInstance());
	}

	@Override
	public void onFinish(ITestContext iTestContext) {
		log.info("-------FINISHED TEST " + iTestContext.getName() + "------");
		ExtentManager.extentReports.flush();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		log.info("------------" + iTestResult.getName() + " is STARTED ------------");
		saveToReport(getTestName(iTestResult), getTestDescription(iTestResult));
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		log.info("------------" + iTestResult.getName() + " is PASSED ------------");
		getTest().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		log.error("------------" + iTestResult.getName() + " is FAILED ------------");
		log.info("Please refer screenshot in Extent Test Report");
		Object testClass = iTestResult.getInstance();
		WebDriver driver = ((BaseTest) testClass).getDriverInstance();
		String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		getTest().log(Status.FAIL, "Test Failed", getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));

	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		log.warn("------------" + iTestResult.getName() + " is SKIPPED ------------");
		getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	}
}
