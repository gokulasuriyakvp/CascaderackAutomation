package pages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

	By cartIcon = By.xpath("//div[@data-block='minicart']/a[@class='action showcart']");
	By cartIconCounter = By
			.xpath("//div[@data-block='minicart']/a[@class='action showcart']//span[@class='counter-number']");
	By cartViewButton = By.xpath("//a[@class='action viewcart']");
	By cartItemCard = By.xpath("//tbody[@class='cart item']/tr[@class='item-info']");
	By cartItemRemoveButton = By.xpath(".//a[@title='Remove']");
	By updateCartButton = By.xpath("//button[@title='Update Cart']");
	By checkoutButton = By.xpath("//button[@title='Go to Checkout']");

	WebDriver driver;
	WebDriverWait wait;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	public boolean isCartIconDisplayed() {
		return driver.findElement(cartIcon).isDisplayed();
	}

	public int getCartOrderCount() {
		return Integer.parseInt(driver.findElement(cartIconCounter).getText());
	}

	public void gotoCartpage() {
		driver.findElement(cartIcon).click();
		driver.findElement(cartViewButton).click();
	}

	public List<WebElement> getCartItems() {
		return driver.findElements(cartItemCard);
	}

	public int getCartOrderCountInCartPage() {
		return driver.findElements(cartItemCard).size();
	}

	public void removeProduct(WebElement productElement) {
		productElement.findElement(cartItemRemoveButton).click();
	}

	public void clearCart() {
		if (getCartOrderCount() > 0) {
			List<WebElement> cartItems = getCartItems();
			for (int i = 0; i < cartItems.size(); i++) {
				WebElement cartItem = cartItems.get(i);
				removeProduct(cartItem);
			}
		}
	}

	public boolean isUpdateCartButtonDisplayed() {
		return driver.findElement(updateCartButton).isDisplayed();
	}

	public void checkoutCart() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(checkoutButton).click();
	}

}
