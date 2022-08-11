package pageObjects;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.ProductDetailUI;

public class ProductDetailPageObject extends BasePage {

	WebDriver driver;

	public ProductDetailPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Click Add Review link")
	public void clickAddReviewLink() {
		waitForElementClickable(ProductDetailUI.ADD_REVIEW_LINK);
		clickToElement(ProductDetailUI.ADD_REVIEW_LINK);

	}

	@Step("Input review title = {0}")
	public void inputToReviewTitle(String inputValue) {
		waitForElementVisible(ProductDetailUI.REVIEW_TITLE, inputValue);
		sendKeyToElement(ProductDetailUI.REVIEW_TITLE, inputValue);

	}

	@Step("Input review text = {0}")
	public void inputToReviewText(String inputValue) {
		waitForElementVisible(ProductDetailUI.REVIEW_TEXT, inputValue);
		sendKeyToElement(ProductDetailUI.REVIEW_TEXT, inputValue);
	}

	@Step("Rating for product = 4*")
	public void selectRatingRadioBtn() {
		waitForElementClickable(ProductDetailUI.REVIEW_RATING_RADIO_BTN_4);
		clickToElement(ProductDetailUI.REVIEW_RATING_RADIO_BTN_4);

	}

	@Step("Click Submit review button")
	public void clickSubmitReviewBtn() {
		waitForElementClickable(ProductDetailUI.SUBMIT_REVIEW);
		clickToElement(ProductDetailUI.SUBMIT_REVIEW);

	}

	@Step("Get date time when submit review to verify in My Product Review")
	public String getDateTimeSubmit() {
		LocalDateTime date = LocalDateTime.now();
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy HH:mm aa");
		return formater.format(date);
	}

}
