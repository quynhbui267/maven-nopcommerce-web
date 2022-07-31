package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.ChangePasswordPageObject;
import pageObjects.CustomerAddressPageObject;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.ProductMenuPageObject;
import pageObjects.MyProductReviewPageObject;
import pageObjects.ProductDetailPageObject;
import pageObjects.ProductSubMenuPageObject;
import pageObjects.RegisterPageObject;
import pageObjects.SearchPageObject;
import pageObjects.SidebarMyAccountPageObject;

public class PageGeneratorManager extends BasePage{

	public PageGeneratorManager(WebDriver driver) {
		super(driver);
	}

	public static SidebarMyAccountPageObject getMyAccountPage(WebDriver driver) {
		return new SidebarMyAccountPageObject(driver);
	}
	
	public static ChangePasswordPageObject getChangePassPage(WebDriver driver) {
		return new ChangePasswordPageObject(driver);
	}

	public static HomePageObject getUserHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static LoginPageObject getUserLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static CustomerInfoPageObject getCustomerInfoPage(WebDriver driver) {
		return new CustomerInfoPageObject(driver);
	}

	public static CustomerAddressPageObject getCustomerAddressPage(WebDriver driver) {
		return new CustomerAddressPageObject(driver);
	}
	public static MyProductReviewPageObject getMyProductReviewPage(WebDriver driver) {
		return new MyProductReviewPageObject(driver);
	}

	public static ProductMenuPageObject getProductMenuPage(WebDriver driver) {
		return new ProductMenuPageObject(driver);
	}
	
	public static ProductSubMenuPageObject getProductSubMenuPage(WebDriver driver) {
		return new ProductSubMenuPageObject(driver);
	}
	
	public static ProductDetailPageObject getProductDetailPage(WebDriver driver) {
		return new ProductDetailPageObject(driver);
	}

	public static SearchPageObject getSearchPage(WebDriver driver) {
		return new SearchPageObject(driver);
	}
	
	
	
}
