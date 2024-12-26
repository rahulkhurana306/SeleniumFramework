package RKProject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RKProject.AbstractComponents.AbstractComponent;

public class Lect158_LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public Lect158_LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//WebElement uEmail = driver.findElement(By.id("userEmail"));
	// This can be written in page factory as
	@FindBy(id="userEmail")
	WebElement userEml;
	
	@FindBy(id="userPassword")
	WebElement userPwd;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css="[class*='ng-star-inserted']")
	WebElement errorMsg;
	
	
	public Lect159_ProductCatalogPage LoginApplication (String email , String Password)
	{
		userEml.sendKeys(email);
		userPwd.sendKeys(Password);
		loginBtn.click();
		Lect159_ProductCatalogPage productCatalogPage = new Lect159_ProductCatalogPage(driver);
		return productCatalogPage;
	}
		
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String GetErrorMessage()
	{
		waitForWebElementToApear(errorMsg);
		return errorMsg.getText();
	}

}
