package Test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Excel.ExcelData;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import ObejectRepository.HeaderPage;
import ObejectRepository.HomePage;
import ObejectRepository.LoginPage;
import ObejectRepository.WSRPage;
import Resources.base;

public class CreateHeader extends base {

	public WebDriver driver;
	//ExtentReports extent;
	

	
	
	
	
	@BeforeTest
	public void initialize() throws IOException, InterruptedException {


		
	// Initialize Driver
		driver = initializeDriver();
		driver.get(Url);

		executor = (JavascriptExecutor) driver;
		LoginPage log = new LoginPage(driver);
		HomePage home = new HomePage(driver);

	// Login with credentials in properties file
		log.LogUsername().sendKeys(userId);
		log.LogPassword().sendKeys(passId);
		log.LogButton().click();

	// Go to WSR Application
		home.ButtonWaffle().click();
		home.SearchHomepage().sendKeys("Wsr");
		jsClick(home.Wsr());

	// Open Header Tab ////
		
		WSRPage wsr = new WSRPage(driver);
		jsClick(wsr.HeaderTab());

	}

	@Test(dataProvider = "getDataExcel")

	public void createHeader(String headerName, String user, String salesforceManagerEmail, String visionAndValues,
			String measures, String methods, String obstacles) throws InterruptedException {


	// Create new header ////
		
		HeaderPage header = new HeaderPage(driver);
		jsClick(header.ButtonNewHeader());

	// Fill Data ////

		HeaderPage hp = new HeaderPage(driver);
		SoftAssert s = new SoftAssert();
		String startingUrl = driver.getCurrentUrl();
			
		hp.inputUser().click();
		Thread.sleep(2000);
		hp.inputUser().sendKeys(Keys.chord(Keys.ENTER));
		hp.inputManagerEmail().sendKeys(salesforceManagerEmail);
		hp.inputvisionsAndValues().sendKeys(visionAndValues);
		hp.inputMeasures().sendKeys(measures);
		hp.inputMethod().sendKeys(methods);
		hp.inputObstacles().sendKeys(obstacles);
		
		hp.buttonSaveHeader().click();
		Thread.sleep(2000);
		
		WebElement error = driver.findElement(By.xpath("//ul[@class='errorsList slds-list_dotted slds-m-left_medium']/li"));
		
		s.assertAll();
		s.assertTrue(error.getText().contains("Header"));
		
		hp.inputHeaderName().sendKeys(headerName);	
	
		
		
		System.out.println(startingUrl);
		System.out.println(driver.getCurrentUrl());
		
		s.assertNotEquals(startingUrl, driver.getCurrentUrl());
	
	}

	@DataProvider
	public Object[][] getDataExcel( ) throws IOException {

		ExcelData d = new ExcelData();
		Object[][] dataExcel = d.getData("InputsHeader");

		return dataExcel;

	}

	@AfterTest
	public void Quit() {

		 driver.quit();

	}

}
