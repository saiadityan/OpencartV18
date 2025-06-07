package utilities;

import java.awt.Desktop;
import java.io.File;
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.ImageHtmlEmail;
//import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;	//UI of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test;	//create test case entries in the report and update status of the test methods
	String repName;
	public void onStart(ITestContext context) 
	{
		// creat a time stamp variable
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp = df.format(dt);
		*/
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		// add time stamp to report name
		repName = "Test-Report-"+timestamp+".html";
		//specify location of the report
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle("Opencasrt Automation Report"); //title of the report
		sparkReporter.config().setReportName("Opencart Functional Testing"); //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		//sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os =	context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String>  includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) 
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) 
	{
		//create a new entry in the report
		test= extent.createTest(result.getTestClass().getName());
		
		// to display groups in the report
		test.assignCategory(result.getMethod().getGroups());
		
		//update status of the TC- p/f/sk
		test.log(Status.PASS, result.getName()+" got successfully executed");
	}
	
	public void onTestFailure(ITestResult result) 
	{
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try 
		{
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) 
	{
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) 
	{
		extent.flush();
		
		// to open the report automatically after test execution
		String pathofExtentReport =  System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathofExtentReport);
		try 
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		// to send the report to email automatically after test execution
		/*try 
		{
			String filepth = "file://"+ System.getProperty("user.dir")+"\\reports\\"+repName;
			URL url = new URL(filepth);
			// create email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com"); // this is for gmail.com
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("saiadityaautomation2022@gmail.com", "Password"));
			email.setSSLOnConnect(true);
			email.setFrom("saiadityaautomation2022@gmail.com"); // sender
			email.setSubject("Test Results");
			email.setMsg("Please find attached report... ");
			email.addTo("saiadityaautomation2022@gmail.com"); //receiver
			email.attach(url, "extent report", "please check report");
			email.send(); // sends email
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		*/
		
	}
}
