package RKProject.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Lect161_CartPage {
	
WebDriver driver;
	
	public Lect161_CartPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	@FindBy(css=".cartWrap h3")
	List<WebElement> cartItems;
	
	
	public List<WebElement> cartItems()
	{
		return cartItems;
	}
	
	public Boolean searchProductInCart(String productName)
	{
		Boolean cartItemExists = cartItems().stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return cartItemExists;
	
	}
	
	public Lect163_CheckOutPage ClickCheckoutButton()
	{
		checkoutButton.click();
		Lect163_CheckOutPage checkoutPage = new Lect163_CheckOutPage(driver);
		return checkoutPage;
	}
	

}
