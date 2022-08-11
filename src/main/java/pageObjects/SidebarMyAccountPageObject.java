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
	public void openCustomerInfoLink() {
		waitForElementClickable(SidebarMyAccountPageUI.CUSTOMER_INFO_LINK);
		clickToElement(SidebarMyAccountPageUI.CUSTOMER_INFO_LINK);
	}

	public void openCustomerAddressLink() {
		waitForElementClickable(SidebarMyAccountPageUI.CUSTOMER_ADDRESSES_LINK);
		doubleClick(SidebarMyAccountPageUI.CUSTOMER_ADDRESSES_LINK);
	}
	
	public void openChangePasswordLink() {
		waitForElementClickable(SidebarMyAccountPageUI.CHANGE_PASSWORD_LINK);
		doubleClick(SidebarMyAccountPageUI.CHANGE_PASSWORD_LINK);
		
	}
	
	public void openMyProductReviewLink() {
		waitForElementClickable(SidebarMyAccountPageUI.MY_PRODUCT_REVIEW_LINK);
		doubleClick(SidebarMyAccountPageUI.MY_PRODUCT_REVIEW_LINK);

	}

}
