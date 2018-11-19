package lk.allianz.emotor.pages;
import static lk.allianz.emotor.pages.utilities.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class DocumentUpload {

	private WebDriver driver;
	
	
	public DocumentUpload (WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickDocumentUpload() {
		By documentUpload = By.linkText("Document Upload");
		ClickElement(driver, documentUpload);
		
	}
	
	
	
	public void clickUpload() {
		
		By upload = By.linkText("Upload");
		ClickElement(driver, upload);
		
	}
	
	
	public void searchByVehicleNumber() {
		
		WebElement select_element = driver.findElement(By.id("seacrCriteria1"));
		ClickElement(driver, select_element);
		
		WebElement select_index = driver.findElement(By.xpath("//option[@value='vehiclenumber']"));
		ClickElement(driver, select_index);
	}
	
	
	public void enterSearchText(String verhicle_number) {
		
		By searchText = By.id("searchByPolicy");
		EnterValue(driver, searchText, verhicle_number);
	}
	
	
	public void clickOnSearchButton() {
		By searchButton = By.cssSelector("button[ng-click='searchPoliciesForDocUp()']");
		ClickElement(driver, searchButton);
	}
	
	
	public void checkSearchResultsAreShown() {
		By resultTable = By.id("docUplaodTable2");
		boolean status=driver.findElement(resultTable).isDisplayed();
		
		System.out.println(status);
		
		//Get the number of rows in the search result
		List <WebElement> rows=driver.findElements(By.xpath("//tr[@class='ng-scope']"));

		//Get the number of records with the given vehicle number
		List <WebElement> vehicleNumbers = driver.findElements(By.xpath(".//*[@id='docUplaodTable2']//td[contains(.,'KR-9694')]"));
		
		
		//Assert that the number of rows in the search result and the number of records with the given vehicle number is equeal
		SoftAssert softassert= new SoftAssert();
		softassert.assertEquals(rows.size(), vehicleNumbers.size());
		softassert.assertAll();
		
		
	}
	
	
	public void clickPendingPolicyDocumentButton(int index) {
		List <WebElement> pendingPolicyButton = driver.findElements(By.xpath("//button[contains(text(),'Policy')]"));
		ClickElement(driver, pendingPolicyButton.get(index));

	}
	
	
	public void checkMandatoryDocuments(String documents) {
		SoftAssert soft_assert = new SoftAssert();
		
		//Number of documetns required in the Emotor
		List <WebElement> documentList = driver.findElements(By.xpath("//td[@ng-if=\"doc.required == '1' \"]"));
		System.out.println(documentList.size()+"is the size of the list document");
		System.out.println(documentList.get(0).getText());
		
		//Number of documents expected in actual result (excel sheet)
		String docList[]=documents.split(",");
		
		//Assert that the number of documents mandatory in Emotor is equal to the expected number in the excel sheet
		soft_assert.assertEquals(documentList.size(), docList.length);
		
		//Assert that the documents mandatory in the Emotor are matching with the expected documents in the excel sheet
		for(int i=0; i<docList.length;i++) {
		
			soft_assert.assertEquals(docList[i],documentList.get(i));
		}
		
	}
	
	
	
	
	
	
	
	
}
