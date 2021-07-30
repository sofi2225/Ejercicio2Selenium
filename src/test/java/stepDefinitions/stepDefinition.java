package stepDefinitions;

import org.testng.annotations.AfterMethod;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import ObejectRepository.HeaderPage;
import ObejectRepository.HomePage;
import ObejectRepository.LoginPage;
import ObejectRepository.WSRBodyRecordPage;
import ObejectRepository.WSRPage;
import Resources.Utilities;
import Resources.base;
import Resources.calendar;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


//RunWith(Cucumber.class)
public class stepDefinition extends base {
	
	
	 private static StepData stepData;
	   
	    public stepDefinition(StepData stepData) {
	        stepDefinition.stepData = stepData;
	    }
	    
	

	WSRBodyRecordPage wsrBody = new WSRBodyRecordPage(driver);
	String[] dias = {"Monday","Tuesday","Wednesday" ,"Thursday","Friday"};
	
	
	
	String highlights;
	String lowlights;
	String bodyName;
	String status;
	String upcomingLeaves;
	String sprintDeliverables;
	
	String startDateDay ;
	String startDateMonth ;
	String startDateYear ;
	
	String endDateDay;
	String endDateMonth;
	String endDateYear;

	String storiesAssigned="1";
	String storiesCompleted="1";
	String storiesInProgress="0";
	String storiesNotStarted="0";
	
	

	
	@Before
	public void initialize() throws IOException {

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
		Utilities.jsClick(home.Wsr());

	}

	@Given("User is on Header Tab")
	public void user_is_on_header_tab() {

		
		System.out.println("User is on Header Tab");
		// Open Header Tab ////

		WSRPage wsr = new WSRPage(driver);
		Utilities.jsClick(wsr.HeaderTab());
		
        //stepData.j ="hola";


	}

	@When("User Creates New Header Form")
	public void user_creates_new_header_form() {

		System.out.println("User Creates New Header Form");
		// Create new header ////
		HeaderPage hp = new HeaderPage(driver);
		Utilities.jsClick(hp.ButtonNewHeader());
		
	}

