package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.RegisterPageUI;
import pageUIs.UserHomePageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void inputRegisterField(String inputValue, String fieldName) {
		waitForElementVisible(RegisterPageUI.REGISTER_FIELD_TXTBOX, fieldName );
		sendKeyToElement(RegisterPageUI.REGISTER_FIELD_TXTBOX, inputValue, fieldName );
	}

	public void clickToRegisterButton() {
		waitForElementClickable(RegisterPageUI.REGISTER_BTN);
		clickToElement(RegisterPageUI.REGISTER_BTN);
	}

	public String getRegisterSuccessfulMsgText() {
		waitForElementVisible(RegisterPageUI.REGISTER_SUCCESS_MSG);
		return getElementText(RegisterPageUI.REGISTER_SUCCESS_MSG);
	}

	public String getErrorMessageByFieldName(String fieldName) {
		waitForElementVisible(RegisterPageUI.ERROR_MSG_BY_FIELDNAME, fieldName );
		return getElementText(RegisterPageUI.ERROR_MSG_BY_FIELDNAME, fieldName);
			}

	public HomePageObject clickLogoutLink() {
		waitForElementClickable(getRegisterSuccessfulMsgText());
		return null;
	}

	public String getEmailErrorMessage() {
		waitForAllElementVisible(RegisterPageUI.EMAIL_ERROR_MSG);
		return getElementText(RegisterPageUI.EMAIL_ERROR_MSG);
	}
}
