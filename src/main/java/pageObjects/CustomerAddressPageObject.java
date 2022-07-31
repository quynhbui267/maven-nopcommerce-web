package pageObjects;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import pageUIs.CustomerAddressUI;

public class CustomerAddressPageObject extends SidebarMyAccountPageObject {
	WebDriver driver;

	//Neu class cha co constructor thi class con phai goi qua constructor cua class cha
	public CustomerAddressPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Click Add new address button")
	public void clickAddNewBtn() {
		waitForElementVisible(CustomerAddressUI.ADD_NEW_BTN);
		clickToElement(CustomerAddressUI.ADD_NEW_BTN);
		
	}

	@Step("Input to field {1} = {0}")
	public void inputToAddressTextField(String value, String fieldName) {
		waitForElementVisible(CustomerAddressUI.ADDRESS_FIELD_TXTBOX, fieldName);
	    sendKeyToElement(CustomerAddressUI.ADDRESS_FIELD_TXTBOX, value, fieldName);
		
	}

	@Step("Select country value = {0}")
	public void selectCountryDropdown(String country) {
		waitForElementVisible(CustomerAddressUI.COUNTRY_DROPDOWNLIST);
		selectItemInDefaultDropdown(CustomerAddressUI.COUNTRY_DROPDOWNLIST, country);
		
	}
	
	@Step("Select state value = {0}")
	public void selectStateDropdown(String state) {
		waitForElementVisible(CustomerAddressUI.STATE_DROPDOWNLIST);
		selectItemInDefaultDropdown(CustomerAddressUI.STATE_DROPDOWNLIST, state);
	}
	
	@Step("Click save button")
	public void clickSaveBtn() {
		waitForElementVisible(CustomerAddressUI.SAVE_BTN);
		clickToElement(CustomerAddressUI.SAVE_BTN);
	}

	@Step("Get text from field {0} to verify infomation is saved correctly")
	public String getTextbyFieldName(String fieldName) {
		waitForElementVisible(CustomerAddressUI.ADDRESS_FIELD_AFTER_ADDED, fieldName);
		return getElementText(CustomerAddressUI.ADDRESS_FIELD_AFTER_ADDED, fieldName);
	}
}