	@Then("Assert that titles and field types present in Form matches with expected in {string}")
	public void assert_that_titles_and_field_types_present_in_form_matches_with_expected_in(String string)
			throws IOException {

	//	System.out.println("Assert");
		HeaderPage header = new HeaderPage(driver);

		/// Find all label names present in Header ////

		ArrayList<String> listadoLables = new ArrayList<String>();
		int j = 0;

		for (WebElement label : header.LabelsInHeaderForm())

		{
			listadoLables.add(j, label.getText().replace("*", ""));
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

	@Then("Error should be displayed when submitting no data")
	public void error_should_be_displayed_when_submitting_no_data() {
		HeaderPage hp = new HeaderPage(driver);
		SoftAssert s = new SoftAssert();
		// String startingUrl = driver.getCurrentUrl();

		hp.buttonSaveHeader().click();

		try {
			hp.errorHeader();

		} catch (Exception e) {
			System.out.println("No error found");

		}

		s.assertAll();
		s.assertTrue(hp.errorHeader().getText().contains("Header"));

	}

	@Then("Error should be displayed when submitting with missing data")
	public void error_should_be_displayed_when_submitting_with_missing_data() throws IOException {

		String salesforceManagerEmail = getRowData(1, "InputsHeader").get(2);
		String visionAndValues = getRowData(1, "InputsHeader").get(3);
		String measures = getRowData(1, "InputsHeader").get(4);
		String methods = getRowData(1, "InputsHeader").get(5);
		String obstacles = getRowData(1, "InputsHeader").get(6);
		

		HeaderPage hp = new HeaderPage(driver);
		SoftAssert s = new SoftAssert();

		;

		//System.out.println(getRowData(1, "InputsHeader"));

		hp.inputUser().click();
		hp.inputUser().sendKeys(Keys.chord(Keys.ENTER));
		hp.inputManagerEmail().sendKeys(salesforceManagerEmail);
		hp.inputvisionsAndValues().sendKeys(visionAndValues);
		hp.inputMeasures().sendKeys(measures);
		hp.inputMethod().sendKeys(methods);
		hp.inputObstacles().sendKeys(obstacles);

		hp.buttonSaveHeader().click();
		// Thread.sleep(2000);

		try {
			hp.errorHeader();
		} catch (Exception e) {
			System.out.println("No error found");
		}

		s.assertAll();
		s.assertTrue(hp.errorHeader().getText().contains("Header"));

	}

	@Then("Header created succesfullly when submitting with all fields completed")
	public void header_created_succesfullly_when_submitting_with_all_fields_completed() throws IOException {

		HeaderPage hp = new HeaderPage(driver);
		SoftAssert s = new SoftAssert();

		String startingUrl = driver.getCurrentUrl();
		String headerName = getRowData(1, "InputsHeader").get(1);

		hp.inputHeaderName().sendKeys(headerName);
		hp.buttonSaveHeader().click();

		s.assertNotEquals(startingUrl, driver.getCurrentUrl()); // If the form is saved correctly the url changes

	}
	
	
	@When("User Creates  New WSR Body Form")
	public void user_creates_new_wsr_body_form() {
	   
		//Create new Body record
		WSRBodyRecordPage body= new WSRBodyRecordPage(driver);
		Utilities.jsClick(body.newWSRBody());
		
	}
	
	@Then("Assert that titles and field types present in Form matches with expected in excel {string}")
	public void assert_that_titles_and_field_types_present_in_form_matches_with_expected_in_excel(String string) throws IOException {
	   
		
		System.out.println(getLabelsInWSRBodyRecord()); //Field Names present on the application
		
		System.out.println(getRowData(0, "WSRBodyRecodTypes")); //Label Names Expected from Excel
		
		Assert.assertEquals(getLabelsInWSRBodyRecord(), getRowData(0, "WSRBodyRecodTypes"));
	
		
	//Check type of Input
		
		System.out.println(checkTypeLabelWSR(getLabelsInWSRBodyRecord())); //Field Types present on the application
		
		System.out.println(getRowData(1, "WSRBodyRecodTypes")); //Field Types Expected from Excel
		
		
		Assert.assertEquals(getRowData(1, "WSRBodyRecodTypes"), checkTypeLabelWSR(getLabelsInWSRBodyRecord()));
		
	}


	@Given("User is in WSR Body Record Tab")
	public void user_is_in_wsr_bosy_record_tab() {

		//Open WsrBody  Tab
				WSRPage wsr= new WSRPage(driver);
				Utilities.jsClick(wsr.WsrBodyTab());
			
	}

	@Given("Email Owner is created")
	public void email_owner_is_created() {

		System.out.println("Email Owner is created");
		
		//stepData.j= "hola";
	}

	@When("User Creates and fills New WSR Body Form")
	public void user_creates_and_fills_new_wsr_body_form() throws InterruptedException, IOException {

		WebDriverWait wait=new WebDriverWait(driver, 20);
		WSRBodyRecordPage wsrBody = new WSRBodyRecordPage(driver);
		// Create new header
				//Thread.sleep(2000);
				Utilities.jsClick(wsrBody.newWSRBody());
				
			 highlights = getRowData(1, "InputsWSRBodyRecord").get(1);
			 lowlights=getRowData(1, "InputsWSRBodyRecord").get(3);
			 bodyName=getRowData(1, "InputsWSRBodyRecord").get(4);
			 status=getRowData(1, "InputsWSRBodyRecord").get(5);
			 upcomingLeaves=getRowData(1, "InputsWSRBodyRecord").get(6);
			 sprintDeliverables = getRowData(1, "InputsWSRBodyRecord").get(7);
			 
			 
			String[] startDate = getRowData(1, "InputsWSRBodyRecord").get(8).split("/");
			String[] endDate = getRowData(1, "InputsWSRBodyRecord").get(9).split("/");

		
			 
			 startDateDay = startDate[1];
			 startDateMonth = startDate[0];
			 startDateYear = startDate[2];
			 
			 endDateDay = endDate[1];
			 endDateMonth = endDate[0];
			 endDateYear = endDate[2];
				
				
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
				
				
				
	}

	@Then("Error should be displayed when leaving work done field empty")
	public void error_should_be_displayed_when_leaving_work_done_field_empty() throws InterruptedException {

		WSRBodyRecordPage wsrBody = new WSRBodyRecordPage(driver);
		
		for (String dia :dias){
		
		Utilities.GetInView(wsrBody.inputTextArea(dia));
		Utilities.jsClick(wsrBody.inputTextArea(dia));
		//Thread.sleep(2000);
		wsrBody.inputClickDay(dia).sendKeys(dia);
		
		wsrBody.inputHours(dia).clear();
		wsrBody.inputHours(dia).sendKeys("0");
		
		}
		
		
	}

	@Then("Error should be displayed when hours >{int}")
	public void error_should_be_displayed_when_hours_8(Integer int1) throws Throwable {

		System.out.println("^Error should be displayed when hours >8 $");
		WebDriverWait wait=new WebDriverWait(driver, 20);
		WSRBodyRecordPage wsrBody = new WSRBodyRecordPage(driver);
		String bodyRecordUrl = driver.getCurrentUrl();
		
		for (String dia: dias) {
			
			Utilities.GetInView(wsrBody.findTextareaDays(dia));
			wsrBody.findTextareaDays(dia).clear();
			wsrBody.saveButton().click();
	
		
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
		
		
	}

	@Then("Error should be displayed when hours <{int}")
	public void error_should_be_displayed_when_hours(Integer int1) {

		WebDriverWait wait=new WebDriverWait(driver, 20);
		WSRBodyRecordPage wsrBody = new WSRBodyRecordPage(driver);
		
		System.out.println("^Error should be displayed when hours <8 $");
		String errorExtraHours = "No extra hours allowed.";
		WebElement error = null;
		
			for (String dia: dias) {
						
				wsrBody.inputDayHours(dia).clear();
				wsrBody.inputDayHours(dia).sendKeys("9");
				wsrBody.saveButton().click();

				try { 
					wait.until(ExpectedConditions.textToBePresentInElement(wsrBody.errorPresent(), errorExtraHours));
					error = wsrBody.errorPresent();
			
					}
				catch(Exception e){
				
					System.out.println("No se ecuentra el error");
				}
			
			Assert.assertEquals(error.getText(), errorExtraHours);	

			}
		
		for (String dia: dias) {
			
			wsrBody.inputDayHours(dia).clear();
			wsrBody.saveButton().click();

			
				try { 
					
					wait.until(ExpectedConditions.textToBePresentInElement( wsrBody.errorPresent(), dia));
						error = wsrBody.errorPresent();
			
					}
				catch(Exception e){
				
					System.out.println("No se ecuentra el error");
					}
			
			Assert.assertTrue(error.getText().contains(dia));		
			wsrBody.inputDayHours(dia).clear();
			wsrBody.inputDayHours(dia).sendKeys("8");
		}
		
		
		
	}



	@Then("Error should be displayed when amount of stories doesn`t match with stories in completed, in progress and not started.")
	public void error_should_be_displayed_when_amount_of_stories_doesn_t_match_with_stories_in_completed_in_progress_and_not_started() {
	   
		String errorExpectedStoriesAssigned = "Stories completed, In progress, Not started exceed Number of stories assigned";
		String bodyRecordUrl = driver.getCurrentUrl();
		WebDriverWait wait=new WebDriverWait(driver, 20);
		WSRBodyRecordPage wsrBody = new WSRBodyRecordPage(driver);
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
		
		wsrBody.saveButton().click();
		
			try { 
				wait.until(ExpectedConditions.textToBePresentInElement(wsrBody.errorPresent(), errorExpectedStoriesAssigned));
				error2 = wsrBody.errorPresent();
		
				}
			catch(Exception e){
			
				  System.out.println("No se ecuentra el error");
				}
		
		Assert.assertEquals(error2.getText(), errorExpectedStoriesAssigned);
		Assert.assertEquals(bodyRecordUrl, driver.getCurrentUrl() );
		
		wsrBody.inputStoriesNotStarted().clear();
		wsrBody.inputStoriesNotStarted().sendKeys("1");
		
		
	}




	@Then("Error should be displayed when sprint start date = sprint end date")
	public void error_should_be_displayed_when_sprint_start_date_equals_sprint_end_date() throws InterruptedException {


		String errorExpectedDate ="No time Travel Allowed";
		WebDriverWait wait=new WebDriverWait(driver, 20);
		WSRBodyRecordPage wsrBody = new WSRBodyRecordPage(driver);
		calendar c= new calendar();
		WebElement errordate = null;
		
		c.Calendario(startDateDay,startDateMonth,startDateYear ,wsrBody.inputSprintStart(), driver); //Start Date
		
		c.Calendario("15", "March","2021" ,wsrBody.inputSprintEnd(), driver); //End Date
		
		wsrBody.saveButton().click();
		
		
		try { 
			wait.until(ExpectedConditions.textToBePresentInElement(wsrBody.errorInStartDate(), errorExpectedDate));

			errordate= wsrBody.errorInStartDate();
		
		}
		catch(Exception e){
			
			System.out.println("No se ecuentra el error");
		}
		
		Assert.assertEquals(errordate.getText(), errorExpectedDate);
		
		
	}

	@Then("Error should be displayed when sprint start date > sprint end date")
	public void error_should_be_displayed_when_sprint_start_date_sprint_end_date() throws InterruptedException {

		Thread.sleep(2000);
		String bodyRecordUrl = driver.getCurrentUrl();
		WebDriverWait wait=new WebDriverWait(driver, 20);
		WSRBodyRecordPage wsrBody = new WSRBodyRecordPage(driver);
		calendar c= new calendar();
		
		c.Calendario(startDateDay,startDateMonth,startDateYear ,wsrBody.inputSprintEnd(), driver); //End Date
	
		wsrBody.saveButton().click();
		

		wait.until(ExpectedConditions.urlContains("view"));
		String lastUrl = driver.getCurrentUrl();
		String[] getid= lastUrl.split("/");
				
		//System.out.println(id[6]);
		
		stepData.j= getid[6].toString();
		
		//context.setAttribute("idnumber", Id);
	
		 
		 
		System.out.println(stepData.j);
	//	System.out.println( stepData.a);
		
		
		
		SoftAssert s = new SoftAssert();
		s.assertEquals(bodyRecordUrl, driver.getCurrentUrl());
		s.assertAll();
	}

	
	
	
	@Then("WSR Body created succesfullly with all fields completed with data form Excel")
	public void wsr_body_created_succesfullly_with_all_fields_completed_with_data_form_excel() {

		
		System.out.println("WSR Body created succesfullly with all fields completed with data form Excel");
	}
	
	
	
	
	@When("User click in Submit to manager")
	public void user_click_in_submit_to_manager() {
		WSRBodyRecordPage body= new WSRBodyRecordPage(driver);
		WebDriverWait wait=new WebDriverWait(driver, 20);
		String url ="https://tdccom-dev-ed.lightning.force.com/lightning/r/Body_WSR__c/"+StepData.j+"/view";
		
		driver.get(url);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(WSRBodyRecordPage.xpathSubmitToManager));
		body.buttonSubmitToManager().click();  //Submits to Manager
		
		wait.until(ExpectedConditions.elementToBeClickable(WSRBodyRecordPage.xpathButtonSaveAndSend)); //Send email
		body.buttonSaveAndSend().click();
		
	
	}
	
	
	
	@Then("Record should be sent succesfully")
	public void record_should_be_sent_succesfully() {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		WSRBodyRecordPage body= new WSRBodyRecordPage(driver);

		
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






	@After
	public void quit() {

		driver.quit();

	}

}
