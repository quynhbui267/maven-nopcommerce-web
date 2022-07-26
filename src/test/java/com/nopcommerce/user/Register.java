package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.RegisterPageObject;
import static utilities.ExtentTestManager.getTest;
import pageObjects.HomePageObject;
import utilities.DataHelper;

public class Register extends BaseTest {
	WebDriver driver;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	DataHelper dataHelper = DataHelper.getDataHelper();
	String firstName, lastName, email, password, invalidEmail, TC_05_email, TC_05_password, TC_06_email, TC_06_password;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browerName) {
		driver = getBrowserName(browerName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = dataHelper.getFirstName();
		lastName = dataHelper.getLastName();
		email = dataHelper.getEmail();
		password = dataHelper.getPassword();
		invalidEmail = "abc@com";
		TC_05_email = dataHelper.getEmail();
		TC_05_password = "12345";
		TC_06_email = dataHelper.getEmail();
		TC_06_password = dataHelper.getPassword();
	}

	@Test(description = "Verify that error message will be shown when register with empty required data")
	public void TC_01_Register_With_Empty_Required_Data() {
		getTest().log(Status.INFO, "Click to Register link");
		registerPage = homePage.clickRegisterLink();

		getTest().log(Status.INFO, "Leave blank required field and click register button");
		registerPage.clickToRegisterButton();

		getTest().log(Status.INFO, "Verify first name required field error message");
		
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("FirstName"), "First name is required.");

		getTest().log(Status.INFO, "Verify last name required field error message");
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("LastName"), "Last name is required.");

		getTest().log(Status.INFO, "Verify email required field error message");
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("Email"), "Email is required.");

		getTest().log(Status.INFO, "Verify password required field error message");
		Assert.assertEquals( registerPage.getErrorMessageByFieldName("Password"), "Password is required.");

		getTest().log(Status.INFO, "Verify confirm password required field error message");
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"), "Password is required.");
	}

	@Test(description = "Verify that error message will be shown when register with invalid email")
	public void TC_02_Register_With_Invalid_Email() {
		getTest().log(Status.INFO, "Click to Register link");
		registerPage = homePage.clickRegisterLink();

		getTest().log(Status.INFO, "Input firstname = " + firstName + " and lastname = " + lastName);
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");

		getTest().log(Status.INFO, "Input invalid email = " + invalidEmail);
		registerPage.inputRegisterField(invalidEmail, "Email");

		getTest().log(Status.INFO, "Input password and confirm password = " + password);
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(password, "ConfirmPassword");

		getTest().log(Status.INFO, "Click register button");
		registerPage.clickToRegisterButton();

		getTest().log(Status.INFO, "Verify email error message is shown");
		Assert.assertEquals(registerPage.getEmailErrorMessage(), "Wrong email");

	}

	@Test(description = "Verify register succesfully with valid data")
	public void TC_03_Register_With_Valid_Data() {
		getTest().log(Status.INFO, "Click to Register link");
		registerPage = homePage.clickRegisterLink();

		getTest().log(Status.INFO, "Input firstname = " + firstName + " and lastname = " + lastName);
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");

		getTest().log(Status.INFO, "Input email = " + email);
		registerPage.inputRegisterField(email, "Email");

		getTest().log(Status.INFO, "Input password and confirm password = " + password);
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(password, "ConfirmPassword");

		getTest().log(Status.INFO, "Click register button");
		registerPage.clickToRegisterButton();

		getTest().log(Status.INFO, "Verify successful message is shown");
		Assert.assertEquals(registerPage.getRegisterSuccessfulMsgText(), "Your registration completed");

	}

	
	@Test (description = "Verify that error message will be shown when register with existing email")
	public void TC_04_Register_With_Existing_Email() {
		getTest().log(Status.INFO, "Click to Logout link");
		homePage.clickLogoutLink();
		
		getTest().log(Status.INFO, "Click to Register link in Homepage");
		registerPage = homePage.clickRegisterLink();
		
		getTest().log(Status.INFO, "Input firstname = " + firstName +" and lastname = " + lastName);
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		
		getTest().log(Status.INFO, "Input existing email =  " + email);
		registerPage.inputRegisterField(email, "Email");
		
		getTest().log(Status.INFO, "Input password and confirm password = " + password);
		registerPage.inputRegisterField(password, "Password");
		registerPage.inputRegisterField(password, "ConfirmPassword");
		
		getTest().log(Status.INFO, "Click register button");
		registerPage.clickToRegisterButton();
		
		getTest().log(Status.INFO, "Verify existing email error message is shown");
		Assert.assertEquals(registerPage.getEmailErrorMessage(),"The specified email already exists");	
		
	}
	
	@Test (description = "Verify that error message will be shown when register with password has less than 6 characters")
	public void TC_05_Register_With_Invalid_Password() {
		
		getTest().log(Status.INFO, "Click to Register link");
		registerPage = homePage.clickRegisterLink();
		
		getTest().log(Status.INFO, "Input firstname = " + firstName +" and lastname = " + lastName);
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		
		getTest().log(Status.INFO, "Input email = " + TC_05_email);
		registerPage.inputRegisterField(TC_05_email, "Email");
		
		getTest().log(Status.INFO, "Input password and confirm password have less than 6 character = " + TC_05_password);
		registerPage.inputRegisterField(TC_05_password, "Password");
	
		registerPage.inputRegisterField(TC_05_password, "ConfirmPassword");
		
		getTest().log(Status.INFO, "Click register button");
		registerPage.clickToRegisterButton();
		
		getTest().log(Status.INFO, "Verify error message is shown");
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("Password"),"Password must meet the following rules:\n"
				+ "must have at least 6 characters");	
		
	}
	
	@Test (description = "Verify that error message will be shown when register with confirm password is not matched")
	public void TC_06_Register_With_Invalid_Confirm_Password() {
		
		getTest().log(Status.INFO, "Click to Register link");
		registerPage = homePage.clickRegisterLink();
		
		getTest().log(Status.INFO, "Input firstname = " + firstName +" and lastname = " + lastName);
		registerPage.inputRegisterField(firstName, "FirstName");
		registerPage.inputRegisterField(lastName, "LastName");
		
		getTest().log(Status.INFO, "Input email =  " + TC_06_email);
		registerPage.inputRegisterField(TC_06_email, "Email");
		
		getTest().log(Status.INFO, "Input password ="+ password + "and confirm password = " + TC_06_password );
		registerPage.inputRegisterField(password, "Password");
	
		registerPage.inputRegisterField(TC_06_password, "ConfirmPassword");
		
		getTest().log(Status.INFO, "Click register button");
		registerPage.clickToRegisterButton();
		
		getTest().log(Status.INFO, "Verify error message is shown");
		Assert.assertEquals(registerPage.getErrorMessageByFieldName("ConfirmPassword"),"The password and confirmation password do not match.");	
		
	}
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
