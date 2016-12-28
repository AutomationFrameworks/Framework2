package Generic_Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Base_class {
	
	public static Process process;
	public static  AppiumDriver driver;
	
	public static ExtentReports extentReport;
	public static ExtentTest startTest;
	
	
	public static void Start_Server() throws IOException, InterruptedException
	{
		//String Start_server="‪C:\\APPIUM\\Appium\\node.exe C:\\APPIUM\\Appium\\node_modules\\appium\\bin\\appium.js";
		String Start_server="node ‪‪C:\\Program Files (x86)\\Appium";
		process = Runtime.getRuntime().exec(Start_server);
		if(process!=null)
		{
			System.out.println("Appium server is Running");
		}
		else
		{
			System.out.println("Not started the Server");
		}
				
		Thread.sleep(12000);
		
	}
	
	@BeforeSuite
	public static void initialsetup()
	{
		Date date= new Date();
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		
		String Report_date = df.format(date);
		
		//extentReport= new ExtentReports("D:\\Nov_BB_project\\Report\\"+"Bigbasket"+"-"+Report_date+".html",false);
		extentReport= new ExtentReports("C:\\Users\\Valyoo\\Videos\\11-Dec-2016\\November_Framework1\\Report\\"+"Bigbasket"+"-"+Report_date+".html",false);
		

				
	}
	
	
	public static void InitializeApp() throws InterruptedException, IOException
	{
		DesiredCapabilities capabilities= new DesiredCapabilities();
		
		//device details
		capabilities.setCapability("deviceName", "GT-I9300I");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.4");
		
		//app details
		capabilities.setCapability("appPackage",Utility_Class.Reading_properties("Package_name"));
		capabilities.setCapability("appActivity",Utility_Class.Reading_properties("Activity_name"));
		
		//Appium Server details
		 driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		Thread.sleep(4000);
	}
	
	public void Explicit_wait(WebElement ele, long T1)
	{
		WebDriverWait wait= new WebDriverWait(driver, T1);
		wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
		
	}
	
	
	public static String Capture_screenshot(String TC_ID, String Order) throws IOException
	{
		Date date= new Date();		
		SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");		
		File file= new File(df.format(date)+".png");
			
		TakesScreenshot screenshot= (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File("C:\\Users\\Valyoo\\Videos\\11-Dec-2016\\November_Framework1\\Screenshot\\"+TC_ID+"-"+Order+"-"+file));
		String Path="C:\\Users\\Valyoo\\Videos\\11-Dec-2016\\November_Framework1\\Screenshot\\"+TC_ID+"-"+Order+"-"+file;
		return Path;
	}
	
	
	
	public static void Stop_Server() throws InterruptedException
	{
		if(process!=null)
		{
			process.destroy();
			Thread.sleep(8000);
			System.out.println("Stopped the Appium Server");
		}
		
		extentReport.flush();
		
	
		
	}

}
