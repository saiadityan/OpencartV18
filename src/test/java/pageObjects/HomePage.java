package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	
	// constructor
	public HomePage(WebDriver driver) 
	{
		super(driver); // invoking parent class constructor
	}
	
	//locators
	@FindBy(xpath="//a[normalize-space()='Register']") 
	WebElement Lnk_Register;
	@FindBy(xpath="//span[normalize-space()='My Account']") 
	WebElement Lnk_MyAccount;
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") 
	WebElement Lnk_login;
	
	//action methods
	public void clickMyAccount()
	{
		Lnk_MyAccount.click();
	}
	
	public void clickRegister()
	{
		Lnk_Register.click();
	}
	
	public void clickLogin()
	{
		Lnk_login.click();
	}
	
}
