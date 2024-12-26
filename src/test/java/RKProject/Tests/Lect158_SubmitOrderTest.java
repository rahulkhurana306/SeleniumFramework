
package RKProject.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RKProject.TestComponents.BaseTest;
import RKProject.pageobjects.Lect159_ProductCatalogPage;
import RKProject.pageobjects.Lect161_CartPage;
import RKProject.pageobjects.Lect163_CheckOutPage;
import RKProject.pageobjects.Lect164_ConfirmationPage;
import RKProject.pageobjects.OrderPage;

public class Lect158_SubmitOrderTest extends BaseTest {

	//String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void SubmitOrder(HashMap<String, String> paramValue ) throws IOException {

		// Lect158_LandingPage landingPage = LaunchApplication();
		Lect159_ProductCatalogPage productCatalogPage = landingPage.LoginApplication(paramValue.get("email"), paramValue.get("pwd"));
		productCatalogPage.addProductToCart(paramValue.get("product"));
		Lect161_CartPage cartPage = productCatalogPage.goToCart();
		Boolean match = cartPage.searchProductInCart(paramValue.get("product"));
		Assert.assertTrue(match);
		Lect163_CheckOutPage checkoutPage = cartPage.ClickCheckoutButton();
		checkoutPage.SelectCountry("India");
		checkoutPage.ScrollDownby2000();
		Lect164_ConfirmationPage confirmationPage = checkoutPage.ClickPlaceOrder();
		Boolean orderValidation = confirmationPage.OrderConfirmationMessage();
		Assert.assertTrue(orderValidation);
		System.out.println(driver.findElement(By.cssSelector(".hero-primary")).getText());

	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() {
		String productName = "ZARA COAT 3";

		Lect159_ProductCatalogPage productCatalogPage = landingPage.LoginApplication("rahul1234rahul1234@gmail.com",
				"Rahul@1234");
		OrderPage orderPage = productCatalogPage.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));  

	}
	
//	This is created under lect 171 Implementing parameterisation
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"rahul1234rahul1234@gmail.com", "Rahul@1234", "ZARA COAT 3"},{"rahul1234@gmail.com", "Rahul@1234", "IPHONE 13 PRO"}};
//	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data = getJsonDataMap(System.getProperty("user.dir")+"//src//main//java//Data//PurchaseOrder.json");
//		HashMap <String, String> map  = new HashMap<String, String>();
//		map.put("email", "rahul1234rahul1234@gmail.com");	
//		map.put("pwd", "Rahul@1234");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap <String, String> map1  = new HashMap<String, String>();
//		map1.put("email", "rahul1234@gmail.com");
//		map1.put("pwd", "Rahul@1234");
//		map1.put("product", "IPHONE 13 PRO");
		
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
}
