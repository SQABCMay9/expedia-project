package com.sqa.jf.core;

import java.util.concurrent.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;

public class BasicTest {
	private static String baseURL = "http://mtv.com";
	private static WebDriver driver;

	/**
	 * @return the baseURL
	 */
	public static String getBaseURL() {
		return baseURL;
	}

	/**
	 * @return the driver
	 */
	public static WebDriver getDriver() {
		return driver;
	}

	@BeforeClass(enabled = false)
	public static void setupChrome() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeClass(enabled = true)
	public static void setupFirefox() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeClass(enabled = false)
	public static void setupNewFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		driver = new MarionetteDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public static void tearDown() {
		// driver.quit();
	}

	/**
	 * Constructor
	 */
	public BasicTest(String baseURL) {
		super();
		BasicTest.baseURL = baseURL;
	}

	@BeforeMethod
	public void setupTest() {
		// Delete all saved cookies
		getDriver().manage().deleteAllCookies();
		// Maximize Window
		getDriver().manage().window().maximize();
		// Go to base URL
		getDriver().get(getBaseURL());

	}

}
