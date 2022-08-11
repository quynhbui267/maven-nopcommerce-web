package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;
import utilities.DataHelper;

public class Register extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browerName) {
		driver = getBrowserDriver(browerName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		homePage.clickRegisterLink();
		firstName = dataHelper.getFirstName();
		lastName = dataHelper.getLastName();
		email = dataHelper.getEmail();
		day = dataHelper.getDay();
		month = "July";
		year = dataHelper.getYear();
		company = dataHelper.getFirstName();
		password = dataHelper.getPassword();
		invalidEmail = "abc@com";
		TC_05_email = dataHelper.getEmail();
		TC_05_password = "12345";
		TC_06_email = dataHelper.getEmail();
		TC_06_password = dataHelper.getPassword();

	}

	@Test(description = "Verify that error message will be shown when register with empty required data")
	public void TC_01_Register_With_Empty_Required_Data() {
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getErrorMessageByFieldName("FirstName"), "First name is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("LastName"), "Last name is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("Email"), "Email is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("Password"), "Password is required.");
		verifyEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "Password is required.");
	}

	@Test(description = "Verify that error message will be shown when register with invalid email")
	public void TC_02_Register_With_Invalid_Email() {
		homePage.clickRegisterLink();
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		registerPage.inputRegisterField(invalidEmail, "Email");
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(password, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getEmailErrorMessage(), "Wrong email");
	}

	@Test(description = "Verify register succesfully with valid data")
	public void TC_03_Register_With_Valid_Data() {
		homePage.clickRegisterLink();
		registerPage.selectFemaleGender();
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");

		registerPage.selectDropdownListByField(day, "DateOfBirthDay");
		registerPage.selectDropdownListByField(month, "DateOfBirthMonth");
		registerPage.selectDropdownListByField(year, "DateOfBirthYear");

		registerPage.inputRegisterField(company, "Company");
		registerPage.inputRegisterField(email, "Email");
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(password, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getRegisterSuccessfulMsgText(), "Your registration completed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test(description = "Verify that error message will be shown when register with existing email")
	public void TC_04_Register_With_Existing_Email() {
		homePage.clickLogoutLink();
		homePage.clickRegisterLink();
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		registerPage.inputRegisterField(email, "Email");
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(password, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getEmailErrorMessage(), "The specified email already exists");

	}

	@Test(description = "Verify that error message will be shown when register with password has less than 6 characters")
	public void TC_05_Register_With_Invalid_Password() {
		homePage.clickRegisterLink();
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		registerPage.inputRegisterField(TC_05_email, "Email");
		registerPage.inputRegisterField(TC_05_password, "Password");
		registerPage.inputRegisterField(TC_05_password, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getErrorMessageByFieldName("Password"), "Password must meet the following rules:\n" + "must have at least 6 characters");
	}

	@Test(description = "Verify that error message will be shown when register with confirm password is not matched")
	public void TC_06_Register_With_Invalid_Confirm_Password() {
		homePage.clickRegisterLink();
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		registerPage.inputRegisterField(TC_06_email, "Email");
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(TC_06_password, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		verifyEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "The password and confirmation password do not match.");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	WebDriver driver;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	DataHelper dataHelper = DataHelper.getDataHelper();
	String firstName, lastName, email, password, invalidEmail, TC_05_email, TC_05_password, TC_06_email, TC_06_password, day, month, year, company;

}
