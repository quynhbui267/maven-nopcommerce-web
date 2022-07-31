package commons;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DataHelper;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;

	public BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	@BeforeSuite
	public void initBeforeSuite() {
		//deleteAllureReportFilesinFolder();
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserListEnum browserList = BrowserListEnum.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case FIREFOX:
			// Disable browser log in Console and save in browserLogs file
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.FIREFOX_LOGFILE);
			driver = WebDriverManager.firefoxdriver().create();
			// driver = new FirefoxDriver();
			break;
		case CHROME:
			// Disable browser log in Console
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver = WebDriverManager.chromedriver().create();
			break;
		case EDGE_CHRONIUM:
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException("Browser name is not valid");
		}
		driver.get(GlobalConstants.DEV_USER_URL);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}

	public static int getRandomNumber(int maxValue) {
		Random rd = new Random();
		return rd.nextInt(maxValue);
	}

	public static long getRandomNumberByDateTime() {
		return Calendar.getInstance().getTimeInMillis();
	}

	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteAllureReportFilesinFolder() {
		try {
			String pathFolderDownload = GlobalConstants.ALLURE_REPORTING_PATH;
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

}
