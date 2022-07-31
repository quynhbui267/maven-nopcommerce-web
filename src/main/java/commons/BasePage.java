package commons;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void openPageUrl(String pageURL) {
		driver.get(pageURL);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getPageUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		return driver.getPageSource();

	}

	public void backToPage() {
		driver.navigate().back();
	}

	public void forwardToPage() {
		driver.navigate().forward();
	}

	public void refreshCurrentPage() {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence() {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert() {
		waitForAlertPresence().accept();
	}

	public void dismissAlert() {
		waitForAlertPresence().dismiss();
	}

	public String getAlertText() {
		return waitForAlertPresence().getText();
	}

	public void sendKeyToAlert(String textValue) {
		waitForAlertPresence().sendKeys(textValue);
	}

	public void switchToWindowByID(String parentId) {
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.equals(parentId)) {
				driver.switchTo().window(window);
			}
		}
	}

	public void switchToWindowByTitle(String title) {
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().equals(title)) {
				break;
			}
		}
	}

	public void closeAllTabExceptParent(String parentId) {
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.equals(parentId)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}
		driver.switchTo().window(parentId);
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// private By getByXpath(String xpathLocator) {
	// return By.xpath(xpathLocator);
	// }

	public String parseStringToLocator(String locator, String str) {
		return String.format(locator, str);
	}

	public String parseStringToObject(String locator, String... str) {
		return String.format(locator, (Object[]) str);
	}

	public String castRestParameter(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}

	public By getByLocator(String locator) {
		By by;
		if (locator.startsWith("id=") || locator.startsWith("ID=") || locator.startsWith("Id=")) {
			by = By.id(locator.substring(3));
		} else if (locator.startsWith("css=") || locator.startsWith("CSS=") || locator.startsWith("Css=")) {
			by = By.cssSelector(locator.substring(4));
		} else if (locator.startsWith("name") || locator.startsWith("NAME=") || locator.startsWith("Name=")) {
			by = By.name(locator.substring(5));
		} else if (locator.startsWith("class") || locator.startsWith("CLASS=") || locator.startsWith("Class=")) {
			by = By.className(locator.substring(6));
		} else if (locator.startsWith("xpath") || locator.startsWith("XPATH=") || locator.startsWith("Xpath=")) {
			by = By.xpath(locator.substring(6));
		} else {
			throw new RuntimeException("Locator is not valid");
		}
		return by;
	}

	private WebElement getWebElement(String locator) {
		return driver.findElement(getByLocator(locator));
	}

	public List<WebElement> getListElement(String locator) {
		return driver.findElements(getByLocator(locator));
	}

	public void clickToElement(String locator) {
		try {
			getWebElement(locator).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickToElement(String locator, String... dynamicValues) {
		getWebElement(castRestParameter(locator, dynamicValues)).click();
	}

	public void sendKeyToElement(String locator, String textValue) {
		WebElement element = getWebElement(locator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendKeyToElement(String locator, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(castRestParameter(locator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectItemInDefaultDropdown(String locator, String textItem) {
		Select select = new Select(getWebElement(locator));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdown(String locator, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(castRestParameter(locator, dynamicValues)));
		select.selectByVisibleText(textItem);
	}

	public String getFirstSelectedItemInDefaultDropdown(String locator) {
		Select select = new Select(getWebElement(locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isMultipleSelection(String locator) {
		Select select = new Select(getWebElement(locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropDown(String parentLocator, String childLocator, String selectItem) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(parentLocator))).click();

		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement item : allItems) {
			if (item.getText().equals(selectItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("agruments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

	public void selectItemInEditedCustomDropDown(String parentLocator, String childLocator, String selectItem) {
		sendKeyToElement(parentLocator, selectItem);
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
		for (WebElement item : allItems) {
			if (item.getText().equals(selectItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("agruments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

	public String getElementAttribute(String locator, String attributeName) {
		return getWebElement(locator).getAttribute(attributeName);
	}

	public String getElementAttribute(String locator, String attributeName, String... dynamicValues) {
		return getWebElement(castRestParameter(locator, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementText(String locator) {
		return getWebElement(locator).getText();
	}

	public String getElementText(String locator, String... dynamicValues) {
		return getWebElement(castRestParameter(locator, dynamicValues)).getText();
	}

	public String getElementCssValue(String locator, String propertyName) {
		return getWebElement(locator).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(String locator) {
		return getListElement(locator).size();
	}

	public void checkToCheckboxRadioByJS(String locator) {
		if (!getWebElement(locator).isSelected()) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", getWebElement(locator));
		}
	}

	public void uncheckToCheckboxRadioByJS(String locator) {
		if (getWebElement(locator).isSelected()) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", getWebElement(locator));
		}
	}

	public void checkToCheckboxRadio(String locator) {
		if (!getWebElement(locator).isSelected()) {
			getWebElement(locator).click();
		}
	}
	public void checkToCheckboxRadio(String locator, String... dynamicValues) {
		if (!getWebElement(castRestParameter(locator, dynamicValues)).isSelected()) {
			getWebElement(locator).click();
		}
	}
	
	public void unscheckToCheckboxRadio(String locator) {
		if (getWebElement(locator).isSelected()) {
			getWebElement(locator).click();
		}
	}

	public void unscheckToCheckboxRadio(String locator, String... dynamicValues) {
		if (getWebElement(castRestParameter(locator, dynamicValues)).isSelected()) {
			getWebElement(castRestParameter(locator, dynamicValues)).click();
		}
	}

	public boolean isElementEnabled(String locator) {
		return getWebElement(locator).isEnabled();
	}

	public boolean isElementDisplayed(String locator) {
		return getWebElement(locator).isDisplayed();
	}

	public boolean isElementDisplayed(String locator, String... dynamicValues) {
		return getWebElement(castRestParameter(locator, dynamicValues)).isDisplayed();
	}

	public void setImplicitWait(long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(String locator) {
		boolean status = true;
		setImplicitWait(5);
		List<WebElement> element = getListElement(locator);
		setImplicitWait(GlobalConstants.LONG_TIMEOUT);
		if (element.size() == 0) {
			status = true;
		} else if (element.size() > 0 && !element.get(0).isDisplayed()) {
			return true;
		} else {
			status = false;
		}
		return status;
	}

	public boolean isElementSelected(String locator) {
		return getWebElement(locator).isSelected();
	}

	public void switchToFrameIframe(String locator) {
		driver.switchTo().frame(getWebElement(locator));
	}

	public void switchToParentFrame() {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(locator)).perform();
	}
	
	public void hoverMouseToElement(String locator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(castRestParameter(locator, dynamicValues))).perform();
	}

	public void doubleClick(String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(locator)).perform();
	}

	public void rightClick(String locator) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(locator)).perform();
	}

	public void scrollToBottomPage() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(locator));
	}

	public void pressKeyToElement(String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(locator), key).perform();
		;
	}

	public void pressKeyToElement(String locator, Keys key, String dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(castRestParameter(locator, dynamicValues)), key).perform();
		;
	}

	public void dragAndDropElement(String sourceLocator, String targetLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getWebElement(sourceLocator), getWebElement(targetLocator)).perform();
	}

	public void scrollToElement(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(locator));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(locator));
	}

	public boolean isImageLoaded(String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}

	public void waitForElementVisible(String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castRestParameter(locator, dynamicValues))));
	}

	public void waitForAllElementVisible(String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
	}

	public void waitForElementInVisible(String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}

	public void waitForElementInVisible(String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castRestParameter(locator, dynamicValues))));
	}

	public void waitForAllElementInVisible(String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElement(locator)));
	}

	public void waitForElementClickable(String locator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}

	public void waitForElementClickable(WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementClickable(String locator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(castRestParameter(locator, dynamicValues))));
	}

	public void uploadMultipleFiles(String... fileNames) {
		String uploadFilePath = GlobalConstants.UPLOAD_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + uploadFilePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		System.out.println(fullFileName);
		getWebElement("xpath=//input[@type='file']").sendKeys(fullFileName);
	}

	public static String getDirectorySlash(String folderName) {
		// // Cach 1
		// String seperator = System.getProperty("file.seperator");
		// // Cach 2
		// seperator = FileSystems.getDefault().getSeparator();
		// Cach 3
		String seperator = File.separator;
		return seperator + folderName + seperator;
	}

	public Set<Cookie> getAllCookie(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void addCookies(Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
			sleepInSecond(2);
		}
	}
}
