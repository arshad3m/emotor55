package lk.allianz.emotor.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExceptionTestContextImpl;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.SessionStatusStats;
import com.aventstack.extentreports.SystemAttributeContext;
import com.aventstack.extentreports.TestAttributeTestContextProvider;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Screencast;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import lk.allianz.emotor.pages.CreateQuotation;
import lk.allianz.emotor.pages.DocumentUpload;
import lk.allianz.emotor.pages.LoginPage;
import lk.allianz.emotor.pages.ReviseQuotation;
import lk.allianz.emotor.utilities.ExcelReader;
import lk.allianz.emotor.utilities.utilities;



public class EmotorBasePage {
	
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	
	private static LoginPage login;
	protected static ExcelReader dataFile;
	
	public static ExtentHtmlReporter html;
	public static ExtentReports report; 
	public static ExtentTest test;
	

	@BeforeMethod
	public static void init_smoke() throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		dataFile = new ExcelReader("src\\main\\java\\lk\\allianz\\emotor\\resources\\smoke_test_data_sheet.xlsx", 0);
		System.setProperty("webdriver.chrome.driver",
				"src\\main\\java\\lk\\allianz\\emotor\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		login = new LoginPage(driver);
		driver.get("http://192.168.128.68:8081/emotor/");
		driver.manage().window().maximize();
		login.loginToEmotor("T221", "allianz@2018");
		
		

	}
	
	
	@BeforeSuite
	public static void init_reports() {
		//Report related attributes
		String date=LocalDateTime.now().toString().replace(".", "_").replace(":", "_");
		html = new ExtentHtmlReporter("src\\test\\java\\lk\\allianz\\emotor\\reports\\"+date+"_report.html");
		report = new ExtentReports();
		report.attachReporter(html);
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		
		
		
		if(result.getStatus()==ITestResult.FAILURE) {
			String path=utilities.takeScreenshot(driver);
			
		test.fail(MarkupHelper.createLabel(result.getName()+" Test case failed for count:"+result.getMethod().getCurrentInvocationCount(), ExtentColor.RED));
		test.fail(result.getThrowable().fillInStackTrace(),MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		
		
		else if(result.getStatus()==ITestResult.SUCCESS) {
			
		test.pass(MarkupHelper.createLabel(result.getName()+" Test case passed", ExtentColor.GREEN));
		}
		
		else {
			
			test.skip(MarkupHelper.createLabel(result.getName()+" Test case Skipped", ExtentColor.YELLOW));
			test.skip(result.getThrowable().getMessage());

		}
		
		driver.quit();

	}
	
	@org.testng.annotations.AfterSuite
	public void AfterSuite() {
		report.flush();

	}
	
	public String failed(String testName, int count) throws IOException {
		
		String date=LocalDateTime.now().toString().replace(".", "_").replace(":", "_");
		
		System.out.println(date);
		
		String location="src\\test\\java\\lk\\allianz\\emotor\\screenshots\\"+date+"_"+testName+"_TC_Number_"+count+"_.jpg";
				
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(location));
		
		return location;
	}
	
	
	
}
