package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	// constructor
	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//input[@id='input-firstname']") 
	WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']") 
	WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txtEMail;
	@FindBy(xpath="//input[@id='input-telephone']") 
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']") 
	WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']") 
	WebElement txtPasswordConfirm;

	@FindBy(xpath="//input[@name='agree']") 
	WebElement chkdAgree;
	@FindBy(xpath="//input[@value='Continue']") 
	WebElement btnContinue;
	
	@FindBy(xpath="//p[contains(text(),'Congratulations! Your new account has been success')]") 
	WebElement msgConfirmation;

	
	//action methods
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String eml)
	{
		txtEMail.sendKeys(eml);
	}
	
	public void setTelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtPasswordConfirm.sendKeys(pwd);
	}
	
	public void clickPrivacyPolicy()
	{
		chkdAgree.click();
	}
	
	public void clickContinue()
	{
		//sol1
		btnContinue.click();
		
		//sol2
		//btnContinue.submit();
		
		//sol3
		//Actions act = new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
	}
	
	public String getConfirmationMessage()
	{
		try 
		{
			return (msgConfirmation.getText());
		} 
		catch (Exception e) 
		{
			return (e.getMessage());
		}
	}
	
}
