package Emotor555pp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static Emotor555pp.utilities.*;

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
	
	
	public void selectQuotationReferenceByVehicleNumber(String vehicle_number) throws InterruptedException {
		
		By dd_reference = By.xpath("//*[@id=\"select2-chosen-2\"]");
		By textBox_search =By.id("s2id_autogen2_search");
		
		ClickElement(driver, dd_reference);

		Thread.sleep(2000);

		
		EnterValue(driver, textBox_search, vehicle_number);

		driver.findElement(textBox_search).sendKeys(Keys.RETURN);
		
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
		
		int futureDate = Integer.parseInt (dtf.format(localDate))+2;

		By dateFrom = By.id("date-range-from");

		ClickElement(driver, dateFrom);

		driver.findElement(By.xpath("//table[@class='table-condensed']//*[text()="+futureDate+"]")).click();

		Thread.sleep(5000);
		
		
		
	}
	
	
	
	
	public void enterCustomerDetails() {
		
		By dd_salutation = By.id("salutation1");
		By email=By.id("emailaddress1");
		SelectByText(driver, dd_salutation, "Mr.");
		EnterValue(driver, email, "exampleemail@email.com");
	}
	
	
	public void tickAgreedToConvertQuotationToPolicy() {
				
 
		WebElement web = driver.findElement(By.xpath("//input[@id='exampleCheck1']"));
		ClickByFocusingOnTheElement(driver, web);
		
	}
	
	
	public void clickConfirmQuotationButton() {
		WebElement confirm_button = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/ng-view/div[2]/div[1]/div/div/div/div[5]/div[1]/div[1]/div/div/button[1]"));
		ClickElement(driver, confirm_button);
		
	}
	
	
	public void clickProceedButton() {
		
		By proceed_button = By.xpath("/html/body/div[2]/div[2]/div/ng-view/div[2]/div[1]/div/div/div/div[5]/div[2]/div[1]/div/div/button");
		ClickElement(driver, proceed_button);
		
	}
	
	
	public void clickConvertToPolicyButton() {
		
		By convert_to_policy_button = By.xpath("/html/body/div[2]/div[2]/div/ng-view/div[2]/div[5]/div/div/div[3]/div/div[1]/button");
		ClickElement(driver, convert_to_policy_button);
	}
	
	
	
	

}
