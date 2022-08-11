package com.nopcommerce.precondition;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import utilities.DataHelper;
import static utilities.TestLogger.info;

public class PreconditionForAll extends BaseTest {

	@Parameters("browser")
	@BeforeTest (description = "Register new account and using for all test cases in Test suite")
	public void beforeTestAll(String browerName) {
		driver = getBrowserDriver(browerName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = dataHelper.getFirstName();
		lastName = dataHelper.getLastName();
		email = dataHelper.getEmail();
		password = dataHelper.getPassword();
		company = dataHelper.getFirstName();
		day = dataHelper.getDay();
		month = "July";
		year = dataHelper.getYear();
		info("Register new account with email = " + email+ " and password = " + password);
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		homePage.clickRegisterLink();
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		registerPage.inputRegisterField(email, "Email");
		registerPage.selectDropdownListByField( day, "DateOfBirthDay");
		registerPage.selectDropdownListByField(month, "DateOfBirthMonth");
		registerPage.selectDropdownListByField(year, "DateOfBirthYear");
		registerPage.inputRegisterField(company, "Company");
		registerPage.inputRegisterField(email, "Email");
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(password, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		homePage.clickLogoutLink();
		driver.quit();
	}

	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	DataHelper dataHelper = DataHelper.getDataHelper();
	public static String firstName, lastName, day, month, year, company, email, password;
	
}
