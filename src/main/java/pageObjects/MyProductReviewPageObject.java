package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.MyProductReviewUI;

public class MyProductReviewPageObject extends BasePage {

	WebDriver driver;

	public MyProductReviewPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Get text from My Product review title")
	public String getProductReviewTitle() {
		waitForElementVisible(MyProductReviewUI.MY_REVIEW_TITLE);
		return getElementText(MyProductReviewUI.MY_REVIEW_TITLE);
	}

	@Step("Get text from My Product review text")
	public String getProductReviewText() {
		waitForElementVisible(MyProductReviewUI.MY_REVIEW_TEXT);
		return getElementText(MyProductReviewUI.MY_REVIEW_TEXT);
	}

	@Step("Get My Product review rating")
	public String getProductReviewRating() {
		waitForElementVisible(MyProductReviewUI.MY_REVIEW_RATING);
		return getElementAttribute(MyProductReviewUI.MY_REVIEW_RATING,"style");
	}

	@Step("Get product title from My Product review info")
	public String getProductReviewInfoProduct() {
		waitForElementVisible(MyProductReviewUI.MY_REVIEW_INFO_PRODUCT);
		return getElementText(MyProductReviewUI.MY_REVIEW_INFO_PRODUCT);
	}

	@Step("Get review date time from My Product review info")
	public String getProductReviewInfoDate() {
		waitForElementVisible(MyProductReviewUI.MY_REVIEW_INFO_DATE);
		return getElementText(MyProductReviewUI.MY_REVIEW_INFO_DATE);
	}

}
