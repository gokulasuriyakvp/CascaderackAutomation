package pages;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BasePage {
	public static WebDriver driver;
	String url = "https://www.cascaderack.com/";
	String chromeDriverPath = "/home/aravindkumar/Workspace/web-driver/chromedriver";
	By accountButton = By.cssSelector("div[class='user-status user--logged-out top-header-blocks']");

	@BeforeClass(alwaysRun = true)
	public void setup() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.presenceOfElementLocated(accountButton));
	}

	public static void takeSnapShot(WebDriver webDriver, String fileName) {
		try {
			String screenshotPath = "/home/aravindkumar/Downloads/CascaderackAutomation/CascaderackAutomation/screenshots/";
			TakesScreenshot scrShot = ((TakesScreenshot) webDriver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(screenshotPath + fileName);
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (Exception e) {
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
