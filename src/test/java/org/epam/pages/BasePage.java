package org.epam.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public abstract class BasePage {
	
protected WebDriver driver ;
	
	public BasePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public boolean isElementPresent(By byLocator)
	{
		return driver.findElements(byLocator).size()>0;
	}
	
	public boolean isElementPresent(WebElement ele)
	{
		boolean blnStatus = false;
		try{
			ele.isEnabled();
		}
		catch(NoSuchElementException e)
		{
			blnStatus = true;
		}
		return blnStatus;
	}
	public boolean isTextPresent(String strText)
	{
			String strXpath = "//*[text()[contains(.,'"+strText+"')]]";
			return (driver.findElements(By.xpath(strXpath)).size()!=0);
	}
	
	public void waitForElementVisible(WebElement webElement,int timeout)
	{
		WebDriverWait wait = (new WebDriverWait( driver,timeout));
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	
	public void waitForElementIsNotStale(WebElement webElement,int timeout)
	{
		WebDriverWait wait = (new WebDriverWait( driver,timeout));
		wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(webElement)));
	}
	
	public void waitForElementEnabled(WebElement webElement,int timeout)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
				Function<WebDriver,Boolean> function = new Function<WebDriver,Boolean>()
		{
			public Boolean apply(WebDriver t) {
				return (webElement.isEnabled());
			}
		};
		wait.until(function);
	}
	
	public void scrollToElement(WebElement webElement)
	{
		JavascriptExecutor jse =(JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView()", webElement);
	}
	

}
