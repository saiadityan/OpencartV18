package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_RF_001_AccountRegistration extends BaseClass
{
	
	@Test(groups= {"Regression","Master"})
	public void testAccountRegistration()
	{
		logger.info("***** Starting TC_RF_001_AccountRegistration *****");
		logger.debug("This is a debug log message");
		
		try
		{
		// accessing home page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link...");
		
		hp.clickRegister();
		logger.info("Clicked on Register link...");
		
		// accessing registration page
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		logger.info("Providing customer details...");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");  //randomly email generated
		regpage.setTelephone(randomNumber());
		
		String pwd = randomAlphaNumeric();
		regpage.setPassword(pwd);
		regpage.setConfirmPassword(pwd);
		
		regpage.clickPrivacyPolicy();
		regpage.clickContinue();
		
		// validations
		String cfrmsg =	regpage.getConfirmationMessage();
		logger.info(" Validating expected message...");
		/*if (cfrmsg.endsWith("Congratulations! Your new account has been successfully created!")) 
		{
			Assert.assertTrue(true);
		} 
		else 
		{
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.assertTrue(false);
		}*/
		Assert.assertEquals(cfrmsg, "Congratulations! Your new account has been successfully created!","Confirmation Message Mismatch");
		logger.info("Test Passed");
		}
		catch(Exception e) 
		{
			logger.error("Test Failed"+e.getMessage());
			Assert.fail("Test Failed"+e.getMessage());
		}
		finally 
		{
			logger.info("***** Finished TC_RF_001_AccountRegistration *****");
		}
		
	}
	
}
