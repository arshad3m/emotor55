package lk.allianz.emotor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import lk.allianz.emotor.base.EmotorBasePage;

import static lk.allianz.emotor.pages.utilities.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class ConfirmQuotation {
	
	private WebDriver driver;
	
	public ConfirmQuotation(WebDriver driver) {
		
		this.driver = driver;
	}
	
	
	
	public void clickToConfirmQuotation() {
		
		By quotationLink = By.linkText("Quotations");
	    By confirmQuotationLink = By.linkText("Confirm Quotation");

		
		ClickElement(driver, quotationLink);
		ClickElement(driver, confirmQuotationLink);
		

	}
	
	
	public void clickConfirmQuotationbutton() {
	    By confirmQuotationLink = By.linkText("Confirm Quotation");
		ClickElement(driver, confirmQuotationLink);
	}
	
	
	public void selectQuotationReferenceByVehicleNumber(String vehicle_number) throws InterruptedException {
		
		//By dd_reference = By.xpath("//*[@id=\"select2-chosen-2\"]");
		By dd_reference = By.xpath("//*[text()='Select Quotation Reference']");
		
		//input[starts-with(@id, 'activation:') and contains(@id, ':voId:1')]
		By textBox_search =By.xpath("//input[starts-with(@id,'s2id_autogen') and @role='combobox']");
		//By textBox_search =By.xpath("//*[@id=\"s2id_autogen1088_search\"]");
		
		List <WebElement> list = driver.findElements(textBox_search);
		System.out.println(list.size());
		
		
		
		ClickElement(driver, dd_reference);

		Thread.sleep(2000);

		
		//EnterValue(driver, textBox_search, vehicle_number);
		list.get(0).sendKeys(vehicle_number);
		
		list.get(0).sendKeys(Keys.RETURN);
		
		Thread.sleep(5000);
	}
	
	
	public void selectPolicyStartDate(int date) throws InterruptedException {
		By dateFrom = By.id("date-range-from");
		
		ClickElement(driver, dateFrom);
		
		int i = 0;
		List <WebElement> dates=driver.findElements(By.xpath("//table[@class='table-condensed']//*[text()="+date+"]"));
		Iterator <WebElement> itr = dates.iterator();
		
		while(itr.hasNext()) {
			WebElement element = itr.next();
			if (element.getAttribute("class").equals("day")) {
				break;
			}
			
			else i++;
			System.out.println(element.getAttribute("class"));
		}
		
		dates.get(i).click();
		Thread.sleep(5000);
		//driver.findElement(dateFrom).sendKeys(Keys.ENTER);
	}
	
	
	public void selectFuturePolicyDate() throws InterruptedException {
		

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate)); // 2016/11/16
		int futureDate = Integer.parseInt(dtf.format(localDate)) + 2;
		By dateFrom = By.id("date-range-from");
		ClickElement(driver, dateFrom);
		driver.findElement(By.xpath("//table[@class='table-condensed']//*[text()=" + futureDate + "]")).click();
		Thread.sleep(5000);
		
	}
	
	
	
	
	public void enterCustomerDetails() {
		
		By dd_salutation = By.id("salutation1");
		By email=By.id("emailaddress1");
		SelectByText(driver, dd_salutation, "Mr.");
		
		String email_address = driver.findElement(email).getAttribute("value");
		
		if(email.equals("")) {
		EnterValue(driver, email, "exampleemail@email.com");
		}
	}
	
	
	public void tickAgreedToConvertQuotationToPolicy() {
				
 
		WebElement web = driver.findElement(By.xpath("//input[@id='exampleCheck1']"));
		ClickByFocusingOnTheElement(driver, web);
		
	}
	
	
	public void clickConfirmQuotationButton() {
																
		WebElement confirm_button = driver.findElement(By.xpath("//button[contains(@ng-click,'postInsuredProperties()')]"));
		ClickElement(driver, confirm_button);
		
	}
	
	
	public void clickProceedButton() {
		
		List <WebElement> list = driver.findElements(By.xpath("//button[contains(@data-toggle,'modal')]"));
		System.out.println(list.size());
		ClickElement(driver, list.get(0));
		
	}
	
	
	public void verifyCustomerDetailsBeforeConvertingToPolicy(String nic) {
		WebElement nic_number = driver.findElement(By.xpath("//h4[@class='ng-binding']"));
	}
	
	
	public void clickConvertToPolicyButton() {
		
		By convert_to_policy_button = By.xpath("//button[contains(@ng-click,'proceedtoPolicy()')]");
		ClickElement(driver, convert_to_policy_button);
	}
	
	
	
	public void checkcustomerDetails(String nic_number, String frist_name, String last_name, String contact_number, String house, String street) {
		
		By nic=By.xpath("//input[@id='nic1']");
		By fName= By.xpath("//input[@id='firstName1']");
		By lName= By.xpath("//input[@id='lastname1']");
		By contact=By.xpath("//input[@id='contactnumber1']");
		SoftAssert softassert= new SoftAssert();
		
		String nic_value = driver.findElement(nic).getAttribute("value");
		String fname_value = driver.findElement(fName).getAttribute("value");
		String lname_value = driver.findElement(lName).getAttribute("value");
		String contact_value = driver.findElement(contact).getAttribute("value");
		String house_number = driver.findElement(By.xpath("//input[@id='address1']")).getAttribute("value");
		String street_value = driver.findElement(By.xpath("//input[@id='address2']")).getAttribute("value");
		//String city_value = driver.findElement(By.xpath("//span[@id='select2-chosen-1']")).getText();
		
		//assert NIC
		softassert.assertEquals(nic_value, nic_number, "NIC number not matching:");
				
		//assert First name
		softassert.assertEquals(fname_value, frist_name, "first name not matching:");
		
		//assert Last name
		softassert.assertEquals(lname_value, last_name, "last name not matching:");
		
		//assert contact
		softassert.assertEquals(contact_value, contact_number, "contact number not matching:");
		
		//assert house number
		softassert.assertEquals(house_number, house, "house number not matching:");
		
		//assert street value
		softassert.assertEquals(street_value, street, "street value not matching:");
		
		//assert city value
		//softassert.assertEquals(city_value, city);
		
		System.out.println(house_number);
		System.out.println(street_value);
//		System.out.println(city_value);
	
		softassert.assertAll();
	}
	
	
	public void checkVechileDetails() {
		
	}
	
	
	public void checkQuotationDetails() {
		
	}
	
	public void checkExtendedCoversDetails() {
		
	}
	
	
	

}
