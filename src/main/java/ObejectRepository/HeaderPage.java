package ObejectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {

	WebDriver driver;

	public HeaderPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@title='New']")
	WebElement buttonNewHeader;

	@FindBy(xpath = "//button[@name='SaveEdit']")
	WebElement buttonsaveheader;

	
	@FindBy(xpath = "//label[text()='Header Name']/parent::*/div/input")
	WebElement headername;
	
	@FindBy(xpath = "//label[text()='User']/parent::*/div/div/lightning-base-combobox/div/div/input")
	WebElement user;

	@FindBy(xpath = "//label[text()='Salesforce Manager Email']/parent::*/div/input")
	WebElement managerEmail;

	@FindBy(xpath = "//label[text()='Vision & Values']/parent::*/div/textarea")
	WebElement visionsAndValues;

	@FindBy(xpath = "//label[text()='Measures']/parent::*/div/textarea")
	WebElement measures;

	@FindBy(xpath = "//label[text()='Methods']/parent::*/div/textarea")
	WebElement method;

	@FindBy(xpath = "//label[text()='Obstacles']/parent::*/div/textarea")
	WebElement obstacles;
	
	@FindBys(@FindBy (xpath = "//ul[@class='errorsList slds-list_dotted slds-m-left_medium']/li/a"))
	List<WebElement>  errorList;
	
	@FindBys(@FindBy (xpath = "//*[@data-input-element-id='input-field']"))
	List<WebElement> labelsInHeaderForm;
	
	@FindBy (xpath="//ul[@class='errorsList slds-list_dotted slds-m-left_medium']/li")
	WebElement errorheader;
	
	
	public WebElement errorHeader() {

		return errorheader;
	}
	

	public List<WebElement> LabelsInHeaderForm() {

		return labelsInHeaderForm;
	}
	
	public  List<WebElement>  ErrorList() {

		return errorList;
	}
	
	public WebElement inputManagerEmail() {

		return managerEmail;
	}
	
	public WebElement inputHeaderName() {

		return headername;
	}


	public WebElement inputMethod() {

		return method;
	}

	public WebElement inputObstacles() {

		return obstacles;
	}

	public WebElement inputMeasures() {

		return measures;
	}

	public WebElement inputvisionsAndValues() {

		return visionsAndValues;
	}

	public WebElement inputUser() {

		return user;
	}

	public WebElement ButtonNewHeader() {

		return buttonNewHeader;
	}

	public WebElement buttonSaveHeader() {

		return buttonsaveheader;
	}

}
