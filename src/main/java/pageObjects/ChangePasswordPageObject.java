package pageObjects;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import pageUIs.ChangePasswordUI;

public class ChangePasswordPageObject extends SidebarMyAccountPageObject {
	WebDriver driver;

	public ChangePasswordPageObject(WebDriver driver) {
		super(driver);
		driver = this.driver;
	}

	@Step("Input to field {1} = {0}")
	public void inputToChangePasswordField(String inputValue, String fieldName) {
		waitForElementVisible(ChangePasswordUI.PASSWORD_TXT_FIELD, fieldName);
		sendKeyToElement(ChangePasswordUI.PASSWORD_TXT_FIELD, inputValue, fieldName);

	}

	@Step("Click change password button")
	public void clickChangePasswordBtn() {
		waitForElementClickable(ChangePasswordUI.CHANGE_PASS_BTN);
		clickToElement(ChangePasswordUI.CHANGE_PASS_BTN);
	}

	@Step("After changing password, verify successful message is shown")
	public String getSuccessfulMsg() {
		waitForElementVisible(ChangePasswordUI.CHANGE_SUCCESSFUL_MSG);
		return getElementText(ChangePasswordUI.CHANGE_SUCCESSFUL_MSG);

	}

	@Step("Close messsage then log out and log in with old password/ new password")
	public void clickCloseBtn() {
		waitForElementClickable(ChangePasswordUI.CLOSE_MSG_BTN);
		clickToElement(ChangePasswordUI.CLOSE_MSG_BTN);

	}

}
