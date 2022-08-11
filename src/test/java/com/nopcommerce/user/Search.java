package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.HomePageObject;
import pageObjects.SearchPageObject;
import utilities.DataHelper;

public class Search extends BaseTest {
	WebDriver driver;
	HomePageObject homePage;
	SearchPageObject searchPage;
	DataHelper dataHelper = DataHelper.getDataHelper();
	String notExistingSearchText = dataHelper.getFirstName();

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browerName) {
		driver = getBrowserDriver(browerName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		searchPage= PageGeneratorManager.getSearchPage(driver);
		homePage.clickFooterLink("Search");
	}

	@Test(description = "Verify error msg will shown when leave search text blank")
	public void TC_01_Search_With_Empty_Data() {
		searchPage.clickSearchBtn();
		verifyEquals(searchPage.getSearchErrorMessage(), "Search term minimum length is 3 characters");
	}

	@Test(description = "Verify error msg will shown when leave search text blank")
	public void TC_02_Search_With_Not_Existing_Data() {
		searchPage.inputSearchText(notExistingSearchText);
		searchPage.clickSearchBtn();
		verifyEquals(searchPage.getNoResultMessage(), "No products were found that matched your criteria.");
	}
}
