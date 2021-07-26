package ObejectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

WebDriver driver;
	
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy (xpath="//div[@class='slds-icon-waffle']")
	WebElement waffle;
	
	@FindBy (xpath="//a[@data-label='Servicio']/div/lightning-formatted-rich-text/span/p")
	WebElement service;
	
	@FindBy (xpath="//button[text()='View All']")
	WebElement viewall;
	
	@FindBy (xpath="//a[@data-label='WSR builder']")
	WebElement wsr;
	
	@FindBy (xpath="//input[@placeholder=\"Search apps and items...\"]")
	WebElement searchbar;
	
	
	
	public WebElement SearchHomepage() {
		
		return searchbar;
	}

	public WebElement Wsr() {
		
		return wsr;
	}

	
	public WebElement viewAll() {
		
		return viewall;
	}

	public WebElement ButtonWaffle() {
		
		return waffle;
	}
	
	public WebElement ButtonService() {
		
		return service;
	}
	
	
	
	
}
