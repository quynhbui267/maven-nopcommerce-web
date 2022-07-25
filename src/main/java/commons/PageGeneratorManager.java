package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.CustomerAddressesPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.SidebarMyAccountPageObject;

public class PageGeneratorManager extends BasePage {

	public PageGeneratorManager(WebDriver driver) {
		super(driver);
	}

	public static SidebarMyAccountPageObject getMyAccountPage(WebDriver driver) {
		return new SidebarMyAccountPageObject(driver);
	}

	public static HomePageObject getUserHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static LoginPageObject getUserLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static CustomerInfoPageObject getCustomerInfoPage(WebDriver driver) {
		return new CustomerInfoPageObject(driver);
	}

	public static CustomerAddressesPageObject getCustomerAddressesPage(WebDriver driver) {
		return new CustomerAddressesPageObject(driver);
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
}
