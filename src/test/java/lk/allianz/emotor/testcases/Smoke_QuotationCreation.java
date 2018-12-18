package lk.allianz.emotor.testcases;

import static lk.allianz.emotor.utilities.ExcelReader.excelData;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import lk.allianz.emotor.base.EmotorBasePage;
import lk.allianz.emotor.pages.CreateQuotation;
import lk.allianz.emotor.utilities.ExcelReader;

public class Smoke_QuotationCreation extends EmotorBasePage {
	
	
	private CreateQuotation quotation;
	private int i=0;
	private int j=0;
	private int k=0;
	private int l=0;
	
	public Smoke_QuotationCreation () {
		super();
	}
	
	@BeforeMethod
	public void setup() throws EncryptedDocumentException, InvalidFormatException, IOException {
	
	quotation= new CreateQuotation(driver);
	

	}
	
	
	//Test 1
	@Test(invocationCount = 5,priority=1)
		public void test_create_quotation_with_customer_details()throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		test = report.createTest("Create quotation with customer: "+(i));
		dataFile = new ExcelReader("src\\main\\java\\lk\\allianz\\emotor\\resources\\smoke_test_data_sheet.xlsx" ,1);
		i++;
				Thread.sleep(15000);

				// Click create quoation
				quotation.clickToCreateQuotation();

				// Add initial details
				quotation.addInitialDetails(excelData("customer", i), excelData("market_code", i));

				// Tick checkbox 'With customer details'
				quotation.clickCheckBox_WithCustomerDetails();

				// add initial customer details
				quotation.addInitialCustomerDetails(excelData("salutation", i), excelData("nic", i));

				// Add customer details
				quotation.addCustomerDetails(excelData("first_name", i), excelData("last_name", i), excelData("contact_number_1", 1), excelData("house_number", 1), excelData("street", 1));

				// Add vehicle details
				quotation.addVehicleDetails(excelData("region", i), excelData("car_number", i),excelData("vehicle_usage", i) );

				// Add quotation details
				quotation.addQuotationDetails(excelData("insured_amount", i), excelData("driving_exp", i),	excelData("garage", i), excelData("package_type", i), excelData("voluntary_excess", i),	excelData("NCD", i));

				// Submit
				quotation.submitQuotation();

				// Generate quotation
				quotation.generateQuotation();

				// Assert Quotation is generated
				Assert.assertEquals(true, quotation.checkQuotationIsGenerated(),"Failed for the following package: "+excelData("package_type", i));

			
		}
	
	
	
	//Test 2
