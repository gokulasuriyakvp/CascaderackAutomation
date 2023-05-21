package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.ProductDetailsPage;
import pages.ProductsListingPage;

public class ProductDetailsPageTest extends BasePage {
	String productTitle;
	String productCategory;
	String productAvailability;

	@BeforeClass(alwaysRun = true)
	public void setup2() {
		ProductsListingPage productsListingPage = new ProductsListingPage(driver);
		BasePage.takeSnapShot(driver, "ProductDetailsPageTest/homePage.png");
		productsListingPage.gotoCategory();
		BasePage.takeSnapShot(driver, "ProductDetailsPageTest/categoryPage.png");
		WebElement productItem = productsListingPage.getProductItem();
		productTitle = productsListingPage.getProductTitle(productItem);
		productAvailability = productsListingPage.getProductAvailability(productItem);
		productCategory = productsListingPage.getProductCategory();
		productItem.findElement(By.xpath(".//a")).click();
		BasePage.takeSnapShot(driver, "ProductDetailsPageTest/productDetailsPage.png");
	}

	@Test(priority = 1)
	public void checkProductWithListingPage() {
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
		Assert.assertEquals(productTitle.trim(), productDetailsPage.getProductTitle());
//		Assert.assertEquals(productCategory.trim(), productDetailsPage.getProductCategory());
		Assert.assertEquals(productTitle.trim(), productDetailsPage.getProductBreadcrumbPathText());

	}

	@Test(priority = 2)
	public void checkProductItemCount() {
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);

		// Check decrement
		productDetailsPage.decreaseProductQuantity();
		productDetailsPage.decreaseProductQuantity();
		productDetailsPage.decreaseProductQuantity();
		Assert.assertEquals(productDetailsPage.getProductCurrentSelectedQuantity(), 0);
		BasePage.takeSnapShot(driver, "ProductDetailsPageTest/countDecrease.png");

		// check increment
		productDetailsPage.increaseProductQuantity();
		productDetailsPage.increaseProductQuantity();
		Assert.assertEquals(productDetailsPage.getProductCurrentSelectedQuantity(), 2);
		BasePage.takeSnapShot(driver, "ProductDetailsPageTest/countIncrease.png");
	}

	@Test(priority = 3)
	public void checkControls() {
		ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);

		Assert.assertTrue(productDetailsPage.isAddToCartButtonDisplayed());
		Assert.assertTrue(productDetailsPage.isProductWishlistButtonDisplayed());
		Assert.assertTrue(productDetailsPage.isProductCompareButtonDisplayed());
	}

}
