package Test;

import org.testng.annotations.Test;
import java.io.IOException;


import org.junit.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import ObejectRepository.HomePage;
import ObejectRepository.LoginPage;
import ObejectRepository.WSRBodyRecordPage;
import ObejectRepository.WSRPage;
import Resources.Utilities;
import Resources.base;

public class VerifyInputsBodyRecord extends base {
	
	public  WebDriver driver;
	
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
		Utilities.jsClick(home.Wsr());
	
}
	
	@Test 
	
	public void VerifyWSRBodyFields() throws InterruptedException, IOException {
	
	//Open Header Tab
		WSRPage wsr= new WSRPage(driver);
		Utilities.jsClick(wsr.WsrBodyTab());
	
		
	//Create new Body record
		WSRBodyRecordPage body= new WSRBodyRecordPage(driver);
		Utilities.jsClick(body.newWSRBody());
		
	//Check text present match	
		
		System.out.println(getLabelsInWSRBodyRecord()); //Field Names present on the application
		
		System.out.println(getRowData(0, "WSRBodyRecodTypes")); //Label Names Expected from Excel
		
		Assert.assertEquals(getLabelsInWSRBodyRecord(), getRowData(0, "WSRBodyRecodTypes"));
	
		
	//Check type of Input
		
		System.out.println(checkTypeLabelWSR(getLabelsInWSRBodyRecord())); //Field Types present on the application
		
		System.out.println(getRowData(1, "WSRBodyRecodTypes")); //Field Types Expected from Excel
		
		
		Assert.assertEquals(getRowData(1, "WSRBodyRecodTypes"), checkTypeLabelWSR(getLabelsInWSRBodyRecord()));
	
	}
	

	

	
	@AfterTest
	public void Quit() {
		
		driver.quit();

	}
	
	


	    
		
	
}
