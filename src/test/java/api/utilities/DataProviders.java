package api.utilities;

import java.io.IOException;


import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "data")
	public String[][] getAllData()throws IOException{
		String path=System.getProperty("user.dir")+"//testData//testData.xlsx";
		XLUtility xl=new XLUtility(path);
		
		int rownum=xl.getRowCount("testDataSheet");
		int colnum=xl.getCellCount("testDataSheet", 1);
		
		String apidata[][]=new String[rownum][colnum];
		
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colnum;j++) {
				apidata[i-1][j]=xl.getCellData("testDataSheet", i, j);
			}
		}
		return apidata;
	}
	
	@DataProvider(name = "userNames")
	public String[] getUserNames()throws IOException{
		
		String path=System.getProperty("user.dir")+"//testData//testData.xlsx";
		XLUtility xl=new XLUtility(path);
		int rownum=xl.getRowCount("testDataSheet");
		
		String apidata[]=new String[rownum];
		for(int i=1;i<=rownum;i++) {
			apidata[i-1]=xl.getCellData("testDataSheet", i, 1);
		}
		
		return apidata;
	}
}
