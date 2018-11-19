package lk.allianz.emotor.pages;

import static lk.allianz.emotor.pages.utilities.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class LoginPage {

	WebDriver driver;
	By username=By.xpath("//*[@id=\"loginform\"]/div[1]/div/input");
    By password=By.xpath("//*[@id=\"loginform\"]/div[2]/div/input");
    By LoginButton=By.xpath("//*[@id=\"loginform\"]/div[3]/div[2]/div/button");
	
	
	
public  LoginPage(WebDriver driver) {
		this.driver=driver;
		
	}

public void enterUsername(String strUsername) {
	EnterValue(driver, username, strUsername);
}

public void enterPassword(String strPassword) {
	EnterValue(driver, password, strPassword);
}

public void clickLoginButton() {
	ClickElement(driver, LoginButton);
}

public String titleVerification()
{
	return driver.getTitle();
}

public void loginToEmotor(String username, String password) {
	
	enterUsername(username);
	enterPassword(password);
	clickLoginButton();
	
}

/*public void quiteDriver()
{
 driver.quit();
}*/

}


