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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test_createQuotation {
	private WebDriver driver;
	private LoginPage login;
	private CreateQuotation quotation;
	private ConfirmQuotation quotationToConfirm;
	int i=3;
	
	

    
	@BeforeMethod
	public void setup() throws EncryptedDocumentException, InvalidFormatException, IOException{
		System.setProperty("webdriver.chrome.driver", "src\\main\\java\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();
		i++;
		ExcelReader xxx = new ExcelReader("");
	}
	
	//@Test(invocationCount = 1,priority=2)
	public void testCreateQuotation()throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		  

			System.out.println("I before increment............"+i);
			driver.get("http://192.168.128.68:8081/emotor/");
			driver.manage().window().maximize();
			
			login = new LoginPage(driver);
			quotation = new CreateQuotation(driver);
			//ExcelReader xxx = new ExcelReader("");

			// login to page
			login.loginToEmotor("T221", "allianz@2018");

			Thread.sleep(10000);

			// Click create quoation
			quotation.clickToCreateQuotation();

			// Add initial details
			quotation.addInitialDetails(excelData("customer", i), excelData("market_code", i));

			// Tick checkbox 'With customer details'
			quotation.clickCheckBox_WithCustomerDetails();

			// add initial customer details
			quotation.addInitialCustomerDetails(excelData("salutation", i), excelData("nic", i));

			// Add customer details
			quotation.addCustomerDetails(excelData("first_name", i), excelData("last_name", i),
					excelData("contact_number_1", 1), excelData("house_number", 1), excelData("street", 1));

/*			// Add vehicle details
			quotation.addVehicleDetails(excelData("region", i), excelData("car_number", i),
					excelData("vehicle_type", i), excelData("vehicle_make", i), excelData("vehicle_model", i),
					excelData("seat_capacity", i), excelData("YOM", i), excelData("vehicle_usage", i));*/
			
			quotation.addVehicleDetails(excelData("region", i), excelData("car_number", i),excelData("vehicle_usage", i) );

			// Add quotation details
			quotation.addQuotationDetails(excelData("insured_amount", i), excelData("driving_exp", i),
					excelData("garage", i), excelData("package_type", i), excelData("voluntary_excess", i),
					excelData("NCD", i));

			// Submit
			quotation.submitQuotation();

			// Generate quotation
			quotation.generateQuotation();

			// Assert Quotation is generated
			Assert.assertEquals(true, quotation.checkQuotationIsGenerated(),"Failed for the following package: "+excelData("package_type", i));

		
	}
	
	@Test(priority=1)
	public void testConfirmQuotation() throws InterruptedException {
		driver.get("http://192.168.128.68:8081/emotor/");
		driver.manage().window().maximize();
		
		login = new LoginPage(driver);

		login.loginToEmotor("T221", "allianz@2018");
		
		
		Thread.sleep(10000);

		quotationToConfirm=new ConfirmQuotation(driver);

		quotationToConfirm.clickToConfirmQuotation();
		quotationToConfirm.selectQuotationReferenceByVehicleNumber(excelData("car_number", 3));
		

		quotationToConfirm.selectPolicyStartDate();
		
		quotationToConfirm.enterCustomerDetails();
		
		quotationToConfirm.tickAgreedToConvertQuotationToPolicy();
		
		Thread.sleep(10000);

	}
	
	@AfterMethod
	public void quit() {
		System.out.println("I increased...." + i);
		driver.quit();

	}
	
    
}
