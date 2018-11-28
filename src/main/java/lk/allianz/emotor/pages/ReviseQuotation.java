package lk.allianz.emotor.pages;
import static lk.allianz.emotor.utilities.utilities.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReviseQuotation {
	
	private WebDriver driver;
	
	public ReviseQuotation(WebDriver driver) {
		
		this.driver=driver;
	}
	
	
	
	public void clickToReviseQuotationPage() {
		
		By quotationLink = By.linkText("Quotations");
	    By reviseQuotationLink = By.linkText("Revise Quotation");

		
		ClickElement(driver, quotationLink);
		ClickElement(driver, reviseQuotationLink);
	}
	
	public void selectQuotationReference() {
		By reference_dd=By.xpath("//*[@id='select2-drop-mask']");
		ClickElement(driver, reference_dd);
	}
	
	
	
	public void changeCustomerDetails() {
		
		
	}
	
	
	public void changeVehicleDetails() {
		
	}
	
	
	public void changeQuotationDetails() {
		
	}
	
	
	public void changeExtendedCovers() {
		
	}
	
	
	

}
