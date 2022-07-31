package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.SidebarMyAccountPageUI;

public class SidebarMyAccountPageObject extends BasePage {
	WebDriver driver;

	public SidebarMyAccountPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	// Switch common page
	public CustomerInfoPageObject openCustomerInfoLink() {
		waitForElementClickable(SidebarMyAccountPageUI.CUSTOMER_INFO_LINK);
		clickToElement(SidebarMyAccountPageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getCustomerInfoPage(driver);
	}

	public CustomerAddressPageObject openCustomerAddressLink() {
		waitForElementClickable(SidebarMyAccountPageUI.CUSTOMER_ADDRESSES_LINK);
		doubleClick(SidebarMyAccountPageUI.CUSTOMER_ADDRESSES_LINK);
		return PageGeneratorManager.getCustomerAddressPage(driver);
	}
	
	public ChangePasswordPageObject openChangePasswordLink() {
		waitForElementClickable(SidebarMyAccountPageUI.CHANGE_PASSWORD_LINK);
		doubleClick(SidebarMyAccountPageUI.CHANGE_PASSWORD_LINK);
		return PageGeneratorManager.getChangePassPage(driver);
		
	}
	
	public MyProductReviewPageObject openMyProductReviewLink() {
		waitForElementClickable(SidebarMyAccountPageUI.MY_PRODUCT_REVIEW_LINK);
		doubleClick(SidebarMyAccountPageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getMyProductReviewPage(driver);

	}

}
