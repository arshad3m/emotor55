package lk.allianz.emotor.testcases;

import static lk.allianz.emotor.pages.ExcelReader.excelData;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import lk.allianz.emotor.pages.ConfirmQuotation;
import lk.allianz.emotor.pages.CreateQuotation;
import lk.allianz.emotor.pages.DocumentUpload;
import lk.allianz.emotor.pages.ExcelReader;
import lk.allianz.emotor.pages.LoginPage;
import lk.allianz.emotor.pages.ReviseQuotation;

public class Test_Quotation_Regression {

	
	private WebDriver driver;
	int i=1;
	
	private LoginPage login ;
	private CreateQuotation quotation;
	private ConfirmQuotation confirm;
	private DocumentUpload upload ;
	
	
	
	@BeforeMethod
	public void setup() throws EncryptedDocumentException, InvalidFormatException, IOException{

		

		
		 
			ExcelReader xxx = new ExcelReader("src\\main\\java\\lk\\allianz\\emotor\\resources\\test_data_sheet.xlsx" ,0);
			System.setProperty("webdriver.chrome.driver", "src\\main\\java\\lk\\allianz\\emotor\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
			login = new LoginPage(driver);
			quotation= new CreateQuotation(driver);
			 confirm = new ConfirmQuotation(driver);

			upload = new DocumentUpload(driver);
			driver.get("http://192.168.128.68:8081/emotor/");
			driver.manage().window().maximize();
			login.loginToEmotor("T221", "allianz@2018");
	}
	
	
	@Test (invocationCount = 6)
	public void create_quotation_regression () throws InterruptedException {
		
		i++;
		
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
				excelData("contact_number_1", i), excelData("house_number", i), excelData("street", i));

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
		
		
		Thread.sleep(10000);


		    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs2.get(1));
		    driver.close();
		    driver.switchTo().window(tabs2.get(0));
		
		
		Thread.sleep(5000);
		
		confirm.clickConfirmQuotationbutton();
		
		Thread.sleep(5000);

		//Select reference by car number
		confirm.selectQuotationReferenceByVehicleNumber(excelData("car_number", i));
		
		//Select policy start date
		confirm.selectPolicyStartDate(Integer.parseInt(excelData("policy_start_date", i)));
		
		//Enter customer details
		confirm.enterCustomerDetails();
		
		//Check customer details
		confirm.checkcustomerDetails(excelData("nic", i), excelData("first_name", i), excelData("last_name", i), excelData("contact_number_1", i),excelData("house_number", i),excelData("street", i));
		
		//Convert quotation to policy
		confirm.tickAgreedToConvertQuotationToPolicy();
		
		Thread.sleep(5000);

		//Confirm quotation
		confirm.clickConfirmQuotationButton();
		
		Thread.sleep(10000);

		//Click button to proceed
		confirm.clickProceedButton();
		
		Thread.sleep(10000);
		
		//Click button to convert the policy
		confirm.clickConvertToPolicyButton();
		
		Thread.sleep(20000);
		
		
/*		upload.clickDocumentUpload();
		
		upload.clickUpload();
		
		Thread.sleep(8000);

		upload.searchByVehicleNumber();
		
		upload.enterSearchText(excelData("car_number", i));
		
		upload.clickOnSearchButton();
		
		Thread.sleep(20000);
		
		upload.checkSearchResultsAreShown();

		Thread.sleep(10000);
		
		//Click first document
		upload.clickPendingPolicyDocumentButton(0);
		
		Thread.sleep(8000);
		
		upload.checkMandatoryDocuments(excelData("mandatory_documents", i));
		*/
		Thread.sleep(20000);
		
		
	}
	
	@AfterMethod
	public void quit() {
		driver.quit();

	}
}
