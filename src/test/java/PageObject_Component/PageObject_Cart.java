package PageObject_Component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class PageObject_Cart {
	
	public AppiumDriver driver;
	
	@FindBy(id="com.bigbasket.mobileapp:id/action_search")
	public WebElement Search_btn;
	
	@FindBy(id="com.bigbasket.mobileapp:id/searchView")
	public WebElement Search_txtbox;
	
	@FindBy(id="com.bigbasket.mobileapp:id/btnAddToBasket")
	public WebElement Add_btn;
	
	@FindBy(id="com.bigbasket.mobileapp:id/basketimageview")
	public WebElement Cart_img;
	
	@FindBy(id="com.bigbasket.mobileapp:id/txtProductDesc")
	public WebElement item_addcart;
	
	@FindBy(id="com.bigbasket.mobileapp:id/imgRemove")
	public WebElement Delete_btn;
	
	@FindBy(id="com.bigbasket.mobileapp:id/txtEmptyMsg1")
	public WebElement Delete_msg;
	
	@FindBy(id="com.bigbasket.mobileapp:id/btnBlankPage")
	public WebElement startShopping;
	
	public PageObject_Cart(AppiumDriver driver)
	{
		
		PageFactory.initElements(driver, this);
		
	}
	
	//Create the action oriented method
	
		public void Click_Searchbtn()
		{
			Search_btn.click();
			
		}
		
		public void Enter_Searchtxt(String Value)
		{
			
			Search_txtbox.sendKeys(Value +"\n");
			
		}

		
		public void Click_Addbtn()
		{
			
			Add_btn.click();
		}
		
		public void Click_Cartimg()
		{
			
			Cart_img.click();
		}
		
		public String getAddcartmsg()
		{
			
			return item_addcart.getText();
		}
		
		public void Click_Deletebtn()
		{
			
			Delete_btn.click();
		}
		
		
		public String getDeletecartmsg()
		{
			
			return Delete_msg.getText();
		}
		
		public void clickStartShoppingBtn()
		{
			startShopping.click();
		}
		
		
}
