package Emotor555pp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static Emotor555pp.utilities.*;

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

		EnterValue(driver, textBox_search, vehicle_number);

		driver.findElement(textBox_search).sendKeys(Keys.RETURN);
		
		Thread.sleep(5000);
	}
	
	
	public void selectPolicyStartDate() {
		By dateFrom = By.id("date-range-from");
		
		ClickElement(driver, dateFrom);
		driver.findElement(dateFrom).sendKeys(Keys.ENTER);
	}
	
	
	public void enterCustomerDetails() {
		
		By dd_salutation = By.id("salutation1");
		By email=By.id("emailaddress1");
		SelectByText(driver, dd_salutation, "Mr.");
		EnterValue(driver, email, "exampleemail@email.com");
	}
	
	
	public void tickAgreedToConvertQuotationToPolicy() {
		By checkbox_convertPolicy = By.id("exampleCheck1");
		
		driver.switchTo().defaultContent(); 
		WebElement web = driver.findElement(By.xpath("//input[@id='exampleCheck1']"));

		//ClickElement(driver, checkbox_convertPolicy);
		//ClickElement(driver, web);
		
		Actions action = new Actions(driver);
		action.moveToElement(web).click().build().perform();
		action.click().build().perform();
		System.out.println("moved to element");
		//ClickElement(driver, web);
		//ClickElementById2(driver, checkbox_convertPolicy);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", web);
	}
	
	
	
	

}
