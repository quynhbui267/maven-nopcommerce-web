package utilities;

import java.util.HashMap;
import java.util.Map;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.createExtentReports();

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) Thread.currentThread().getId());
	}

	public static synchronized ExtentTest saveToReport(String testName, String description) {
		ExtentTest test = extent.createTest(testName, description);
		extentTestMap.put((int) Thread.currentThread().getId(), test);
		return test;
	}
}
