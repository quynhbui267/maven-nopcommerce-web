package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import pageUIs.SearchUI;

public class SearchPageObject extends BasePage {
	WebDriver driver;

	public SearchPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String getSearchErrorMessage() {
		waitForElementVisible(SearchUI.SEARCH_ERROR_MSG);
		return getElementText(SearchUI.SEARCH_ERROR_MSG);
	}

	public void inputSearchText(String inputValue) {
		waitForElementVisible(SearchUI.SEARCH_KEYWORD_TXTBOX);
		sendKeyToElement(SearchUI.SEARCH_KEYWORD_TXTBOX,inputValue);
	}

	public String getNoResultMessage() {
		waitForElementVisible(SearchUI.SEARCH_NO_RESULT_MSG);
		return getElementText(SearchUI.SEARCH_NO_RESULT_MSG);
	}
	public void clickSearchBtn() {
		waitForElementClickable(SearchUI.SEARCH_BTN);
		clickToElement(SearchUI.SEARCH_BTN);
		
	}

}
