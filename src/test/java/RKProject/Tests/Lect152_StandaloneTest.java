package RKProject.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Lect152_StandaloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//Lect 152
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("rahul1234rahul1234@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahul@1234");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".offset-sm-1")));

		List<WebElement> prods = driver.findElements(By.cssSelector(".offset-sm-1"));

		// prods.stream().filter(product->)

		
		
//	 for(WebElement prod : prods) {
//		 
//		 System.out.println(prod.findElement(By.tagName("b")).getText());
//		 if(prod.findElement(By.tagName("b")).getText()=="ZARA COAT 3")
//		 {
//			 
//		 }
// Lect 153
		WebElement prod = prods.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Lect 154 Onwards
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".overlay-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		// Commented above as this is taking time
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
		driver.findElement(By.cssSelector("button[routerlink*=\"cart\"]")).click();
		
		List<WebElement> cartProds = driver.findElements(By.cssSelector(".cartWrap h3"));
		
		//Lect155 Onwards
		Boolean match = cartProds.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Lect156
		
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("india");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group-item")));
	
		
		List<WebElement> countries = driver.findElements(By.cssSelector(".list-group-item"));
		
		//System.out.println(driver.findElements(By.xpath("//button[@class=\"ta-item list-group-item ng-star-inserted\"]")));
		
		WebElement countr = countries.stream().filter(country -> country.getText().equals("India")).findFirst().orElse(null);
		
		countr.click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("window.scrollBy(0,800)");
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		Boolean orderValidation = driver.findElement(By.cssSelector(".hero-primary")).getText().equalsIgnoreCase("Thankyou for the order.");
			
		Assert.assertTrue(orderValidation);
		
		//System.out.println(orderMsg);
		driver.close();
		
		
		
		
		
		
		

	}

}
