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
		ExcelReader xxx=new ExcelReader("");
		System.out.println(getData("market_code", 1)+".........arshad");
		
		
		//login to page
		login.enterUsername("T220");
		login.enterPassword("allianz@2018");
		login.clickLoginButton();
		quotation = new CreateQuotation(driver);
		Thread.sleep(5000);
		quotation.clickToCreateQuotation();
		quotation.addInitialDetails("Individual", getData("market_code", 1));
		quotation.clickCheckBox_WithCustomerDetails();

		//quotation.clickCheckBox_WithoutCustomerDetails();

		quotation.addInitialCustomerDetails("Miss", "865360920V");
		quotation.addCustomerDetails("Lilani","Silva","071463764377","300/A","Rajapihilla");

		driver.quit();
	}
	      
    
}
