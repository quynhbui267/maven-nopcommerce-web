package pageObjects;

import org.openqa.selenium.WebDriver;

public class CustomerInfoPageObject extends SidebarMyAccountPageObject {
	WebDriver driver;

	public CustomerInfoPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
