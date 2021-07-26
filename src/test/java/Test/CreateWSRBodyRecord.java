package Test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;




import Excel.ExcelData;

import java.io.IOException;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import ObejectRepository.HomePage;
import ObejectRepository.LoginPage;
import ObejectRepository.WSRBodyRecordPage;
import ObejectRepository.WSRPage;
import Resources.base;
import Resources.calendar;

@Test
public class CreateWSRBodyRecord extends base {
	
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
		jsClick(home.Wsr());
	
}
	

@Test
	public void VerifyWSRBodyFields(ITestContext context) throws InterruptedException, IOException {
	
	//Open Header Tab
		WSRPage wsr= new WSRPage(driver);
		jsClick(wsr.WsrBodyTab());
	
		
	//Create new header
		WSRBodyRecordPage body= new WSRBodyRecordPage(driver);
		jsClick(body.newWSRBody());
		
	/// FILL WSR BODY RECORD ///
		
		
		String highlights= "Highlights";
		String lowlights="Lowlights";
		String bodyName="Body Name";
		String status="Draft";
		String upcomingLeaves="Upcoming Leaves";
		String sprintDeliverables = "ON TRACK";
		
		String startDateDay = "14";
		String startDateMonth = "March";
		String startDateYear = "2024";
		
		String endDateDay = "15";
		String endDateMonth = "March";
		String endDateYear = "2024";

		String storiesAssigned="1";
		String storiesCompleted="1";
		String storiesInProgress="0";
		String storiesNotStarted="0";
		
		WSRBodyRecordPage wsrBody = new WSRBodyRecordPage(driver);
	
		wsrBody.inputUser().click();
		Thread.sleep(2000);
		wsrBody.inputUser().sendKeys(Keys.ENTER); //Picks user
		
		wsrBody.inputHighlights().clear();
		wsrBody.inputHighlights().sendKeys(highlights); // Enters Highlight
		
		
		wsrBody.inputHeader().click();
		Thread.sleep(2000);
		wsrBody.inputHeader().sendKeys(Keys.ENTER); // Picks Header
		
		
		wsrBody.inputLowlights().clear();
		wsrBody.inputLowlights().sendKeys(lowlights); // Enters Lowlights
		
		
		wsrBody.inputBodyName().sendKeys(bodyName);// Enters Body Name
		
		selectDropdownText(wsrBody.inputSatus(),status); // Picks Status
		
		
	//	selectDropdownText(wsrBody.inputSatus(), "Draft");
		
		
		wsrBody.inputUpcomingLeaves().clear();
		wsrBody.inputUpcomingLeaves().sendKeys(upcomingLeaves);// Enters Upcoming Leaves
		
		selectDropdownText(wsrBody.inputSprintDeliverables(), sprintDeliverables); //Picks Sprint  Deliverables
		
		///CALENDAR ///
		
		calendar c= new calendar();
		c.Calendario(startDateDay, startDateMonth,startDateYear ,wsrBody.inputSprintStart(), driver); //Start Date
		
		c.Calendario(endDateDay, endDateMonth,endDateYear ,wsrBody.inputSprintEnd(), driver); //End Date
		
		/// STORIES////
		
		wsrBody.inputStoriesAssigned().sendKeys(storiesAssigned);
		wsrBody.inputStoriesCompleted().sendKeys(storiesCompleted);
		wsrBody.inputStoriesInProgress().sendKeys(storiesInProgress);
		wsrBody.inputStoriesNotStarted().sendKeys(storiesNotStarted);
		
		Thread.sleep(4000);
		
		/// FILL DAY DATA ///

		fillDayData(wsrBody.inputMondayText(),wsrBody.inputClickMonday() ,"Monday"); 
		fillDayHours(wsrBody.inputMondayHours(), "0");
		
		//Tuesday
		fillDayData(wsrBody.inputTuesdayText(),wsrBody.inputClickTuesday() ,"Tuesday"); 
		fillDayHours(wsrBody.inputTuesdayHours() , "0"); 

		
		
		fillDayData(wsrBody.inputWednesdayText(),wsrBody.inputClickWednesday() ,"Wednesday" ); 
		fillDayHours(wsrBody.inputWednesdayHours() , "0"); 

		
		fillDayData(wsrBody.inputThursdayText(),wsrBody.inputThursdayClick() ,"Thursday"); 
		fillDayHours(wsrBody.inputThursdayHours() , "0"); 

		
		fillDayData(wsrBody.inputFridayText(),wsrBody.inputClickFriday() ,"Friday"); 
		fillDayHours(wsrBody.inputFirdayHours() , "0"); 

		
		// ASSERT 1 // Check if sending value of work empty Form is not submitted and error text match
		
		String[] dias = {"Monday","Tuesday","Wednesday" ,"Thursday","Friday"};
		String bodyRecordUrl = driver.getCurrentUrl();
		WebElement buttonSave = body.saveButton();
		
		for (String dia: dias) {
			
		//WebElement b = findTextArea(dia);
		GetInView(findTextArea(dia));
		findTextArea(dia).clear();
		buttonSave.click();
		
	
		Thread.sleep(1000);
		
		try {   findErrorPresentDay(dia);
		
		}
		catch(Exception e){
			
			System.out.println("No se ecuentra el error");
		}
		
		
		findTextArea(dia).sendKeys("test");
		
		Assert.assertEquals(bodyRecordUrl, driver.getCurrentUrl() );
		//Assert.assertTrue( findErrorPresentDay(dia).size() != 0);
		
		}
		
		
		// ASSERT 2 // Check that form cant be submitted with Extra Hours Or Empty field
		
		String errorExtraHours = "No extra hours allowed.";
		WebElement error = null;
		
		for (String dia: dias) {
						
			inputDayHours(dia).clear();
			inputDayHours(dia).sendKeys("9");
			buttonSave.click();
			Thread.sleep(1000);

			
			try {  error = body.errorPresent();
			
			}
			catch(Exception e){
				
				System.out.println("No se ecuentra el error");
			}
			
			
			Assert.assertEquals(error.getText(), errorExtraHours);	

		}
		
		for (String dia: dias) {
			
			//WebElement a = driver.findElement(By.xpath("//input[@name='"+dia+"_Hours__c']"));
			
		
			inputDayHours(dia).clear();
			buttonSave.click();
			
			Thread.sleep(1000);
			
			try {  error = body.errorPresent();
			
			}
			catch(Exception e){
				
				System.out.println("No se ecuentra el error");
			}
			
			
			Assert.assertTrue(error.getText().contains(dia));		
			inputDayHours(dia).clear();
			inputDayHours(dia).sendKeys("8");
		}
		
		
		
		/// ASSERT 3 //
		
		//Caso1 
		String errorExpectedStoriesAssigned = "Stories completed, In progress, Not started exceed Number of stories assigned";
		WebElement error2 =null;
		
		storiesAssigned ="4";
		storiesCompleted ="2";
		storiesInProgress ="1";
		storiesNotStarted ="0";
		
		Thread.sleep(2000);
		wsrBody.inputStoriesAssigned().clear();
		wsrBody.inputStoriesAssigned().sendKeys(storiesAssigned);
		
		wsrBody.inputStoriesCompleted().clear();
		wsrBody.inputStoriesCompleted().sendKeys(storiesCompleted);
		
		wsrBody.inputStoriesInProgress().clear();
		wsrBody.inputStoriesInProgress().sendKeys(storiesInProgress);
		
		wsrBody.inputStoriesNotStarted().clear();
		wsrBody.inputStoriesNotStarted().sendKeys(storiesNotStarted);
		
		buttonSave.click();
		Thread.sleep(2000);
		
		try { error2 = body.errorPresent();
		
		}
		catch(Exception e){
			
			System.out.println("No se ecuentra el error");
		}
		

		Assert.assertEquals(error2.getText(), errorExpectedStoriesAssigned);
		Assert.assertEquals(bodyRecordUrl, driver.getCurrentUrl() );
		
		wsrBody.inputStoriesNotStarted().clear();
		wsrBody.inputStoriesNotStarted().sendKeys("1");
		
		
		////ASSERT 4/// Case1: Start date < End Date , Case2: Start date = End Date
	
		String errorExpectedDate ="No time Travel Allowed";
		WebElement errordate = null;
		
		c.Calendario(startDateDay,startDateMonth,startDateYear ,wsrBody.inputSprintStart(), driver); //Start Date
		
		c.Calendario("15", "March","2021" ,wsrBody.inputSprintEnd(), driver); //End Date
		
		buttonSave.click();
		
		Thread.sleep(2000);
		
		try { errordate= body.errorInStartDate();
		
		}
		catch(Exception e){
			
			System.out.println("No se ecuentra el error");
		}
		
		

		
		System.out.println(errordate.getText());
		System.out.println(errorExpectedDate);
		Assert.assertEquals(errordate.getText(), errorExpectedDate);
		
		c.Calendario(startDateDay,startDateMonth,startDateYear ,wsrBody.inputSprintEnd(), driver); //End Date
		
		buttonSave.click();
		Thread.sleep(2000);
		
		String Idnumber = driver.getCurrentUrl();
		String[] a = Idnumber.split("/");
		
		System.out.println(a[0]);
		System.out.println(a[1]);
		System.out.println(a[2]);
		System.out.println(a[3]);
		
		context.setAttribute("urlLastCreatedWsrRecord", );
		
		
		SoftAssert s = new SoftAssert();
		s.assertEquals(bodyRecordUrl, driver.getCurrentUrl());
		s.assertAll();
		
		System.out.println(driver.getCurrentUrl());

	}
	
	
	
	
	@DataProvider
	public Object[][] getDataExcelWSRBody( ) throws IOException {

		ExcelData d = new ExcelData();
		Object[][] dataExcel = d.getData("");

		return dataExcel;

	}
	

	
	@AfterTest
	public void Quit() {
		
		//driver.quit();

	}
	
	


	    
		
	
}
