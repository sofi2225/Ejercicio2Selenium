package Resources;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Excel.ExcelData2;

public class base {

	public static JavascriptExecutor executor;
	public static WebDriver driver;
	public Properties prop;
	public static String Url;
	public static String userId;
	public static String passId;

	public WebDriver initializeDriver() throws IOException {

		prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Resources//data.properties");
		prop.load(fis);
		// mvn test -browser
		// String browserName = System.getProperty("browser");

		String browserName = prop.getProperty("browser");
		Url = prop.getProperty("url");
		userId = prop.getProperty("UserId");
		passId = prop.getProperty("PassId");

		if (browserName.contains("chrome")) 
			
			{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");

				if (browserName.contains("headless")) 
					{
						options.addArguments("headless");
					}

			driver = new ChromeDriver(options);

			}
		else if (browserName.equals("firefox")) 
			{
			// Firefox code
			}
		else if (browserName.equals("IE")) 
			{
			// IE code
			}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return driver;
	}

	
	///// Methods /////

	public static void UseCombobox(WebElement element, WebDriverWait wait) {

		element.click();
		wait.until(ExpectedConditions.attributeContains(element, "aria-expanded", "true"));
		element.sendKeys(Keys.ENTER); // Picks user

	}

	
	public static void selectDropdownText(WebElement webElement, String text) throws InterruptedException {

		executor.executeScript("arguments[0].scrollIntoView();", webElement);
		Thread.sleep(1000);
		webElement.click();
		driver.findElement(By.xpath("//*[text()='" + text + "']")).click();

	}
	

	public static void fillDayHours(WebElement element3, String hours) {
		
		element3.clear();
		element3.sendKeys(hours);

	}

	
	public static ArrayList<String> checkTypeLabelWSR(ArrayList<String> list2) {

		ArrayList<String> list = new ArrayList<String>();
		WebElement element;

		for (String l : list2) {

			String type = "";
			String tag;

			element = driver.findElement(By.xpath("//*[text()='" + l + "']/parent::*"));
			tag = element.getTagName();

			if (!tag.contains("span")) 
				{
					element = driver.findElement(By.xpath("//label[text()='" + l + "']/parent::*/parent::*"));
					tag = element.getTagName();
				}
			// System.out.println(l);
			// System.out.println(tag);

			if (tag.contains("lookup"))
				{
					type = "Lookup Field";
				}

			if (tag.contains("date")) 
				{
					type = "Date";
				}

			if (tag.contains("picklist")) {

				type = "Picklist";
			}

			if (tag.contains("input")) {

				type = "Text";
			}

			if (tag.contains("span")) {

				type = "Long Text";
			}

			if (tag.contains("slot")) {

				type = "Decimal";
			}

			list.add(type);

		}
		return list;
	}

	public static ArrayList<String> getRowData(int row, String tableName) throws IOException {

		ArrayList<String> a = new ArrayList<String>();

		for (int i = 0; i < ExcelData2.getDataTest(tableName)[row].length; i++) 
			
			{
				a.add(ExcelData2.getDataTest(tableName)[row][i].toString().trim());
			}
		
		return a;
	}

	public static ArrayList<String> checkTypeLabel(ArrayList<String> list2) {

		ArrayList<String> list = new ArrayList<String>();

		for (String l : list2) {

			String type = "";
			WebElement element = driver.findElement(By.xpath("//label[text()='" + l + "']/parent::*"));
			WebElement element2;
			String tag = element.getTagName();

			if (tag.contains("input")) {

				element2 = driver.findElement(By.xpath("//*[text()='" + l + "']/parent::*/div/input"));
				type = "Text";

				// Checkear si inputmode es de tipo email
				if (!(element2.getAttribute("inputmode") == null)) {

					type = element2.getAttribute("inputmode").substring(0, 1).toUpperCase()
							+ element2.getAttribute("inputmode").substring(1);
				}
			}

			//// Si la etiqueta es desplegable////

			if (tag.contains("combobox")) {

				element2 = driver.findElement(By.xpath("//*[text()='" + l + "']/parent::*"));

				if (element2.getAttribute("aria-autocomplete") != null
						&& element2.getAttribute("aria-autocomplete").contains("list")) {

					type = "Picklist";
				} else {

					type = "Lookup Field";

				}
			}
			//// Si la etiqueta es Textarea////
			if (tag.contains("textarea")) {

				type = "Long Text";
			}

			list.add(type);

		}
		return list;

	}

	public static ArrayList<String> getLabelsInWSRBodyRecord() {

		List<WebElement> labelsInHeaderForm = driver.findElements(By.xpath("//*[@data-input-element-id='input-field']"));
		ArrayList<String> list2 = new ArrayList<String>();

		int j = 0;
		String[] a;

		for (WebElement label : labelsInHeaderForm) {

			if (!label.getTagName().contains("force-record-layout-rich-text"))
				
				{
					list2.add(j, label.getText().replace("*", "").replace("Help", "").replace("Select a date", "").trim());
					j++;
				} 
			else 
				{
					a = label.getText().split(" ");
					list2.add(j, a[0].replace("Font", "").trim());
					j++;
				}
		}
		
		return list2;
	}

	
	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destFile = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";

		FileUtils.copyFile(source, new File(destFile));

		return destFile;
	}



}
