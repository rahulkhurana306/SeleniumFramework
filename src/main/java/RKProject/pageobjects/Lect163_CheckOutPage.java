package RKProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import RKProject.AbstractComponents.AbstractComponent;

public class Lect163_CheckOutPage extends AbstractComponent {
	
WebDriver driver;
	
	public Lect163_CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".form-group input")
	WebElement countryDropdown;
	
	@FindBy(css=".list-group-item")
	List<WebElement> countries;
	
	@FindBy(css=".action__submit")
	WebElement submitBuutton;
	
	By submitButtonBy = By.cssSelector(".action__submit");
	
	By countryValues  = By.cssSelector(".list-group-item");
	
		
	public void SelectCountry(String countrytoSelect)
	{
		countryDropdown.sendKeys(countrytoSelect);
		waitForElementToApear(countryValues);
		WebElement countr = countries.stream().filter(country -> country.getText().equals(countrytoSelect)).findFirst().orElse(null);
		countr.click();
		
	}
	

	
	public Lect164_ConfirmationPage ClickPlaceOrder()
	{
		waitForElementToApear(submitButtonBy);
		submitBuutton.click();
		Lect164_ConfirmationPage confirmationPage = new Lect164_ConfirmationPage(driver);
		return confirmationPage;
		
	}
	

	

	

}
