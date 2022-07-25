package com.nopcommerce.user;

import java.lang.reflect.Method;

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
	String firstName ,lastName, email, password;
	
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browerName) {
		driver = getBrowserName(browerName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		firstName = dataHelper.getFirstName();
		lastName = dataHelper.getLastName();
		email = dataHelper.getEmail();
		password = dataHelper.getPassword();
	}

	@Test (description = "Register with valid data")
	public void TC_03_Register_With_Valid_Data() {
		getTest().log(Status.INFO, "Step 1: Click to Register link");
		registerPage = homePage.clickRegisterLink();		
		registerPage.inputFirstNameTextbox(firstName);
		registerPage.inputLastNameTextbox(lastName);
		
		//info("Register - Step 4: Input email= " + email);
		registerPage.inputEmailTextbox(email);
		
		//info("Register - Step 5: Input password and confirm password= " + password);
		registerPage.inputPasswordTextbox(password);
		registerPage.inputConfirmPasswordTextbox(password);
		
		//info("Register - Step 6: Click register button");
		registerPage.clickToRegisterButton();
		Assert.assertTrue(false);
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}
}
