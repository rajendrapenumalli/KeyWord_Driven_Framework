package com.qtpselenium.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.qtpselenium.xls.read.Xls_Reader;

public class DriverScript {

	public static Logger APP_LOGS;
	//suite.xlsx
	public Xls_Reader suiteXLS;
	public int currentSuiteID;
	public String currentTestSuite;
	
	//Current  test suite
	public Xls_Reader currentTestSuiteXLS;
	public int currentTestCaseID;
	public String currentTestCaseName;
	public int currentTestStepID;
	public String currentKeyword;
	public int  currentTestDataSetID;
	public Method method[];	
	public Keywords keywords;
	public String keyword_execution_result;
	public ArrayList<String> resultSet;
	public int index=0;
	public String data;
	public String object;
	
	//
	public static Properties CONFIG;
	public static Properties OR;
	
	public DriverScript(){
		
		keywords = new Keywords();
		method = keywords.getClass().getMethods();
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//com//qtpselenium//config//config.properties");
		CONFIG=new Properties();
		CONFIG.load(fs);
		
		fs = new FileInputStream(System.getProperty("user.dir")+"//src//com//qtpselenium//config//OR.properties");
		OR=new Properties();
		OR.load(fs);
		
		//System.out.println(CONFIG.getProperty("testSiteName"));
		//System.out.println(OR.getProperty("name"));
		
		DriverScript test = new DriverScript();
		test.start();
	
	}
		

		
	public void start() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// Initisalise the app logs
		APP_LOGS=Logger.getLogger("devpinoyLogger");
		APP_LOGS.debug("Hello");
		APP_LOGS.debug("Properties loaded, started testing");
		
		//Steps
		// 1. Check the runmode of the test suite
		// 2. Runmode of the test case in test suite
		// 3. Execute keywords of the test case serially
		// 4. Execute keywords as many times as number of data sets - set to Y
		// 5. To execute keywords even if there are no data sets
		
		// Step 1 : 
		
		APP_LOGS.debug("Initialize Suite xlsx");
		suiteXLS = new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\Suite.xlsx");
		
