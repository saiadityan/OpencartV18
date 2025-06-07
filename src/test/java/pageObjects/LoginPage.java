package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{

	public LoginPage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	//locators
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txt_EMailAddress;
	@FindBy(xpath="//input[@id='input-password']") 
	WebElement txt_password;
	@FindBy(xpath="//input[@value='Login']") 
	WebElement btn_login;
	
	//action methods
	public void setEmail(String email)
	{
		txt_EMailAddress.sendKeys(email);
	}
	
	public void setPassword(String pwd)
	{
		txt_password.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btn_login.click();
	}

}
