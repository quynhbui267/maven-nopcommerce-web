package com.nopcommerce.precondition;

import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import utilities.DataHelper;

public class Precondition extends BaseTest {

	@Parameters("browser")
	@BeforeTest (description = "Register new account and using for all test cases in Test suite")
	public void beforeTest(String browerName) {
		driver = getBrowserDriver(browerName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = dataHelper.getFirstName();
		lastName = dataHelper.getLastName();
		email = dataHelper.getEmail();
		password = dataHelper.getPassword();
		company = dataHelper.getFirstName();
		day = "27";
		month = "July";
		year = "1991";
		log.info("Register new account with email = " + email+ " and password = " + password);
		registerPage = homePage.clickRegisterLink();
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
		log.info("Login with new account and get cookies");
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(email);
		loginPage.inputToPassword(password);
		homePage = loginPage.clickToLoginBtn();
		cookies = homePage.getAllCookie(driver);
		driver.quit();
	}

	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	DataHelper dataHelper = DataHelper.getDataHelper();
	public static String firstName, lastName, day, month, year, company;
	public static String email, password;
	public static Set<Cookie> cookies;
}
