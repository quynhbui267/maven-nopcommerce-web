package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	 WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Input to email = {0}")
	public void inputToEmailAddress(String emailAddress) {
		waitForElementVisible(LoginPageUI.EMAIL_TXTBOX);
		sendKeyToElement(LoginPageUI.EMAIL_TXTBOX, emailAddress);
		
	}

	@Step ("Input to pasword = {0}")
	public void inputToPassword(String password) {
		waitForElementVisible(LoginPageUI.PASSWORD_TXTBOX);
		sendKeyToElement(LoginPageUI.PASSWORD_TXTBOX, password);
		
	}
	@Step ("Click to Login button")
	public void clickToLoginBtn() {
		waitForElementClickable(LoginPageUI.LOGIN_BTN);
		clickToElement(LoginPageUI.LOGIN_BTN);
	}
	
	@Step ("Login with username = {0} and password = {1}")
	public void loginToApp(String email, String password) {
		inputToEmailAddress(email);
		inputToPassword(password);
		clickToLoginBtn();
	}
		
	@Step ("Get email error message text to verify")
	public String getEmailErrorMessage() {
		waitForElementVisible(LoginPageUI.EMAIL_ERROR_MSG);
		return getElementText(LoginPageUI.EMAIL_ERROR_MSG);

	}
	
	@Step ("Get credentials error message text to verify")
	public String getCredentialErrorMessage() {
		waitForElementVisible(LoginPageUI.CREDENTIAL_ERROR_MSG);
		return getElementText(LoginPageUI.CREDENTIAL_ERROR_MSG);
		
	}
	

}
