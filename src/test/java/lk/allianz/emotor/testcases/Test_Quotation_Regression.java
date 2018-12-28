package lk.allianz.emotor.testcases;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lk.allianz.emotor.base.EmotorBasePage;
import lk.allianz.emotor.pages.ConfirmQuotation;
import lk.allianz.emotor.pages.CreateQuotation;
import lk.allianz.emotor.utilities.ExcelReader;
import lk.allianz.emotor.utilities.utilities;


public class Test_Quotation_Regression extends EmotorBasePage{

	
	int i=1;
	
	private CreateQuotation quotation;
	private ConfirmQuotation confirm;
	

	
	public  Test_Quotation_Regression() {
		super();
	}
	
	


	
	@BeforeMethod
	public void setup() throws EncryptedDocumentException, InvalidFormatException, IOException {

		quotation = new CreateQuotation(driver);
		confirm = new ConfirmQuotation(driver);

	}
	
	
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, InvalidFormatException, IOException {
		Object[][] data=utilities.getDataFromExcel("src\\main\\java\\lk\\allianz\\emotor\\resources\\test_data_sheet.xlsx", "1");
		return data;
	}
	
	
	@Test (dataProvider="getData")
	public void create_quotation_regression (String testcase, String customer, String market_code, String salutation, String nic, String email,  String first_name,
			String last_name, String contact_number_1, String house_number, String street, String city,String region, String car_number, String ttt, String vehicle_usage, String insured_amount,
			String driving_exp, String garage, String package_type, String voluntary_excess, String NCD,  String policy_start_date, String covers) throws InterruptedException, InvalidPasswordException, IOException, EncryptedDocumentException, InvalidFormatException {
		
		dataFile=new ExcelReader("src\\main\\java\\lk\\allianz\\emotor\\resources\\test_data_sheet.xlsx" ,1);
		test = report.createTest("Create quotation - Reg. Test case: "+(i+1));

		i++;
		
		Thread.sleep(10000);

		// Click create quoation
		quotation.clickToCreateQuotation();

		Thread.sleep(5000);
		
		// Add initial details
		quotation.addInitialDetails(customer, market_code);
		
		Thread.sleep(8000);


		// Tick checkbox 'With customer details'
		quotation.clickCheckBox_WithCustomerDetails();

		// add initial customer details
		quotation.addInitialCustomerDetails(salutation, nic);

		Thread.sleep(5000);

		// Add customer details
		quotation.addCustomerDetails(first_name, last_name,contact_number_1, house_number, street);

		// Add vehicle details
		quotation.addVehicleDetails(region, car_number,vehicle_usage );
		
		Thread.sleep(8000);


		// Add quotation details
		quotation.addQuotationDetails(insured_amount, driving_exp,garage, package_type, voluntary_excess,NCD);

		Thread.sleep(10000);

		// Add covers
		quotation.addCovers(covers);

					
					
		// Submit
		quotation.submitQuotation();

		// Generate quotation
		quotation.generateQuotation();
		
		

		// Assert Quotation is generated
		Assert.assertEquals(true, quotation.checkQuotationIsGenerated(),"Failed for the following package: "+package_type);
		
		System.out.println(quotation.getQuotationReferenceNumber());
		
		String quotation_premium=quotation.getPremiumInQuotation();

		
		Thread.sleep(10000);


		    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs2.get(1));
		    driver.close();
		    driver.switchTo().window(tabs2.get(0));
		
		
		Thread.sleep(5000);
		
		confirm.clickConfirmQuotationbutton();
		
		Thread.sleep(5000);

		//Select reference by car number
		confirm.selectQuotationReferenceByVehicleNumber(car_number);
		
		//Select policy start date
		confirm.selectPolicyStartDate(Integer.parseInt(policy_start_date));
		
		//Enter customer details
		confirm.enterCustomerDetails();
		
		//Check customer details
	//	confirm.checkcustomerDetails(excelData("nic", i), excelData("first_name", i), excelData("last_name", i), excelData("contact_number_1", i),excelData("house_number", i),excelData("street", i));
		
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
		
		Thread.sleep(40000);
		
		confirm.submitTheQuestions();
		
		Thread.sleep(10000);
		
		confirm.selectReason();
		
		confirm.printCoverNOte();
		
		Thread.sleep(10000);

		String covernote_premium=confirm.getPremiumInCovernote();
	//	System.out.println(covernote_premium);
		
		Assert.assertEquals(quotation_premium, covernote_premium, "Premiums did not match for:"+package_type);
		
		Thread.sleep(10000);
		
		
	}
	

	
	//@Test 
	public void secondTest() {
		test = report.createTest("Second test");

		System.out.println("dummy test");
	}
	
	
	
/*public void create_quotation_regression () throws InterruptedException, InvalidPasswordException, IOException, EncryptedDocumentException, InvalidFormatException {
		
		dataFile=new ExcelReader("src\\main\\java\\lk\\allianz\\emotor\\resources\\test_data_sheet.xlsx" ,1);
		test = report.createTest("Create quotation - Reg. Test case: "+(i+1));

		i++;
		
		Thread.sleep(10000);

		// Click create quoation
		quotation.clickToCreateQuotation();

		Thread.sleep(5000);
		
		// Add initial details
		quotation.addInitialDetails(excelData("customer", i), excelData("market_code", i));
		
		Thread.sleep(8000);


		// Tick checkbox 'With customer details'
		quotation.clickCheckBox_WithCustomerDetails();

		// add initial customer details
		quotation.addInitialCustomerDetails(excelData("salutation", i), excelData("nic", i));

		Thread.sleep(5000);

		// Add customer details
		quotation.addCustomerDetails(excelData("first_name", i), excelData("last_name", i),
				excelData("contact_number_1", i), excelData("house_number", i), excelData("street", i));

		// Add vehicle details
		quotation.addVehicleDetails(excelData("region", i), excelData("car_number", i),excelData("vehicle_usage", i) );
		
		Thread.sleep(8000);


		// Add quotation details
		quotation.addQuotationDetails(excelData("insured_amount", i), excelData("driving_exp", i),
				excelData("garage", i), excelData("package_type", i), excelData("voluntary_excess", i),
				excelData("NCD", i));

		Thread.sleep(10000);

		// Add covers
		quotation.addCovers(excelData("covers", i));

					
					
		// Submit
		quotation.submitQuotation();

		// Generate quotation
		quotation.generateQuotation();
		
		

		// Assert Quotation is generated
		Assert.assertEquals(true, quotation.checkQuotationIsGenerated(),"Failed for the following package: "+excelData("package_type", i));
		
		System.out.println(quotation.getQuotationReferenceNumber());
		
		String quotation_premium=quotation.getPremiumInQuotation();

		
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
	//	confirm.checkcustomerDetails(excelData("nic", i), excelData("first_name", i), excelData("last_name", i), excelData("contact_number_1", i),excelData("house_number", i),excelData("street", i));
		
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
		
		Thread.sleep(40000);
		
		confirm.submitTheQuestions();
		
		Thread.sleep(10000);
		
		confirm.selectReason();
		
		confirm.printCoverNOte();
		
		Thread.sleep(10000);

		String covernote_premium=confirm.getPremiumInCovernote();
	//	System.out.println(covernote_premium);
		
		Assert.assertEquals(quotation_premium, covernote_premium, "Premiums did not match for:"+excelData("package_type", i));
		
		Thread.sleep(10000);
		
		
	}*/
	
	
	
	
}
