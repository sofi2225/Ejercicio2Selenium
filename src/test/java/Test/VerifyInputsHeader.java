package Test;

import org.testng.annotations.Test;
import java.io.IOException;

import java.util.ArrayList;


import org.junit.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import ObejectRepository.HeaderPage;
import ObejectRepository.HomePage;
import ObejectRepository.LoginPage;
import ObejectRepository.WSRPage;
import Resources.Utilities;
import Resources.base;

public class VerifyInputsHeader extends base {
	
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
	
	public void VerifyHeaderFields() throws InterruptedException, IOException {
	
	/// Open WSR Body Record Tab  ////
		
		WSRPage wsr= new WSRPage(driver);
		Utilities.jsClick(wsr.HeaderTab());
		
	
	/// Create new WSR Body Record //
		
		HeaderPage header= new HeaderPage(driver);
		Utilities.jsClick(header.ButtonNewHeader());
		
		
	/// Find all label names present in Header ////
		
		ArrayList<String> listadoLables = new ArrayList<String>();
		int j=0;
		
			for (WebElement label :header.LabelsInHeaderForm())
				
				{
					listadoLables.add(j,label.getText().replace("*", ""));
					j++;
				}
		
	/// Assert Label names match expected label name ///
		
		System.out.println(listadoLables);
		System.out.println(getRowData(0, "TypeFieldsHeader")); 
		
		Assert.assertEquals(listadoLables, getRowData(0, "TypeFieldsHeader"));
		
		
	//// Assert type of Input matches Expected Type ///
		
		System.out.println(getRowData(1, "TypeFieldsHeader")); 
		System.out.println(checkTypeLabel(listadoLables));
		
		Assert.assertEquals(getRowData(1, "TypeFieldsHeader"), checkTypeLabel(listadoLables));
		
		
	}
	

	
	@AfterTest
	public void Quit() {
		
		driver.quit();

	}
	
	


	    
		
	
}
