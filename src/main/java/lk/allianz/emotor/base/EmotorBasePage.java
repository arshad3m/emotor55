package lk.allianz.emotor.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import lk.allianz.emotor.pages.CreateQuotation;
import lk.allianz.emotor.pages.DocumentUpload;
import lk.allianz.emotor.pages.ExcelReader;
import lk.allianz.emotor.pages.LoginPage;
import lk.allianz.emotor.pages.ReviseQuotation;



public class EmotorBasePage {
	
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	
	private static LoginPage login;
	protected static ExcelReader dataFile;
	
	/*public EmotorBasePage() {
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("src\\main\\java\\lk\\allianz\\emotor\\config\\config.properties"));
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\main\\java\\lk\\allianz\\emotor\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "/Users/naveenkhunteta/Documents/SeleniumServer/geckodriver");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get(prop.getProperty("url"));
	}*/
	
	
	public static void init_smoke() throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		 dataFile = new ExcelReader("src\\main\\java\\lk\\allianz\\emotor\\resources\\smoke_test_data_sheet.xlsx" ,0);
		System.setProperty("webdriver.chrome.driver", "src\\main\\java\\lk\\allianz\\emotor\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		login = new LoginPage(driver);
		driver.get("http://192.168.128.68:8081/emotor/");
		driver.manage().window().maximize();
		login.loginToEmotor("T221", "allianz@2018");
	}
}
