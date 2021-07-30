package Test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import ObejectRepository.HomePage;
import ObejectRepository.LoginPage;
import ObejectRepository.WSRBodyRecordPage;
import ObejectRepository.WSRPage;
import Resources.Utilities;
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
		Utilities.jsClick(home.Wsr());
	
}
	

@Test
	public void VerifyWSRBodyFields(ITestContext context) throws InterruptedException, IOException {
	
	//Open Wsr  Tab
		WSRPage wsr= new WSRPage(driver);
		Utilities.jsClick(wsr.WsrBodyTab());
	
		
	//Create new header
		WSRBodyRecordPage body= new WSRBodyRecordPage(driver);
		Utilities.jsClick(body.newWSRBody());
		
	/// FILL WSR BODY RECORD ///
		
		WSRBodyRecordPage wsrBody = new WSRBodyRecordPage(driver);
		WebDriverWait wait=new WebDriverWait(driver, 20);

		
		//variables//
		
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
		
	//Fill data//
		
		UseCombobox(wsrBody.inputUser(), wait); //Picks User
	
		wsrBody.inputHighlights().clear();
		wsrBody.inputHighlights().sendKeys(highlights); // Enters Highlight
		
		
		UseCombobox(wsrBody.inputHeader(), wait); //Picks Header

		wsrBody.inputLowlights().clear();
		wsrBody.inputLowlights().sendKeys(lowlights); // Enters Lowlights
		
		
		wsrBody.inputBodyName().sendKeys(bodyName);// Enters Body Name
		
		
		selectDropdownText(wsrBody.inputSatus(),status); // Picks Status
			
		
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
		
		//Thread.sleep(4000);
		
		/// FILL DAY DATA ///
		String[] dias = {"Monday","Tuesday","Wednesday" ,"Thursday","Friday"};
		
		
		for (String dia :dias){
			
		Utilities.GetInView(wsrBody.inputTextArea(dia));
		Utilities.jsClick(wsrBody.inputTextArea(dia));
		//Thread.sleep(2000);
		wsrBody.inputClickDay(dia).sendKeys(dia);
		
		wsrBody.inputHours(dia).sendKeys("0");
		
		}
		
		// ASSERT 1 // Check if sending value of work empty Form is not submitted and error text match
		
		
		String bodyRecordUrl = driver.getCurrentUrl();
		
		for (String dia: dias) {
			
			Utilities.GetInView(wsrBody.findTextareaDays(dia));
			wsrBody.findTextareaDays(dia).clear();
			body.saveButton().click();
	
		
				try {   
						wait.until(ExpectedConditions.visibilityOf(wsrBody.ErrorInDays(dia).get(0)));
						wsrBody.ErrorInDays(dia);
						Assert.assertEquals(bodyRecordUrl, driver.getCurrentUrl() );
						Assert.assertTrue(wsrBody.ErrorInDays(dia).size() != 0);
					}
				catch(Exception e){
			
					System.out.println("No se ecuentra el error");
					}
		
			wsrBody.findTextareaDays(dia).sendKeys("test");
		
		}
		
		
		// ASSERT 2 // Check that form can't be submitted with Extra Hours Or Empty field
		
		String errorExtraHours = "No extra hours allowed.";
		WebElement error = null;
		
			for (String dia: dias) {
						
				wsrBody.inputDayHours(dia).clear();
				wsrBody.inputDayHours(dia).sendKeys("9");
				body.saveButton().click();

				try { 
					wait.until(ExpectedConditions.textToBePresentInElement(body.errorPresent(), errorExtraHours));
					error = body.errorPresent();
			
					}
				catch(Exception e){
				
					System.out.println("No se ecuentra el error");
				}
			
			Assert.assertEquals(error.getText(), errorExtraHours);	

			}
		
		for (String dia: dias) {
			
			wsrBody.inputDayHours(dia).clear();
			body.saveButton().click();

			
				try { 
					
					wait.until(ExpectedConditions.textToBePresentInElement( body.errorPresent(), dia));
						error = body.errorPresent();
			
					}
				catch(Exception e){
				
					System.out.println("No se ecuentra el error");
					}
			
			Assert.assertTrue(error.getText().contains(dia));		
			wsrBody.inputDayHours(dia).clear();
			wsrBody.inputDayHours(dia).sendKeys("8");
		}
		
		
		/// ASSERT 3 //
		
		//Caso1 
		String errorExpectedStoriesAssigned = "Stories completed, In progress, Not started exceed Number of stories assigned";
		WebElement error2 =null;
		
		storiesAssigned ="4";
		storiesCompleted ="2";
		storiesInProgress ="1";
		storiesNotStarted ="0";

		wsrBody.inputStoriesAssigned().clear();
		wsrBody.inputStoriesAssigned().sendKeys(storiesAssigned);
		
		wsrBody.inputStoriesCompleted().clear();
		wsrBody.inputStoriesCompleted().sendKeys(storiesCompleted);
		
		wsrBody.inputStoriesInProgress().clear();
		wsrBody.inputStoriesInProgress().sendKeys(storiesInProgress);
		
		wsrBody.inputStoriesNotStarted().clear();
		wsrBody.inputStoriesNotStarted().sendKeys(storiesNotStarted);
		
		body.saveButton().click();
		
			try { 
				wait.until(ExpectedConditions.textToBePresentInElement(body.errorPresent(), errorExpectedStoriesAssigned));
				error2 = body.errorPresent();
		
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
		
		body.saveButton().click();
		
		
		try { 
			wait.until(ExpectedConditions.textToBePresentInElement(body.errorInStartDate(), errorExpectedDate));

			errordate= body.errorInStartDate();
		
		}
		catch(Exception e){
			
			System.out.println("No se ecuentra el error");
		}

		
		//System.out.println(errordate.getText());
		//System.out.println(errorExpectedDate);
		Assert.assertEquals(errordate.getText(), errorExpectedDate);
		
		Thread.sleep(2000);
		c.Calendario(startDateDay,startDateMonth,startDateYear ,wsrBody.inputSprintEnd(), driver); //End Date
	
		body.saveButton().click();
		

		wait.until(ExpectedConditions.urlContains("view"));
		String lastUrl = driver.getCurrentUrl();
		String[] id = lastUrl.split("/");
				
		//System.out.println(id[6]);
		
		String Id = id[6].toString();
		context.setAttribute("idnumber", Id);
		
		
		SoftAssert s = new SoftAssert();
		s.assertEquals(bodyRecordUrl, driver.getCurrentUrl());
		s.assertAll();
		

	}
	
@Test 
public void sendToManager(ITestContext context) throws InterruptedException  {

	//Open Header Tab
	WSRPage wsr= new WSRPage(driver);
	WSRBodyRecordPage body= new WSRBodyRecordPage(driver);
	
	WebDriverWait wait=new WebDriverWait(driver, 20);
	String idNumber = (String)context.getAttribute("idnumber");

	
	Utilities.jsClick(wsr.WsrBodyTab());
	
	
	body.lastCreatedRecord(idNumber).click(); //Open last created record changing the Id in url
	
	wait.until(ExpectedConditions.presenceOfElementLocated(WSRBodyRecordPage.xpathSubmitToManager));
	body.buttonSubmitToManager().click();  //Submits to Manager
	
	wait.until(ExpectedConditions.elementToBeClickable(WSRBodyRecordPage.xpathButtonSaveAndSend)); //Send email
	body.buttonSaveAndSend().click();
	
	
	//Assert mail is sent successfully
	
	String messageExpected = "WSR mailed succesfully!";
	
		try { 
				wait.until(ExpectedConditions.textToBePresentInElement(body.messageMailSucces(), "mailed"));
				Assert.assertEquals(body.messageMailSucces().getText(), messageExpected);
			}
		catch(Exception e)
			{
				System.out.println("No se envio el formulario");
			}
	
}

	

	
	@AfterTest
	public void Quit() {
		
		driver.quit();

	}
	

	    
		
	
}
