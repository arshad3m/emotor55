package Emotor555pp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class LoginPage {
	//private WebElement username;
	//private WebElement password;
	//private WebElement LoginButton;
	WebDriver driver;
	By username=By.xpath("//*[@id=\"loginform\"]/div[1]/div/input");
    By password=By.xpath("//*[@id=\"loginform\"]/div[2]/div/input");
    By LoginButton=By.xpath("//*[@id=\"loginform\"]/div[3]/div[2]/div/button");
	
	
	
public  LoginPage(WebDriver driver) {
		this.driver=driver;
		
	}

public void enterUsername(String strUsername) {
	driver.findElement(username).sendKeys(strUsername);
}

public void enterPassword(String strPassword) {
	driver.findElement(password).sendKeys(strPassword);
}

public void clickLoginButton() {
	driver.findElement(LoginButton).click();
}

public String titleVerification()
{
	return driver.getTitle();
}

/*public void quiteDriver()
{
 driver.quit();
}*/

}


