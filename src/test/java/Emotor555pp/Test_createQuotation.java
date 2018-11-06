package Emotor555pp;

import static org.testng.Assert.assertEquals;
import static Emotor555pp.ExcelReader.*;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test_createQuotation {
	private WebDriver driver;
	private String actualTittle;
	private String expectedTittle;
	private LoginPage login;
	private CreateQuotation quotation;

    
	@BeforeTest
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "src\\main\\java\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://192.168.128.68:8081/emotor/");
		driver.manage().window().maximize();

	}
	
	@Test
	public void testCreateQuotation() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		login = new LoginPage(driver);
		quotation = new CreateQuotation(driver);
		ExcelReader xxx=new ExcelReader("");
		
		
		//login to page
		login.loginToEmotor("T220", "allianz@2018");
		
		Thread.sleep(5000);
		
		//Click create quoation
		quotation.clickToCreateQuotation();
		
		//Add initial details
		quotation.addInitialDetails(excelData("customer", 1), excelData("market_code", 1));
		
		//Tick checkbox 'With customer details'
		quotation.clickCheckBox_WithCustomerDetails();

		//add initial customer details
		quotation.addInitialCustomerDetails(excelData("salutation", 1), excelData("nic", 1));
		
		//Add customer details
		quotation.addCustomerDetails(excelData("first_name", 1),excelData("last_name", 1),excelData("contact_number_1", 1),excelData("house_number", 1),excelData("street", 1));
		
		
		//Add vehicle details
		quotation.addVehicleDetails(excelData("region", 1), excelData("car_number", 1), excelData("vehicle_type", 1), excelData("vehicle_make", 1), excelData("vehicle_model", 1), excelData("seat_capacity", 1), excelData("YOM", 1), excelData("vehicle_usage", 1));
		
		
		//Add quotation details
		quotation.addQuotationDetails(excelData("insured_amount", 1), excelData("driving_exp", 1), excelData("garage", 1), excelData("package_type", 1), excelData("voluntary_excess", 1),excelData("NCD", 1));
		
		
		//Submit
		quotation.submitQuotation();
		
		//Generate quotation
		quotation.generateQuotation();
		
		//Assert Quotation is generated
		Assert.assertEquals(true, quotation.checkQuotationIsGenerated());

		//driver.quit();
	}
	      
    
}
