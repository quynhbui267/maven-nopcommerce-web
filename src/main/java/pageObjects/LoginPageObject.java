package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
	 WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	@Step ("Input to email textbox with value is {0}")
	public void inputToEmailAddress(String emailAddress) {
		sendKeyToElement(LoginPageUI.EMAIL_TXTBOX, emailAddress);
		
	}
	@Step ("Input to password textbox with value is {0}")
	public void inputToPassword(String password) {
		sendKeyToElement(LoginPageUI.PASSWORD_TXTBOX, password);
		
	}
	@Step ("Click to Login button")
	public HomePageObject clickToLoginBtn() {
		waitForElementClickable(LoginPageUI.LOGIN_BTN);
		clickToElement(LoginPageUI.LOGIN_BTN);
		return PageGeneratorManager.getUserHomePage(driver);
	}
		
	@Step ("Get Email error message to verify")
	public String getEmailErrorMessage() {
		waitForElementVisible(LoginPageUI.EMAIL_ERROR_MSG);
		return getElementText(LoginPageUI.EMAIL_ERROR_MSG);
		
	}
	public String getCredentialErrorMessage() {
		waitForElementVisible(LoginPageUI.CREDENTIAL_ERROR_MSG);
		return getElementText(LoginPageUI.CREDENTIAL_ERROR_MSG);
	}
	

}
