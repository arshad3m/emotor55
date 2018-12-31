package lk.allianz.emotor.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import lk.allianz.emotor.base.EmotorBasePage;

public class utilities extends EmotorBasePage {
	

	

	
	//wait for drop down methods
	public static void WaitForDropDown(WebDriver driver, WebElement element) {
		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		wait1.until(ExpectedConditions.elementToBeClickable(element));
		test.log(Status.INFO, "Selected: "+element.getText());

	}
	
	
	public static void WaitForDropDown(WebDriver driver, By by) {
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.elementToBeClickable(by));
		

	}
	
	
	
	//click methods
	public static void ClickElement(WebDriver driver, By element) {
		WebDriverWait wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		test.log(Status.INFO, "Clicked: "+element);

		
	}
	
	
	
	public static void ClickElement(WebDriver driver, WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		test.log(Status.INFO, "Clicked: "+element.getText());

	}
	
	
	//click by id
	public static void ClickElementById(WebDriver driver, String id) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(id)))).click();
		test.log(Status.INFO, "Clicked: "+driver.findElement(By.id(id)).getText());

	}
	
	//click by id
	public static void ClickElementByName(WebDriver driver, String name) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name(name)))).click();
		test.log(Status.INFO, "Clicked: "+name);

	}
	
	//click by id
	public static void ClickElementByXpath(WebDriver driver, String xpath) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath)))).click();
		test.log(Status.INFO, "Clicked: "+driver.findElement(By.xpath(xpath)).getText());
		
	}
	
	
	//click action
	public static void ClickByFocusingOnTheElement(WebDriver driver, WebElement element) {
		
		Actions action = new Actions(driver);
		//action.moveToElement(element).click().build().perform();
		action.moveToElement(element).build().perform();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		test.log(Status.INFO, "Clicked: "+element.getText());

	}
	
	

	
	
	//Enter value
	public static void EnterValue(WebDriver driver,By element, String value) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		driver.findElement(element).clear();
		wait.until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(value);
		test.log(Status.INFO, "Entered "+value);
	}
	
	
	//Drop down select
	
	public static void SelectByText(WebDriver driver, WebElement element, String text) {
		WaitForDropDown(driver, element);
		Select select = new Select (element);
	    select.selectByVisibleText(text);
	    test.log(Status.INFO, "Selected "+text);
	}
	
	
	public static void SelectByText(WebDriver driver, By element, String text) {
		WaitForDropDown(driver, element);
		Select select = new Select (driver.findElement(element));
	    select.selectByVisibleText(text);
	    test.log(Status.INFO, "Selected "+text);
	}
	
	
	public static void SelectByIndex(WebDriver driver, By element, int index) {
		WaitForDropDown(driver, element);
		Select select = new Select (driver.findElement(element));
		select.selectByIndex(index);
		test.log(Status.INFO, "Selected dropdown index: "+index);
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
			test.log(Status.INFO, "Selected dropdown index: "+index);

	    }
	    else {
	    select.selectByIndex(index);
		test.log(Status.INFO, "Selected dropdown index: "+index);}
	    
	}
	
	
	//Take screenshot
	public static String takeScreenshot(WebDriver driver) throws IOException {
		String path;
		
		TakesScreenshot tc= (TakesScreenshot)driver;
		File src= tc.getScreenshotAs(OutputType.FILE);
		path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";
		
		File destination = new File (path);
		FileUtils.copyFile(src, destination);
		
		return path;
	}
	
	
	
	public static Object[][] getDataFromExcel(String path, String sheetName) throws EncryptedDocumentException, InvalidFormatException, IOException{
		
		FileInputStream file = null;
		
		Workbook book;
		Sheet sheet;

		String TESTDATA_SHEET_PATH = path;

		file = new FileInputStream(TESTDATA_SHEET_PATH);

		book = WorkbookFactory.create(file);

		sheet = book.getSheet(sheetName);
		//Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		Object[][] data = new Object[10][24];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
//		for (int i = 0; i < sheet.getLastRowNum(); i++) {
		for (int i = 0; i < 10; i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;


		
	}
}
