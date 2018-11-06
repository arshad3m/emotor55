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

public class LoginpageTest {
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
		quotation.addInitialDetails(getData("customer", 1), getData("market_code", 1));
		
		//Tick checkbox 'With customer details'
		quotation.clickCheckBox_WithCustomerDetails();

		//add initial customer details
		quotation.addInitialCustomerDetails(getData("salutation", 1), getData("nic", 1));
		
		//Add customer details
		quotation.addCustomerDetails(getData("first_name", 1),getData("last_name", 1),getData("contact_number_1", 1),getData("house_number", 1),getData("street", 1));
		
		
		//Add vehicle details
		quotation.addVehicleDetails("CP", "KR-9691", "Passenger Car", "PERODUA", "AMIZHR", "4", "2012", "Private");
		
		
		//Add quotation details
		quotation.addQuotationDetails("2,300,000", "6", "Standard", "Allianz Standard Package", "2000","60%");
		
		
		//Submit
		quotation.submitQuotation();
		
		//Generate quotation
		quotation.generateQuotation();
		
		//Assert Quotation is generated
		Assert.assertEquals(true, quotation.checkQuotationIsGenerated());

		//driver.quit();
	}
	      
    
}
