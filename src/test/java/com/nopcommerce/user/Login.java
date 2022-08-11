package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.nopcommerce.precondition.PreconditionForAll;
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
	String emptyEmailMsg, invalidEmailMsg, notRegisterEmailMsg, wrongPassMsg;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browerName) {
		driver = getBrowserDriver(browerName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage = PageGeneratorManager.getUserLoginPage(driver);
		homePage.clickToLoginLink();
		invalidEmail = "abc123.com";
		notRegisteredEmail = dataHelper.getEmail();
		wrongPassword = dataHelper.getPassword();
		emptyEmailMsg = "Please enter your email";
		invalidEmailMsg = "Wrong email";
		notRegisterEmailMsg = "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found";
		wrongPassMsg = "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect";
	}

	@Test
	public void TC_01_Login_With_Empty_Data() {
		loginPage.clickToLoginBtn();
		Assert.assertEquals(loginPage.getEmailErrorMessage(), emptyEmailMsg);
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(invalidEmail);
		loginPage.clickToLoginBtn();
		verifyEquals(loginPage.getEmailErrorMessage(), invalidEmailMsg);
	}

	@Test
	public void TC_03_Login_With_Not_Registerd_Email() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(notRegisteredEmail);
		loginPage.inputToPassword(PreconditionForAll.password);
		loginPage.clickToLoginBtn();
		verifyEquals(loginPage.getCredentialErrorMessage(), notRegisterEmailMsg);
	}

	@Test
	public void TC_4_Login_With_Empty_Password() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(PreconditionForAll.email);
		loginPage.clickToLoginBtn();
		verifyEquals(loginPage.getCredentialErrorMessage(), wrongPassMsg);
	}

	@Test
	public void TC_5_Login_With_Wrong_Password() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(PreconditionForAll.email);
		loginPage.inputToPassword(wrongPassword);
		loginPage.clickToLoginBtn();
		verifyEquals(loginPage.getCredentialErrorMessage(), wrongPassMsg);
	}

	@Test
	public void TC_6_Login_With_Valid_Credential() {
		homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(PreconditionForAll.email);
		loginPage.inputToPassword(PreconditionForAll.password);
		loginPage.clickToLoginBtn();
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
