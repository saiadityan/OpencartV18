package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LF_002_LoginTest extends BaseClass
{
	@Test(groups= {"Sanity","Master"})
	public void testLogin()
	{
		logger.info("***** Starting TC_LF_002_LoginTest *****");
		
		try
		{
		// Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		// Login Page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(prop.getProperty("email"));
		lp.setPassword(prop.getProperty("password"));
		lp.clickLogin();
		logger.info("Login Successful");
		
		//My Account Page
		MyAccountPage macc = new MyAccountPage(driver);
		boolean display =  macc.isMyAccountPagedisplay();
		
		//validation
		//Assert.assertEquals(display, true,"Login Failed");
		Assert.assertTrue(display, "Login Failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TC_LF_002_LoginTest *****");
	}
}
