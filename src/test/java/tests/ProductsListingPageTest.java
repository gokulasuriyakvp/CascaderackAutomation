package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.ProductsListingPage;

public class ProductsListingPageTest extends BasePage {
	@Test(priority = 1)
	public void checkSectionTitle() {
		ProductsListingPage productsListingPage = new ProductsListingPage(driver);
		BasePage.takeSnapShot(driver, "ProductsListingPageTest/homePage.png");
		String expectedSectionTitle = "SHOP BY CATEGORIES";
		String actualSectionTitle = productsListingPage.getPageSectionTitleText();
		Assert.assertEquals(expectedSectionTitle, actualSectionTitle);
	}

	@Test(priority = 2)
	public void checkFeaturedProductsTitle() {
		ProductsListingPage productsListingPage = new ProductsListingPage(driver);
		Assert.assertTrue(productsListingPage.isFeaturedProductsTitleDisplayed());
	}

	@Test(priority = 3)
	public void checkCategoryCard() {
		ProductsListingPage productsListingPage = new ProductsListingPage(driver);

		WebElement activeCategory = productsListingPage.getActiveCategory();
		Assert.assertTrue(productsListingPage.isCategoryHasImage(activeCategory)); // image
		Assert.assertTrue(productsListingPage.isCategoryHasTitle(activeCategory)); // title
		Assert.assertTrue(productsListingPage.isCategoryLink(activeCategory)); // link
	}

	@Test(priority = 4)
	public void checkCategoryPage() {
		ProductsListingPage productsListingPage = new ProductsListingPage(driver);

		WebElement activeCategory = productsListingPage.getActiveCategory();
		String currentCategoryTitle = productsListingPage.getCategoryTitle(activeCategory);
		productsListingPage.clickCategory(activeCategory);

		Assert.assertEquals(currentCategoryTitle, productsListingPage.getcategoryPageTitle()); // title as selected one
		Assert.assertTrue(productsListingPage.isPriceSidebarAvailable());
		Assert.assertTrue(productsListingPage.isSearchButtonVisible());

	}

	@Test(priority = 5)
	public void checkSortByOptionInCategoryPage() {
		ProductsListingPage productsListingPage = new ProductsListingPage(driver);

		Assert.assertTrue(productsListingPage.isSortBySelectoryVisible());
		BasePage.takeSnapShot(driver, "ProductsListingPageTest/sort.png");

		productsListingPage.sortByQuantity();
		String currentUrl = driver.getCurrentUrl();
		String expectedUrlQuery = "?product_list_order=quantity";
		Assert.assertTrue(currentUrl.contains(expectedUrlQuery));
	}

	@Test(priority = 6)
	public void checkProduct() {
		ProductsListingPage productsListingPage = new ProductsListingPage(driver);

		BasePage.takeSnapShot(driver, "ProductsListingPageTest/productComponents.png");
		WebElement productItem = productsListingPage.getProductItem();
		Assert.assertTrue(productsListingPage.isProductHaveImage(productItem));
		Assert.assertTrue(productsListingPage.isProductHavePrice(productItem));
		Assert.assertTrue(productsListingPage.isProductHaveAddToCartButton(productItem));
	}
}
