package Emotor555pp;
import java.awt.event.ActionEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;
//import org.testng.asserts.Assertion;

public class CreateQuotation {
private WebDriver driver;
private Select customerType;
private Select marketerCode;
private Select Salutation;
private WebElement leasingCompany;
private WebElement leasingCompanyOption;
private WebElement financeInterest;
private WebElement financeInterestOption;
By quotationLink=By.linkText("Quotations");
By createquotationLink=By.linkText("Create Quotation");
By nic=By.id("nic1");
By firstName=By.id("firstName1");
By lastName=By.id("lastname1");
By contactNumber1=By.id("contactnumber1");
By add1=By.id("address1");
By add2=By.id("address2");

	
	public  CreateQuotation(WebDriver driver) {
		this.driver=driver;
		
	}
	public void pageNavigation()
	{
		
		driver.findElement(quotationLink).click();
		driver.findElement(createquotationLink).click();
		
		
	}
	public void addinitialDetails(String strCustomerType,String strMarkerterCode) throws InterruptedException
	{
		 
		customerType = new Select(driver.findElement(By.id("partyType")));
	    customerType.selectByVisibleText(strCustomerType);
	    marketerCode=new Select(driver.findElement(By.id("accountmanager")));
	    marketerCode.selectByVisibleText(strMarkerterCode);
	    driver.findElement(createquotationLink).click();
	    
	    //
	    leasingCompany=driver.findElement(By.id("select2-chosen-3"));
	    Thread.sleep(5000);
	    leasingCompany.click();
	    Thread.sleep(1000);
	    leasingCompanyOption=driver.findElement(By.id("select2-results-3"));
	    Thread.sleep(1000);
	    leasingCompanyOption.click();
	    
	    ///
	    financeInterest=driver.findElement(By.id("select2-chosen-4"));
	    Thread.sleep(5000);
	    financeInterest.click();
	    Thread.sleep(1000);
	    financeInterestOption=driver.findElement(By.id("select2-results-4"));
	    Thread.sleep(1000);
	    financeInterestOption.click();
	    ///
	    
	    //Actions builder = new Actions(driver);
	    //Actions
	   
	 
	}
	  public void addinitialCustomerDetails(String strSalutation,String strNic)
	  {
		    Salutation = new Select(driver.findElement(By.id("salutation1")));
			Salutation.selectByVisibleText(strSalutation);
			driver.findElement(nic).sendKeys(strNic);
	  }
	public void addcustomerDetails(String strfirstName,String strlastName,String strcontactNumber1,String strAdd1,String strAdd2)
	{
		 		
	    driver.findElement(firstName).sendKeys(strfirstName);
	    driver.findElement(lastName).sendKeys(strlastName);
	    driver.findElement(contactNumber1).sendKeys(strcontactNumber1);
	    driver.findElement(add1).sendKeys(strAdd1);
	    driver.findElement(add2).sendKeys(strAdd2);
	    
		
	}
}