		//Iterating from 2nd row till last row in the sheet by getting row count in the excel sheet
		for(currentSuiteID=2;currentSuiteID<=suiteXLS.getRowCount(Constants.TEST_SUITE_SHEET);currentSuiteID++)
		{
			
			// Printing row1 and row 3 cell data
			APP_LOGS.debug(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_ID, currentSuiteID)+ "-------" +suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.RUNMODE, currentSuiteID));
			
			/* Replacing the commented code as no need to declare the String variable Runmode
			// The Runmode column values from excel sheet gets saved in string Runmode
			String Runmode = suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_ID3, currentSuiteID);
			
			// If Runmode is equal to the value Y which is stored in String Constants.Runmode_Yes. then,
			if(Runmode.equals(Constants.Runmode_Yes)) {
			
			*/
			// Data from first cell of excel sheet is saved here as string
			currentTestSuite=suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_ID, currentSuiteID);
			
			
			// Step 2 :	
			// If the Run mode is Yes
			if(suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.RUNMODE, currentSuiteID).equals(Constants.Runmode_Yes)) {
				
				APP_LOGS.debug("*************Executing the script*****************  --- "  + suiteXLS.getCellData(Constants.TEST_SUITE_SHEET, Constants.TEST_SUITE_ID, currentSuiteID));
				
				// Moving to the appropriate test cases excel sheets when suite excel Runmode is yes 
				currentTestSuiteXLS=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\qtpselenium\\xls\\"+currentTestSuite+".xlsx");
				
				for(currentTestCaseID=2;currentTestCaseID<=currentTestSuiteXLS.getRowCount(Constants.TEST_CASE_SHEET);currentTestCaseID++){
					APP_LOGS.debug(currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASES_ID, currentTestCaseID));
					
					currentTestCaseName=currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASES_ID, currentTestCaseID);									
					
					
					
					if(currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.RUNMODE, currentTestCaseID).equals(Constants.Runmode_Yes)){					
					APP_LOGS.debug("Executing the Test cases  --- " + currentTestSuiteXLS.getCellData(Constants.TEST_CASE_SHEET, Constants.TEST_CASES_ID, currentTestCaseID));
					
					
					//Step 5 :
					//Keywords to be executed even if the data sets are not available
					
					if(currentTestSuiteXLS.isSheetExist(currentTestCaseName)){
					
					//Step 4 : 
					//Run as many times as number of  test data sets with Runmode Y
					
					for(currentTestDataSetID=2;currentTestDataSetID<=currentTestSuiteXLS.getRowCount(currentTestCaseName);currentTestDataSetID++) {						
						APP_LOGS.debug("*** Iteration ***" +(currentTestDataSetID-1));
						
						
						resultSet = new ArrayList<String>(); // To add result from keyword methods					
						if(currentTestSuiteXLS.getCellData(currentTestCaseName, Constants.RUNMODE, currentTestDataSetID).equals(Constants.Runmode_Yes)){
								
						//Step 3:
						//Iterating through all keywords	
							executeKeywords();
							
																	
						}createXLSReport();				
					}
					
					}else{
						currentTestDataSetID=2;
						executeKeywords();	
						createXLSReport();	
						
					}
					
					}
				}
		}
	
	
	}

	}

	public void executeKeywords() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		for(currentTestStepID=2;currentTestStepID<=currentTestSuiteXLS.getRowCount(Constants.TEST_STEPS);currentTestStepID++){
			data=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.DATA, currentTestStepID);
			object=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, Constants.OBJECT, currentTestStepID);
			
			// Checking TCID
			if(currentTestCaseName.equals(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS, Constants.TCID, currentTestStepID)))
			{			
			currentKeyword=currentTestSuiteXLS.getCellData(Constants.TEST_STEPS, Constants.KEYWORD, currentTestStepID);
			APP_LOGS.debug(currentKeyword);
			
						
			// Code to execute keyword as well
			// Reflection API			
			
			for(int i=0;i<method.length;i++){
				//System.out.println(method[i].getName());
				
				
				if(method[i].getName().equals(currentKeyword)){
					keyword_execution_result = (String) method[i].invoke(keywords,data,object);
					APP_LOGS.debug(keyword_execution_result);					
					resultSet.add(keyword_execution_result);
				}
			}
			
			
			}
		}
	
	}

	public void createXLSReport(){
		
		// To populate Pass, Skip in the Test steps sheet
		
		String colName=Constants.RESULT +(currentTestDataSetID-1);
		boolean isColExist=false;
		
		for(int c=0;c<currentTestSuiteXLS.getColumnCount(Constants.TEST_STEPS_SHEET);c++){
			//System.out.println(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, colNum, rowNum)
			if (currentTestSuiteXLS.getCellData(Constants.TEST_STEPS_SHEET, c, 1).equals(colName)){
				isColExist=true;
				break;				
			}	
		}
		
		if(!isColExist)
			currentTestSuiteXLS.addColumn(Constants.TEST_STEPS_SHEET, colName);
		
		for(int i=2;i<=currentTestSuiteXLS.getRowCount(Constants.TEST_STEPS_SHEET);i++){
			index=0;
			if(currentTestCaseName.equals(currentTestSuiteXLS.getCellData(Constants.TEST_STEPS, Constants.TCID, i))){
				if(resultSet.size()==0)
					currentTestSuiteXLS.setCellData(Constants.TEST_STEPS_SHEET, colName, i, Constants.KEYWORD_SKIP);
				else
					currentTestSuiteXLS.setCellData(Constants.TEST_STEPS_SHEET, colName, i, Constants.KEYWORD_PASS);
				index++;	
			}
		}
							
		
		// To populate Pass/Skip in the Data test sheet
		
		if(resultSet.size()==0){
			//Skip
			currentTestSuiteXLS.setCellData(currentTestCaseName, Constants.RESULT, currentTestDataSetID, Constants.KEYWORD_SKIP);
			return;
		}else{
			for(int i=0;i<resultSet.size();i++){
				if(!resultSet.get(i).equals(Constants.KEYWORD_PASS)){
					currentTestSuiteXLS.setCellData(currentTestCaseName, Constants.RESULT, currentTestDataSetID, Constants.KEYWORD_FAIL);
				}	
			}	
		}
		
		currentTestSuiteXLS.setCellData(currentTestCaseName, Constants.RESULT, currentTestDataSetID, Constants.KEYWORD_PASS);
		
	}
	
	
}

