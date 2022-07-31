package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageObjects.SidebarMyAccountPageObject;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Click to Login link")
	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(HomePageUI.LOGIN_LINK);
		clickToElement(HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	@Step("Verify My Account link is displayed after login")
	public boolean isMyAccountLinkDisplayed() {
		return isElementDisplayed(HomePageUI.MY_ACCOUNT_LINK);
	}

	@Step("Click to My account link")
	public SidebarMyAccountPageObject clickMyAccountLink() {
		waitForElementClickable(HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getMyAccountPage(driver);
	}

	@Step("Click to Register link")
	public RegisterPageObject clickRegisterLink() {
		waitForElementClickable(HomePageUI.REGISTER_LINK);
		clickToElement(HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	@Step("Click to Logout link")
	public void clickLogoutLink() {
		waitForElementClickable(HomePageUI.LOGOUT_LINK);
		clickToElementByJS(HomePageUI.LOGOUT_LINK);
		sleepInSecond(2);
	}

	@Step("Click to product = {0} in Top menu")
	public ProductMenuPageObject clickProductMenuByProductName(String product) {
		waitForElementClickable(HomePageUI.TOP_MENU_PRODUCT_LINK, product);
		clickToElement(HomePageUI.TOP_MENU_PRODUCT_LINK, product);
		return PageGeneratorManager.getProductMenuPage(driver);
	}

	@Step("Hover to product = {0} in Top menu")
	public void hoverProductMenuByProductName(String product) {
		waitForElementVisible(HomePageUI.TOP_MENU_PRODUCT_LINK, product);
		hoverMouseToElement(HomePageUI.TOP_MENU_PRODUCT_LINK, product);
	}

	@Step("Click to product = {0} in Sub menu")
	public ProductSubMenuPageObject clickProductSubMenuByProductName(String product) {
		waitForElementClickable(HomePageUI.TOP_SUB_MENU_PRODUCT_LINK,product);
		clickToElement(HomePageUI.TOP_SUB_MENU_PRODUCT_LINK,product);
		return PageGeneratorManager.getProductSubMenuPage(driver);
	}

	public SearchPageObject clickFooterLink(String linkName) {
		waitForElementClickable(HomePageUI.FOOTER_LINK_BY_LINK_NAME, linkName);
		clickToElement(HomePageUI.FOOTER_LINK_BY_LINK_NAME, linkName);
		return PageGeneratorManager.getSearchPage(driver);
	}
}
