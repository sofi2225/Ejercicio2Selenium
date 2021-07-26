package ObejectRepository;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WSRPage {

WebDriver driver;
	
	
	public WSRPage(WebDriver driver) {
	
	this.driver=driver;
	PageFactory.initElements(driver, this);
}

	
	@FindBy (xpath="//a[@title='Headers']")
	WebElement headerTab;
	
	@FindBy (xpath="//a[@title='Body WSRs']")
	WebElement WSRBodyTab;
	

	public WebElement HeaderTab() {
		
		return headerTab;
	}
	
	public WebElement WsrBodyTab() {
		
		return WSRBodyTab;
	}
	
}
