package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsListingPage;

public class CartPageTest extends BasePage {
	@BeforeClass(alwaysRun = true)
	@Parameters({ "emailid", "password" })
	public void setup4(String emailid, String password) throws InterruptedException {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.login(emailid, password);
	}

	@Test(priority = 1)
	public void checkCartIcon() {
		CartPage cartPage = new CartPage(driver);
		BasePage.takeSnapShot(driver, "CartPageTest/homePage.png");

		Assert.assertTrue(cartPage.isCartIconDisplayed());
		Assert.assertEquals(cartPage.getCartOrderCount(), 0);
	}

	@Test(priority = 2)
	public void checkAddToCartFunctionality() {
		ProductsListingPage productsListingPage = new ProductsListingPage(driver);
		CartPage cartPage = new CartPage(driver);

		productsListingPage.gotoCategory();
		BasePage.takeSnapShot(driver, "CartPageTest/categoryPage.png");
		WebElement productElement = productsListingPage.getProductItem();
		productsListingPage.addProductToCart(productElement);
		BasePage.takeSnapShot(driver, "CartPageTest/addProduct.png");
		Assert.assertEquals(cartPage.getCartOrderCount(), 1);
	}

	@Test(priority = 3)
	public void checkCartPage() {
		CartPage cartPage = new CartPage(driver);

		cartPage.gotoCartpage();
		BasePage.takeSnapShot(driver, "CartPageTest/cartPage.png");
		Assert.assertEquals(cartPage.getCartOrderCountInCartPage(), 1);
		Assert.assertTrue(cartPage.isUpdateCartButtonDisplayed());
		cartPage.clearCart();

		Assert.assertEquals(cartPage.getCartOrderCountInCartPage(), 0);
	}

}
