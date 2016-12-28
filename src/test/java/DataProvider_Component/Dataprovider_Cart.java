package DataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.DataProvider;

import Generic_Component.ExcelReadWrite;

public class Dataprovider_Cart {
	
	@DataProvider(name="dp_AddCart")
	public static Iterator<Object[]> getAddCartdata() throws IOException
	{
		
			List<Object[]> Obj = flagrowCount("AddCart");
			return Obj.iterator();
		
	}
	
	@DataProvider(name="dp_DeleteCart")
	public static Iterator<Object[]> getDeleteCartdata() throws IOException
	{
		
			List<Object[]> Obj = flagrowCount("DeleteCart");
			return Obj.iterator();
		
	}
	
	
	public static List<Object[]> flagrowCount(String Scriptname) throws IOException
	{
		
		ExcelReadWrite xl= new ExcelReadWrite("C:\\Users\\Valyoo\\Videos\\11-Dec-2016\\November_Framework1\\TestData.xlsx");
		XSSFSheet Scenario_Cart = xl.Setsheet("Scenario_Cart");
		
		int RowCount = xl.getrowcount(Scenario_Cart);
		int Colcount = xl.getcolcount(Scenario_Cart, 0);
		
		//Create
		List<Object[]> arrlist_Cart= new ArrayList<Object[]>();
		
		for(int irow=1;irow<=RowCount;irow++)
		{
			String Execute_Flag = xl.Readvalue(Scenario_Cart, irow, "Execute_Flag");
			String Script_name = xl.Readvalue(Scenario_Cart, irow, "Script_name");
			
			if((Execute_Flag.equals("Y")) && (Script_name.equals(Scriptname)))
			{
				Map<String, String> dcMap= new HashMap<String, String>();
				
				for(int jCol=0;jCol<=Colcount;jCol++)
				{			
					String Key = xl.Readvalue(Scenario_Cart, 0, jCol);
					String Value = xl.Readvalue(Scenario_Cart, irow, jCol);	

					dcMap.put(Key, Value);
				}//col for loop
				
				Object[] x=new Object[1];
				x[0]=dcMap;
				arrlist_Cart.add(x);
					
			}//end of if condition	
						
		}//end of row for loop
		
		return arrlist_Cart;
		
		
		
	}
	
	
	
	
	

}
