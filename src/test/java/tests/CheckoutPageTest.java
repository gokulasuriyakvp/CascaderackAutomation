package tests;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsListingPage;

public class CheckoutPageTest extends BasePage {
	@BeforeClass(alwaysRun = true)
	@Parameters({ "emailid", "password" })
	public void setup3(String emailid, String password) throws InterruptedException {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login(emailid, password);
	}

	@Test(priority = 1)
	public void checkCheckoutUrl() {
		ProductsListingPage productsListingPage = new ProductsListingPage(driver);
		CartPage cartPage = new CartPage(driver);
		CheckoutPage checkoutPage = new CheckoutPage(driver);

		BasePage.takeSnapShot(driver, "CheckoutPageTest/productslistpage.png");

		productsListingPage.gotoCategory();
		BasePage.takeSnapShot(driver, "CheckoutPageTest/productsCategoryPage.png");
		WebElement productElement = productsListingPage.getProductItem();
		productsListingPage.addProductToCart(productElement);
		BasePage.takeSnapShot(driver, "CheckoutPageTest/addToCart.png");
		cartPage.gotoCartpage();
		BasePage.takeSnapShot(driver, "CheckoutPageTest/cartPage.png");
		cartPage.checkoutCart();
		BasePage.takeSnapShot(driver, "CheckoutPageTest/checkoutPage.png");

		String actualValue = checkoutPage.checkoutPageUrl();
		String expectedValue = "https://www.cascaderack.com/checkout/";
		Assert.assertTrue(actualValue.contains(expectedValue));
	}

	@Test(priority = 2)
	public void checkCheckoutPageComponents() {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.waitForShippingPreferenceLabel();
		BasePage.takeSnapShot(driver, "CheckoutPageTest/checkoutPage_Components.png");

		Assert.assertTrue(checkoutPage.isShippingPreferenceLabelDisplayed());
		Assert.assertTrue(checkoutPage.isContinueToPaymentButtonDisplayed());

		checkoutPage.selectShipToMeOption();
		BasePage.takeSnapShot(driver, "CheckoutPageTest/checkoutPage_shipToMe.png");
		Assert.assertTrue(checkoutPage.isCountryLabelDisplayed());
		Assert.assertTrue(checkoutPage.isFirstNameLabelDisplayed());
	}

	@Test(priority = 3)
	public void checkContinueToPaymentOptionErrorCase() {
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		CartPage cartPage = new CartPage(driver);

		checkoutPage.clickContinueToPaymentButton();
		Assert.assertTrue(checkoutPage.isRequiredFieldWarningAlertDisplayed());
		BasePage.takeSnapShot(driver, "CheckoutPageTest/checkoutPage_requiredFieldAlert.png");

		try {
			Thread.sleep(Duration.ofSeconds(40));
		} catch (Exception e) {
		}
		cartPage.gotoCartpage();
		cartPage.clearCart();
	}

}
