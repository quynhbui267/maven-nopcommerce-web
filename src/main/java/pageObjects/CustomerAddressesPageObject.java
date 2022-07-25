package pageObjects;

import org.openqa.selenium.WebDriver;

public class CustomerAddressesPageObject extends SidebarMyAccountPageObject {
	WebDriver driver;

	//Neu class cha co constructor thi class con phai goi qua constructor cua class cha
	public CustomerAddressesPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
}
