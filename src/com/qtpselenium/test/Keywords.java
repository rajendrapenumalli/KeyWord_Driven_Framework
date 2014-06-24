package com.qtpselenium.test;

import static com.qtpselenium.test.DriverScript.APP_LOGS;
import static com.qtpselenium.test.DriverScript.CONFIG;
import static com.qtpselenium.test.DriverScript.OR;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Keywords {
	
	public WebDriver driver;
	
	public String openBrowser(String object, String data){
		APP_LOGS.debug("Opening the browser");
		System.out.println("Opening the browser"); //Remove
		
		try{
		if(CONFIG.getProperty(data).equals("MOZILLA"))
			driver=new FirefoxDriver();
		else if(CONFIG.getProperty(data).equals("IE"))
			driver=new InternetExplorerDriver();
		else if(CONFIG.getProperty(data).equals("CHROME"))
			driver=new ChromeDriver();
		}catch (Throwable t){
			System.out.println("something wrong");
		}
		return Constants.KEYWORD_PASS;
		
	}
	
	public String navigate(){
		APP_LOGS.debug("Navigating to the browser");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String clickLink(){
		APP_LOGS.debug("clickLink in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String verifyLinkText(){
		APP_LOGS.debug("verifyLinkText in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String writeInInput(){
		APP_LOGS.debug("writeInInput in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String verifyTextinInput(){
		APP_LOGS.debug("verifyTextinInput in page");
		
		return Constants.KEYWORD_FAIL;
		
	}
	
	public String clickButton(){
		APP_LOGS.debug("clickButton in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String verifyButtonText(){
		APP_LOGS.debug("verifyButtonText in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String verifyText(){
		APP_LOGS.debug("verifyText in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String closeBroswer(){
		APP_LOGS.debug("closeBroswer in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String selectList(){
		APP_LOGS.debug("selectList in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String verifyListSelection(){
		APP_LOGS.debug("verifyListSelection in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String verifyAllListElements(){
		APP_LOGS.debug("verifyAllListElements in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String selectRadio(){
		APP_LOGS.debug("selectRadio in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String verifyRadioSelected(){
		APP_LOGS.debug("verifyRadioSelected in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String checkCheckBox(){
		APP_LOGS.debug("checkCheckBox in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String unCheckCheckBox(){
		APP_LOGS.debug("unCheckCheckBox in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String verifyCheckBoxSelected(){
		APP_LOGS.debug("verifyCheckBoxSelected in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String pause(){
		APP_LOGS.debug("pause in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String verifyTitle(){
		APP_LOGS.debug("verifyTitle in page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String exist(){
		APP_LOGS.debug("exist from page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String click(){
		APP_LOGS.debug("click in the page");
		
		return Constants.KEYWORD_PASS;
		
	}
	
	public String synchronize(){
		APP_LOGS.debug("synchronize from page");
		
		return Constants.KEYWORD_PASS;
		
	}


}












