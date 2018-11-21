package lk.allianz.emotor.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lk.allianz.emotor.base.TestBase;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;

import static lk.allianz.emotor.pages.utilities.*;

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
	    SelectByText(driver, dd_customerType, strCustomerType);
	    
	    //Select marketer code
	    SelectByText(driver,dd_marketerCode,strMarkerterCode);
	    	
     
/*	    //click leasing company dropdown
	    ClickElementById(driver, "select2-chosen-3");
	    
	    //click leasing company dropdown result
	    ClickElementById(driver,"select2-results-3");

	    
	    //click finance interest dropdown
	    ClickElementById(driver,"select2-chosen-4");
	    
	    //click finance interest dropdown result
	    ClickElementById(driver,"select2-results-4");*/
	    
    

	}

	public void addInitialCustomerDetails(String strSalutation, String strNic) {

		//select salutation
		SelectByText(driver,driver.findElement(By.id("salutation1")),strSalutation);
		
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
	
	
	
	//add vehicle details -custom
	public void addVehicleDetails(String prefix, String number, String type, String make, String model, String capacity, String year, String usage) throws InterruptedException {
		
		By vehicle_number = By.id("vehiclenumber1");
		By vehicle_type = By.id("vehicle-type");
		By vehicle_make = By.xpath("//*[@id=\"vehiclemake\"]");
		By vehicle_modle =By.id("vehiclemodel");
		By vehicle_capacity = By.id("seatcapacity1");
		By vehicle_year = By.id("vage1");
		By vehicle_usage =By.id("vusage1");
		
		//select prefix
		SelectByText(driver, driver.findElement(By.id("regionCode1")), prefix);
		
		//Enter number
		EnterValue(driver, vehicle_number, number);
		
		//Wait for the load to happen
		Thread.sleep(5000);

		
		//Select vehicle type
		SelectByText(driver, vehicle_type, type);
		
		//select vehicle make
		SelectByText(driver, vehicle_make, make);
		
		//Select vehicle model
		SelectByText(driver, vehicle_modle, model);
		
		//select vehicle capacity
		SelectByText(driver, vehicle_capacity, capacity);
		
		//select vehicle manufacture year
		SelectByText(driver, vehicle_year, year);
		
		//select vehicle usage
		SelectByText(driver, vehicle_usage, usage);
		
		
	}
	
	
	//add vehicle details - default
	public void addVehicleDetails(String prefix, String number, String usage) throws InterruptedException {
		By vehicle_number = By.id("vehiclenumber1");
		By vehicle_type = By.id("vehicle-type");
		By vehicle_make = By.xpath("//*[@id=\"vehiclemake\"]");
		By vehicle_modle =By.id("vehiclemodel");
		By vehicle_usage =By.id("vusage1");
		
		
		String make;
		String model[];
		
		
		//select prefix
				SelectByText(driver, driver.findElement(By.id("regionCode1")), prefix);
				
				//Enter number
				EnterValue(driver, vehicle_number, number);
				
				//Wait for the load to happen
				Thread.sleep(5000);

				model = driver.findElement(By.cssSelector("span[ng-bind='vModelShowValue']")).getText().split(" ");
				make = driver.findElement(By.cssSelector("span[ng-bind='vMakeShowValue']")).getText();
				
				//Select vehicle type
				SelectByText(driver, vehicle_type, "Passenger Car");
				
				Thread.sleep(2000);

				//select vehicle make
				SelectByText(driver, vehicle_make, make);
				
				//select vehicle make
				SelectByTextIgnoreCase(driver, vehicle_modle, model[0]);
				
				//select vehicle usage
				SelectByText(driver, vehicle_usage, usage);
				

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
		SelectByText(driver, driving_exp, years);
		
		//Select garage type
		SelectByText(driver, garage_type, garage);
				
		//Select package type
		SelectByText(driver, package_type, pakage);
		
		//Select voluntary excess
		SelectByText(driver, voluntary_excess, voluntary);
		
		//Select NCD percentage
		SelectByText(driver, NCD_value, NCD);
		
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
		
		// wait for the load to happen
		Thread.sleep(15000);

		Boolean state = false;
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());

		if (driver.switchTo().window(tabs2.get(1)).getCurrentUrl().contains("getAllProductsWithCovers")) {
			state = true;
		}

		else {
			state = false;
		}
		return state;
	}
	
	
	public void addExtendedCovers() {
		
		
	}

}
