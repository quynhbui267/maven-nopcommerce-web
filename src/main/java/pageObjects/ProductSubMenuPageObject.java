package pageObjects;

import org.openqa.selenium.WebDriver;
import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.ProductSubMenuUI;

public class ProductSubMenuPageObject extends BasePage{
	 WebDriver driver;
		
	public ProductSubMenuPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@Step("Click to product with product title  = {0}")
	public void clickProductDetailByTitle(String productTitle) {
		waitForElementVisible(ProductSubMenuUI.PRODUCT_TITLE_BY_TITLE, productTitle);
		clickToElement(ProductSubMenuUI.PRODUCT_TITLE_BY_TITLE, productTitle);
	} 
	
	

}
