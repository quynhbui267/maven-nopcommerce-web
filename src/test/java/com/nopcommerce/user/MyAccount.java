package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.nopcommerce.precondition.PreconditionForUpdateAccount;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomerAddressPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.ProductSubMenuPageObject;
import pageObjects.SidebarMyAccountPageObject;
import utilities.DataHelper;

public class MyAccount extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	SidebarMyAccountPageObject sidebarMyAccountPage;
	CustomerInfoPageObject customerInfoPage;
	CustomerAddressPageObject customerAddressPage;
	ChangePasswordPageObject changePasswordPage;
	MyProductReviewPageObject myProductReviewPage;
	ProductSubMenuPageObject productSubMenuPage;
	ProductDetailPageObject productDetailPage;
	DataHelper dataHelper = DataHelper.getDataHelper();
	String firstName, lastName, email, day, month, year, company, city, country, state, address1, address2, postalCode, phoneNumber, faxNumber, newPassword,
	reviewTitle, reviewText, productTitle, dateTimeSubmit, wrongPassMsg;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage= PageGeneratorManager.getUserLoginPage(driver);
		changePasswordPage = PageGeneratorManager.getChangePassPage(driver);
		customerInfoPage=PageGeneratorManager.getCustomerInfoPage(driver);
		customerAddressPage=PageGeneratorManager.getCustomerAddressPage(driver);
		myProductReviewPage = PageGeneratorManager.getMyProductReviewPage(driver);
		productSubMenuPage = PageGeneratorManager.getProductSubMenuPage(driver);
		productDetailPage = PageGeneratorManager.getProductDetailPage(driver);
		sidebarMyAccountPage = PageGeneratorManager.getSideBarMyAccountPage(driver);
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
		wrongPassMsg="Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect";
		homePage.clickToLoginLink();
		loginPage.loginToApp(PreconditionForUpdateAccount.email, PreconditionForUpdateAccount.password);
		homePage.clickMyAccountLink();

	}

	//@Test(description = "Verify customer info is updated correctly")
	public void TC_01_Update_Customer_Info() {
		sidebarMyAccountPage.openCustomerInfoLink();
		customerInfoPage.inputCustomerInfoByField(firstName, "FirstName");
		customerInfoPage.inputCustomerInfoByField(lastName, "LastName");
		customerInfoPage.inputCustomerInfoByField(email, "Email");
		customerInfoPage.selectDropdownListByField(day, "DateOfBirthDay");
		customerInfoPage.selectDropdownListByField(month, "DateOfBirthMonth");
		customerInfoPage.selectDropdownListByField(year, "DateOfBirthYear");
		customerInfoPage.inputCustomerInfoByField(company, "Company");
		customerInfoPage.clickSaveButton();
		customerInfoPage.refreshCurrentPage();

		verifyEquals(customerInfoPage.getCustomerInfoByField("FirstName"), firstName);
		verifyEquals(customerInfoPage.getCustomerInfoByField("LastName"), lastName);
		verifyEquals(customerInfoPage.getCustomerInfoByField("Email"), email);
		verifyEquals(customerInfoPage.getCustomerInfoByField("Company"), company);
		verifyEquals(customerInfoPage.getDropdownListSelectedByField("DateOfBirthDay"), day);
		verifyEquals(customerInfoPage.getDropdownListSelectedByField("DateOfBirthMonth"), month);
		verifyEquals(customerInfoPage.getDropdownListSelectedByField("DateOfBirthYear"), year);
	}

	//@Test(description = "Verify customer address is added successfully")
	public void TC_02_Add_Customer_Address() {
		sidebarMyAccountPage.openCustomerAddressLink();
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

		verifyEquals(customerAddressPage.getTextbyFieldName("name"), firstName + " " + lastName);
		verifyEquals(customerAddressPage.getTextbyFieldName("email"), "Email: " + email);
		verifyEquals(customerAddressPage.getTextbyFieldName("phone"), "Phone number: " + phoneNumber);
		verifyEquals(customerAddressPage.getTextbyFieldName("fax"), "Fax number: " + faxNumber);
		verifyEquals(customerAddressPage.getTextbyFieldName("company"), company);
		verifyEquals(customerAddressPage.getTextbyFieldName("address1"), address1);
		verifyEquals(customerAddressPage.getTextbyFieldName("address2"), address2);
		verifyEquals(customerAddressPage.getTextbyFieldName("city-state-zip"), city + ", " + state + ", " + postalCode);
		verifyEquals(customerAddressPage.getTextbyFieldName("country"), country);
	}

	//@Test(description = "Verify pasword is changed successfully")
	public void TC_03_Change_Password() {
		sidebarMyAccountPage.openChangePasswordLink();
		changePasswordPage.inputToChangePasswordField(PreconditionForUpdateAccount.password, "OldPassword");
		changePasswordPage.inputToChangePasswordField(newPassword, "NewPassword");
		changePasswordPage.inputToChangePasswordField(newPassword, "ConfirmNewPassword");
		changePasswordPage.clickChangePasswordBtn();
		verifyEquals(changePasswordPage.getSuccessfulMsg(), "Password was changed");
		changePasswordPage.clickCloseBtn();
		homePage.clickLogoutLink();
		homePage.clickToLoginLink();
		loginPage.loginToApp(email, PreconditionForUpdateAccount.password);
		verifyEquals(loginPage.getCredentialErrorMessage(), wrongPassMsg);
		loginPage.loginToApp(email, newPassword);
		verifyTrue(homePage.isMyAccountLinkDisplayed());

	}

	@Test(description = "Verify Product review is shown")
	public void TC_04_View_Product_Review() {
		homePage.hoverProductMenuByProductName("Computers");
		homePage.clickProductSubMenuByProductName("Desktops");
		productSubMenuPage.clickProductDetailByTitle(productTitle);
		productDetailPage.clickAddReviewLink();
		productDetailPage.inputToReviewTitle(reviewTitle);
		productDetailPage.inputToReviewText(reviewText);
		productDetailPage.selectRatingRadioBtn();
		productDetailPage.clickSubmitReviewBtn();
		dateTimeSubmit = productDetailPage.getDateTimeSubmit();
		homePage.clickMyAccountLink();
		sidebarMyAccountPage.openMyProductReviewLink();
		verifyEquals(myProductReviewPage.getProductReviewTitle(), reviewTitle);
		verifyEquals(myProductReviewPage.getProductReviewText(), reviewText);
		verifyEquals(myProductReviewPage.getProductReviewRating(), "width: 80%;");
		verifyEquals(myProductReviewPage.getProductReviewInfoProduct(), productTitle);
		verifyEquals(myProductReviewPage.getProductReviewInfoDate(), dateTimeSubmit);

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

}
