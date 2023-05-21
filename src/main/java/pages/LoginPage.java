package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	By accountButton = By.cssSelector("div[class='user-status user--logged-out top-header-blocks']");
	By accountLoggedInButton = By.xpath(
			"//div[@class='user-status user--logged-in top-header-blocks']");
	By signInLink = By.xpath(
			"//div[@class='myaccount-section-menu active']/div/ul[@class='header links']/li[@class='link authorization-link']/a[contains(text(),'Sign In')]");
	By signOutLink = By.xpath(
			"//div[@class='myaccount-section-menu active']/div/div/ul[@class='header links']/li[@class='link authorization-link']/a[contains(text(),'Sign Out')]");
	By emailIdTextBox = By.xpath("//input[@name='login[username]']");
	By passwordTextBox = By.id("pass");
	By loginButton = By.id("send2");

	WebDriver driver;
	WebDriverWait wait;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	public void clickOnSignInButton() {
		driver.findElement(accountButton).click();
		driver.findElement(signInLink).click();
	}

	public void enterEmailId(String emailId) {
		WebElement emailElement = driver.findElement(emailIdTextBox);
		emailElement.clear();
		emailElement.sendKeys(emailId);
	}

	public void enterPassword(String password) {
		WebElement passwordElement = driver.findElement(passwordTextBox);
		passwordElement.clear();
		passwordElement.sendKeys(password);
	}

	public void login(String emailId, String password) throws InterruptedException {
		clickOnSignInButton();
		enterEmailId(emailId);
		enterPassword(password);
		driver.findElement(loginButton).click();
	}

	public boolean verifyLogin() {
		return driver.findElement(accountLoggedInButton).isDisplayed();
	}

}
