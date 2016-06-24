/**
 *   File Name: ExpediaTest.java<br>
 *
 *   LastName, FirstName<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Jun 23, 2016
 *
 */

package com.sqa.jf.expedia;

import org.openqa.selenium.*;
import org.testng.*;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import com.sqa.jf.core.*;
import com.sqa.jf.helpers.*;

/**
 * ExpediaTest //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author LastName, FirstName
 * @version 1.0.0
 * @since 1.0
 *
 */
public class ExpediaTest extends BasicTest {

	/**
	 * @param baseURL
	 */
	public ExpediaTest() {
		super("http://expedia.com");
	}

	@DataProvider
	public Object[][] expediaData() {
		return DataHelper.getExcelFileData("src/main/resources/", "expedia-data.xlsx", true);
	}

	@Test(dataProvider = "expediaData")
	public void expediaTest(String originString, String destinationString, String departDate, String returnDate,
			double minExpectedPrice, double maxExpectedPrice) throws InterruptedException {

		double actualTicketPrice;

		// Test flight prices
		WebElement flightTab = getDriver().findElement(By.id("primary-header-flight"));
		flightTab.click();
		WebElement origin = getDriver().findElement(By.id("flight-origin"));
		WebElement destination = getDriver().findElement(By.id("flight-destination"));
		WebElement departField = getDriver().findElement(By.id("flight-departing"));
		WebElement returnField = getDriver().findElement(By.id("flight-returning"));
		WebElement search = getDriver().findElement(By.id("search-button"));

		origin.clear();
		origin.sendKeys(originString);
		destination.clear();
		destination.sendKeys(destinationString);
		departField.clear();
		departField.sendKeys(departDate);
		returnField.clear();
		returnField.sendKeys(returnDate);
		search.click();

		WebElement priceField = getDriver().findElement(By.cssSelector("span.dollars.price-emphasis"));

		actualTicketPrice = Double.parseDouble(priceField.getText().replace("$", ""));
		Assert.assertTrue(actualTicketPrice > minExpectedPrice && actualTicketPrice < maxExpectedPrice, "Ticket price ["
				+ actualTicketPrice + "] is not within range (" + minExpectedPrice + " - " + maxExpectedPrice + ")");

	}

}
