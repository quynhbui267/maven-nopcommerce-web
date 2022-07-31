package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import pageObjects.HomePageObject;
import utilities.DataHelper;

public class Register extends BaseTest {
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browerName) {
		driver = getBrowserDriver(browerName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		registerPage = homePage.clickRegisterLink();
		firstName = dataHelper.getFirstName();
		lastName = dataHelper.getLastName();
		email = dataHelper.getEmail();
		day = "27";
		month = "July";
		year = "1991";
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
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("FirstName"), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("LastName"), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("Email"), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("Password"), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "Password is required.");
	}

	@Test(description = "Verify that error message will be shown when register with invalid email")
	public void TC_02_Register_With_Invalid_Email() {
		registerPage.refreshCurrentPage();
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		registerPage.inputRegisterField(invalidEmail, "Email");
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(password, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");
	}

	@Test(description = "Verify register succesfully with valid data")
	public void TC_03_Register_With_Valid_Data() {
		registerPage.refreshCurrentPage();
		registerPage.selectGender("female");
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");

		registerPage.selectDropdownListByField("DateOfBirthDay", day);
		registerPage.selectDropdownListByField("DateOfBirthMonth", month);
		registerPage.selectDropdownListByField("DateOfBirthYear", year);

		registerPage.inputRegisterField(company, "Company");
		registerPage.inputRegisterField(email, "Email");
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(password, "ConfirmPassword");

		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessfulMsgText(), "Your registration completed");

	}

	@Test(description = "Verify that error message will be shown when register with existing email")
	public void TC_04_Register_With_Existing_Email() {
		homePage.clickLogoutLink();
		registerPage = homePage.clickRegisterLink();
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		registerPage.inputRegisterField(email, "Email");
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(password, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "The specified email already exists");

	}

	@Test(description = "Verify that error message will be shown when register with password has less than 6 characters")
	public void TC_05_Register_With_Invalid_Password() {
		registerPage.refreshCurrentPage();
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		registerPage.inputRegisterField(TC_05_email, "Email");
		registerPage.inputRegisterField(TC_05_password, "Password");
		registerPage.inputRegisterField(TC_05_password, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("Password"), "Password must meet the following rules:\n" + "must have at least 6 characters");

	}

	@Test(description = "Verify that error message will be shown when register with confirm password is not matched")
	public void TC_06_Register_With_Invalid_Confirm_Password() {
		registerPage.refreshCurrentPage();
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		registerPage.inputRegisterField(TC_06_email, "Email");
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(TC_06_password, "ConfirmPassword");
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "The password and confirmation password do not match.");

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
