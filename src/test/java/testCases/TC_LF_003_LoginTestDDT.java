package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_LF_003_LoginTestDDT extends BaseClass
{
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class , groups="Datadriven")
	public void testLoginDDT(String email,String pwd,String exp)
	{
		logger.info("***** Starting TC_LF_003_LoginTestDDT *****");
		try
		{
		// home page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//login page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//My Account Page
		MyAccountPage macc = new MyAccountPage(driver);
		boolean display =  macc.isMyAccountPagedisplay();
		
		/*
		 * Data valid - login success - test pass - logout
		 * 			  - login failed  - test fail
		 * 
		 * Data invalid -login success - test fail - logout
		 * 				-login failed - test pass
		 */
		if (exp.equalsIgnoreCase("valid")) 
		{
			if (display==true) 
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			} 
			else 
			{

				Assert.assertTrue(false);
			}
		}
		
		if (exp.equalsIgnoreCase("invalid")) 
		{
			if (display==true) 
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			} 
			else 
			{
				Assert.assertTrue(true);
			}
		}
	
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC_LF_003_LoginTestDDT *****");
	}
	
}
