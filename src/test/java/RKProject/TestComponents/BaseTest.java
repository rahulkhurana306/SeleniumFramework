package RKProject.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RKProject.pageobjects.Lect158_LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	
	//Lect 165 , 166
	public  WebDriver driver;
	public Lect158_LandingPage landingPage;
	public WebDriver InitlializeDriver() throws IOException
	{
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\RKProject\\Resources\\GlobalData.properties");
		properties.load(fis);
		String browserName =System.getProperty("browser")!=null? System.getProperty("browser") : properties.getProperty("browser");
		// properties.getProperty("browser");
		
		if(browserName.toLowerCase().contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browserName.toLowerCase().contains("headless"))
			{
				options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			 driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	
	@BeforeMethod (alwaysRun =true)
	public Lect158_LandingPage LaunchApplication() throws IOException {
		
		driver = InitlializeDriver();
		landingPage = new Lect158_LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun =true)
	public void TearDown()
	{
		
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataMap(String filePath ) throws IOException
	{
		
		//Covert Json file to String
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//Covert String to Hashmap
		ObjectMapper mapper  = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
				
	}
	
	public String getScreenshot (String testCaseName , ITestResult result) throws IOException
	{
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		TakesScreenshot ts  = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"//reports//"+testCaseName+".png"; 
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
		return path;
	
	}
		

}
