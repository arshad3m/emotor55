package Emotor555pp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utilities {
	

	

	
	//wait for drop down methods
	public static void WaitForDropDown(WebDriver driver, WebElement element) {
		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		wait1.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	public static void WaitForDropDown(WebDriver driver, By by) {
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.elementToBeClickable(by));
		

	}
	
	
	
	//click methods
	public static void ClickElement(WebDriver driver, By element) {
		WebDriverWait wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		
		

	}
	
	
	
	public static void ClickElement(WebDriver driver, WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	
	//click by id
	public static void ClickElementById(WebDriver driver, String id) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(id)))).click();
	}
	
	//click by id
	public static void ClickElementByName(WebDriver driver, String name) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name(name)))).click();
	}
	
	//click by id
	public static void ClickElementByXpath(WebDriver driver, String xpath) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath)))).click();
		
	}
	
	
	//click action
	public static void ClickByFocusingOnTheElement(WebDriver driver, WebElement element) {
		
		Actions action = new Actions(driver);
		//action.moveToElement(element).click().build().perform();
		action.moveToElement(element).build().perform();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
	}
	
	

	
	
	//Enter value
	public static void EnterValue(WebDriver driver,By element, String value) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(value);

	}
	
	
	//Drop down select
	
	public static void SelectByText(WebDriver driver, WebElement element, String text) {
		WaitForDropDown(driver, element);
		Select select = new Select (element);
	    select.selectByVisibleText(text);
	    
	}
	
	
	public static void SelectByText(WebDriver driver, By element, String text) {
		WaitForDropDown(driver, element);
		Select select = new Select (driver.findElement(element));
	    select.selectByVisibleText(text);
	}
	
	
	public static void SelectByIndex(WebDriver driver, By element, int index) {
		WaitForDropDown(driver, element);
		Select select = new Select (driver.findElement(element));
		select.selectByIndex(index);
	}
	
	public static void SelectByTextIgnoreCase(WebDriver driver, By element, String text) {
		WaitForDropDown(driver, element);
		Select select = new Select (driver.findElement(element));
		
		
		int index = 0;
	    for (WebElement option : select.getOptions()) {
	        if (option.getText().equalsIgnoreCase(text) || option.getText().contains(text))
	            break;
	        index++;
	    }
	   
	    if(select.getOptions().size()<=index) {
	    	select.selectByIndex(1);
	    }
	    else
	    select.selectByIndex(index);
	}
	
	
}
