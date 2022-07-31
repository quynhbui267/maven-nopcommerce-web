package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;

public class ProductMenuPageObject extends BasePage{
	 WebDriver driver;
		
	public ProductMenuPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
