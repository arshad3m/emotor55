package lk.allianz.emotortests;

import static lk.allianz.emotor.ExcelReader.*;
import static org.testng.Assert.assertEquals;

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

import lk.allianz.emotor.ConfirmQuotation;
import lk.allianz.emotor.CreateQuotation;
import lk.allianz.emotor.DocumentUpload;
import lk.allianz.emotor.ExcelReader;
import lk.allianz.emotor.LoginPage;

public class Test_Quotation_Smoke {
	private WebDriver driver;
	private LoginPage login;
	private CreateQuotation quotation;
	private ConfirmQuotation quotationToConfirm;
	private DocumentUpload documentUpload;
	int i=0;
	int j=0;
	
	

    
	@BeforeMethod
	public void setup() throws EncryptedDocumentException, InvalidFormatException, IOException{
		System.setProperty("webdriver.chrome.driver", "src\\main\\java\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();
		i++;
		j++;
		ExcelReader xxx = new ExcelReader("src\\main\\java\\Resources\\test_data_sheet.xlsx" ,0);
		
		driver.get("http://192.168.128.68:8081/emotor/");
		driver.manage().window().maximize();
		
		login = new LoginPage(driver);
		quotation= new CreateQuotation(driver);
		documentUpload = new DocumentUpload(driver);
		

		login.loginToEmotor("T221", "allianz@2018");
	}
	
	
	//Create quotations with customer details
	//@Test(invocationCount = 8,priority=1)
	public void testCreateQuotationWithCustomerDetails()throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		  

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

			// Add vehicle details
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
	
	//@Test
	public void testCreateQuotationWithoutCustomerDetails() throws InterruptedException {
		Thread.sleep(10000);

		// Click create quoation
		quotation.clickToCreateQuotation();

		
		// Add initial details
		quotation.addInitialDetails(excelData("customer", i), excelData("market_code", i));
		
		Thread.sleep(5000);
		
		quotation.clickCheckBox_WithoutCustomerDetails();
		quotation.addInitialCustomerDetails(excelData("salutation", i), excelData("nic", i));
		quotation.addCustomerDetails(excelData("first_name", i), excelData("last_name", i),
				excelData("contact_number_1", 1), excelData("house_number", 1), excelData("street", 1));
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
	
	
	@Test(invocationCount = 1,priority=2)
	public void testConfirmQuotation() throws InterruptedException {
		
		
		
		Thread.sleep(10000);

		quotationToConfirm=new ConfirmQuotation(driver);

		quotationToConfirm.clickToConfirmQuotation();
		
		Thread.sleep(5000);

		//Select reference by car number
		quotationToConfirm.selectQuotationReferenceByVehicleNumber(excelData("car_number", j));
		
		//Select policy start date
		quotationToConfirm.selectPolicyStartDate(Integer.parseInt(excelData("policy_start_date", j)));
		
		//Enter customer details
		quotationToConfirm.enterCustomerDetails();
		
		//Convert quotation to policy
		quotationToConfirm.tickAgreedToConvertQuotationToPolicy();
		
		Thread.sleep(5000);

		//Confirm quotation
		quotationToConfirm.clickConfirmQuotationButton();
		
		Thread.sleep(5000);

		//Click button to proceed
		quotationToConfirm.clickProceedButton();
		
		//Click button to convert the policy
		quotationToConfirm.clickConvertToPolicyButton();
		
		Thread.sleep(10000);

	}
	
	//@Test(priority=1)
	public void testDocumentUpload() throws InterruptedException {
		
		Thread.sleep(10000);
		
		documentUpload.clickDocumentUpload();
		
		documentUpload.clickUpload();
		
		Thread.sleep(8000);

		documentUpload.searchByVehicleNumber();
		
		documentUpload.enterSearchText();
		
		documentUpload.clickOnSearchButton();
		
		Thread.sleep(20000);
		
		documentUpload.checkSearchResultsAreShown();

		Thread.sleep(10000);
		
		//Click first document
		documentUpload.clickPendingPolicyDocumentButton(0);
		
		Thread.sleep(8000);

		
		documentUpload.checkMandatoryDocuments(excelData("mandatory_documents", 3));
		
		Thread.sleep(20000);
	}

	
	
	@AfterMethod
	public void quit() {
		driver.quit();

	}
	
    
}