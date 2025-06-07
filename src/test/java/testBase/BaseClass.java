package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
   public static WebDriver driver;
   public Logger logger;
   public Properties prop;
	
	@BeforeClass(groups= {"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		// to load properties file
		FileReader file = new FileReader(".\\src\\test\\resources\\config.properties");
		prop = new Properties();
		prop.load(file);
		
		// to load log files
		logger = LogManager.getLogger(this.getClass());
		
		// for remote execution
		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			String huburl = "http://localhost:4444/wd/hub";
			DesiredCapabilities cap = new DesiredCapabilities();
			// for operating system
			if (os.equalsIgnoreCase("windows")) 
			{
				cap.setPlatform(Platform.WIN10);
			}
			else if (os.equalsIgnoreCase("mac")) 
			{
				cap.setPlatform(Platform.MAC);
			}
			else if (os.equalsIgnoreCase("linux")) 
			{
				cap.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("Remote operating System not matched... ");
				return;
			}
			// for browser
			switch (br.toLowerCase()) 
			{
			case "chrome":cap.setBrowserName("chrome");break;
			case "edge":cap.setBrowserName("MicrosoftEdge");break;
			case "firefox":cap.setBrowserName("firefox");break;
			default: System.out.println("Browser not matched..");return;
			}
			driver = new RemoteWebDriver(new URL(huburl), cap);
		}
		
		//  for local execution -to run cross browser test
		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome":driver = new ChromeDriver();break;
			case "edge" : driver = new EdgeDriver();break;
			case "firefox" : driver = new FirefoxDriver();break;
			default : System.out.println("Invalid browser..");return;
			}
		}
		/*switch(br.toLowerCase())
		{
		case "chrome":driver = new ChromeDriver();break;
		case "edge" : driver = new EdgeDriver();break;
		case "firefox" : driver = new FirefoxDriver();break;
		default : System.out.println("Invalid browser..");return;
		}*/
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// read URL from properties file
		driver.get(prop.getProperty("appURL")); 
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master","Datadriven"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedString = RandomStringUtils.secure().nextAlphabetic(5);
		return generatedString;
	}
	
	public String randomNumber()
	{
		String generateNum = RandomStringUtils.secure().nextNumeric(10);
		return generateNum;
	}
	public String randomAlphaNumeric()
	{
		String genAlphaNumeric = RandomStringUtils.secure().nextAlphanumeric(8);
		return (genAlphaNumeric+"!");
	}
	
	public String captureScreen(String tname)
	{
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilepath = System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_"+ timestamp+".png";
		
		File targetFile = new File(targetFilepath);
		sourceFile.renameTo(targetFile);
		
		return targetFilepath; // returns the filepath to attach it to the report onTestFailure
	}
	
}
