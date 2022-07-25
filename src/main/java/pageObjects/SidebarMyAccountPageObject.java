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

	public CustomerAddressesPageObject openCustomerAddressesLink() {
		waitForElementClickable(SidebarMyAccountPageUI.CUSTOMER_ADDRESSES);
		clickToElement(SidebarMyAccountPageUI.CUSTOMER_ADDRESSES);
		return PageGeneratorManager.getCustomerAddressesPage(driver);

	}
}
