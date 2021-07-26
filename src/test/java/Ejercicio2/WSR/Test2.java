package Ejercicio2.WSR;

import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import ObejectRepository.HeaderPage;
import ObejectRepository.HomePage;
import ObejectRepository.LoginPage;
import ObejectRepository.WSRPage;
import Resources.base;

public class Test2 extends base {
	
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
	
	public void JSoupTest() throws InterruptedException, IOException {
	
	
		 // String ruta = System.getProperty("user.dir");
	       
		 // String data = "Test data";
		String currentUrl = driver.getCurrentUrl();
		Thread.sleep(5000);
		
		  Document doc = Jsoup.connect(currentUrl).timeout(6000).get();
		
		  String data = doc.toString();
		  
		  FileOutputStream out = new FileOutputStream(System.getProperty("user.dir")+"archivo.txt");
		   
		
		  out.write(data.getBytes());
		  out.close();
		  
		  
	        

		
	
	}
	

	

	
	@AfterTest
	public void Quit() {
		
		//driver.quit();

	}
	
	


	    
		
	
}
