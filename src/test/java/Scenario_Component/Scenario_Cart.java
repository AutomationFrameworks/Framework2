package Scenario_Component;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.api.Session;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Generic_Component.Base_class;
import PageObject_Component.PageObject_Cart;

public class Scenario_Cart extends Base_class {
	
	public static Logger log= Logger.getLogger(Scenario_Cart.class);
	
	SoftAssert sAssert= new SoftAssert();
	
	@Test(dataProvider="dp_AddCart",dataProviderClass=DataProvider_Component.Dataprovider_Cart.class,groups={"regression"})
	public void testAddCart(Map Cart) throws IOException, InterruptedException
	{
		String TC_ID = Cart.get("TC_ID").toString();
		String TC_Name = Cart.get("TC_Name").toString();
		String Order = Cart.get("Order").toString();
		String Search_item = Cart.get("Search_item").toString();
		String Exp_Result = Cart.get("Exp_Result").toString().trim();
		
		 startTest = extentReport.startTest(TC_ID+" ("+TC_Name+")");
		
		log.info("Executing the Testcase " +TC_ID+" Order is  "+Order);
		Start_Server();
		InitializeApp();
		startTest.log(LogStatus.PASS, "App is invoked");
		
		PageObject_Cart PC_Pob= new PageObject_Cart(driver);
		
		Explicit_wait(PC_Pob.Search_btn, 25);
		PC_Pob.Click_Searchbtn();
		
		Explicit_wait(PC_Pob.Search_txtbox, 25);
		PC_Pob.Enter_Searchtxt(Search_item);
		
		Explicit_wait(PC_Pob.Add_btn, 25);
		PC_Pob.Click_Addbtn();
		
		Explicit_wait(PC_Pob.Cart_img, 25);
		PC_Pob.Click_Cartimg();
		
		Explicit_wait(PC_Pob.item_addcart, 25);
		String Actual_Result = PC_Pob.getAddcartmsg();
		
		if(Actual_Result.trim().equals(Exp_Result))
		{
			log.info("Passed as Actual Result is  " +Actual_Result + " Expected Result is " +Exp_Result);
			startTest.log(LogStatus.PASS, "Passed as Actual Result is  " +Actual_Result + " Expected Result is " +Exp_Result, startTest.addScreenCapture(Capture_screenshot(TC_ID, Order)));
			Capture_screenshot(TC_ID, Order);
		}
		else
		{
			log.info("Failed as Actual Result is  " +Actual_Result + " Expected Result is " +Exp_Result);
			startTest.log(LogStatus.FAIL, "Failed as Actual Result is  " +Actual_Result + " Expected Result is " +Exp_Result, startTest.addScreenCapture(Capture_screenshot(TC_ID, Order)));
			Capture_screenshot(TC_ID, Order);
			sAssert.fail("Failed as Actual Result is  " +Actual_Result + " Expected Result is " +Exp_Result);
		}
		
		System.out.println("Session ID after the Add cart"+driver.getSessionId());
		Stop_Server();
		sAssert.assertAll();	
	
	}
	
	
	@Test(dataProvider="dp_DeleteCart",dataProviderClass=DataProvider_Component.Dataprovider_Cart.class,groups={"regression"})
	public void testDeleteCart(Map Cart) throws IOException, InterruptedException
	{
		String TC_ID = Cart.get("TC_ID").toString();
		String TC_Name = Cart.get("TC_Name").toString();
		String Order = Cart.get("Order").toString();
		String Search_item = Cart.get("Search_item").toString();
		String Exp_Result = Cart.get("Exp_Result").toString().trim();
		
		startTest = extentReport.startTest(TC_ID+" ("+TC_Name+")");
		log.info("Executing the Testcase " +TC_ID+" Order is  "+Order);
		Start_Server();
		InitializeApp();
		startTest.log(LogStatus.PASS, "App is invoked");
		
		PageObject_Cart PC_Pob= new PageObject_Cart(driver);
		
		Explicit_wait(PC_Pob.Search_btn, 25);
		PC_Pob.Click_Searchbtn();
		
		Explicit_wait(PC_Pob.Search_txtbox, 25);
		PC_Pob.Enter_Searchtxt(Search_item);
		
		Explicit_wait(PC_Pob.Add_btn, 25);
		PC_Pob.Click_Addbtn();
		
		Explicit_wait(PC_Pob.Cart_img, 25);
		PC_Pob.Click_Cartimg();
		
		Explicit_wait(PC_Pob.Delete_btn, 25);
		PC_Pob.Click_Deletebtn();
				
		Explicit_wait(PC_Pob.Delete_msg, 25);
		String Actual_Result = PC_Pob.getDeletecartmsg();
			
		
		if(Actual_Result.trim().equals(Exp_Result))
		{
			log.info("Passed as Actual Result is  " +Actual_Result + " Expected Result is " +Exp_Result);
			startTest.log(LogStatus.PASS, "Passed as Actual Result is  " +Actual_Result + " Expected Result is " +Exp_Result,startTest.addScreenCapture(Capture_screenshot(TC_ID, Order)));
			Capture_screenshot(TC_ID, Order);
		}
		else
		{
			log.info("Failed as Actual Result is  " +Actual_Result + " Expected Result is " +Exp_Result);
			startTest.log(LogStatus.FAIL, "Failed as Actual Result is  " +Actual_Result + " Expected Result is " +Exp_Result,startTest.addScreenCapture(Capture_screenshot(TC_ID, Order)));
			Capture_screenshot(TC_ID, Order);
			sAssert.fail("Failed as Actual Result is  " +Actual_Result + " Expected Result is " +Exp_Result);
		}
		
		PC_Pob.clickStartShoppingBtn();
		Thread.sleep(1000);
		System.out.println("Session ID after the Delete cart"+driver.getSessionId());
		//driver.close();
		Stop_Server();
		sAssert.assertAll();	
		
	}
	

}
