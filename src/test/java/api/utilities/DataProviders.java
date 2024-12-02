package api.utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {
		
		//DataProvider 1 (2D array)
		
		@DataProvider(name="Data")
		public String [][] getAllData() throws IOException
		{
			String path=".\\testData\\Userdata.xlsx"; //taking excel file from testData
			XLUtility xlutil=new XLUtility(path); //creating an object for excel Utility
			
			int totalrows=xlutil.getRowCount("Sheet1");
			int totalcols=xlutil.getCellCount("Sheet1", 1);
			
			String apidata[][]=new String[totalrows][totalcols]; //create for two dimensional array
			
			for(int i=1;i<=totalrows;i++) //1 //1st row is header row so we start with 2nd row which index is 2
			{
				for(int j=0;j<totalcols;j++) //0 //i is rows j is cols which start from 1st 
				{
					apidata[i-1][j]=xlutil.getCellData("Sheet1", i, j);  //1,0
				}
			}

			return apidata;   //returning two dimension array
		}
		
		//DataProvider 2 (1D array)
		
		@DataProvider(name="UserNames")
		public String [] getUserNames() throws IOException
		{
			String path=".\\testData\\Userdata.xlsx"; //taking excel file from testData
			XLUtility xlutil=new XLUtility(path); //creating an object for excel Utility
			
			int totalrows=xlutil.getRowCount("Sheet1");
			
			String apidata[] = new String[totalrows]; //create for two dimensional array
			
			for(int i=1;i<=totalrows;i++) //1 //1st row is header row so we start with 2nd row which index is 2
			{
					apidata[i-1] = xlutil.getCellData("Sheet1", i, 1);  //column is constant for username i.e. 1
			}

			return apidata;   //returning two dimension array
		}
		
		//DataProvider 3

}
