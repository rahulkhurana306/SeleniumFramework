package RKProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import RKProject.AbstractComponents.AbstractComponent;

public class Lect159_ProductCatalogPage extends AbstractComponent {
	
	WebDriver driver;
	
	public Lect159_ProductCatalogPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//WebElement uEmail = driver.findElement(By.id("userEmail"));
	// This can be written in page factory as
	@FindBy(css=".offset-sm-1")
	List<WebElement> prods;
	
	By productBy = By.cssSelector(".offset-sm-1");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By alertProdAdded = By.cssSelector(".overlay-container");
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToApear(productBy);
		return prods;
	}
	
	
	public WebElement getProductByName(String prodName)
	{
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(prodName)).findFirst()
				.orElse(null);
		
		return prod;
	}
	
	public void addProductToCart(String prodName)
	{
		WebElement prod = getProductByName(prodName);
		prod.findElement(addToCart).click();
		waitForElementToApear(alertProdAdded);
		waitForElementToDisApear(spinner);
		
	}
	
	
	

}
