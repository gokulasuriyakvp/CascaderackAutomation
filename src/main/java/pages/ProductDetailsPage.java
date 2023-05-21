package pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {
	By productCategory = By.xpath("//span[@class='replacebreadcrumbs']/a[2]");
	By productBreadcrumbPath = By.xpath("//span[@class='replacebreadcrumbs']/span[@class='active']");
	By productQuantityIncButton = By.xpath("//div[@class='qty-changer']/a[@class='qty-inc']");
	By productQuantityDecButton = By.xpath("//div[@class='qty-changer']/a[@class='qty-dec']");
	By productQuantitySelected = By.xpath("//div[@class='box-tocart']//div[@class='control']/input[@name='qty']");
	By productAddToCartButton = By.xpath("//div[@class='actions']/button[@title='Add to Cart']");
	By productWishlistButton = By.xpath("//div[@class='moved-add-to-links']//a[@data-action='add-to-wishlist']");
	By productCompareButton = By.xpath("//div[@class='moved-add-to-links']//a[@data-role='add-to-links']");

	WebDriver driver;
	WebDriverWait wait;

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	public String getProductTitle() {
		return driver.getTitle();
	}

	public String getProductCategory() {
		return driver.findElement(productCategory).getText();
	}

	public String getProductBreadcrumbPathText() {
		return driver.findElement(productBreadcrumbPath).getText();
	}

	public void increaseProductQuantity() {
		driver.findElement(productQuantityIncButton).click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}

	public void decreaseProductQuantity() {
		driver.findElement(productQuantityDecButton).click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}

	public int getProductCurrentSelectedQuantity() {
		return Integer.parseInt(driver.findElement(productQuantitySelected).getAttribute("value"));
	}

	public boolean isAddToCartButtonDisplayed() {
		return driver.findElement(productAddToCartButton).isDisplayed();
	}

	public boolean isProductWishlistButtonDisplayed() {
		return driver.findElement(productWishlistButton).isDisplayed();
	}

	public boolean isProductCompareButtonDisplayed() {
		return driver.findElement(productCompareButton).isDisplayed();
	}
}
