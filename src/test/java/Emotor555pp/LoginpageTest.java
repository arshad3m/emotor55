package Emotor555pp;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginpageTest {
	WebDriver driver;
	private String actualTittle;
	private String expectedTittle;
	LoginPage objLg;
	CreateQuotation objCq;
    
	@BeforeTest
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "src\\main\\java\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://192.168.128.68:8081/emotor/");
		driver.manage().window().maximize();

	}
	
	@Test
	public void testCreateQuotation() throws InterruptedException
	{
	 objLg=new LoginPage(driver);
	 objLg.enterUsername("T220");
	 objLg.enterPassword("allianz@2018");
	 Thread.sleep(5000);
	  objLg.clickLoginButton();
	  actualTittle=objLg.titleVerification();
	  expectedTittle="Allianz Emotor";
	  assertEquals( actualTittle,expectedTittle);
	  Thread.sleep(5000);
	  objCq=new CreateQuotation(driver);
	  objCq.pageNavigation();
	  Thread.sleep(2500);
	  objCq.addinitialDetails("Individual","T220-Prepod");
	  Thread.sleep(2500);
	  objCq.addinitialCustomerDetails("Miss","865360920V");
	  Thread.sleep(1000);
	  objCq.addcustomerDetails("Lilani","Silva","071463764377","300/A","Rajapihilla");
	 //lg.quiteDriver();
	}
	      
    
}
