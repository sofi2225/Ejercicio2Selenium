package ObejectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Resources.base;

public class WSRBodyRecordPage extends base{

	WebDriver driver;

	public WSRBodyRecordPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public static String xpathErrorInDays = "//span[text()='$dia']/parent::*/following-sibling::div/div/div/following-sibling::div[2]";
	public static String xpathTextareaDays = "//span[text()='$dia']/parent::*/following-sibling::div/div/div/following-sibling::div/div/p";
	public static String inputDayHours = "//input[@name='$dia_Hours__c']";
	public static String lastCreatedrecord ="//a[contains(@href,'$id')]";
	public static String InputHours= "//input[@name='$dia_Hours__c']";
	public static String InputTextArea= "//span[text()='$dia']/parent::*/following-sibling::div/div/div[3]/div";
	public static String InputPClick ="//span[text()='$dia']/parent::*/following-sibling::div/div/div/following-sibling::div/div/p";
	
	public static By xpathSubmitToManager = By.xpath("//button[text()='Submit to Manager']");
	public static By xpathButtonSaveAndSend = By.xpath("//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']");
	
	
	@FindBy(xpath = "//a[@title='New']")
	WebElement newWSR;
	
	@FindBy(xpath = "//*[text()='User']/following-sibling::*/div/lightning-base-combobox/div/div/input")
	WebElement user;
	
	@FindBy(xpath = "//*[text()='Highlights']/following-sibling::*/input")
	WebElement highlights;
	
	@FindBy(xpath = "//*[text()='Header']/following-sibling::*/div/lightning-base-combobox/div/div/input")
	WebElement header;
	
	@FindBy(xpath = "//*[text()='Lowlights']/following-sibling::*/input")
	WebElement lowlights;
	
	@FindBy(xpath = "//*[text()='Body Name']/following-sibling::*/input")
	WebElement bodyname;
	
	@FindBy(xpath = "//*[text()='Status']/following-sibling::*[2]/lightning-base-combobox/div/div/input")
	WebElement status;
	
	
	@FindBy(xpath = "//*[text()='Upcoming Leaves']/following-sibling::*/input")
	WebElement upcomingLeaves;
	
	@FindBy(xpath = "//*[text()='Sprint Deliverables']/following-sibling::*/lightning-base-combobox")
	WebElement sprintDeliverables ;
	
	
	@FindBy(xpath = "//*[text()='Sprint Start Date']/following-sibling::*/input")
	WebElement sprintStart;
	
	@FindBy(xpath = "//*[text()='Sprint End Date']/following-sibling::*/input")
	WebElement sprintEnd;
	
	@FindBy(xpath = "//*[text()='# Stories Assigned']/following-sibling::*/input")
	WebElement storiesAssigned;
	
	@FindBy(xpath = "//*[text()='# Stories Completed']/following-sibling::*/input")
	WebElement storiesCompleted;
	
	@FindBy(xpath = "//*[text()='# Stories In Progress']/following-sibling::*/input")
	WebElement storiesInProgress;
	
	@FindBy(xpath = "//*[text()='# Stories Not Started']/following-sibling::*/input")
	WebElement storiesNotStarted;
	
	
	@FindBy (xpath ="//ul[@class='errorsList slds-list_dotted slds-m-left_medium']/li")
	WebElement errorPresent;
	
	@FindBy (xpath ="//*[text()='Sprint Start Date']/parent::*/following-sibling::div")
	WebElement errorInStartdate;
	
	@FindBy (xpath="//button[@name='SaveEdit']")
	WebElement saveButton;
	
	@FindBy (xpath="//button[text()='Submit to Manager']")
	WebElement buttonSubmitToManager;
	
	@FindBy (xpath="//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']")
	WebElement buttonSaveAndSend;
	
	
	@FindBy (xpath="//span[@class='toastMessage slds-text-heading--small forceActionsText']")
	WebElement messageMailSucces;
	
	
	

	
	public List<WebElement> ErrorInDays(String dia) {
		
		List<WebElement>  ListwebElement = driver.findElements(By.xpath(xpathErrorInDays.replace("$dia", dia)));

		return ListwebElement;
	 
	}
	public WebElement findTextareaDays(String dia) {
		
		WebElement webElement = driver.findElement(By.xpath(xpathTextareaDays.replace("$dia", dia)));

		return webElement;
	 
	}
	
	
	public WebElement inputDayHours(String dia) {
		
		WebElement webElement = driver.findElement(By.xpath(inputDayHours.replace("$dia", dia)));

		return webElement;
	 
	}
	
	public WebElement lastCreatedRecord(String id) {
		
		WebElement webElement = driver.findElement(By.xpath(lastCreatedrecord.replace("$id", id)));

		return webElement;
	 
	}
	
	public WebElement inputHours(String dia) {
		
		WebElement webElement = driver.findElement(By.xpath(InputHours.replace("$dia", dia)));

		return webElement;
	 
	}
	
	public WebElement inputTextArea(String dia) {
		
		WebElement webElement = driver.findElement(By.xpath(InputTextArea.replace("$dia", dia)));

		return webElement;
	 
	}
	

	
	
	public WebElement inputClickDay(String dia) {
		
		WebElement webElement = driver.findElement(By.xpath(InputPClick.replace("$dia", dia)));

		return webElement;
	 
	}
	
	
	public WebElement messageMailSucces() {
		
		return messageMailSucces;
	}
	
	public WebElement buttonSaveAndSend() {
		
		return buttonSaveAndSend;
	}
	
	public WebElement buttonSubmitToManager() {

		return buttonSubmitToManager;
	}
	

	
	public WebElement saveButton() {

		return saveButton;
	}
	

	public WebElement errorInStartDate() {

		return errorInStartdate;
	}
	
	public WebElement errorPresent() {

		return errorPresent;
	}
	
	
	public WebElement inputStoriesCompleted() {

		return storiesCompleted;
	}
	
	public WebElement inputStoriesInProgress() {

		return storiesInProgress;
	}
	public WebElement inputStoriesNotStarted() {

		return storiesNotStarted;
	}
	
	
	public WebElement inputStoriesAssigned() {

		return storiesAssigned;
	}
	
	public WebElement inputSprintEnd() {

		return sprintEnd;
	}
	
	public WebElement inputSprintStart() {

		return sprintStart;
	}
	
	
	public WebElement inputSprintDeliverables() {

		return sprintDeliverables;
	}
	
	
	public WebElement inputUpcomingLeaves() {

		return upcomingLeaves;
	}
	
	
	public WebElement inputSatus() {

		return status;
	}
	
	
	public WebElement inputBodyName() {

		return bodyname;
	}
	
	public WebElement inputLowlights() {

		return lowlights;
	}
	
	public WebElement inputHeader() {

		return header;
	}
	
	public WebElement inputHighlights() {

		return highlights;
	}
	
	public WebElement inputUser() {

		return user;
	}

	public WebElement newWSRBody() {

		return newWSR;
	}
}
	