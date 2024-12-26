package RKProject.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Lect164_ConfirmationPage {

	WebDriver driver;

	public Lect164_ConfirmationPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement OrderMessage;

	public Boolean OrderConfirmationMessage() {
		Boolean orderValidation = OrderMessage.getText().equalsIgnoreCase("Thankyou for the order.");
		return orderValidation;
	}

}
