package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		//take xl file from testData
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		
		//create an object for xlutility class
		Excel_Utility xlutil = new Excel_Utility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcells = xlutil.getCellCount("Sheet1",1);
		
		//create a two dimensional array that stores xl data
		String logindata [][]= new String[totalrows][totalcells];
		
		for(int i=1;i<=totalrows;i++) //i is rows
		{
			for(int j=0;j<totalcells;j++) //j is cells
			{
				logindata[i-1][j]= xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		//returns two dimensional string array
		return logindata;
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
	

}
