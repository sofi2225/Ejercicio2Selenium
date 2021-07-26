package Test;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ObejectRepository.HomePage;
import ObejectRepository.LoginPage;
import ObejectRepository.WSRPage;
import Resources.base;

public class SendToManager extends base {

	
@BeforeTest
	public void initialize() throws IOException, InterruptedException {

	//Initialize Driver
		driver = initializeDriver();
		driver.get(Url);
		
		executor = (JavascriptExecutor)driver;
		LoginPage log = new LoginPage(driver);
		HomePage home = new HomePage(driver);

	//Login with credentials in properties file
		log.LogUsername().sendKeys(userId);
		log.LogPassword().sendKeys(passId);
		log.LogButton().click();
		
		
	//Go to WSR Application
		home.ButtonWaffle().click();
		home.SearchHomepage().sendKeys("Wsr");
		jsClick(home.Wsr());
	
	}

	@Test
	public void sendToManager()  {

		//Open Header Tab
		WSRPage wsr= new WSRPage(driver);
		jsClick(wsr.WsrBodyTab());
		
		https://tdccom-dev-ed.lightning.force.com/lightning/r/Body_WSR__c/a005e000004bDZgAAM/view
		
	}



	
	
	
	
	
	
}
