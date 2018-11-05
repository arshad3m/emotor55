package Emotor555pp;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Emotor555pp.utilities.*;
//import org.testng.asserts.Assertion;

public class CreateQuotation {
private WebDriver driver;

private By quotationLink=By.linkText("Quotations");
private By createquotationLink=By.linkText("Create Quotation");
//private By createquotationLink;

private By nic=By.id("nic1");
private By firstName=By.id("firstName1");
private By lastName=By.id("lastname1");
private By contactNumber1=By.id("contactnumber1");
private By add1=By.id("address1");
private By add2=By.id("address2");

private WebDriverWait wait;

	
	public  CreateQuotation(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver, 30);
	}
	public void clickToCreateQuotation()
	{
		//Click on Quotations section
		ClickElement(driver, quotationLink);
		
		//Click on Create quotation
		ClickElement(driver, createquotationLink);
		
		
	}
	


	
	
	public void addInitialDetails(String strCustomerType,String strMarkerterCode) throws InterruptedException
	{
		
		
		WebElement dd_customerType = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("partyType")));
		WebElement dd_marketerCode = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("accountmanager")));
		
		
	    //Select customer type dropdown
	    SelectByVisibleText(driver, dd_customerType, strCustomerType);
	    
	    //Select marketer code
	    SelectByVisibleText(driver,dd_marketerCode,strMarkerterCode);
	    	
    
    
	    //click leasing company dropdown
	    ClickElementById(driver, "select2-chosen-3");
	    
	    //click leasing company dropdown result
	    ClickElementById(driver,"select2-results-3");

	    
	    //click finance interest dropdown
	    ClickElementById(driver,"select2-chosen-4");
	    
	    //click finance interest dropdown result
	    ClickElementById(driver,"select2-results-4");
	    
    

	}

	public void addInitialCustomerDetails(String strSalutation, String strNic) {

		//select salutation
		SelectByVisibleText(driver,driver.findElement(By.id("salutation1")),strSalutation);
		
		EnterValue(driver, nic, strNic);
	}

	public void addCustomerDetails(String strfirstName, String strlastName, String strcontactNumber1, String strAdd1,
			String strAdd2) {

		EnterValue(driver, firstName, strfirstName);
		EnterValue(driver, lastName, strlastName);
		EnterValue(driver, contactNumber1, strcontactNumber1);
		EnterValue(driver, add1, strAdd1);
		EnterValue(driver, add2, strAdd2);

	}
	
	public void clickCheckBox_WithCustomerDetails() {
		
		ClickElementByXpath(driver, "//*[@id=\"main-wrapper\"]/div/div/ng-view/div[2]/div[1]/div/div/div/div[1]/div/div[3]/div[1]/div/label");
	}
	
	
	public void clickCheckBox_WithoutCustomerDetails() {
		ClickElementByXpath(driver, "//*[@id=\"main-wrapper\"]/div/div/ng-view/div[2]/div[1]/div/div/div/div[1]/div/div[3]/div[2]/div/label");
	}
}