//	@Test(invocationCount = 3,priority=2)
		public void test_create_quotation_without_customer_details() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, IOException {
		test = report.createTest("Create quotation without customer: "+(i));
		dataFile = new ExcelReader("src\\main\\java\\lk\\allianz\\emotor\\resources\\smoke_test_data_sheet.xlsx" ,0);
		j++;
			Thread.sleep(10000);

			// Click create quoation
			quotation.clickToCreateQuotation();

			
			// Add initial details
			quotation.addInitialDetails(excelData("customer", j), excelData("market_code", j));
			
			Thread.sleep(5000);
			
			quotation.clickCheckBox_WithoutCustomerDetails();
			
			Thread.sleep(5000);

			quotation.addVehicleDetails(excelData("region", j), excelData("car_number", j),excelData("vehicle_usage", j) );

			// Add quotation details
			quotation.addQuotationDetails(excelData("insured_amount", j), excelData("driving_exp", j),excelData("garage", j), excelData("package_type", j), excelData("voluntary_excess", j), excelData("NCD", j));
			
			// Add covers
			quotation.addCovers(excelData("covers", j));


			// Submit
			quotation.submitQuotation();

			Thread.sleep(5000);

			
			// Generate quotation
			quotation.generateQuotation();

			// Assert Quotation is generated
			Assert.assertEquals(true, quotation.checkQuotationIsGenerated(),"Failed for the following package: "+excelData("package_type", j));
			
		}
	
	
	
	//Test 3
	//@Test(priority=3)
	public void test_create_company_quotation() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		k++;
		test = report.createTest("Create company quotation: "+(k));

		dataFile = new ExcelReader("src\\main\\java\\lk\\allianz\\emotor\\resources\\smoke_test_data_sheet.xlsx" ,2);

		Thread.sleep(10000);

		// Click create quoation
		quotation.clickToCreateQuotation();
		
		// Add initial details
		quotation.addInitialDetails(excelData("customer", k), excelData("market_code", k));

		
		//Add company details
		quotation.addCompanyDetails("reg", "vat", "name", "emailid@email.com", "1111222333", "44455666", "house", "street");
		
		// Add vehicle details
		quotation.addVehicleDetails(excelData("region", k), excelData("car_number", k),excelData("vehicle_usage", k) );
		
		Thread.sleep(6000);


		// Add quotation details
		quotation.addQuotationDetails(excelData("insured_amount", k), excelData("driving_exp", k),	excelData("garage", k), excelData("package_type", k), excelData("voluntary_excess", k),	excelData("NCD", k));

		Thread.sleep(4000);

		// Add covers
		quotation.addCovers(excelData("covers", k));

		Thread.sleep(4500);

		// Submit
		quotation.submitQuotation();

		Thread.sleep(10000);

		
		// Generate quotation
		quotation.generateQuotation();

		// Assert Quotation is generated
		Assert.assertEquals(true, quotation.checkQuotationIsGenerated(),"Failed for the following package: "+excelData("package_type", k));
		
	}
	
	
	
	//Test 4
	//@Test
	public void test_create_quotation_for_unregistered_vehicle() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException {
		l++;
		test = report.createTest("Create quotation for unregistered vehicle: "+(l));

		dataFile = new ExcelReader("src\\main\\java\\lk\\allianz\\emotor\\resources\\smoke_test_data_sheet.xlsx" ,3);

		Thread.sleep(10000);

		// Click create quoation
		quotation.clickToCreateQuotation();
		
		// Add initial details
		quotation.addInitialDetails(excelData("customer", l), excelData("market_code", l));

		
		//Add company details
		quotation.addCompanyDetails("reg", "vat", "name", "emailid@email.com", "1111222333", "44455666", "house", "street");
		
		Thread.sleep(6000);

		
		// Add vehicle details
		quotation.addUnregisteredVehicle(excelData("region", l), excelData("car_number", l), excelData("type", l), excelData("make", l), excelData("model", l), excelData("capacity", l), excelData("year", l), excelData("vehicle_usage", l));
		
		Thread.sleep(6000);


		// Add quotation details
		quotation.addQuotationDetails(excelData("insured_amount", l), excelData("driving_exp", l),	excelData("garage", l), excelData("package_type", l), excelData("voluntary_excess", l),	excelData("NCD", l));

		Thread.sleep(6000);

		// Add covers
		quotation.addCovers(excelData("covers", l));

		Thread.sleep(4500);

		// Submit
		quotation.submitQuotation();

	
		// Generate quotation
		quotation.generateQuotation();
		
		Thread.sleep(5000);


		// Assert Quotation is generated
		Assert.assertEquals(true, quotation.checkQuotationIsGenerated(),"Failed for the following package: "+excelData("package_type", l));
		
		Thread.sleep(5000);
		
		quotation.getQuotationReferenceNumber();
	}
	
	
	//@Test
	public void testPDF() throws InvalidPasswordException, IOException, EncryptedDocumentException, InvalidFormatException {
		dataFile = new ExcelReader("src\\main\\java\\lk\\allianz\\emotor\\resources\\smoke_test_data_sheet.xlsx" ,0);

		//quotation.getQuotationReferenceNumber();
		//ExcelReader.writeData("reference", 1);
		
		String test=quotation.getPremiumInQuotation();
		System.out.println(test);
		System.out.println("arshad");
	}
	
	
	
	
	
	

}
