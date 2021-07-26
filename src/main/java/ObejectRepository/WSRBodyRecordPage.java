package ObejectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WSRBodyRecordPage{

	WebDriver driver;

	public WSRBodyRecordPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

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
	
	
	@FindBy(xpath = "//div[@role='toolbar']/following-sibling::div/div")
	WebElement mondayTextarea;
	
	@FindBy(xpath = "//div[@role='toolbar']/following-sibling::div/div/p")
	WebElement monday;
	
	
	@FindBy(xpath = "//span[text()='Tuesday']/parent::*/following-sibling::div/div/div[3]/div")
	WebElement tuesdayTextarea;
	
	@FindBy(xpath = "//span[text()='Tuesday']/parent::*/following-sibling::div/div/div/following-sibling::div/div/p")
	WebElement tuesday;
	
	
	@FindBy(xpath = "//span[text()='Wednesday']/parent::*/following-sibling::div/div/div[3]/div")
	WebElement 	wednesdayTextarea;
	
	@FindBy(xpath = "//span[text()='Wednesday']/parent::*/following-sibling::div/div/div/following-sibling::div/div/p")
	WebElement 	wednesday;
	
	
	@FindBy(xpath = "//span[text()='Thursday']/parent::*/following-sibling::div/div/div[3]/div")
	WebElement 	thursdayTextarea;
	
	@FindBy(xpath = "//span[text()='Thursday']/parent::*/following-sibling::div/div/div/following-sibling::div/div/p")
	WebElement 	thursday;
	

	@FindBy(xpath = "//span[text()='Friday']/parent::*/following-sibling::div/div/div[3]/div")
	WebElement 	fridayTextarea;
	
	@FindBy(xpath = "//span[text()='Friday']/parent::*/following-sibling::div/div/div/following-sibling::div/div/p")
	WebElement 	friday;
	
	
	@FindBy(xpath = "//input[@name='Monday_Hours__c']")
	WebElement mondayHours;
	
	@FindBy(xpath = "//input[@name='Tuesday_Hours__c']")
	WebElement tuesdayHours;
	
	@FindBy(xpath = "//input[@name='Wednesday_Hours__c']")
	WebElement wednesdayHours;
	
	@FindBy(xpath = "//input[@name='Thursday_Hours__c']")
	WebElement thursdayHours;
	
	@FindBy(xpath = "//input[@name='Friday_Hours__c']")
	WebElement fridayHours;
	
	
	
	@FindBy (xpath ="//ul[@class='errorsList slds-list_dotted slds-m-left_medium']/li")
	WebElement errorPresent;
	
	@FindBy (xpath ="//*[text()='Sprint Start Date']/parent::*/following-sibling::div")
	WebElement errorInStartdate;
	
	@FindBy (xpath="//button[@name='SaveEdit']")
	WebElement saveButton;
	
	
	public WebElement saveButton() {

		return saveButton;
	}
	

	public WebElement errorInStartDate() {

		return errorInStartdate;
	}
	
	public WebElement errorPresent() {

		return errorPresent;
	}
	
	public WebElement inputFirdayHours() {

		return fridayHours;
	}

	public WebElement inputThursdayHours() {

		return thursdayHours;
	}
	
	public WebElement inputWednesdayHours() {

		return wednesdayHours;
	}
	
	public WebElement inputMondayHours() {

		return mondayHours;
	}
	
	public WebElement inputTuesdayHours() {

		return tuesdayHours;
	}
	
	
	

	public WebElement inputThursdayClick() {

		return thursday;
	}
	
	
	public WebElement inputThursdayText() {

		return thursdayTextarea;
	}
	
	

	public WebElement inputClickFriday() {

		return friday;
	}
	
	
	public WebElement inputFridayText() {

		return fridayTextarea;
	}
	
	
	
	
	
	
	public WebElement inputClickWednesday() {

		return wednesday;
	}
	
	
	public WebElement inputWednesdayText() {

		return wednesdayTextarea;
	}
	
	
	public WebElement inputClickTuesday() {

		return tuesday;
	}
	
	
	public WebElement inputTuesdayText() {

		return tuesdayTextarea;
	}
	
	
	
	public WebElement inputClickMonday() {

		return monday;
	}
	
	
	public WebElement inputMondayText() {

		return mondayTextarea;
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
	