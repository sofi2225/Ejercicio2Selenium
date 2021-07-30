package Resources;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Utilities extends base {

	
	//WebDriver driver;
	//private  JavascriptExecutor executor;


	public  Utilities(WebDriver driver){
		
		Utilities.driver =driver;
		
		Utilities.executor = (JavascriptExecutor) driver;
		
	}
	

	public static void GetInView(WebElement webElement) throws InterruptedException {

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].scrollIntoView();", webElement);

	}
	
	public static void jsClick(WebElement webElement) {

		executor.executeScript("arguments[0].click();", webElement);
	}
	

	public void implicitWaitChange(long time) {

		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	
	
	
}
