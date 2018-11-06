package Emotor555pp;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Emotor555pp.utilities.*;

import java.util.ArrayList;
//import org.testng.asserts.Assertion;

public class CreateQuotation {
private WebDriver driver;

private By quotationLink;
private By createquotationLink=By.linkText("Create Quotation");
//private By createquotationLink;

private By nic=By.id("nic1");


private WebDriverWait wait;

	
	public  CreateQuotation(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver, 30);
	}
	public void clickToCreateQuotation()
	{
		quotationLink=By.linkText("Quotations");
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
		
		//Enter NIC number
		EnterValue(driver, nic, strNic);
	}

	public void addCustomerDetails(String strfirstName, String strlastName, String strcontactNumber1, String strAdd1,
			String strAdd2) throws InterruptedException {
		
		By firstName=By.id("firstName1");
		By lastName=By.id("lastname1");
		By contactNumber1=By.id("contactnumber1");
		By add1=By.id("address1");
		By add2=By.id("address2");
		
		Thread.sleep(5000);
		
		//Enter first name
		EnterValue(driver, firstName, strfirstName);
		
		//Enter last name
		EnterValue(driver, lastName, strlastName);
		
		//Enter first conact number
		EnterValue(driver, contactNumber1, strcontactNumber1);
		
		//Enter street address 1
		EnterValue(driver, add1, strAdd1);
		
		//Enter street adress 2
		EnterValue(driver, add2, strAdd2);

	}
	
	public void clickCheckBox_WithCustomerDetails() {
		
		ClickElementByXpath(driver, "//*[@id=\"main-wrapper\"]/div/div/ng-view/div[2]/div[1]/div/div/div/div[1]/div/div[3]/div[1]/div/label");
	}
	
	
	public void clickCheckBox_WithoutCustomerDetails() {
		ClickElementByXpath(driver, "//*[@id=\"main-wrapper\"]/div/div/ng-view/div[2]/div[1]/div/div/div/div[1]/div/div[3]/div[2]/div/label");
	}
	
	
	public void addVehicleDetails(String prefix, String number, String type, String make, String model, String capacity, String year, String usage) throws InterruptedException {
		
		By vehicle_number = By.id("vehiclenumber1");
		By vehicle_type = By.id("vehicle-type");
		By vehicle_make = By.xpath("//*[@id=\"vehiclemake\"]");
		By vehicle_modle =By.id("vehiclemodel");
		By vehicle_capacity = By.id("seatcapacity1");
		By vehicle_year = By.id("vage1");
		By vehicle_usage =By.id("vusage1");
		
		//select prefix
		SelectByVisibleText(driver, driver.findElement(By.id("regionCode1")), prefix);
		
		//Enter number
		EnterValue(driver, vehicle_number, number);
		
		//Wait for the load to happen
		Thread.sleep(5000);

		
		//Select vehicle type
		SelectByVisibleText(driver, vehicle_type, type);
		
		//select vehicle make
		SelectByVisibleText(driver, vehicle_make, make);
		
		//Select vehicle model
		SelectByVisibleText(driver, vehicle_modle, model);
		
		//select vehicle capacity
		SelectByVisibleText(driver, vehicle_capacity, capacity);
		
		//select vehicle manufacture year
		SelectByVisibleText(driver, vehicle_year, year);
		
		//select vehicle usage
		SelectByVisibleText(driver, vehicle_usage, usage);
		
		
	}
	
	
	public void addQuotationDetails(String value, String years, String garage, String pakage, String voluntary, String NCD) throws InterruptedException {
		
		By insured_value = By.id("suminsvalue1");
		By driving_exp = By.id("insuredexperience1");
		By garage_type = By.id("garagetype1");
		By package_type = By.id("package1");
		By voluntary_excess = By.id("voluntaryexcess");
		By NCD_value = By.id("ncd");
		
		Thread.sleep(5000);
		//Enter insured sum
		EnterValue(driver, insured_value, value);
		
		//Select driving experience in number of years
		SelectByVisibleText(driver, driving_exp, years);
		
		//Select garage type
		SelectByVisibleText(driver, garage_type, garage);
				
		//Select package type
		SelectByVisibleText(driver, package_type, pakage);
		
		//Select voluntary excess
		SelectByVisibleText(driver, voluntary_excess, voluntary);
		
		//Select NCD percentage
		SelectByVisibleText(driver, NCD_value, NCD);
		
	}
	
	
	public void submitQuotation() throws InterruptedException {
		By submit_button = By.xpath("//*[@id=\"main-wrapper\"]/div[2]/div/ng-view/div[2]/div[1]/div/div/div/div[2]/div/div[1]/div/div/button[1]");
		
		//Wait for the load to happen
		Thread.sleep(6000);
		
		//Click on Submit button
		ClickElement(driver, submit_button);
	}
	
	
	public void generateQuotation() throws InterruptedException {
		
		By generate_quotation_button = By.xpath("//*[@id=\"main-wrapper\"]/div/div/ng-view/div[2]/div[3]/div/div[2]/button[1]");
		
		//wait for the load to happen
		Thread.sleep(16000);
		
		ClickElement(driver, generate_quotation_button);
		
	}
	
	
	public boolean checkQuotationIsGenerated() throws InterruptedException {
		
		//wait for the load to happen
		Thread.sleep(10000);
		
		//Navigate to the next tab
		Boolean state=false;
/*		Actions action= new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();*/
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		
		
		System.out.println(driver.getTitle());
		if(driver.getTitle().equals("getAllProductsWithCovers")) {
			state=true;
		}
		
		else {
			state =false;
		}
		return state;
	}
	
}
