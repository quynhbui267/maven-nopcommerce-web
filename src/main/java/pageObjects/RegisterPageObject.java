package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.RegisterPageUI;
import pageUIs.HomePageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Input to register text field - {1} = {0}")
	public void inputRegisterField(String inputValue, String fieldName) {
		waitForElementVisible(RegisterPageUI.REGISTER_FIELD_TXTBOX, fieldName);
		sendKeyToElement(RegisterPageUI.REGISTER_FIELD_TXTBOX, inputValue, fieldName);
	}

	@Step("Click Register button")
	public void clickToRegisterButton() {
		waitForElementClickable(RegisterPageUI.REGISTER_BTN);
		clickToElement(RegisterPageUI.REGISTER_BTN);
	}

	@Step("Get message when register successfully to verify")
	public String getRegisterSuccessfulMsgText() {
		waitForElementVisible(RegisterPageUI.REGISTER_SUCCESS_MSG);
		return getElementText(RegisterPageUI.REGISTER_SUCCESS_MSG);
	}

	@Step("Get error message for field {0} to verify")
	public String getErrorMessageByFieldName(String fieldName) {
		waitForElementVisible(RegisterPageUI.ERROR_MSG_BY_FIELDNAME, fieldName);
		return getElementText(RegisterPageUI.ERROR_MSG_BY_FIELDNAME, fieldName);
	}

	@Step("Get email error message to verify")
	public String getEmailErrorMessage() {
		waitForAllElementVisible(RegisterPageUI.EMAIL_ERROR_MSG);
		return getElementText(RegisterPageUI.EMAIL_ERROR_MSG);
	}

	@Step("Select dropdown list - {1} = {0}")
	public void selectDropdownListByField(String value, String fieldName) {
		waitForElementVisible(RegisterPageUI.DOB_DROPDOWN_LIST, fieldName);
		selectItemInDefaultDropdown(RegisterPageUI.DOB_DROPDOWN_LIST, value, fieldName);

	}
	@Step("Select gender = {0}")
	public void selectFemaleGender() {
		waitForElementVisible(RegisterPageUI.FEMALE_GENDER_RADIOBTN);
		checkToCheckboxRadio(RegisterPageUI.FEMALE_GENDER_RADIOBTN);
		
	}

}
