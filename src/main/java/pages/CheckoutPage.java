package pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {
	By shippingPreferenceLabel = By.xpath("//label[normalize-space()='Select your shipping preference']");
	By shipToMeRadioButton = By.xpath("//input[@id='DoorDeliveryId']");
	By firstNameLabel = By.xpath("//span[normalize-space()='First Name']");
	By countryLabel = By.xpath("//span[normalize-space()='Country']");
	By continueToPaymentButton = By.xpath("//button[@id='continue-to-payment-trigger']");
	By requiredFieldWarningAlert = By.xpath("//div[@class='message notice']");

	WebDriver driver;
	WebDriverWait wait;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	public String checkoutPageUrl() {
		return driver.getCurrentUrl();
	}

	public void waitForShippingPreferenceLabel() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(shippingPreferenceLabel));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public boolean isShippingPreferenceLabelDisplayed() {
		return driver.findElement(shippingPreferenceLabel).isDisplayed();
	}

	public void selectShipToMeOption() {
		driver.findElement(shipToMeRadioButton).click();
	}

	public boolean isFirstNameLabelDisplayed() {
		return driver.findElement(firstNameLabel).isDisplayed();
	}

	public boolean isCountryLabelDisplayed() {
		return driver.findElement(countryLabel).isDisplayed();
	}

	public boolean isContinueToPaymentButtonDisplayed() {
		return driver.findElement(continueToPaymentButton).isDisplayed();
	}

	public void clickContinueToPaymentButton() {
		driver.findElement(continueToPaymentButton).click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}

	public boolean isRequiredFieldWarningAlertDisplayed() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver.findElement(requiredFieldWarningAlert).isDisplayed();
	}

}
