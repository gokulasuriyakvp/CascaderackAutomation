package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsListingPage {
	By pageSectionTitle = By.xpath("//span[@class='homepage-section-title']");
	By featureProductsTitle = By.xpath("//h2[contains(text(), 'Featured Products')]");
	By activeCategory = By.xpath(
			"//div[@class='category-slider porto-products wpb_content_element shop-category-section-blk']//div[@class='owl-item active']");
	By categoryTitle = By.xpath(".//h3");
	By categoryLink = By.xpath(".//a");
	By categoryImage = By.xpath(".//img");
	By categoryPageTitle = By.xpath("//div[@class='breadcrumbs']//li/strong");
	By priceSidebar = By.xpath("//div[contains(@class, 'Price')]");
	By searchButton = By.xpath("//span[normalize-space()='Search']");
	By sortSelector = By.xpath("(//select[@id='sorter'])[1]");
	By productItem = By.xpath("//li[contains(@class, 'product-item')]");
	By productImage = By.xpath(".//img[contains(@class, 'product-image-photo')]");
	By productPrice = By.xpath(".//span[@class='price']");
	By productCartButton = By.xpath(".//button[@title='Add to Cart']");
	By productTitle = By.xpath(".//strong[@class='product name product-item-name']/a[@class='product-item-link']");
	By productAvailability = By.xpath(".//div[@title='Availability']/span");
	By productCategory = By.xpath("//div[@class='breadcrumbs']//li[2]/strong");
	By productCartAddedAlert = By.xpath("//div[@role='alert']");

	WebDriver driver;
	WebDriverWait wait;

	public ProductsListingPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	}

	public String getPageSectionTitleText() {
		return driver.findElement(pageSectionTitle).getText();
	}

	public boolean isFeaturedProductsTitleDisplayed() {
		return driver.findElement(featureProductsTitle).isDisplayed();
	}

	public WebElement getActiveCategory() {
		return driver.findElement(activeCategory);
	}

	public String getCategoryTitle(WebElement categoryElement) {
		return categoryElement.findElement(categoryTitle).getText();
	}

	public boolean isCategoryHasTitle(WebElement categoryElement) {
		return categoryElement.findElement(categoryTitle).isDisplayed();
	}

	public boolean isCategoryHasImage(WebElement categoryElement) {
		return categoryElement.findElement(categoryImage).isDisplayed();
	}

	public boolean isCategoryLink(WebElement categoryElement) {
		return categoryElement.findElement(categoryLink).isDisplayed();
	}

	public void clickCategory(WebElement categoryElement) {
		categoryElement.findElement(categoryLink).click();
	}

	public WebElement gotoCategory() {
		WebElement categoryElement = driver.findElement(activeCategory);
		categoryElement.findElement(categoryLink).click();
		return categoryElement;
	}

	public String getcategoryPageTitle() {
		return driver.findElement(categoryPageTitle).getText();
	}

	public boolean isPriceSidebarAvailable() {
		return driver.findElement(priceSidebar).isDisplayed();
	}

	public boolean isSearchButtonVisible() {
		return driver.findElement(searchButton).isDisplayed();
	}

	public boolean isSortBySelectoryVisible() {
		return driver.findElement(sortSelector).isDisplayed();
	}

	public void sortByQuantity() {
		String quantityValue = "quantity";
		Select sortBySelect = new Select(driver.findElement(sortSelector));
		sortBySelect.selectByValue(quantityValue);
	}

	public WebElement getProductItem() {
		return driver.findElement(productItem);
	}

	public boolean isProductHaveImage(WebElement productElement) {
		return productElement.findElement(productImage).isDisplayed();
	}

	public String getProductTitle(WebElement productElement) {
		return productElement.findElement(productTitle).getText();
	}

	public String getProductAvailability(WebElement productElement) {
		return productElement.findElement(productAvailability).getText();
	}

	public String getProductCategory() {
		return driver.findElement(productCategory).getText();
	}

	public boolean isProductHavePrice(WebElement productElement) {
		return productElement.findElement(productPrice).isDisplayed();
	}

	public boolean isProductHaveAddToCartButton(WebElement productElement) {
		Actions action = new Actions(driver);
		action.moveToElement(productElement).perform();
		return productElement.findElement(productCartButton).isDisplayed();
	}

	public void addProductToCart(WebElement productElement) {
		Actions action = new Actions(driver);
		action.moveToElement(productElement).perform();
		productElement.findElement(productCartButton).click();
//		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(productCartAddedAlert));
	}
}
