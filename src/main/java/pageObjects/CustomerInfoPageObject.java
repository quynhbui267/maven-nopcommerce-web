package pageObjects;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import pageUIs.CustomerInfoUI;

public class CustomerInfoPageObject extends SidebarMyAccountPageObject {
	WebDriver driver;

	public CustomerInfoPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Update customer info - text field {1} = {0}")
	public void inputCustomerInfoByField(String inputValue, String fieldName) {
		waitForElementVisible(CustomerInfoUI.CUSTOMER_INFO_FIELD_TXTBOX, fieldName);
		sendKeyToElement(CustomerInfoUI.CUSTOMER_INFO_FIELD_TXTBOX, inputValue, fieldName);
	}

	@Step("Select DOB dropdown - field {1} = {0}")
	public void selectDropdownListByField(String value, String fieldName) {
		waitForElementVisible(CustomerInfoUI.DOB_DROPDOWN_LIST, fieldName);
		selectItemInDefaultDropdown(CustomerInfoUI.DOB_DROPDOWN_LIST, value, fieldName);
	}

	@Step("Click Save button")
	public void clickSaveButton() {
		waitForElementVisible(CustomerInfoUI.SAVE_BTN);
		clickToElement(CustomerInfoUI.SAVE_BTN);
	}

	@Step("Get text from customer info - text field {0} to verify")
	public String getCustomerInfoByField(String fieldName) {
		waitForElementVisible(CustomerInfoUI.CUSTOMER_INFO_FIELD_TXTBOX, fieldName);
		return getElementAttribute(CustomerInfoUI.CUSTOMER_INFO_FIELD_TXTBOX, "value", fieldName);
	}

	@Step("Get text from DOB dropdown - field {0} to verify")
	public String getDropdownListSelectedByField(String fieldName) {
		waitForElementVisible(CustomerInfoUI.DOB_DROPDOWN_LIST_SELECTED, fieldName);
		return getElementText(CustomerInfoUI.DOB_DROPDOWN_LIST_SELECTED, fieldName);
	}
}
