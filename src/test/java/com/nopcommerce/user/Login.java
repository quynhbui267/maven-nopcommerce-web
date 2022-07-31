package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.nopcommerce.precondition.Precondition;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import utilities.DataHelper;

public class Login extends BaseTest {
	WebDriver driver;
	DataHelper dataHelper = DataHelper.getDataHelper();
	LoginPageObject loginPage;
	HomePageObject homePage;
	String invalidEmail, notRegisteredEmail, wrongPassword;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browerName) {
		driver = getBrowserDriver(browerName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage = homePage.clickToLoginLink();
		invalidEmail = "abc123.com";
		notRegisteredEmail = dataHelper.getEmail();
		wrongPassword = dataHelper.getPassword();
	}

	@Test
	public void TC_01_Login_With_Empty_Data() {
		loginPage.clickToLoginBtn();
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(invalidEmail);
		loginPage.clickToLoginBtn();
		Assert.assertEquals(loginPage.getEmailErrorMessage(), "Wrong email");
	}

	@Test
	public void TC_03_Login_With_Not_Registerd_Email() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(notRegisteredEmail);
		loginPage.inputToPassword(Precondition.password);
		loginPage.clickToLoginBtn();
		Assert.assertEquals(loginPage.getCredentialErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");
	}

	@Test
	public void TC_4_Login_With_Empty_Password() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(Precondition.email);
		loginPage.clickToLoginBtn();
		Assert.assertEquals(loginPage.getCredentialErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
	}

	@Test
	public void TC_5_Login_With_Wrong_Password() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(Precondition.email);
		loginPage.inputToPassword(wrongPassword);
		loginPage.clickToLoginBtn();
		Assert.assertEquals(loginPage.getCredentialErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
	}

	@Test
	public void TC_6_Login_With_Valid_Credential() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(Precondition.email);
		loginPage.inputToPassword(Precondition.password);
		loginPage.clickToLoginBtn();
		homePage.isMyAccountLinkDisplayed();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
