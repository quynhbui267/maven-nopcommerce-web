package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void inputFirstNameTextbox(String firstName) {
		waitForElementVisible(RegisterPageUI.FIRSTNAME_TXTBOX);
		sendKeyToElement(RegisterPageUI.FIRSTNAME_TXTBOX, firstName);
	}

	public void inputLastNameTextbox(String lastName) {
		waitForElementVisible(RegisterPageUI.LASTNAME_TXTBOX);
		sendKeyToElement(RegisterPageUI.LASTNAME_TXTBOX, lastName);
	}

	public void inputEmailTextbox(String email) {
		waitForElementVisible(RegisterPageUI.EMAIL_TXTBOX);
		sendKeyToElement(RegisterPageUI.EMAIL_TXTBOX, email);
	}

	public void inputPasswordTextbox(String password) {
		waitForElementVisible( RegisterPageUI.PASSWORD_TXTBOX);
		sendKeyToElement(RegisterPageUI.PASSWORD_TXTBOX, password);
	}

	public void inputConfirmPasswordTextbox(String password) {
		waitForElementVisible(RegisterPageUI.CPASSWORD_TXTBOX);
		sendKeyToElement(RegisterPageUI.CPASSWORD_TXTBOX, password);
	}

	public void clickToRegisterButton() {
		waitForElementClickable(RegisterPageUI.REGISTER_BTN);
		clickToElement(RegisterPageUI.REGISTER_BTN);
	}

	public String getRegisterSuccessfulMsgText() {
		waitForElementVisible(RegisterPageUI.REGISTER_SUCCESS_MSG);
		return getElementText(RegisterPageUI.REGISTER_SUCCESS_MSG);
	}

}
