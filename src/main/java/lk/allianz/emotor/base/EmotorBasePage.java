package lk.allianz.emotor.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import lk.allianz.emotor.pages.CreateQuotation;
import lk.allianz.emotor.pages.DocumentUpload;
import lk.allianz.emotor.pages.LoginPage;
import lk.allianz.emotor.pages.ReviseQuotation;
import lk.allianz.emotor.utilities.ExcelReader;



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
	
	
	public void failed(String testName, int count) throws IOException {
		
		String date=LocalDateTime.now().toString().replace(".", "_").replace(":", "_");
		System.out.println(date);
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("src\\test\\java\\lk\\allianz\\emotor\\screenshots\\"+date+"_"+testName+"_TC_Number_"+count+"_.jpg"));
		
		
	}
}
