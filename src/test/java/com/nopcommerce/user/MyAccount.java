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
import io.qameta.allure.Epic;
import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomerAddressPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.ProductSubMenuPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.SidebarMyAccountPageObject;
import utilities.DataHelper;

public class MyAccount extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	LoginPageObject loginPage;
	SidebarMyAccountPageObject sidebarMyAccountPage;
	CustomerInfoPageObject customerInfoPage;
	CustomerAddressPageObject customerAddressPage;
	ChangePasswordPageObject changePasswordPage;
	MyProductReviewPageObject myProductReviewPage;
	ProductSubMenuPageObject productSubMenuPage;
	ProductDetailPageObject productDetailPage;
	DataHelper dataHelper = DataHelper.getDataHelper();
	String firstName, lastName, email, day, month, year, company, city, country, state, address1, address2, postalCode, phoneNumber, 
	faxNumber, newPassword, reviewTitle, reviewText, productTitle, dateTimeSubmit;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		//homePage.addCookies(Precondition.cookies);
		firstName = dataHelper.getFirstName();
		lastName = dataHelper.getLastName();
		email = dataHelper.getEmail();
		company = dataHelper.getFirstName();
		day = dataHelper.getDay();
		month = "June";
		year = dataHelper.getYear();
		city = dataHelper.getCity();
		address1 = dataHelper.getAddress();
		address2 = dataHelper.getAddress();
		postalCode = dataHelper.getPostalCode();
		phoneNumber = dataHelper.getPhoneNumber();
		faxNumber = "555-123-4567";
		country = "United States";
		state = "Alabama";
		newPassword = dataHelper.getPassword();
		reviewTitle = "This is a product review";
		reviewText = "The product is excellent. Recommend to buy. I will comeback again";
		productTitle = "Build your own computer";
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(Precondition.email);
		loginPage.inputToPassword(Precondition.password);
		homePage = loginPage.clickToLoginBtn();
		sidebarMyAccountPage = homePage.clickMyAccountLink();

	}

	@Test(description = "Verify customer info is updated correctly")
	public void TC_01_Update_Customer_Info() {

		log.info("Update account information for acc email =  " + Precondition.email + " and password = " + Precondition.password);
		customerInfoPage = sidebarMyAccountPage.openCustomerInfoLink();
		customerInfoPage.inputCustomerInfoByField(firstName, "FirstName");
		customerInfoPage.inputCustomerInfoByField(lastName, "LastName");
		customerInfoPage.inputCustomerInfoByField(email, "Email");
		customerInfoPage.selectDropdownListByField(day, "DateOfBirthDay");
		customerInfoPage.selectDropdownListByField(month, "DateOfBirthMonth");
		customerInfoPage.selectDropdownListByField(year, "DateOfBirthYear");
		customerInfoPage.inputCustomerInfoByField(company, "Company");
		customerInfoPage.clickSaveButton();
		customerInfoPage.refreshCurrentPage();

		log.info("Verify info after updating");
		Assert.assertEquals(customerInfoPage.getCustomerInfoByField("FirstName"), firstName);
		Assert.assertEquals(customerInfoPage.getCustomerInfoByField("LastName"), lastName);
		Assert.assertEquals(customerInfoPage.getCustomerInfoByField("Email"), email);
		Assert.assertEquals(customerInfoPage.getCustomerInfoByField("Company"), company);
		Assert.assertEquals(customerInfoPage.getDropdownListSelectedByField("DateOfBirthDay"), day);
		Assert.assertEquals(customerInfoPage.getDropdownListSelectedByField("DateOfBirthMonth"), month);
		Assert.assertEquals(customerInfoPage.getDropdownListSelectedByField("DateOfBirthYear"), year);
	}

	@Test(description = "Verify customer address is added successfully")
	public void TC_02_Add_Customer_Address() {
		customerAddressPage = sidebarMyAccountPage.openCustomerAddressLink();
		customerAddressPage.clickAddNewBtn();
		customerAddressPage.inputToAddressTextField(firstName, "FirstName");
		customerAddressPage.inputToAddressTextField(lastName, "LastName");
		customerAddressPage.inputToAddressTextField(email, "Email");
		customerAddressPage.inputToAddressTextField(company, "Company");

		customerAddressPage.selectCountryDropdown(country);
		customerAddressPage.selectStateDropdown(state);

		customerAddressPage.inputToAddressTextField(city, "City");
		customerAddressPage.inputToAddressTextField(address1, "Address1");
		customerAddressPage.inputToAddressTextField(address2, "Address2");
		customerAddressPage.inputToAddressTextField(postalCode, "ZipPostalCode");
		customerAddressPage.inputToAddressTextField(phoneNumber, "PhoneNumber");
		customerAddressPage.inputToAddressTextField(faxNumber, "FaxNumber");

		customerAddressPage.clickSaveBtn();

		Assert.assertEquals(customerAddressPage.getTextbyFieldName("name"), firstName + " " + lastName);
		Assert.assertEquals(customerAddressPage.getTextbyFieldName("email"), "Email: " + email);
		Assert.assertEquals(customerAddressPage.getTextbyFieldName("phone"), "Phone number: " + phoneNumber);
		Assert.assertEquals(customerAddressPage.getTextbyFieldName("fax"), "Fax number: " + faxNumber);
		Assert.assertEquals(customerAddressPage.getTextbyFieldName("company"), company);
		Assert.assertEquals(customerAddressPage.getTextbyFieldName("address1"), address1);
		Assert.assertEquals(customerAddressPage.getTextbyFieldName("address2"), address2);
		Assert.assertEquals(customerAddressPage.getTextbyFieldName("city-state-zip"), city + ", " + state + ", " + postalCode);
		Assert.assertEquals(customerAddressPage.getTextbyFieldName("country"), country);
	}

	@Test(description = "Verify pasword is changed successfully")
	public void TC_03_Change_Password() {
		changePasswordPage = sidebarMyAccountPage.openChangePasswordLink();
		changePasswordPage.inputToChangePasswordField(Precondition.password, "OldPassword");
		changePasswordPage.inputToChangePasswordField(newPassword, "NewPassword");
		changePasswordPage.inputToChangePasswordField(newPassword, "ConfirmNewPassword");
		changePasswordPage.clickChangePasswordBtn();
		Assert.assertEquals(changePasswordPage.getSuccessfulMsg(), "Password was changed");
		changePasswordPage.clickCloseBtn();
		homePage.clickLogoutLink();
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToEmailAddress(email);
		loginPage.inputToPassword(Precondition.password);
		loginPage.clickToLoginBtn();
		Assert.assertEquals(loginPage.getCredentialErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
		loginPage.inputToPassword(newPassword);
		loginPage.clickToLoginBtn();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

	}

	@Test(description = "Verify Product review is shown")
	public void TC_04_View_Product_Review() {
		homePage.hoverProductMenuByProductName("Computers");
		productSubMenuPage = homePage.clickProductSubMenuByProductName("Desktops");
		productDetailPage = productSubMenuPage.clickProductDetailByTitle(productTitle);
		productDetailPage.clickAddReviewLink();
		productDetailPage.inputToReviewTitle(reviewTitle);
		productDetailPage.inputToReviewText(reviewText);
		productDetailPage.selectRatingRadioBtn();
		productDetailPage.clickSubmitReviewBtn();
		dateTimeSubmit = productDetailPage.getDateTimeSubmit();
		homePage.clickMyAccountLink();
		myProductReviewPage = sidebarMyAccountPage.openMyProductReviewLink();
		Assert.assertEquals(myProductReviewPage.getProductReviewTitle(), reviewTitle);
		Assert.assertEquals(myProductReviewPage.getProductReviewText(), reviewText);
		Assert.assertEquals(myProductReviewPage.getProductReviewRating(), "width: 80%;");
		Assert.assertEquals(myProductReviewPage.getProductReviewInfoProduct(), productTitle);
		Assert.assertEquals(myProductReviewPage.getProductReviewInfoDate(), dateTimeSubmit);
		

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		log.info("Change email of Precondition account back to " + Precondition.email);
		sidebarMyAccountPage.openCustomerInfoLink();
		customerInfoPage.inputCustomerInfoByField(Precondition.email, "Email");
		customerInfoPage.clickSaveButton();
		log.info("Change password of Precondition account back to " + Precondition.password);
		sidebarMyAccountPage.openChangePasswordLink();
		changePasswordPage.inputToChangePasswordField(newPassword, "OldPassword");
		changePasswordPage.inputToChangePasswordField(Precondition.password, "NewPassword");
		changePasswordPage.inputToChangePasswordField(Precondition.password, "ConfirmNewPassword");
		changePasswordPage.clickChangePasswordBtn();
		
		closeBrowserAndDriver();
	}

}
