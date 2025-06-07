package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{

	public MyAccountPage(WebDriver driver) 
	{
		super(driver);
	}
	
	//locators
	@FindBy(xpath="//h2[normalize-space()='My Account']") 
	WebElement msgMyAccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") 
	WebElement lnk_logout; // added in step 6
	
	//action methods
	public boolean isMyAccountPagedisplay()
	{
		try
		{
		return(msgMyAccount.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout()
	{
		lnk_logout.click();
	}
	
	

}
