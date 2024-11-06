package testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import pageObjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage land;
	
	public  List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		//read json to string
		
		String jsonContent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		//String to HashMap-->jackson databind==>dependency in mvn
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>> (){});
		return data;
		
		
	}
	
	public WebDriver intializerDriver() throws Exception {
		
		Properties property=new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
		property.load(fis);
		String browserName = property.getProperty("browser");
		if(browserName.equals("chrome")) {
		 driver=new ChromeDriver();
			
		}
		else if(browserName.equals("edge")) {
			 driver=new EdgeDriver();
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	public String getScreenshot(String testName, WebDriver driver) throws IOException {
	    TakesScreenshot shot = (TakesScreenshot) driver;
	    File source = shot.getScreenshotAs(OutputType.FILE);
	    String destinationPath = System.getProperty("user.dir") + "//reports//" + testName + ".png";
	   
	    File destination = new File(destinationPath);
	    
	    FileUtils.copyFile(source, destination);
	    
	    return destinationPath;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws Exception {
		driver=intializerDriver();
		land=new LandingPage(driver);
		land.goTo("https://rahulshettyacademy.com/client/");
		return land;
	}
	
	@AfterMethod(alwaysRun=true)
	public void close() {
		driver.close();
	}
}
